package com.github.thedeathlycow.moregeodes.forge.world.modifications

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderSet
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object MoreGeodesBiomeModifiers {
    val BIOME_MODIFIER_SERIALIZERS: DeferredRegister<Codec<out BiomeModifier>> =
        DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MoreGeodesForge.MODID)

    val ADD_FEATURES_BIOME_MODIFIER_TYPE: RegistryObject<Codec<ConfiguredFeatureAdder>> = BIOME_MODIFIER_SERIALIZERS.register("add_features") {
        RecordCodecBuilder.create { builder: RecordCodecBuilder.Instance<ConfiguredFeatureAdder> ->
            builder.group(
                Codec.STRING.fieldOf("config")
                    .forGetter { obj: ConfiguredFeatureAdder -> obj.key },
                Biome.LIST_CODEC.fieldOf("biomes")
                    .forGetter { obj: ConfiguredFeatureAdder -> obj.biomes },
                PlacedFeature.LIST_CODEC.fieldOf("features")
                    .forGetter { obj: ConfiguredFeatureAdder -> obj.features },
                GenerationStep.Decoration.CODEC.fieldOf("step")
                    .forGetter { obj: ConfiguredFeatureAdder -> obj.step }
            ).apply(
                builder
            ) { config: String,
                biomes: HolderSet<Biome>,
                features: HolderSet<PlacedFeature>,
                step: GenerationStep.Decoration ->
                ConfiguredFeatureAdder(
                    config,
                    biomes,
                    features,
                    step
                )
            }
        }
    }

    val DEPENDENT_ADD_FEATURES_BIOME_MODIFIER_TYPE: RegistryObject<Codec<ModDependentConfiguredFeatureAdder>> = BIOME_MODIFIER_SERIALIZERS.register("add_features_with_dependency") {
        RecordCodecBuilder.create { builder: RecordCodecBuilder.Instance<ModDependentConfiguredFeatureAdder> ->
            builder.group(
                Codec.STRING.fieldOf("config")
                    .forGetter { obj: ModDependentConfiguredFeatureAdder -> obj.key },
                Biome.LIST_CODEC.fieldOf("biomes")
                    .forGetter { obj: ModDependentConfiguredFeatureAdder -> obj.biomes },
                PlacedFeature.LIST_CODEC.fieldOf("features")
                    .forGetter { obj: ModDependentConfiguredFeatureAdder -> obj.features },
                GenerationStep.Decoration.CODEC.fieldOf("step")
                    .forGetter { obj: ModDependentConfiguredFeatureAdder -> obj.step },
                Codec.STRING.fieldOf("required_mod_id")
                    .forGetter { obj: ModDependentConfiguredFeatureAdder -> obj.requiredModId}
            ).apply(
                builder
            ) { config: String,
                biomes: HolderSet<Biome>,
                features: HolderSet<PlacedFeature>,
                step: GenerationStep.Decoration,
                requiredModId: String ->
                ModDependentConfiguredFeatureAdder(
                    config,
                    biomes,
                    features,
                    step,
                    requiredModId
                )
            }
        }
    }

}