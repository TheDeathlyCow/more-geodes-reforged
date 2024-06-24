package com.github.thedeathlycow.moregeodes.forge.entity

import com.github.thedeathlycow.moregeodes.forge.sound.MoreGeodesSoundEvents
import com.github.thedeathlycow.moregeodes.forge.util.set
import com.github.thedeathlycow.moregeodes.forge.world.event.MoreGeodesGameEvents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level

class EchoDisplay(
    pEntityType: EntityType<*>,
    pLevel: Level
) : Display.BlockDisplay(pEntityType, pLevel) {

    private var timeToLive: Int
        get() = entityData[TIME_TO_LIVE]
        set(value) {
            entityData[TIME_TO_LIVE] = value
        }

    private var glowTime: Int
        get() = entityData[GLOW_TIME]
        set(value) {
            entityData[GLOW_TIME] = value
        }

    private var isPinged: Boolean
        get() = entityData[IS_PINGED]
        set(value) {
            entityData[IS_PINGED] = value
        }

    init {
        isInvisible = true
        isInvulnerable = true
    }

    companion object {
        private const val TTL_NBT_KEY = "time_to_live"
        private const val GLOW_TIME_NBT_KEY = "glow_time"
        private const val IS_PINGED_NBT_KEY = "is_pinged"

        private const val BASE_TTL = 30
        private const val BASE_GLOW_TIME = Int.MIN_VALUE

        private val TIME_TO_LIVE: EntityDataAccessor<Int> = SynchedEntityData.defineId(
            EchoDisplay::class.java,
            EntityDataSerializers.INT
        )

        private val GLOW_TIME: EntityDataAccessor<Int> = SynchedEntityData.defineId(
            EchoDisplay::class.java,
            EntityDataSerializers.INT
        )

        private val IS_PINGED: EntityDataAccessor<Boolean> = SynchedEntityData.defineId(
            EchoDisplay::class.java,
            EntityDataSerializers.BOOLEAN
        )
    }

    fun setGlowDelay(value: Int) {
        this.glowTime = this.timeToLive
        this.timeToLive += value
    }

    override fun tick() {
        super.tick()

        this.timeToLive--

        if (this.timeToLive < this.glowTime && !this.isPinged) {
            this.ping()
        }

        if (this.timeToLive <= 0) {
            this.discard()
        }
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(TIME_TO_LIVE, BASE_TTL)
        entityData.define(GLOW_TIME, BASE_GLOW_TIME)
        entityData.define(IS_PINGED, false)
    }

    override fun readAdditionalSaveData(tag: CompoundTag) {
        super.readAdditionalSaveData(tag)

        this.timeToLive = tag.getInt(TTL_NBT_KEY)
        this.glowTime = tag.getInt(GLOW_TIME_NBT_KEY)
        this.isPinged = tag.getBoolean(IS_PINGED_NBT_KEY)
    }

    override fun addAdditionalSaveData(tag: CompoundTag) {
        super.addAdditionalSaveData(tag)

        tag[TTL_NBT_KEY] = this.timeToLive
        tag[GLOW_TIME_NBT_KEY] = this.glowTime
        tag[IS_PINGED_NBT_KEY] = this.isPinged
    }

    private fun ping() {
        isInvisible = false
        isPinged = true
        setGlowingTag(true)

        val level = this.level()
        val pos = this.blockPosition()

        level.gameEvent(this, MoreGeodesGameEvents.CRYSTAL_RESONATE, pos)
        level.playSound(
            null,
            pos,
            MoreGeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,
            SoundSource.BLOCKS,
            3f, 1f
        )
    }

}