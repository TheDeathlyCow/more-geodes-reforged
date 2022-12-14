package com.github.thedeathlycow.moregeodes.forge.sound

import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents

data class CrystalBlockSoundGroup(
    val hitSound: SoundEvent,
    val chimeSound: SoundEvent
) {

    companion object {
        val EMERALD = CrystalBlockSoundGroup(
            SoundEvents.AMETHYST_BLOCK_HIT,
            MoreGeodesSoundEvents.BLOCK_EMERALD_CRYSTAL_BLOCK_CHIME
        )

        val QUARTZ = CrystalBlockSoundGroup(
            SoundEvents.AMETHYST_BLOCK_HIT,
            MoreGeodesSoundEvents.BLOCK_QUARTZ_CRYSTAL_BLOCK_CHIME
        )

        val DIAMOND = CrystalBlockSoundGroup(
            SoundEvents.AMETHYST_BLOCK_HIT,
            MoreGeodesSoundEvents.BLOCK_DIAMOND_CRYSTAL_BLOCK_CHIME
        )

        val ECHO = CrystalBlockSoundGroup(
            SoundEvents.AMETHYST_BLOCK_HIT,
            EchoGeodeBlockSoundEvents.BLOCK_ECHO_BLOCK_CHIME
        )

        val LAPIS = CrystalBlockSoundGroup(
            SoundEvents.AMETHYST_BLOCK_HIT,
            MoreGeodesSoundEvents.BLOCK_GYPSUM_CRYSTAL_BLOCK_CHIME
        )

        val GYPSUM = CrystalBlockSoundGroup(
            SoundEvents.AMETHYST_BLOCK_HIT,
            MoreGeodesSoundEvents.BLOCK_GYPSUM_CRYSTAL_BLOCK_CHIME
        )
    }
}
