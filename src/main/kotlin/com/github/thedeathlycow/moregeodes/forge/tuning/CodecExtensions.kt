package com.github.thedeathlycow.moregeodes.forge.tuning

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.*
import net.minecraft.advancements.critereon.LocationPredicate
import net.minecraft.network.chat.Component


val LOCATION_PREDICATE_CODEC: Codec<LocationPredicate> = object : Codec<LocationPredicate> {
    override fun <T : Any> encode(input: LocationPredicate, ops: DynamicOps<T>, prefix: T): DataResult<T> {
        return try {
            ops.mergeToPrimitive(prefix, Dynamic.convert(JsonOps.INSTANCE, ops, input.serializeToJson()))
        } catch (e: java.lang.Exception) {
            DataResult.error {
                e.message
            }
        }
    }

    override fun <T : Any> decode(ops: DynamicOps<T>, input: T): DataResult<Pair<LocationPredicate, T>> {
        try {
            val json = Dynamic.convert(ops, JsonOps.INSTANCE, input)
            val predicate = LocationPredicate.fromJson(json)
            return DataResult.success(Pair(predicate, ops.empty()))
        } catch (e: Exception) {
            return DataResult.error {
                "${e.javaClass.name}: ${e.message}"
            }
        }
    }

}

val COMPONENT_CODEC: Codec<Component> = object : Codec<Component> {

    override fun <T : Any> encode(input: Component, ops: DynamicOps<T>, prefix: T): DataResult<T> {
        return try {
            ops.mergeToPrimitive(
                prefix,
                Dynamic.convert(JsonOps.INSTANCE, ops, Component.Serializer.toJsonTree(input))
            )
        } catch (e: Exception) {
            DataResult.error {
                "${e.javaClass.name}: ${e.message}"
            }
        }
    }

    override fun <T : Any> decode(ops: DynamicOps<T>, input: T): DataResult<Pair<Component, T>> {
        try {
            val json = Dynamic.convert(ops, JsonOps.INSTANCE, input)
            val component = Component.Serializer.fromJson(json)
            return DataResult.success(Pair(component, ops.empty()))
        } catch (e: Exception) {
            return DataResult.error {
                "${e.javaClass.name}: ${e.message}"
            }
        }
    }

}