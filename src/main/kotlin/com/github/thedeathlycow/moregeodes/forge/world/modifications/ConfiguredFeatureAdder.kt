package com.github.thedeathlycow.moregeodes.forge.world.modifications

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.mojang.serialization.Codec
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.common.world.ModifiableBiomeInfo
import net.minecraftforge.fml.ModList
import java.util.function.Consumer

data class ConfiguredFeatureAdder(
    val key: String,
    val biomes: HolderSet<Biome>,
    val features: HolderSet<PlacedFeature>,
    val step: GenerationStep.Decoration,
    val requiresMod: String?
) : BiomeModifier {

    override fun modify(
        biome: Holder<Biome>,
        phase: BiomeModifier.Phase,
        builder: ModifiableBiomeInfo.BiomeInfo.Builder
    ) {

        if (requiresMod !== null && !ModList.get().isLoaded(requiresMod)) {
            return;
        }

        if (phase == BiomeModifier.Phase.ADD && biomes.contains(biome) && MoreGeodesForge.CONFIG.checkConfig(key)) {
            val generationSettings = builder.generationSettings
            features.forEach(
                Consumer { feature: Holder<PlacedFeature> ->
                    generationSettings.addFeature(
                        step,
                        feature
                    )
                }
            )
        }
    }

    override fun codec(): Codec<out BiomeModifier> {
        return MoreGeodesBiomeModifiers.ADD_FEATURES_BIOME_MODIFIER_TYPE.get()
    }
}