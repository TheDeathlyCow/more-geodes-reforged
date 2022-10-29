package com.github.thedeathlycow.moregeodes.forge.sound

import net.minecraft.sounds.SoundEvents
import net.minecraftforge.common.util.ForgeSoundType

object MoreGeodesSoundTypes {

    val EMERALD_CRYSTAL_BLOCK = ForgeSoundType(
        1.0f, 1.7f,
        { SoundEvents.AMETHYST_BLOCK_BREAK },
        { SoundEvents.AMETHYST_BLOCK_STEP },
        { SoundEvents.AMETHYST_BLOCK_PLACE },
        { SoundEvents.AMETHYST_BLOCK_HIT },
        { SoundEvents.AMETHYST_BLOCK_FALL }
    )

    val EMERALD_CLUSTER = ForgeSoundType(
        1.0f,
        1.7f,
        { SoundEvents.AMETHYST_CLUSTER_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.AMETHYST_CLUSTER_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val SMALL_EMERALD_BUD = ForgeSoundType(
        1.0f,
        1.7f,
        { SoundEvents.SMALL_AMETHYST_BUD_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.SMALL_AMETHYST_BUD_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val MEDIUM_EMERALD_BUD = ForgeSoundType(
        1.0f,
        1.7f,
        { SoundEvents.MEDIUM_AMETHYST_BUD_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.MEDIUM_AMETHYST_BUD_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val LARGE_EMERALD_BUD = ForgeSoundType(
        1.0f,
        1.7f,
        { SoundEvents.LARGE_AMETHYST_BUD_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.LARGE_AMETHYST_BUD_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val QUARTZ_CRYSTAL_BLOCK = ForgeSoundType(
        1.0f, 0.5f,
        { SoundEvents.AMETHYST_BLOCK_BREAK },
        { SoundEvents.AMETHYST_BLOCK_STEP },
        { SoundEvents.AMETHYST_BLOCK_PLACE },
        { SoundEvents.AMETHYST_BLOCK_HIT },
        { SoundEvents.AMETHYST_BLOCK_FALL }
    )

}