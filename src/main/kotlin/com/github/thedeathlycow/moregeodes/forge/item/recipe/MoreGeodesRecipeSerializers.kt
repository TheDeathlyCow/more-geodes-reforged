package com.github.thedeathlycow.moregeodes.forge.item.recipe

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object MoreGeodesRecipeSerializers {


    val REGISTRY: DeferredRegister<RecipeSerializer<*>> = DeferredRegister.create(
        ForgeRegistries.RECIPE_SERIALIZERS,
        MoreGeodesForge.MODID
    )

    val CRYSTAL_TUNING by REGISTRY.registerObject("crystal_tuning") {
        TuningRecipe.Serializer()
    }


}