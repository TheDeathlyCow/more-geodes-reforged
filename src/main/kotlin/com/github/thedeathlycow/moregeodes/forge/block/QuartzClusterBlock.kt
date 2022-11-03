package com.github.thedeathlycow.moregeodes.forge.block

import com.github.thedeathlycow.moregeodes.forge.sound.CrystalBlockSoundGroup
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty

@Suppress("OVERRIDE_DEPRECATION")
class QuartzClusterBlock(
    private val signalStrength: Int,
    private val onTime: Int,
    soundGroup: CrystalBlockSoundGroup,
    height: Double,
    xzOffset: Double,
    properties: Properties
) : CrystalClusterBlock(soundGroup, height, xzOffset, properties) {

    companion object {
        val POWDERED: BooleanProperty = BlockStateProperties.POWERED
    }

    init {
        this.registerDefaultState(
            this.defaultBlockState()
                .setValue(POWDERED, false)
        )
    }

    @Suppress("deprecation")
    override fun neighborChanged(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        block: Block,
        neighbourPos: BlockPos,
        notify: Boolean
    ) {
        super.neighborChanged(state, level, pos, block, neighbourPos, notify)
        level.scheduleTick(pos, this, onTime)
    }

    override fun tick(
        state: BlockState,
        serverLevel: ServerLevel,
        pos: BlockPos,
        random: RandomSource
    ) {
        if (serverLevel.hasNeighborSignal(pos)) {
            val isCurrentlyPowered = state.getValue(POWDERED)
            serverLevel.setBlockAndUpdate(pos, state.setValue(POWDERED, !isCurrentlyPowered))
            serverLevel.scheduleTick(pos, this, onTime)
        } else {
            serverLevel.setBlockAndUpdate(pos, state.setValue(POWDERED, false))
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        super.createBlockStateDefinition(builder)
        builder.add(POWDERED)
    }

    override fun isSignalSource(state: BlockState): Boolean {
        return true
    }

    override fun getSignal(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        direction: Direction
    ): Int {
        return if (state.getValue(POWDERED)) signalStrength else 0
    }

}