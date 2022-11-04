package com.github.thedeathlycow.moregeodes.forge.block.entity

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.github.thedeathlycow.moregeodes.forge.block.EchoLocatorType
import com.github.thedeathlycow.moregeodes.forge.world.event.MoreGeodesGameEvents
import com.github.thedeathlycow.moregeodes.forge.world.event.tag.MoreGeodesGameEventTags
import com.mojang.serialization.Dynamic
import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.nbt.*
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.TagKey
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.BlockPositionSource
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.gameevent.GameEventListener
import net.minecraft.world.level.gameevent.vibrations.VibrationListener

class EchoLocatorBlockEntity(
    blockPos: BlockPos,
    state: BlockState
) : BlockEntity(MoreGeodesBlockEntityTypes.ECHO_LOCATOR, blockPos, state), VibrationListener.VibrationListenerConfig {

    var type = EchoLocatorType.EMPTY
    var vibrationListener: VibrationListener

    private var pingTicks = 0
    private var pinging: MutableList<BlockPos> = ArrayList()

    companion object {
        const val SCAN_RADIUS = 30
        val SCAN_BOX = Vec3i(SCAN_RADIUS, SCAN_RADIUS, SCAN_RADIUS)
        const val MAX_PING_TIME = 20 * 20
        private const val TICKS_PER_PING = 20

        fun tick(level: Level, origin: BlockPos, state: BlockState, blockEntity: EchoLocatorBlockEntity) {
            if (!level.isClientSide && blockEntity.isPinging()) {
                blockEntity.pingTicks++
                blockEntity.vibrationListener.tick(level)
                if (blockEntity.pingTicks % TICKS_PER_PING != 0) {
                    return
                }

                val blocksToKeep = ArrayList<BlockPos>()
                for (pos in blockEntity.pinging) {
                    val atState = level.getBlockState(pos)
                    if (blockEntity.highlightBlock(level, pos, atState)) {
                        blocksToKeep.add(pos)
                    }
                }
                blockEntity.pinging.clear()
                blockEntity.pinging.addAll(blocksToKeep)
            }
        }

    }

    init {
        vibrationListener = VibrationListener(BlockPositionSource(blockPos), SCAN_RADIUS, this, null, 0.0f, 0)
    }

    fun activate(level: Level, origin: BlockPos) {
        val from = origin.subtract(SCAN_BOX)
        val to = origin.offset(SCAN_BOX)
        this.pingTicks = 0
        this.pinging.clear()
        for (pos in BlockPos.betweenClosed(from, to)) {
            val state = level.getBlockState(pos)
            val rangedSquared = Mth.square(this.vibrationListener.listenerRadius)
            if (origin.distSqr(pos) <= rangedSquared && state.`is`(this.type.canLocate)) {
                this.pinging.add(pos.immutable())
            }
        }
    }


    override fun getListenableEvents(): TagKey<GameEvent> {
        return MoreGeodesGameEventTags.ECHO_LOCATOR_CAN_LISTEN
    }

    override fun shouldListen(
        serverLevel: ServerLevel,
        gameEventListener: GameEventListener,
        pos: BlockPos,
        event: GameEvent,
        ctx: GameEvent.Context
    ): Boolean {
        return !this.isRemoved
                && event === MoreGeodesGameEvents.CRYSTAL_RESONATE
                && this.isPinging()
    }

    override fun onSignalReceive(
        serverLevel: ServerLevel,
        gameEventListener: GameEventListener,
        pos: BlockPos,
        event: GameEvent,
        entity: Entity?,
        srcEntity: Entity?,
        distance: Float
    ) {
        // do nothing, just make the particles
    }

    override fun saveAdditional(nbt: CompoundTag) {
        super.saveAdditional(nbt)
        nbt.putInt("PingTicks", this.pingTicks)

        nbt.put("Type", this.type.toNbt())

        val pingingNbt = ListTag()
        for (pingingPos in this.pinging) {
            val pos = IntArrayTag(intArrayOf(pingingPos.x, pingingPos.y, pingingPos.z))
            pingingNbt.add(pos)
        }
        nbt.put("Pinging", pingingNbt)

        VibrationListener.codec(this)
            .encodeStart(NbtOps.INSTANCE, this.vibrationListener)
            .resultOrPartial { msg: String ->
                MoreGeodesForge.LOGGER.error(msg)
            }.ifPresent { nbtElement: Tag ->
                nbt.put("listener", nbtElement)
            }
    }

    override fun load(nbt: CompoundTag) {
        super.load(nbt)
        this.pingTicks = nbt.getInt("PingTicks")

        this.pinging.clear()
        val pingingNbt = nbt.getList("Pinging", ListTag.TAG_COMPOUND.toInt())
        for ((index, _) in pingingNbt.withIndex()) {
            val elemList = pingingNbt.getList(index)
            this.pinging.add(
                BlockPos(
                    elemList.getInt(0), elemList.getInt(1), elemList.getInt(2)
                )
            )
        }

        if (nbt.contains("Type", Tag.TAG_COMPOUND.toInt())) {
            this.type = EchoLocatorType.fromNbt(nbt.getCompound("Type"))
        } else {
            this.type = EchoLocatorType.EMPTY
        }

        if (nbt.contains("listener", Tag.TAG_COMPOUND.toInt())) {
            VibrationListener.codec(this).parse(Dynamic(NbtOps.INSTANCE, nbt.getCompound("listener")))
                .resultOrPartial { msg: String ->
                    MoreGeodesForge.LOGGER.error(msg)
                }.ifPresent { listener: VibrationListener ->
                    this.vibrationListener = listener
                }
        }
    }


    private fun highlightBlock(level: Level, pos: BlockPos, state: BlockState): Boolean {
        if (state.`is`(this.type.canLocate)) {
            level.gameEvent(null, MoreGeodesGameEvents.CRYSTAL_RESONATE, pos)
            level.playSound(null, pos, this.type.resonateSound, SoundSource.BLOCKS, 2.5f, 1.0f)
            return true
        }
        return false
    }

    private fun isPinging(): Boolean {
        return this.pingTicks < MAX_PING_TIME
    }

}