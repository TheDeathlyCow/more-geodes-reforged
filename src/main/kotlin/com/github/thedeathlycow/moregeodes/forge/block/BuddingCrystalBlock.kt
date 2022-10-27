package com.github.thedeathlycow.moregeodes.forge.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.material.PushReaction

@Suppress("OVERRIDE_DEPRECATION")
class BuddingCrystalBlock(
    private val crystalBuds: List<CrystalBlock>,
    soundGroup: CrystalBlockSoundGroup,
    properties: Properties
) : CrystalBlock(soundGroup, properties) {

    companion object {
        val DIRECTIONS: Array<Direction> = Direction.values()
    }

    override fun getPistonPushReaction(p_60584_: BlockState): PushReaction {
        return PushReaction.DESTROY
    }

    override fun randomTick(
        blockState: BlockState,
        level: ServerLevel,
        blockPos: BlockPos,
        random: RandomSource
    ) {
        if (random.nextInt(5) == 0) { // slow the speed of the budding growth

            // the direction we want to try growing in
            val dirToGrow = DIRECTIONS[random.nextInt(DIRECTIONS.size)]

            // the position of the block to try growing in
            val posToGrow = blockPos.relative(dirToGrow)

            // current state of PosToGrow
            val currentStateInGrow = level.getBlockState(posToGrow)

            // set the next bud state if present
            val nextBlock: Block? = this.getNextStateForGrowth(currentStateInGrow, dirToGrow)
            if (nextBlock != null) {
                val isWaterlogged = currentStateInGrow.fluidState.fluidType == Fluids.WATER

                val toSet: BlockState = nextBlock.defaultBlockState()
                    .setValue(CrystalClusterBlock.WATERLOGGED, isWaterlogged)
                    .setValue(CrystalClusterBlock.FACING, dirToGrow)
                level.setBlockAndUpdate(posToGrow, toSet)
            }
        }
    }

    private fun getNextStateForGrowth(currentState: BlockState, offsetFromSource: Direction): Block? {
        val currentBlock: Block = currentState.block

        if (crystalBuds.isNotEmpty() && this.canStateGrowNewBud(currentState)) {
            return crystalBuds[0]
        }

        var blockIndex = crystalBuds.indexOf(currentBlock)
        if (blockIndex > -1 && (++blockIndex) < crystalBuds.size) {
            if (this.isStateGrowableBud(currentState, offsetFromSource)) {
                return crystalBuds[blockIndex]
            }
        }

        return null
    }

    private fun canStateGrowNewBud(state: BlockState): Boolean {
        return state.isAir
                || (state.block === Blocks.WATER && state.fluidState.amount == 8)
    }

    private fun isStateGrowableBud(state: BlockState, direction: Direction): Boolean {
        return state.hasProperty(CrystalClusterBlock.FACING)
                && state.getValue(CrystalClusterBlock.FACING) === direction
    }
}