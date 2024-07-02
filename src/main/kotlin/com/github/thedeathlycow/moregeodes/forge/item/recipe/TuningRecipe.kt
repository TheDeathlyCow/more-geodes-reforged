package com.github.thedeathlycow.moregeodes.forge.item.recipe

import com.github.thedeathlycow.moregeodes.forge.item.MoreGeodesCreativeTabs
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import net.minecraft.core.NonNullList
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.GsonHelper
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.ShapelessRecipe


class TuningRecipe(
    pId: ResourceLocation,
    pGroup: String,
    pCategory: CraftingBookCategory,
    private val tuningId: ResourceLocation,
    pIngredients: NonNullList<Ingredient>
) : ShapelessRecipe(
    pId,
    pGroup,
    pCategory,
    MoreGeodesCreativeTabs.makeTunedLocatorStack(tuningId.toString()),
    pIngredients
) {

    override fun getSerializer(): RecipeSerializer<TuningRecipe> {
        return MoreGeodesRecipeSerializers.CRYSTAL_TUNING
    }

    class Serializer : RecipeSerializer<TuningRecipe> {
        override fun fromJson(pRecipeId: ResourceLocation, jsonObject: JsonObject): TuningRecipe {
            val group = GsonHelper.getAsString(jsonObject, "group", "")!!

            val category = CraftingBookCategory.CODEC.byName(
                GsonHelper.getAsString(jsonObject, "category", null),
                CraftingBookCategory.MISC
            )

            val ingredients: NonNullList<Ingredient> = getIngredients(
                GsonHelper.getAsJsonArray(jsonObject, "ingredients")
            )

            if (ingredients.isEmpty()) {
                throw JsonParseException("No ingredients for tuning recipe")
            }

            if (ingredients.size > 9) {
                throw JsonParseException("Too many ingredients for tuning recipe");
            }

            if (!jsonObject.has("tuning")) {
                throw JsonParseException("No tuning ID found for a tuning recipe");
            }

            val tuning = ResourceLocation(GsonHelper.getAsString(jsonObject, "tuning"))

            return TuningRecipe(
                pRecipeId,
                group,
                category,
                tuning,
                ingredients
            )
        }

        override fun fromNetwork(pRecipeId: ResourceLocation, pBuffer: FriendlyByteBuf): TuningRecipe? {
            val group = pBuffer.readUtf()

            val category = pBuffer.readEnum(CraftingBookCategory::class.java)

            val numIngredients = pBuffer.readVarInt()
            val ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY)

            for (i in 0 until ingredients.size) {
                ingredients[i] = Ingredient.fromNetwork(pBuffer)
            }

            val tuning = ResourceLocation(pBuffer.readUtf())

            return TuningRecipe(
                pRecipeId,
                group,
                category,
                tuning,
                ingredients
            )
        }

        override fun toNetwork(pBuffer: FriendlyByteBuf, pRecipe: TuningRecipe) {
            pBuffer.writeUtf(pRecipe.group)
            pBuffer.writeEnum(pRecipe.category())
            pBuffer.writeVarInt(pRecipe.ingredients.size)
            for (ingredient in pRecipe.ingredients) {
                ingredient.toNetwork(pBuffer)
            }
            pBuffer.writeUtf(pRecipe.tuningId.toString())
        }

        private fun getIngredients(json: JsonArray): NonNullList<Ingredient> {
            val list = NonNullList.create<Ingredient>()

            for (i: Int in 0 until json.size()) {
                val ingredient = Ingredient.fromJson(json[i], false)
                if (!ingredient.isEmpty) {
                    list.add(ingredient)
                }
            }

            return list
        }

    }

}