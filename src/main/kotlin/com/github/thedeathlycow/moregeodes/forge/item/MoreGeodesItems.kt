package com.github.thedeathlycow.moregeodes.forge.item

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.github.thedeathlycow.moregeodes.forge.block.CrystalBlock
import com.github.thedeathlycow.moregeodes.forge.block.MoreGeodesBlocks
import com.github.thedeathlycow.moregeodes.forge.sound.CrystalBlockSoundGroup
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.material.MaterialColor
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object MoreGeodesItems {

    val REGISTRY: DeferredRegister<Item> = DeferredRegister.create(
        ForgeRegistries.ITEMS,
        MoreGeodesForge.MODID
    )

    val EMERALD_CRYSTAL_BLOCK by REGISTRY.registerObject("emerald_crystal_block") {
        BlockItem(
            MoreGeodesBlocks.EMERALD_CRYSTAL_BLOCK,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }

    val BUDDING_EMERALD by REGISTRY.registerObject("budding_emerald") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_EMERALD,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }

    val QUARTZ_CRYSTAL_BLOCK by REGISTRY.registerObject("quartz_crystal_block") {
        BlockItem(
            MoreGeodesBlocks.QUARTZ_CRYSTAL_BLOCK,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }

    val BUDDING_QUARTZ by REGISTRY.registerObject("budding_quartz") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_QUARTZ,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }

    val DIAMOND_CRYSTAL_BLOCK by REGISTRY.registerObject("diamond_crystal_block") {
        BlockItem(
            MoreGeodesBlocks.DIAMOND_CRYSTAL_BLOCK,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }

    val ECHO_BLOCK by REGISTRY.registerObject("echo_block") {
        BlockItem(
            MoreGeodesBlocks.ECHO_BLOCK,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }

    val BUDDING_ECHO_BLOCK by REGISTRY.registerObject("budding_echo_block") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_ECHO_BLOCK,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }

    val EMERALD_CLUSTER by REGISTRY.registerObject("emerald_cluster") {
        BlockItem(
            MoreGeodesBlocks.EMERALD_CLUSTER,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val LARGE_EMERALD_BUD by REGISTRY.registerObject("large_emerald_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_EMERALD_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val MEDIUM_EMERALD_BUD by REGISTRY.registerObject("medium_emerald_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_EMERALD_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val SMALL_EMERALD_BUD by REGISTRY.registerObject("small_emerald_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_EMERALD_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val QUARTZ_CLUSTER by REGISTRY.registerObject("quartz_cluster") {
        BlockItem(
            MoreGeodesBlocks.QUARTZ_CLUSTER,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val LARGE_QUARTZ_BUD by REGISTRY.registerObject("large_quartz_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_QUARTZ_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val MEDIUM_QUARTZ_BUD by REGISTRY.registerObject("medium_quartz_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_QUARTZ_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val SMALL_QUARTZ_BUD by REGISTRY.registerObject("small_quartz_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_QUARTZ_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val DIAMOND_CLUSTER by REGISTRY.registerObject("diamond_cluster") {
        BlockItem(
            MoreGeodesBlocks.DIAMOND_CLUSTER,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val ECHO_CLUSTER by REGISTRY.registerObject("echo_cluster") {
        BlockItem(
            MoreGeodesBlocks.ECHO_CLUSTER,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val LARGE_ECHO_BUD by REGISTRY.registerObject("large_echo_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_ECHO_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val MEDIUM_ECHO_BUD by REGISTRY.registerObject("medium_echo_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_ECHO_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val SMALL_ECHO_BUD by REGISTRY.registerObject("small_echo_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_ECHO_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val ECHO_LOCATOR by REGISTRY.registerObject("echo_locator") {
        BlockItem(
            MoreGeodesBlocks.ECHO_LOCATOR,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val GYPSUM_ROSE by REGISTRY.registerObject("gypsum_rose") {
        BlockItem(
            MoreGeodesBlocks.GYPSUM_ROSE,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val LARGE_GYPSUM_BUD by REGISTRY.registerObject("large_gypsum_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_GYPSUM_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val MEDIUM_GYPSUM_BUD by REGISTRY.registerObject("medium_gypsum_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_GYPSUM_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val SMALL_GYPSUM_BUD by REGISTRY.registerObject("small_gypsum_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_GYPSUM_BUD,
            Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)
        )
    }

    val GYPSUM_CRYSTAL_BLOCK by REGISTRY.registerObject("gypsum_crystal_block") {
        BlockItem(
            MoreGeodesBlocks.GYPSUM_CRYSTAL_BLOCK,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }

    val BUDDING_GYPSUM by REGISTRY.registerObject("budding_gypsum") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_GYPSUM,
            Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    }
}