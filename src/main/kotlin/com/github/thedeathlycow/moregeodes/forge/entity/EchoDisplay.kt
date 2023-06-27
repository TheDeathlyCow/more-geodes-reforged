package com.github.thedeathlycow.moregeodes.forge.entity

import com.github.thedeathlycow.moregeodes.forge.block.entity.EchoLocatorBlockEntity
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level

class EchoDisplay(
    type: EntityType<*>,
    level: Level
) : Display.BlockDisplay(type, level) {


    private var timeToLive: Int = EchoLocatorBlockEntity.TICKS_PER_PING / 2

    companion object {
        const val TTL_NBT_KEY: String = "TimeToLive"
    }

    override fun tick() {
        super.tick()

        this.timeToLive--

        if (timeToLive <= 0) {
            discard()
        }
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        timeToLive = nbt.getInt(TTL_NBT_KEY)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt(TTL_NBT_KEY, timeToLive)
    }

}