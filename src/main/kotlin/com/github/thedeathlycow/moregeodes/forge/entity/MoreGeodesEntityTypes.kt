package com.github.thedeathlycow.moregeodes.forge.entity

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.world.entity.EntityType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object MoreGeodesEntityTypes {

    val REGISTRY: DeferredRegister<EntityType<*>> = DeferredRegister.create(
        ForgeRegistries.ENTITY_TYPES,
        MoreGeodesForge.MODID
    )

}