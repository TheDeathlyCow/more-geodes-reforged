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


    val ITEM_CRYSTAL_LOCATOR_USE: SoundEvent by REGISTRY.registerObject("item.geodes.crystal_locator.use") {
        SoundEvent.createVariableRangeEvent(
            ResourceLocation(
                MoreGeodesForge.MODID,
                "item.geodes.crystal_locator.use"
            )
        )

    }

    val BLOCK_ECHO_LOCATOR_RESONATE: SoundEvent by REGISTRY.registerObject("block.geodes.echo_locator.resonate") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_locator.resonate"))
    }
    val BLOCK_ECHO_LOCATOR_USE: SoundEvent by REGISTRY.registerObject("block.geodes.echo_locator.use") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_locator.use"))
    }

    val BLOCK_EMERALD_CRYSTAL_BLOCK_CHIME by REGISTRY.registerObject("block.geodes.emerald_crystal_block.chime") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.emerald_crystal_block.chime"))
    }

    val BLOCK_QUARTZ_CRYSTAL_BLOCK_CHIME by REGISTRY.registerObject("block.geodes.quartz_crystal_block.chime") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.quartz_crystal_block.chime"))
    }

    val BLOCK_DIAMOND_CRYSTAL_BLOCK_CHIME by REGISTRY.registerObject("block.geodes.diamond_crystal_block.chime") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.diamond_crystal_block.chime"))
    }

    val BLOCK_LAPIS_CRYSTAL_BLOCK_CHIME by REGISTRY.registerObject("block.geodes.lapis_crystal_block.chime") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.lapis_crystal_block.chime"))
    }

    val BLOCK_GYPSUM_CRYSTAL_BLOCK_CHIME by REGISTRY.registerObject("block.geodes.gypsum_crystal_block.chime") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.gypsum_crystal_block.chime"))
    }
}