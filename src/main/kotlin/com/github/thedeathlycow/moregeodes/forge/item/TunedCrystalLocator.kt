package com.github.thedeathlycow.moregeodes.forge.item

import com.github.thedeathlycow.moregeodes.forge.tuning.Tuning
import com.github.thedeathlycow.moregeodes.forge.tuning.Tunings
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.RegistryAccess
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class TunedCrystalLocator(
    properties: Properties,
    range: Int = BASE_RANGE
) : CrystalLocator(properties, range) {

    override fun isTuned(stack: ItemStack, level: Level?): Boolean {
        val registryAccess: RegistryAccess = level?.registryAccess() ?: return false
        return getTuning(registryAccess, stack) != null
    }

    override fun isPingableCrystal(locatorStack: ItemStack, serverLevel: ServerLevel, pos: BlockPos): Boolean {
        val tuning: Tuning = getTuning(serverLevel.registryAccess(), locatorStack)
            ?: return super.isPingableCrystal(locatorStack, serverLevel, pos)

        return tuning.test(serverLevel, pos)
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: Level?,
        pTooltipComponents: MutableList<Component>,
        pIsAdvanced: TooltipFlag
    ) {
        pLevel?.let { level ->
            getTuning(level.registryAccess(), pStack)?.let {
                pTooltipComponents.add(it.description)
            }
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced)
    }

    companion object {

        internal const val TUNING_NBT_KEY: String = "tuning"

        internal const val TUNING_ID_NBT_KEY: String = "id"

        fun getTuning(registryManager: RegistryAccess, stack: ItemStack): Tuning? {
            val registry: Registry<Tuning> = registryManager.registryOrThrow(Tunings.REGISTRY_KEY) ?: return null
            val tag: CompoundTag = stack.getTagElement(TUNING_NBT_KEY) ?: return null

            return registry[ResourceLocation(tag.getString(TUNING_ID_NBT_KEY))]
        }

    }

}