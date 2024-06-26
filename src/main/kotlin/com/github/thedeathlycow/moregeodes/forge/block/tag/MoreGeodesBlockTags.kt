package com.github.thedeathlycow.moregeodes.forge.block.tag

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object MoreGeodesBlockTags {

    @JvmField
    val CUSTOM_CRYSTAL_SOUND_BLOCKS: TagKey<Block> = create("custom_crystal_sound_blocks")

    fun create(name: String): TagKey<Block> {
        return BlockTags.create(ResourceLocation(MoreGeodesForge.MODID, name))
    }
}