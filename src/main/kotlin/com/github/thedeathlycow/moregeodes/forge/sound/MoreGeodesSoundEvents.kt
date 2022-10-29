package com.github.thedeathlycow.moregeodes.forge.sound

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object MoreGeodesSoundEvents {

    val REGISTRY: DeferredRegister<SoundEvent> = DeferredRegister.create(
        ForgeRegistries.SOUND_EVENTS,
        MoreGeodesForge.MODID
    )

    val BLOCK_EMERALD_CRYSTAL_BLOCK_CHIME by REGISTRY.registerObject("block.geodes.emerald_crystal_block.chime") {
        SoundEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.emerald_crystal_block.chime"))
    }

    val BLOCK_QUARTZ_CRYSTAL_BLOCK_CHIME by REGISTRY.registerObject("block.geodes.quartz_crystal_block.chime") {
        SoundEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.emerald_crystal_block.chime"))
    }

    val BLOCK_DIAMOND_CRYSTAL_BLOCK_CHIME by REGISTRY.registerObject("block.geodes.diamond_crystal_block.chime") {
        SoundEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.diamond_crystal_block.chime"))
    }
}