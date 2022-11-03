package com.github.thedeathlycow.moregeodes.forge.block

import com.github.thedeathlycow.moregeodes.forge.sound.CrystalBlockSoundGroup
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION")
open class CrystalClusterBlock(
    soundGroup: CrystalBlockSoundGroup,
    height: Double,
    xzOffset: Double,
    properties: Properties
) : CrystalBlock(soundGroup, properties), SimpleWaterloggedBlock {

    private val northShape: VoxelShape
    private val southShape: VoxelShape
    private val eastShape: VoxelShape
    private val westShape: VoxelShape
    private val upShape: VoxelShape
    private val downShape: VoxelShape

    init {
        this.registerDefaultState(
            this.defaultBlockState()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.UP)
        )

        this.upShape = Block.box(xzOffset, 0.0, xzOffset, (16 - xzOffset), height, (16 - xzOffset))
        this.downShape = Block.box(xzOffset, (16 - height), xzOffset, (16 - xzOffset), 16.0, (16 - xzOffset))
        this.northShape = Block.box(xzOffset, xzOffset, (16 - height), (16 - xzOffset), (16 - xzOffset), 16.0)
        this.southShape = Block.box(xzOffset, xzOffset, 0.0, (16 - xzOffset), (16 - xzOffset), height)
        this.eastShape = Block.box(0.0, xzOffset, xzOffset, height, (16 - xzOffset), (16 - xzOffset))
        this.westShape = Block.box((16 - height), xzOffset, xzOffset, 16.0, (16 - xzOffset), (16 - xzOffset))
    }

    companion object {
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val FACING: DirectionProperty = BlockStateProperties.FACING
    }

    override fun getShape(
        state: BlockState,
        blockView: BlockGetter,
        pos: BlockPos,
        collisionContext: CollisionContext
    ): VoxelShape {
        return when (state.getValue(FACING)) {
            Direction.UP -> upShape
            Direction.DOWN -> downShape
            Direction.NORTH -> northShape
            Direction.EAST -> eastShape
            Direction.WEST -> westShape
            Direction.SOUTH -> southShape
            null -> upShape
        }
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val direction = state.getValue(FACING)
        val anchorPos = pos.relative(direction.opposite)
        return level.getBlockState(anchorPos).isFaceSturdy(level, anchorPos, direction);
    }

    @Suppress("DEPRECATION")
    override fun updateShape(
        state: BlockState,
        dirToNeighbour: Direction,
        neighbourState: BlockState,
        level: LevelAccessor,
        pos: BlockPos,
        neighbourPos: BlockPos
    ): BlockState {

        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }

        val shouldBreakBlock = dirToNeighbour == state.getValue(FACING).opposite
                && !state.canSurvive(level, pos)

        return if (shouldBreakBlock) Blocks.AIR.defaultBlockState()
        else super.updateShape(state, dirToNeighbour, neighbourState, level, pos, neighbourPos)
    }

    override fun getStateForPlacement(placeContext: BlockPlaceContext): BlockState? {
        val level = placeContext.level
        val pos = placeContext.clickedPos

        return this.defaultBlockState()
                .setValue(WATERLOGGED, level.getFluidState(pos).type === Fluids.WATER)
                .setValue(FACING, placeContext.clickedFace)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    @Suppress("DEPRECATION")
    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
    }

    @Suppress("DEPRECATION")
    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, FACING)
    }

    override fun getPistonPushReaction(state: BlockState): PushReaction {
        return PushReaction.DESTROY
    }
}