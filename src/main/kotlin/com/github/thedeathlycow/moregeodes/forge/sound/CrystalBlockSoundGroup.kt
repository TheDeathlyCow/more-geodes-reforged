package com.github.thedeathlycow.moregeodes.forge.sound

import net.minecraft.sounds.SoundEvent

data class CrystalBlockSoundGroup(

    val hitSound: SoundEvent,

    val chimeSound: SoundEvent
) {

    companion object {
        val EMERALD_SOUND_GROUP = CrystalBlockSoundGroup(
            MoreGeodesSoundEvents.BLOCK_EMERALD_CRYSTAL_BLOCK_HIT,
            MoreGeodesSoundEvents.BLOCK_EMERALD_CRYSTAL_BLOCK_CHIME
        )
    }
}
