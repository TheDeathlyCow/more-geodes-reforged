package com.github.thedeathlycow.moregeodes.forge.block.entity

import com.github.thedeathlycow.moregeodes.forge.block.EchoLocatorType
import com.github.thedeathlycow.moregeodes.forge.world.event.MoreGeodesGameEvents
import com.github.thedeathlycow.moregeodes.forge.world.event.tag.MoreGeodesGameEventTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.TagKey
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.BlockPositionSource
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.gameevent.GameEventListener
import net.minecraft.world.level.gameevent.vibrations.VibrationListener

class EchoLocatorBlockEntity(
    type: BlockEntityType<*>,
    blockPos: BlockPos,
    state: BlockState
) : BlockEntity(type, blockPos, state), VibrationListener.VibrationListenerConfig {

    var type = EchoLocatorType.EMPTY
    private var pingTicks = 0
    private var pinging: MutableList<BlockPos> = ArrayList()
    private val listener: VibrationListener

    companion object {
        const val SCAN_RADIUS = 30
        val SCAN_BOX = Vec3i(SCAN_RADIUS, SCAN_RADIUS, SCAN_RADIUS)
        const val MAX_PING_TIME = 20 * 20
        const val TICKS_PER_PING = 20
    }
    init {
        listener = VibrationListener(BlockPositionSource(blockPos), SCAN_RADIUS, this, null, 0.0f, 0)
    }

    fun activate(level: Level, origin: BlockPos) {
        val from = origin.subtract(SCAN_BOX)
        val to = origin.offset(SCAN_BOX)
        this.pingTicks = 0
        this.pinging.clear()
        for (pos in BlockPos.betweenClosed(from, to)) {
            val state = level.getBlockState(pos)
            val rangedSquared = Mth.square(this.listener.listenerRadius)
            if (origin.distSqr(pos) <= rangedSquared && state.`is`(this.type.canLocate)) {
                this.pinging.add(pos.immutable())
            }
        }
    }

    fun tick(level: ServerLevel, origin: BlockPos, state: BlockState) {
        if (this.isPinging() && !level.isClientSide) {
            this.pingTicks++
            this.listener.tick(level)
            if (this.pingTicks % TICKS_PER_PING != 0) {
                return
            }

            val blocksToKeep = ArrayList<BlockPos>()
            for (pos in pinging) {
                val atState = level.getBlockState(pos)
                if (highlightBlock(level, pos, atState)) {
                    blocksToKeep.add(pos)
                }
            }
            pinging.clear()
            pinging = blocksToKeep
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

    private fun highlightBlock(serverLevel: ServerLevel, pos: BlockPos, state: BlockState): Boolean {
        if (state.`is`(this.type.canLocate)) {
            serverLevel.gameEvent(null, MoreGeodesGameEvents.CRYSTAL_RESONATE, pos)
            serverLevel.playSound(null, pos, this.type.resonateSound, SoundSource.BLOCKS, 2.5f, 1.0f)
            return true
        } else {
            return false
        }
    }

    private fun isPinging(): Boolean {
        return this.pingTicks < MAX_PING_TIME
    }

}