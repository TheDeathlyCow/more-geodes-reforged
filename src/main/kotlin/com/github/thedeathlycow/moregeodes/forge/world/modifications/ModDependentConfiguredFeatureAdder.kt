package com.github.thedeathlycow.moregeodes.forge.world.modifications

import com.mojang.serialization.Codec
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.common.world.ModifiableBiomeInfo
import net.minecraftforge.fml.ModList

class ModDependentConfiguredFeatureAdder(
    key: String,
    biomes: HolderSet<Biome>,
    features: HolderSet<PlacedFeature>,
    step: GenerationStep.Decoration,
    val requiredModId: String
) : ConfiguredFeatureAdder(key, biomes, features, step) {

    override fun modify(
        biome: Holder<Biome>,
        phase: BiomeModifier.Phase,
        builder: ModifiableBiomeInfo.BiomeInfo.Builder
    ) {
        if (ModList.get().isLoaded(requiredModId)) {
            super.modify(biome, phase, builder)
        }
    }

    override fun codec(): Codec<out ModDependentConfiguredFeatureAdder> {
        return MoreGeodesBiomeModifiers.DEPENDENT_ADD_FEATURES_BIOME_MODIFIER_TYPE.get()
    }
}