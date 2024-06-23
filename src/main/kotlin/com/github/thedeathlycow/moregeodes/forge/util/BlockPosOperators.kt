package com.github.thedeathlycow.moregeodes.forge.util

import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.nbt.CompoundTag

operator fun BlockPos.minus(other: Vec3i): BlockPos = this.subtract(other)

operator fun BlockPos.plus(other: Vec3i): BlockPos = this.offset(other)

operator fun CompoundTag.set(key: String, value: String) {
    this.putString(key, value)
}