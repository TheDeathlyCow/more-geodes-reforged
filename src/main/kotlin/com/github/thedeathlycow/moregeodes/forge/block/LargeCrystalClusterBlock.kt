package com.github.thedeathlycow.moregeodes.forge.block

import com.github.thedeathlycow.moregeodes.forge.sound.CrystalBlockSoundGroup
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.LevelEvent
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
open class LargeCrystalClusterBlock(
    hitSoundGroup: CrystalBlockSoundGroup,
    height: Double,
    xzOffset: Double,
    properties: Properties
) : CrystalClusterBlock(hitSoundGroup, height, xzOffset, properties) {

    private val lowerNorthShape: VoxelShape
    private val lowerSouthShape: VoxelShape
    private val lowerEastShape: VoxelShape
    private val lowerWestShape: VoxelShape
    private val lowerUpShape: VoxelShape
    private val lowerDownShape: VoxelShape

    init {
        this.registerDefaultState(
            this.defaultBlockState()
                .setValue(HALF, DoubleBlockHalf.LOWER)
        )
        val lowerHeight = 16.0
        this.lowerUpShape = Block.box(xzOffset, 0.0, xzOffset, 16 - xzOffset, lowerHeight, 16 - xzOffset)
        this.lowerDownShape = Block.box(xzOffset, 16 - lowerHeight, xzOffset, 16 - xzOffset, 16.0, 16 - xzOffset)
        this.lowerNorthShape = Block.box(xzOffset, xzOffset, (16 - lowerHeight), (16 - xzOffset), (16 - xzOffset), 16.0)
        this.lowerSouthShape = Block.box(xzOffset, xzOffset, 0.0, (16 - xzOffset), (16 - xzOffset), lowerHeight)
        this.lowerEastShape = Block.box(0.0, xzOffset, xzOffset, lowerHeight, (16 - xzOffset), (16 - xzOffset))
        this.lowerWestShape = Block.box((16 - lowerHeight), xzOffset, xzOffset, 16.0, (16 - xzOffset), (16 - xzOffset))
    }

    companion object {
        val HALF: EnumProperty<DoubleBlockHalf> = BlockStateProperties.DOUBLE_BLOCK_HALF

        fun placeAt(
            level: LevelAccessor,
            state: BlockState,
            pos: BlockPos,
            flags: Int
        ) {
            val headPos = pos.relative(state.getValue(FACING))

            level.setBlock(
                pos,
                DoublePlantBlock.copyWaterloggedFrom(level, pos, state.setValue(HALF, DoubleBlockHalf.LOWER)),
                flags
            )

            level.setBlock(
                headPos,
                DoublePlantBlock.copyWaterloggedFrom(level, headPos, state.setValue(HALF, DoubleBlockHalf.UPPER)),
                flags
            )
        }
    }

    override fun getStateForPlacement(placeContext: BlockPlaceContext): BlockState? {

        val state: BlockState? = super.getStateForPlacement(placeContext)

        if (state === null) {
            return null
        }

        val blockPos = placeContext.clickedPos
        val level = placeContext.level

        val facing = state.getValue(FACING)
        var inHeightBounds = true
        if (facing == Direction.UP) {
            inHeightBounds = blockPos.y < level.maxBuildHeight - 1
        } else if (facing == Direction.DOWN) {
            inHeightBounds = blockPos.y < level.minBuildHeight + 1
        }

        val canPlace = inHeightBounds && level.getBlockState(blockPos.relative(facing)).canBeReplaced(placeContext)

        return if (canPlace) state else null
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        super.createBlockStateDefinition(builder)
        builder.add(HALF)
    }

    override fun getShape(
        state: BlockState,
        blockView: BlockGetter,
        pos: BlockPos,
        collisionContext: CollisionContext
    ): VoxelShape {
        return if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            super.getShape(state, blockView, pos, collisionContext)
        } else {
            when (state.getValue(FACING)) {
                Direction.UP -> this.lowerUpShape
                Direction.DOWN -> this.lowerDownShape
                Direction.NORTH -> this.lowerNorthShape
                Direction.EAST -> this.lowerEastShape
                Direction.WEST -> this.lowerWestShape
                Direction.SOUTH -> this.lowerSouthShape
                null -> this.lowerNorthShape
            }
        }
    }

    override fun setPlacedBy(
        level: Level,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        item: ItemStack
    ) {
        val headPos = pos.relative(state.getValue(FACING))
        level.setBlock(
            headPos,
            DoublePlantBlock.copyWaterloggedFrom(
                level,
                headPos,
                state.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
            ),
            Block.UPDATE_ALL
        )
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return when (state.getValue(HALF)) {
            DoubleBlockHalf.LOWER -> super.canSurvive(state, level, pos)
            DoubleBlockHalf.UPPER -> this.canPlaceUpper(state, level, pos)
            null -> false
        }
    }

    override fun updateShape(
        state: BlockState,
        dirToNeighbour: Direction,
        neighbourState: BlockState,
        level: LevelAccessor,
        pos: BlockPos,
        neighbourPos: BlockPos
    ): BlockState {
        val half = state.getValue(HALF)

        val shouldBreakBlock = half == DoubleBlockHalf.LOWER
                && dirToNeighbour == state.getValue(FACING)
                && (!neighbourState.`is`(this) || neighbourState.getValue(HALF) == half)

        return if (shouldBreakBlock) {
            Blocks.AIR.defaultBlockState()
        } else {
            super.updateShape(
                state,
                dirToNeighbour,
                neighbourState,
                level,
                pos,
                neighbourPos
            )
        }
    }

    override fun playerWillDestroy(
        level: Level,
        pos: BlockPos,
        state: BlockState,
        player: Player
    ) {

        if (!level.isClientSide) {
            // server break

            if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                // upper half is broken
                val anchorPos = pos.relative(state.getValue(FACING).opposite)
                val anchorState = level.getBlockState(anchorPos)

                if (anchorState.`is`(this) && anchorState.getValue(HALF) == DoubleBlockHalf.LOWER) {
                    level.setBlock(
                        anchorPos,
                        Blocks.AIR.defaultBlockState(),
                        Block.UPDATE_SUPPRESS_DROPS or Block.UPDATE_ALL
                    )
                    level.levelEvent(
                        player,
                        LevelEvent.PARTICLES_DESTROY_BLOCK,
                        anchorPos,
                        Block.getId(anchorState)
                    )
                }
            }

        }

        super.playerWillDestroy(level, pos, state, player)
    }

    override fun getSeed(state: BlockState, pos: BlockPos): Long {

        val dest = pos.relative(state.getValue(FACING), if (state.getValue(HALF) == DoubleBlockHalf.LOWER) 1 else 0)

        val diff = pos.subtract(dest)

        return Mth.getSeed(
            diff.x,
            diff.y,
            diff.z
        )
    }

    private fun canPlaceUpper(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val direction = state.getValue(FACING)
        val anchorPos = pos.relative(direction.opposite)
        val anchorState = level.getBlockState(anchorPos)

        return anchorState.block is LargeCrystalClusterBlock // allow anchor to not always be strictly 'this' for budding growth
                && anchorState.getValue(HALF) == DoubleBlockHalf.LOWER
    }
}


