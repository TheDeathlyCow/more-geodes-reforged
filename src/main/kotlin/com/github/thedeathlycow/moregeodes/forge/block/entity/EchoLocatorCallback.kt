package com.github.thedeathlycow.moregeodes.forge.block.entity

import com.github.thedeathlycow.moregeodes.forge.world.event.MoreGeodesGameEvents
import com.github.thedeathlycow.moregeodes.forge.world.event.tag.MoreGeodesGameEventTags
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.GameEventTags
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.gameevent.PositionSource
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem

class EchoLocatorCallback(
    private val owner: EchoLocatorBlockEntity,
    private val range: Int,
    private val positionSource: PositionSource
): VibrationSystem.User {

    override fun getListenerRadius(): Int {
        return this.range
    }

    override fun getPositionSource(): PositionSource {
        return this.positionSource
    }

    override fun canReceiveVibration(
        level: ServerLevel,
        pos: BlockPos,
        event: GameEvent,
        ctx: GameEvent.Context
    ): Boolean {
        return !this.owner.isRemoved
                && MoreGeodesGameEvents.CRYSTAL_RESONATE == event
                && owner.isPinging()
    }

    override fun onReceiveVibration(
        serverLevel: ServerLevel,
        pos: BlockPos,
        event: GameEvent,
        entity: Entity?,
        srcEntity: Entity?,
        distance: Float
    ) {
        // empty as we just want particles
    }

    override fun getListenableEvents(): TagKey<GameEvent> {
        return MoreGeodesGameEventTags.ECHO_LOCATOR_CAN_LISTEN
    }

}