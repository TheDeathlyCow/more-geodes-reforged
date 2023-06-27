package com.github.thedeathlycow.moregeodes.forge.world.event.tag

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.level.gameevent.GameEvent

object MoreGeodesGameEventTags {

    val ECHO_LOCATOR_CAN_LISTEN: TagKey<GameEvent> = create("echo_locator_can_listen")

    fun create(name: String): TagKey<GameEvent> {
        return TagKey.create(Registries.GAME_EVENT, ResourceLocation(MoreGeodesForge.MODID, name))
    }
}