package com.github.thedeathlycow.moregeodes.forge.client

import com.github.thedeathlycow.moregeodes.forge.item.MoreGeodesItems
import com.github.thedeathlycow.moregeodes.forge.item.TunedCrystalLocator
import com.github.thedeathlycow.moregeodes.forge.tuning.Tuning
import net.minecraft.client.Minecraft
import net.minecraft.core.RegistryAccess
import net.minecraft.world.item.ItemStack
import net.minecraftforge.client.event.RegisterColorHandlersEvent
import net.minecraftforge.eventbus.api.SubscribeEvent


object ItemColorsSetup {

    @SubscribeEvent
    fun registerCustomColors(event: RegisterColorHandlersEvent.Item) {
        event.register(::itemColor, MoreGeodesItems.TUNED_CRYSTAL_LOCATOR)
    }

    private fun itemColor(stack: ItemStack, index: Int): Int {
        val registryAccess: RegistryAccess? = Minecraft.getInstance().level?.registryAccess()

        var color: Int = -1
        if (index == 0 && registryAccess !== null) {
            val tuning = TunedCrystalLocator.getTuning(registryAccess, stack)
            color = tuning?.color ?: Tuning.UNTUNED.color
        }

        return color
    }

}