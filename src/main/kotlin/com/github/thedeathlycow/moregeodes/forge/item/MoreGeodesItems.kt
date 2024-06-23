package com.github.thedeathlycow.moregeodes.forge.item

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.github.thedeathlycow.moregeodes.forge.block.MoreGeodesBlocks
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
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
            Item.Properties()
        )
    }

    val BUDDING_EMERALD by REGISTRY.registerObject("budding_emerald") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_EMERALD,
            Item.Properties()
        )
    }

    val EMERALD_CLUSTER by REGISTRY.registerObject("emerald_cluster") {
        BlockItem(
            MoreGeodesBlocks.EMERALD_CLUSTER,
            Item.Properties()
        )
    }

    val LARGE_EMERALD_BUD by REGISTRY.registerObject("large_emerald_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_EMERALD_BUD,
            Item.Properties()
        )
    }

    val MEDIUM_EMERALD_BUD by REGISTRY.registerObject("medium_emerald_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_EMERALD_BUD,
            Item.Properties()
        )
    }

    val SMALL_EMERALD_BUD by REGISTRY.registerObject("small_emerald_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_EMERALD_BUD,
            Item.Properties()
        )
    }



    val QUARTZ_CRYSTAL_BLOCK by REGISTRY.registerObject("quartz_crystal_block") {
        BlockItem(
            MoreGeodesBlocks.QUARTZ_CRYSTAL_BLOCK,
            Item.Properties()
        )
    }

    val BUDDING_QUARTZ by REGISTRY.registerObject("budding_quartz") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_QUARTZ,
            Item.Properties()
        )
    }

    val QUARTZ_CLUSTER by REGISTRY.registerObject("quartz_cluster") {
        BlockItem(
            MoreGeodesBlocks.QUARTZ_CLUSTER,
            Item.Properties()
        )
    }

    val LARGE_QUARTZ_BUD by REGISTRY.registerObject("large_quartz_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_QUARTZ_BUD,
            Item.Properties()
        )
    }

    val MEDIUM_QUARTZ_BUD by REGISTRY.registerObject("medium_quartz_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_QUARTZ_BUD,
            Item.Properties()
        )
    }

    val SMALL_QUARTZ_BUD by REGISTRY.registerObject("small_quartz_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_QUARTZ_BUD,
            Item.Properties()
        )
    }



    val DIAMOND_CRYSTAL_BLOCK by REGISTRY.registerObject("diamond_crystal_block") {
        BlockItem(
            MoreGeodesBlocks.DIAMOND_CRYSTAL_BLOCK,
            Item.Properties()
        )
    }

    val DIAMOND_CLUSTER by REGISTRY.registerObject("diamond_cluster") {
        BlockItem(
            MoreGeodesBlocks.DIAMOND_CLUSTER,
            Item.Properties()
        )
    }



    val ECHO_BLOCK by REGISTRY.registerObject("echo_block") {
        BlockItem(
            MoreGeodesBlocks.ECHO_BLOCK,
            Item.Properties()
        )
    }

    val BUDDING_ECHO_BLOCK by REGISTRY.registerObject("budding_echo_block") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_ECHO_BLOCK,
            Item.Properties()
        )
    }

    val ECHO_CLUSTER by REGISTRY.registerObject("echo_cluster") {
        BlockItem(
            MoreGeodesBlocks.ECHO_CLUSTER,
            Item.Properties()
        )
    }

    val LARGE_ECHO_BUD by REGISTRY.registerObject("large_echo_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_ECHO_BUD,
            Item.Properties()
        )
    }

    val MEDIUM_ECHO_BUD by REGISTRY.registerObject("medium_echo_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_ECHO_BUD,
            Item.Properties()
        )
    }

    val SMALL_ECHO_BUD by REGISTRY.registerObject("small_echo_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_ECHO_BUD,
            Item.Properties()
        )
    }



    val LAPIS_CRYSTAL_BLOCK by REGISTRY.registerObject("lapis_crystal_block") {
        BlockItem(
            MoreGeodesBlocks.LAPIS_CRYSTAL_BLOCK,
            Item.Properties()
        )
    }

    val BUDDING_LAPIS by REGISTRY.registerObject("budding_lapis") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_LAPIS,
            Item.Properties()
        )
    }

    val LAPIS_CLUSTER by REGISTRY.registerObject("lapis_cluster") {
        BlockItem(
            MoreGeodesBlocks.LAPIS_CLUSTER,
            Item.Properties()
        )
    }

    val LARGE_LAPIS_BUD by REGISTRY.registerObject("large_lapis_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_LAPIS_BUD,
            Item.Properties()
        )
    }

    val MEDIUM_LAPIS_BUD by REGISTRY.registerObject("medium_lapis_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_LAPIS_BUD,
            Item.Properties()
        )
    }

    val SMALL_LAPIS_BUD by REGISTRY.registerObject("small_lapis_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_LAPIS_BUD,
            Item.Properties()
        )
    }



    val GYPSUM_CRYSTAL_BLOCK by REGISTRY.registerObject("gypsum_crystal_block") {
        BlockItem(
            MoreGeodesBlocks.GYPSUM_CRYSTAL_BLOCK,
            Item.Properties()
        )
    }

    val BUDDING_GYPSUM by REGISTRY.registerObject("budding_gypsum") {
        BlockItem(
            MoreGeodesBlocks.BUDDING_GYPSUM,
            Item.Properties()
        )
    }

    val GYPSUM_ROSE by REGISTRY.registerObject("gypsum_rose") {
        BlockItem(
            MoreGeodesBlocks.GYPSUM_ROSE,
            Item.Properties()
        )
    }

    val LARGE_GYPSUM_BUD by REGISTRY.registerObject("large_gypsum_bud") {
        BlockItem(
            MoreGeodesBlocks.LARGE_GYPSUM_BUD,
            Item.Properties()
        )
    }

    val MEDIUM_GYPSUM_BUD by REGISTRY.registerObject("medium_gypsum_bud") {
        BlockItem(
            MoreGeodesBlocks.MEDIUM_GYPSUM_BUD,
            Item.Properties()
        )
    }

    val SMALL_GYPSUM_BUD by REGISTRY.registerObject("small_gypsum_bud") {
        BlockItem(
            MoreGeodesBlocks.SMALL_GYPSUM_BUD,
            Item.Properties()
        )
    }

    val GYPSUM_SHARD by REGISTRY.registerObject("gypsum_shard") {
        Item(
            Item.Properties()
        )
    }



    val PYRITE_CHUNK by REGISTRY.registerObject("pyrite_chunk") {
        Item(
            Item.Properties()
        )
    }

    val PYRITE by REGISTRY.registerObject("pyrite") {
        BlockItem(
            MoreGeodesBlocks.PYRITE,
            Item.Properties()
        )
    }

    val PYRITE_STAIRS by REGISTRY.registerObject("pyrite_stairs") {
        BlockItem(
            MoreGeodesBlocks.PYRITE_STAIRS,
            Item.Properties()
        )
    }

    val PYRITE_SLAB by REGISTRY.registerObject("pyrite_slab") {
        BlockItem(
            MoreGeodesBlocks.PYRITE_SLAB,
            Item.Properties()
        )
    }

    val PYRITE_WALL by REGISTRY.registerObject("pyrite_wall") {
        BlockItem(
            MoreGeodesBlocks.PYRITE_WALL,
            Item.Properties()
        )
    }


    val CALCITE_STAIRS by REGISTRY.registerObject("calcite_stairs") {
        BlockItem(
            MoreGeodesBlocks.CALCITE_STAIRS,
            Item.Properties()
        )
    }

    val CALCITE_SLAB by REGISTRY.registerObject("calcite_slab") {
        BlockItem(
            MoreGeodesBlocks.CALCITE_SLAB,
            Item.Properties()
        )
    }

    val CALCITE_WALL by REGISTRY.registerObject("calcite_wall") {
        BlockItem(
            MoreGeodesBlocks.CALCITE_WALL,
            Item.Properties()
        )
    }


    val CRYSTAL_LOCATOR: Item by REGISTRY.registerObject("crystal_locator") {
        CrystalLocator(
            Item.Properties().durability(16)
        )
    }

    val ECHO_LOCATOR by REGISTRY.registerObject("echo_locator") {
        BlockItem(
            MoreGeodesBlocks.ECHO_LOCATOR,
            Item.Properties()
        )
    }

    val GABBRO by REGISTRY.registerObject("gabbro") {
        BlockItem(
            MoreGeodesBlocks.GABBRO,
            Item.Properties()
        )
    }
}