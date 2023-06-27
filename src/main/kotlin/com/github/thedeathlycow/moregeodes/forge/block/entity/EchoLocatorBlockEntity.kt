package com.github.thedeathlycow.moregeodes.forge.block.entity

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.github.thedeathlycow.moregeodes.forge.block.EchoLocatorType
import com.github.thedeathlycow.moregeodes.forge.entity.MoreGeodesEntityTypes
import com.github.thedeathlycow.moregeodes.forge.world.event.MoreGeodesGameEvents
import com.mojang.serialization.Dynamic
import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.nbt.*
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.world.entity.Display.BlockDisplay
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.BlockPositionSource
import net.minecraft.world.level.gameevent.GameEventListener
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem

class EchoLocatorBlockEntity(
    blockPos: BlockPos,
    state: BlockState
) : BlockEntity(MoreGeodesBlockEntityTypes.ECHO_LOCATOR, blockPos, state),
    GameEventListener.Holder<VibrationSystem.Listener>,
    VibrationSystem {

    var type = EchoLocatorType.ALL
    private var pingTicks = 0
    private var pinging: MutableList<BlockPos> = ArrayList()
    private var listenerData: VibrationSystem.Data = VibrationSystem.Data()
    private var vibrationListener: VibrationSystem.Listener = VibrationSystem.Listener(this)
    private var callback: VibrationSystem.User


    companion object {
        const val SCAN_RADIUS = 30
        val SCAN_BOX = Vec3i(SCAN_RADIUS, SCAN_RADIUS, SCAN_RADIUS)
        const val MAX_PING_TIME = 20 * 20
        const val TICKS_PER_PING = 20

        fun tick(level: ServerLevel, origin: BlockPos, state: BlockState, blockEntity: EchoLocatorBlockEntity) {
            if (!level.isClientSide && blockEntity.isPinging()) {
                blockEntity.pingTicks++
                if (blockEntity.pingTicks % TICKS_PER_PING != 0) {
                    return
                }

                var shouldHighlight = true

                val pinging: MutableIterator<BlockPos> = blockEntity.pinging.iterator()
                var pos: BlockPos
                while (pinging.hasNext()) {
                    pos = pinging.next()
                    val atState: BlockState = level.getBlockState(pos)
                    if (blockEntity.highlightBlock(level, pos, atState, shouldHighlight)) {
                        shouldHighlight = false
                    } else {
                        pinging.remove()
                    }
                }
            }
        }

        fun serverTick(level: Level, origin: BlockPos, state: BlockState, blockEntity: EchoLocatorBlockEntity) {
            tick(level as ServerLevel, origin, state, blockEntity)
        }

        fun clientTick(level: Level, origin: BlockPos, state: BlockState, blockEntity: EchoLocatorBlockEntity) {
            // nothing to do on client
        }

    }

    init {
        this.callback = EchoLocatorCallback(this, SCAN_RADIUS, BlockPositionSource(blockPos))
    }

    fun activate(level: Level, origin: BlockPos) {
        val from = origin.subtract(SCAN_BOX)
        val to = origin.offset(SCAN_BOX)
        this.pingTicks = 0
        this.pinging.clear()
        for (pos in BlockPos.betweenClosed(from, to)) {
            val state = level.getBlockState(pos)
            val rangedSquared: Int = Mth.square(this.vibrationListener.listenerRadius)
            if ((origin.distSqr(pos) <= rangedSquared) && state.`is`(this.type.canLocate)) {
                this.pinging.add(pos.immutable())
            }
        }
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

        VibrationSystem.Data.CODEC.encodeStart(NbtOps.INSTANCE, this.vibrationData)
            .resultOrPartial { msg: String? ->
                MoreGeodesForge.LOGGER.error(msg)
            }.ifPresent {
                nbt.put("listener", it)
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
            this.type = EchoLocatorType.ALL
        }

        if (nbt.contains("listener", Tag.TAG_COMPOUND.toInt())) {
            VibrationSystem.Data.CODEC.parse(Dynamic(NbtOps.INSTANCE, nbt.getCompound("listener")))
                .resultOrPartial { msg: String? ->
                    MoreGeodesForge.LOGGER.error(msg)
                }.ifPresent {
                    this.listenerData = it
                }
        }
    }


    private fun highlightBlock(level: Level, pos: BlockPos, state: BlockState, shouldHighlight: Boolean): Boolean {
        if (state.`is`(this.type.canLocate)) {
            if (shouldHighlight) {
                highlightBlock(level, pos, state)
            }
            return true
        }
        return false
    }

    private fun highlightBlock(level: Level, pos: BlockPos, state: BlockState) {
        level.gameEvent(null, MoreGeodesGameEvents.CRYSTAL_RESONATE, pos)
        level.playSound(null, pos, this.type.resonateSound, SoundSource.BLOCKS, 2.5f, 1.0f)

        val display: BlockDisplay? = MoreGeodesEntityTypes.ECHO_DISPLAY.create(level)

        if (display === null) {
            return
        }

        display.setBlockState(state)
        display.setPos(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        display.setGlowingTag(true)
        display.isInvulnerable = true

        level.addFreshEntity(display)
    }

    fun isPinging(): Boolean {
        return this.pingTicks < MAX_PING_TIME
    }

    override fun getListener(): VibrationSystem.Listener {
        return this.vibrationListener
    }

    override fun getVibrationData(): VibrationSystem.Data {
        return this.listenerData
    }

    override fun getVibrationUser(): VibrationSystem.User {
        return this.callback
    }

}