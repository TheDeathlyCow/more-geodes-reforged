package com.github.thedeathlycow.moregeodes.forge.item

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object MoreGeodesCreativeTabs {

    val MORE_GEODES: CreativeModeTab = object : CreativeModeTab("geodes.more_geodes") {
        override fun makeIcon(): ItemStack {
            return ItemStack(MoreGeodesItems.EMERALD_CLUSTER)
        }
    }
}