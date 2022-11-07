package com.github.thedeathlycow.moregeodes.forge.block.entity

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.github.thedeathlycow.moregeodes.forge.block.MoreGeodesBlocks
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object MoreGeodesBlockEntityTypes {

    val REGISTRY: DeferredRegister<BlockEntityType<*>> = DeferredRegister.create(
        ForgeRegistries.BLOCK_ENTITY_TYPES,
        MoreGeodesForge.MODID
    )

    val ECHO_LOCATOR: BlockEntityType<EchoLocatorBlockEntity> by REGISTRY.registerObject("echo_locator") {
        BlockEntityType.Builder.of(
            ::EchoLocatorBlockEntity,
            MoreGeodesBlocks.EMERALD_CRYSTAL_ECHO_LOCATOR
        ).build(null)
    }
}