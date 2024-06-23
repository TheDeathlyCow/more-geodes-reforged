package com.github.thedeathlycow.moregeodes.forge.tuning

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.registries.DataPackRegistryEvent

@EventBusSubscriber
object Tunings {

    val REGISTRY_KEY: ResourceKey<Registry<Tuning>> = ResourceKey.createRegistryKey(
        ResourceLocation(MoreGeodesForge.MODID, "tunings")
    )

    @SubscribeEvent
    internal fun createRegistry(event: DataPackRegistryEvent.NewRegistry) {
        event.dataPackRegistry(
            REGISTRY_KEY,
            Tuning.DATAPACK_CODEC,
            Tuning.NETWORK_CODEC
        )
    }
}