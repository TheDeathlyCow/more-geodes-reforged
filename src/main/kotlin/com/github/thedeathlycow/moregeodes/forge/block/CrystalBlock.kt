package com.github.thedeathlycow.moregeodes.forge.block

import com.github.thedeathlycow.moregeodes.forge.sound.CrystalBlockSoundGroup
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.AmethystBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult


@Suppress("OVERRIDE_DEPRECATION")
open class CrystalBlock(
    private val soundGroup: CrystalBlockSoundGroup,
    properties: Properties
) : AmethystBlock(properties) {

    override fun onProjectileHit(
        level: Level,
        state: BlockState,
        hitResult: BlockHitResult,
        projectile: Projectile
    ) {
        if (!level.isClientSide) {
            val blockPos = hitResult.blockPos
            level.playSound(
                null,
                blockPos,
                soundGroup.hitSound,
                SoundSource.BLOCKS,
                1.0f, 0.5f + level.random.nextFloat()
            )
            level.playSound(
                null,
                blockPos,
                soundGroup.chimeSound,
                SoundSource.BLOCKS,
                1.0f, 0.5f + level.random.nextFloat()
            )
        }
    }

}