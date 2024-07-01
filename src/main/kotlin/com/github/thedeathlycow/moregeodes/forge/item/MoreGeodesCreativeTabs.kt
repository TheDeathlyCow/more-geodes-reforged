package com.github.thedeathlycow.moregeodes.forge.item

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.github.thedeathlycow.moregeodes.forge.util.set
import net.minecraft.core.registries.Registries
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraftforge.fml.ModList
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object MoreGeodesCreativeTabs {

    val REGISTRY: DeferredRegister<CreativeModeTab> = DeferredRegister.create(
        Registries.CREATIVE_MODE_TAB,
        MoreGeodesForge.MODID
    )

    val MORE_GEODES: CreativeModeTab by REGISTRY.registerObject("") {
        CreativeModeTab.builder()
            .icon {
                ItemStack(MoreGeodesItems.EMERALD_CLUSTER)
            }
            .title(Component.translatable("itemGroup.geodes.more_geodes"))
            .displayItems { _, entries ->
                // emerald
                entries.accept(ItemStack(MoreGeodesItems.EMERALD_CRYSTAL_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.BUDDING_EMERALD))
                entries.accept(ItemStack(MoreGeodesItems.EMERALD_CLUSTER))
                entries.accept(ItemStack(MoreGeodesItems.LARGE_EMERALD_BUD))
                entries.accept(ItemStack(MoreGeodesItems.MEDIUM_EMERALD_BUD))
                entries.accept(ItemStack(MoreGeodesItems.SMALL_EMERALD_BUD))

                // quartz
                entries.accept(ItemStack(MoreGeodesItems.QUARTZ_CRYSTAL_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.BUDDING_QUARTZ))
                entries.accept(ItemStack(MoreGeodesItems.QUARTZ_CLUSTER))
                entries.accept(ItemStack(MoreGeodesItems.LARGE_QUARTZ_BUD))
                entries.accept(ItemStack(MoreGeodesItems.MEDIUM_QUARTZ_BUD))
                entries.accept(ItemStack(MoreGeodesItems.SMALL_QUARTZ_BUD))

                // diamond
                entries.accept(ItemStack(MoreGeodesItems.DIAMOND_CRYSTAL_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.DIAMOND_CLUSTER))

                // echo
                entries.accept(ItemStack(MoreGeodesItems.ECHO_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.BUDDING_ECHO_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.ECHO_CLUSTER))
                entries.accept(ItemStack(MoreGeodesItems.LARGE_ECHO_BUD))
                entries.accept(ItemStack(MoreGeodesItems.MEDIUM_ECHO_BUD))
                entries.accept(ItemStack(MoreGeodesItems.SMALL_ECHO_BUD))

                // lapis
                entries.accept(ItemStack(MoreGeodesItems.LAPIS_CRYSTAL_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.BUDDING_LAPIS))
                entries.accept(ItemStack(MoreGeodesItems.LAPIS_CLUSTER))
                entries.accept(ItemStack(MoreGeodesItems.LARGE_LAPIS_BUD))
                entries.accept(ItemStack(MoreGeodesItems.MEDIUM_LAPIS_BUD))
                entries.accept(ItemStack(MoreGeodesItems.SMALL_LAPIS_BUD))

                // gypsum
                entries.accept(ItemStack(MoreGeodesItems.GYPSUM_CRYSTAL_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.BUDDING_GYPSUM))
                entries.accept(ItemStack(MoreGeodesItems.GYPSUM_ROSE))
                entries.accept(ItemStack(MoreGeodesItems.LARGE_GYPSUM_BUD))
                entries.accept(ItemStack(MoreGeodesItems.MEDIUM_GYPSUM_BUD))
                entries.accept(ItemStack(MoreGeodesItems.SMALL_GYPSUM_BUD))
                entries.accept(ItemStack(MoreGeodesItems.GYPSUM_SHARD))

                // pyrite and calcite
                entries.accept(ItemStack(MoreGeodesItems.PYRITE_CHUNK))
                entries.accept(ItemStack(MoreGeodesItems.PYRITE))
                entries.accept(ItemStack(MoreGeodesItems.PYRITE_STAIRS))
                entries.accept(ItemStack(MoreGeodesItems.PYRITE_SLAB))
                entries.accept(ItemStack(MoreGeodesItems.PYRITE_WALL))
                entries.accept(ItemStack(Items.CALCITE))
                entries.accept(ItemStack(MoreGeodesItems.CALCITE_STAIRS))
                entries.accept(ItemStack(MoreGeodesItems.CALCITE_SLAB))
                entries.accept(ItemStack(MoreGeodesItems.CALCITE_WALL))

                entries.accept(ItemStack(MoreGeodesItems.GABBRO))

                // gypsum blocks
                entries.accept(ItemStack(MoreGeodesItems.CHISELED_GYPSUM))
                entries.accept(ItemStack(MoreGeodesItems.GYPSUM_PILLAR))
                entries.accept(ItemStack(MoreGeodesItems.POLISHED_GYPSUM_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.POLISHED_GYPSUM_STAIRS))
                entries.accept(ItemStack(MoreGeodesItems.POLISHED_GYPSUM_SLAB))
                entries.accept(ItemStack(MoreGeodesItems.POLISHED_GYPSUM_WALL))
                entries.accept(ItemStack(MoreGeodesItems.SMOOTH_GYPSUM_BLOCK))
                entries.accept(ItemStack(MoreGeodesItems.SMOOTH_GYPSUM_STAIRS))
                entries.accept(ItemStack(MoreGeodesItems.SMOOTH_GYPSUM_SLAB))
                entries.accept(ItemStack(MoreGeodesItems.SMOOTH_GYPSUM_WALL))

                // echo locator
                entries.accept(ItemStack(MoreGeodesItems.CRYSTAL_LOCATOR))
                entries.accept(ItemStack(MoreGeodesItems.ECHO_LOCATOR))
                entries.acceptAll(getTunedCrystalLocators())
            }
            .build()
    }

    private fun getTunedCrystalLocators(): List<ItemStack> {
        val entries = mutableListOf(
            makeTunedLocatorStack("geodes:amethyst"),
            makeTunedLocatorStack("geodes:emerald"),
            makeTunedLocatorStack("geodes:quartz"),
            makeTunedLocatorStack("geodes:diamond"),
            makeTunedLocatorStack("geodes:echo"),
            makeTunedLocatorStack("geodes:lapis"),
            makeTunedLocatorStack("geodes:gypsum")
        )

        if (ModList.get().isLoaded("spectrum")) {
            entries.add(makeTunedLocatorStack("geodes:spectrum/topaz"))
            entries.add(makeTunedLocatorStack("geodes:spectrum/citrine"))
            entries.add(makeTunedLocatorStack("geodes:spectrum/moonstone"))
        }

        return entries
    }

    fun makeTunedLocatorStack(tuningID: String): ItemStack {
        val stack = ItemStack(MoreGeodesItems.TUNED_CRYSTAL_LOCATOR)

        val tag = CompoundTag()
        tag[TunedCrystalLocator.TUNING_ID_NBT_KEY] = tuningID
        stack.addTagElement(TunedCrystalLocator.TUNING_NBT_KEY, tag)

        return stack
    }


}