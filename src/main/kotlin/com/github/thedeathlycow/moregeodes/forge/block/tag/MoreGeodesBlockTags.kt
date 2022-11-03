package com.github.thedeathlycow.moregeodes.forge.block.tag

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object MoreGeodesBlockTags {

    val ECHO_LOCATABLE_DEFAULT: TagKey<Block> = create("echo_locatable/default")
    val ECHO_LOCATABLE_EMERALD: TagKey<Block> = create("echo_locatable/emerald")
    val ECHO_LOCATABLE_AMETHYST: TagKey<Block> = create("echo_locatable/amethyst")
    val ECHO_LOCATABLE_QUARTZ: TagKey<Block> = create("echo_locatable/quartz")
    val ECHO_LOCATABLE_DIAMOND: TagKey<Block> = create("echo_locatable/diamond")
    val ECHO_LOCATABLE_ECHO: TagKey<Block> = create("echo_locatable/echo")

    @JvmField
    val CUSTOM_CRYSTAL_SOUND_BLOCKS: TagKey<Block> = create("custom_crystal_sound_blocks")

    fun create(name: String): TagKey<Block> {
        return BlockTags.create(ResourceLocation(MoreGeodesForge.MODID, name))
    }
}