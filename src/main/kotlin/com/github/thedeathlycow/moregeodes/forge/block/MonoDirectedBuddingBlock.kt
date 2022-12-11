package com.github.thedeathlycow.moregeodes.forge.block

import com.github.thedeathlycow.moregeodes.forge.sound.CrystalBlockSoundGroup
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.state.BlockState

@Suppress("OVERRIDE_DEPRECATION")
class MonoDirectedBuddingBlock(
    private val direction: Direction,
    crystalBuds: List<CrystalClusterBlock>,
    soundGroup: CrystalBlockSoundGroup,
    properties: Properties
): BuddingCrystalBlock(crystalBuds, soundGroup, properties) {

    override fun randomTick(
        blockState: BlockState,
        level: ServerLevel,
        blockPos: BlockPos,
        random: RandomSource
    ) {
        if (this.shouldGrow(random)) {
            this.growCrystalOnce(level, blockPos, direction)
        }
    }

}