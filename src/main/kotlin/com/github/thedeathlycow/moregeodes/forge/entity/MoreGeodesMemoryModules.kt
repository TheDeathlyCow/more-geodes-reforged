package com.github.thedeathlycow.moregeodes.forge.entity

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.mojang.serialization.Codec
import net.minecraft.world.entity.ai.memory.MemoryModuleType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject
import java.util.*

object MoreGeodesMemoryModules {

    val REGISTRY: DeferredRegister<MemoryModuleType<*>> = DeferredRegister.create(
        ForgeRegistries.MEMORY_MODULE_TYPES,
        MoreGeodesForge.MODID
    )

    val REMEMBERS_FOOLS_GOLD: MemoryModuleType<Boolean> by REGISTRY.registerObject("remembers_fools_gold") {
        MemoryModuleType(Optional.of(Codec.BOOL))
    }

}