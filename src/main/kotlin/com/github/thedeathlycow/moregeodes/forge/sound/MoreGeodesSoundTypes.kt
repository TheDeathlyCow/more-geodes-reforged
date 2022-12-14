package com.github.thedeathlycow.moregeodes.forge.sound

import net.minecraft.sounds.SoundEvents
import net.minecraftforge.common.util.ForgeSoundType

object MoreGeodesSoundTypes {

    val EMERALD_CRYSTAL_BLOCK = ForgeSoundType(
        1.0f,
        1.7f,
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
        1.0f,
        0.5f,
        { SoundEvents.AMETHYST_BLOCK_BREAK },
        { SoundEvents.AMETHYST_BLOCK_STEP },
        { SoundEvents.AMETHYST_BLOCK_PLACE },
        { SoundEvents.AMETHYST_BLOCK_HIT },
        { SoundEvents.AMETHYST_BLOCK_FALL }
    )

    val QUARTZ_CLUSTER = ForgeSoundType(
        1.0f,
        0.5f,
        { SoundEvents.AMETHYST_CLUSTER_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.AMETHYST_CLUSTER_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val SMALL_QUARTZ_BUD = ForgeSoundType(
        1.0f,
        0.5f,
        { SoundEvents.SMALL_AMETHYST_BUD_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.SMALL_AMETHYST_BUD_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val MEDIUM_QUARTZ_BUD = ForgeSoundType(
        1.0f,
        0.5f,
        { SoundEvents.MEDIUM_AMETHYST_BUD_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.MEDIUM_AMETHYST_BUD_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val LARGE_QUARTZ_BUD = ForgeSoundType(
        1.0f,
        0.5f,
        { SoundEvents.LARGE_AMETHYST_BUD_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.LARGE_AMETHYST_BUD_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val DIAMOND_CRYSTAL_BLOCK = ForgeSoundType(
        1.0f,
        1.4f,
        { SoundEvents.AMETHYST_BLOCK_BREAK },
        { SoundEvents.AMETHYST_BLOCK_STEP },
        { SoundEvents.AMETHYST_BLOCK_PLACE },
        { SoundEvents.AMETHYST_BLOCK_HIT },
        { SoundEvents.AMETHYST_BLOCK_FALL }
    )

    val DIAMOND_CLUSTER = ForgeSoundType(
        1.0f,
        1.4f,
        { SoundEvents.AMETHYST_CLUSTER_BREAK },
        { SoundEvents.AMETHYST_CLUSTER_STEP },
        { SoundEvents.AMETHYST_CLUSTER_PLACE },
        { SoundEvents.AMETHYST_CLUSTER_HIT },
        { SoundEvents.AMETHYST_CLUSTER_FALL }
    )

    val ECHO_BLOCK = ForgeSoundType(
        1.0f,
        1.0f,
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_BLOCK_BREAK },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_BLOCK_STEP },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_BLOCK_PLACE },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_BLOCK_HIT },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_BLOCK_FALL }
    )

    val ECHO_CLUSTER = ForgeSoundType(
        1.0f,
        1.0f,
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_BREAK },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_STEP },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_PLACE },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_HIT },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_FALL }
    )

    val SMALL_ECHO_BUD = ForgeSoundType(
        1.0f,
        1.0f,
        { EchoGeodeBlockSoundEvents.BLOCK_SMALL_ECHO_BUD_BREAK },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_STEP },
        { EchoGeodeBlockSoundEvents.BLOCK_SMALL_ECHO_BUD_PLACE },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_HIT },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_FALL }
    )

    val MEDIUM_ECHO_BUD = ForgeSoundType(
        1.0f,
        1.0f,
        { EchoGeodeBlockSoundEvents.BLOCK_MEDIUM_ECHO_BUD_BREAK },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_STEP },
        { EchoGeodeBlockSoundEvents.BLOCK_MEDIUM_ECHO_BUD_PLACE },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_HIT },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_FALL }
    )

    val LARGE_ECHO_BUD = ForgeSoundType(
        1.0f,
        1.0f,
        { EchoGeodeBlockSoundEvents.BLOCK_MEDIUM_ECHO_BUD_BREAK },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_STEP },
        { EchoGeodeBlockSoundEvents.BLOCK_MEDIUM_ECHO_BUD_PLACE },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_HIT },
        { EchoGeodeBlockSoundEvents.BLOCK_ECHO_CLUSTER_FALL }
    )

    val ECHO_LOCATOR = ForgeSoundType(
        1.0f, 1.0f,
        { SoundEvents.METAL_BREAK },
        { SoundEvents.METAL_STEP },
        { SoundEvents.METAL_PLACE },
        { SoundEvents.METAL_HIT },
        { SoundEvents.METAL_FALL },
    )

    val LAPIS_CRYSTAL_BLOCK = ForgeSoundType(
        1.0f, 0.9f,
        { SoundEvents.AMETHYST_BLOCK_BREAK },
        { SoundEvents.AMETHYST_BLOCK_STEP },
        { SoundEvents.AMETHYST_BLOCK_PLACE },
        { SoundEvents.AMETHYST_BLOCK_HIT },
        { SoundEvents.AMETHYST_BLOCK_FALL }
    )

    val GYPSUM_CRYSTAL_BLOCK = ForgeSoundType(
        1.0f, 0.2f,
        { SoundEvents.AMETHYST_BLOCK_BREAK },
        { SoundEvents.AMETHYST_BLOCK_STEP },
        { SoundEvents.AMETHYST_BLOCK_PLACE },
        { SoundEvents.AMETHYST_BLOCK_HIT },
        { SoundEvents.AMETHYST_BLOCK_FALL }
    )
}