package com.github.thedeathlycow.moregeodes.forge.tuning

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.advancements.critereon.LocationPredicate
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TextColor
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.FastColor

data class Tuning(
    val color: Int,
    val description: Component,
    val tunedPredicate: LocationPredicate? = LocationPredicate.ANY
) {

    companion object {

        private const val COLOR_KEY = "color"
        private const val DESCRIPTION_KEY = "description"
        private const val PREDICATE_KEY = "tuned_to"

        val UNTUNED: Tuning = Tuning(
            FastColor.ARGB32.color(0xff, 0x44, 0x44, 0x44),
            Component.translatable("geodes.tunings.untuned")
                .setStyle(Style.EMPTY.withColor(TextColor.parseColor("gray"))),
            LocationPredicate.ANY
        )

        val DATAPACK_CODEC: Codec<Tuning> = RecordCodecBuilder.create {
            it.group(
                Codec.INT
                    .fieldOf(COLOR_KEY)
                    .forGetter(Tuning::color),
                COMPONENT_CODEC
                    .fieldOf(DESCRIPTION_KEY)
                    .forGetter(Tuning::description),
                LOCATION_PREDICATE_CODEC
                    .fieldOf(PREDICATE_KEY)
                    .forGetter(Tuning::tunedPredicate)
            ).apply(it, ::Tuning)
        }

        val NETWORK_CODEC: Codec<Tuning> = RecordCodecBuilder.create {
            it.group(
                Codec.INT
                    .fieldOf(COLOR_KEY)
                    .forGetter(Tuning::color),
                COMPONENT_CODEC
                    .fieldOf(DESCRIPTION_KEY)
                    .forGetter(Tuning::description)
            ).apply(it, ::Tuning)
        }

    }

    fun test(serverLevel: ServerLevel, pos: BlockPos): Boolean {
        if (tunedPredicate == null || tunedPredicate === LocationPredicate.ANY) {
            return false
        }
        return tunedPredicate.matches(serverLevel, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
    }

}