package com.github.thedeathlycow.moregeodes.forge.item.tag

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object MoreGeodesItemTags {

    val FOOLS_FOLD: TagKey<Item> = create("fools_gold")

    fun create(name: String): TagKey<Item> {
        return ItemTags.create(ResourceLocation(MoreGeodesForge.MODID, name))
    }

}