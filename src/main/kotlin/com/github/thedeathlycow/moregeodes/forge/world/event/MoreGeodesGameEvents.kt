package com.github.thedeathlycow.moregeodes.forge.world.event

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object MoreGeodesGameEvents {

    val REGISTRY: DeferredRegister<GameEvent> = DeferredRegister.create(
        Registries.GAME_EVENT,
        MoreGeodesForge.MODID
    )

    val CRYSTAL_RESONATE by REGISTRY.registerObject("crystal_resonate") {
        create("crystal_resonate")
    }

    private fun create(name: String): GameEvent {
        return create(name, 16)
    }

    private fun create(name: String, range: Int): GameEvent {
        return GameEvent(String.format("%s:%s", MoreGeodesForge.MODID, name), range)
    }

}