package com.github.thedeathlycow.moregeodes.forge.sound

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object EchoGeodeBlockSoundEvents {

    val REGISTRY: DeferredRegister<SoundEvent> = DeferredRegister.create(
        ForgeRegistries.SOUND_EVENTS,
        MoreGeodesForge.MODID
    )

    val BLOCK_ECHO_BLOCK_BREAK: SoundEvent by REGISTRY.registerObject("block.geodes.echo_block.break") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_block.break"))
    }
    val BLOCK_ECHO_BLOCK_CHIME: SoundEvent by REGISTRY.registerObject("block.geodes.echo_block.chime") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_block.chime"))
    }
    val BLOCK_ECHO_BLOCK_FALL: SoundEvent by REGISTRY.registerObject("block.geodes.echo_block.fall") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_block.fall"))
    }
    val BLOCK_ECHO_BLOCK_HIT: SoundEvent by REGISTRY.registerObject("block.geodes.echo_block.hit") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_block.hit"))
    }
    val BLOCK_ECHO_BLOCK_PLACE: SoundEvent by REGISTRY.registerObject("block.geodes.echo_block.place") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_block.place"))
    }
    val BLOCK_ECHO_BLOCK_STEP: SoundEvent by REGISTRY.registerObject("block.geodes.echo_block.step") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_block.step"))
    }

    val BLOCK_ECHO_CLUSTER_BREAK: SoundEvent by REGISTRY.registerObject("block.geodes.echo_cluster.break") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_cluster.break"))
    }
    val BLOCK_ECHO_CLUSTER_FALL: SoundEvent by REGISTRY.registerObject("block.geodes.echo_cluster.fall") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_cluster.fall"))
    }
    val BLOCK_ECHO_CLUSTER_HIT: SoundEvent by REGISTRY.registerObject("block.geodes.echo_cluster.hit") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_cluster.hit"))
    }
    val BLOCK_ECHO_CLUSTER_PLACE: SoundEvent by REGISTRY.registerObject("block.geodes.echo_cluster.place") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_cluster.place"))
    }
    val BLOCK_ECHO_CLUSTER_STEP: SoundEvent by REGISTRY.registerObject("block.geodes.echo_cluster.step") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.echo_cluster.step"))
    }

    val BLOCK_LARGE_ECHO_BUD_BREAK: SoundEvent by REGISTRY.registerObject("block.geodes.large_echo_bud.break") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.large_echo_bud.break"))
    }
    val BLOCK_LARGE_ECHO_BUD_PLACE: SoundEvent by REGISTRY.registerObject("block.geodes.large_echo_bud.place") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.large_echo_bud.place"))
    }

    val BLOCK_MEDIUM_ECHO_BUD_BREAK: SoundEvent by REGISTRY.registerObject("block.geodes.medium_echo_bud.break") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.medium_echo_bud.break"))
    }
    val BLOCK_MEDIUM_ECHO_BUD_PLACE: SoundEvent by REGISTRY.registerObject("block.geodes.medium_echo_bud.place") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.medium_echo_bud.place"))
    }

    val BLOCK_SMALL_ECHO_BUD_BREAK: SoundEvent by REGISTRY.registerObject("block.geodes.small_echo_bud.break") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.small_echo_bud.break"))
    }
    val BLOCK_SMALL_ECHO_BUD_PLACE: SoundEvent by REGISTRY.registerObject("block.geodes.small_echo_bud.place") {
        SoundEvent.createVariableRangeEvent(ResourceLocation(MoreGeodesForge.MODID, "block.geodes.small_echo_bud.place"))
    }


}