package com.github.thedeathlycow.moregeodes.forge.block

import com.github.thedeathlycow.moregeodes.forge.block.tag.MoreGeodesBlockTags
import com.github.thedeathlycow.moregeodes.forge.sound.MoreGeodesSoundEvents
import net.minecraft.core.Registry
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

data class EchoLocatorType(
    val activateSound: SoundEvent,
    val resonateSound: SoundEvent,
    val canLocate: TagKey<Block>
) {

    fun toNbt(): CompoundTag {
        val nbt = CompoundTag()
        nbt.putString("CanLocate", this.canLocate.location.toString())
        nbt.putString("ActivateSound", this.activateSound.location.toString())
        nbt.putString("ResonateSound", this.resonateSound.location.toString())
        return nbt
    }

    companion object {

        val ALL = EchoLocatorType(
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE,
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,
            MoreGeodesBlockTags.ECHO_LOCATABLE
        )
        val EMERALD = EchoLocatorType(
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE,
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,
            MoreGeodesBlockTags.ECHO_LOCATABLE_EMERALD
        )
        val QUARTZ = EchoLocatorType(
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE,
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,
            MoreGeodesBlockTags.ECHO_LOCATABLE_QUARTZ
        )
        val DIAMOND = EchoLocatorType(
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE,
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,
            MoreGeodesBlockTags.ECHO_LOCATABLE_DIAMOND
        )
        val AMETHYST = EchoLocatorType(
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE,
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,
            MoreGeodesBlockTags.ECHO_LOCATABLE_AMETHYST
        )
        val ECHO = EchoLocatorType(
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE,
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,
            MoreGeodesBlockTags.ECHO_LOCATABLE_ECHO
        )

        fun fromNbt(nbt: CompoundTag): EchoLocatorType {
            val activateSound: SoundEvent = getSoundEventById(nbt.getString("ActivateSound"))
            val resonateSound: SoundEvent = getSoundEventById(nbt.getString("ResonateSound"))
            val canLocate: TagKey<Block> = BlockTags.create(ResourceLocation(nbt.getString("CanLocate")))

            return EchoLocatorType(activateSound, resonateSound, canLocate)
        }

        private fun getSoundEventById(id: String): SoundEvent {
            val location = ResourceLocation(id)
            return Registry.SOUND_EVENT[location] ?: throw Exception(
                String.format(
                    "Sound event '%s' does not exist!",
                    location
                )
            )
        }
    }

}
