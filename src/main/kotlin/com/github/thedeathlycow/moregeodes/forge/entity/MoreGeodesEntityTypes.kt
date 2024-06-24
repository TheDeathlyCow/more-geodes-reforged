package com.github.thedeathlycow.moregeodes.forge.entity

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.github.thedeathlycow.moregeodes.forge.block.MoreGeodesBlocks
import com.github.thedeathlycow.moregeodes.forge.item.MoreGeodesItems
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object MoreGeodesEntityTypes {

    val REGISTRY: DeferredRegister<EntityType<*>> = DeferredRegister.create(
        ForgeRegistries.ENTITY_TYPES,
        MoreGeodesForge.MODID
    )

    val ECHO_DISPLAY by REGISTRY.registerObject("echo_display") {
        EntityType.Builder.of(::EchoDisplay, MobCategory.MISC)
            .sized(0f, 0f)
            .build("echo_display")
    }

}