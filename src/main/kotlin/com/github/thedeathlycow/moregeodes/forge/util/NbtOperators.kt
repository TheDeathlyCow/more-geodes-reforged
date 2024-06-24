package com.github.thedeathlycow.moregeodes.forge.util

import net.minecraft.nbt.CompoundTag

operator fun CompoundTag.set(key: String, value: String) {
    this.putString(key, value)
}

operator fun CompoundTag.set(key: String, value: Int) {
    this.putInt(key, value)
}

operator fun CompoundTag.set(key: String, value: Boolean) {
    this.putBoolean(key, value)
}