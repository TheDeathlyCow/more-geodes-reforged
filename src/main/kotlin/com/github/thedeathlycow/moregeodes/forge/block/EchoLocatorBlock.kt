package com.github.thedeathlycow.moregeodes.forge.block

import com.github.thedeathlycow.moregeodes.forge.block.entity.EchoLocatorBlockEntity
import com.github.thedeathlycow.moregeodes.forge.block.entity.MoreGeodesBlockEntityTypes
import com.github.thedeathlycow.moregeodes.forge.world.event.MoreGeodesGameEvents
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.gameevent.GameEventListener
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION")
class EchoLocatorBlock(
    private val type: EchoLocatorType,
    properties: Properties
) : BaseEntityBlock(properties), SimpleWaterloggedBlock {

    companion object {
        // state companions
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val POWDERED: BooleanProperty = BlockStateProperties.POWERED
        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.HORIZONTAL_AXIS
        val FACING: DirectionProperty = BlockStateProperties.FACING

        // voxel shapes
        val Y_SHAPE: VoxelShape = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0)
        val Z_SHAPE: VoxelShape = Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0)
        val X_SHAPE: VoxelShape = Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0)

        const val POWDERED_TIME = 8
    }

    init {
        this.registerDefaultState(
            this.defaultBlockState()
                .setValue(WATERLOGGED, false)
                .setValue(POWDERED, false)
                .setValue(AXIS, Direction.Axis.X)
                .setValue(FACING, Direction.UP)
        )
    }

    override fun <T : BlockEntity> getListener(serverLevel: ServerLevel, blockEntity: T): GameEventListener? {
        if (blockEntity is EchoLocatorBlockEntity) {
            return blockEntity.vibrationListener
        } else {
            return null
        }
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        val blockEntity = EchoLocatorBlockEntity(pos, state)
        blockEntity.type = this.type
        return blockEntity
    }

    override fun <T : BlockEntity> getTicker(
        level: Level,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return createTickerHelper(
            type,
            MoreGeodesBlockEntityTypes.ECHO_LOCATOR,
        ) { tickLevel, pos, tickState, entity ->
                if (!tickLevel.isClientSide && entity is EchoLocatorBlockEntity) {
                    entity.tick(tickLevel, pos, tickState)
                }
        }
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    override fun getStateForPlacement(placeContext: BlockPlaceContext): BlockState? {
        val fluidState = placeContext.level.getFluidState(placeContext.clickedPos)
        val isWater = fluidState.fluidType == Fluids.WATER
        var playerAxis = placeContext.horizontalDirection.axis

        var state = this.defaultBlockState()
            .setValue(FACING, placeContext.clickedFace)
            .setValue(WATERLOGGED, isWater)

        if (playerAxis.isHorizontal) {
            playerAxis = if (playerAxis === Direction.Axis.X) Direction.Axis.Z else Direction.Axis.X
            state = state.setValue(AXIS, playerAxis)
        }

        return state
    }

    override fun updateShape(
        state: BlockState,
        dirFromNeighbour: Direction,
        neighbourState: BlockState,
        level: LevelAccessor,
        pos: BlockPos,
        neighbourPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }

        return super.updateShape(state, dirFromNeighbour, neighbourState, level, pos, neighbourPos)
    }

    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): InteractionResult {
        this.powerLocator(level, state, pos)

        if (this.ping(level, pos, state, hitResult, player)) {
            return InteractionResult.sidedSuccess(level.isClientSide)
        }

        return InteractionResult.PASS
    }

    fun powerLocator(level: Level, state: BlockState, pos: BlockPos) {
        level.setBlockAndUpdate(pos, state.setValue(POWDERED, true))
        this.updateNeighbors(level, pos)
        level.scheduleTick(pos, this, POWDERED_TIME)
    }

    override fun tick(
        state: BlockState,
        serverLevel: ServerLevel,
        pos: BlockPos,
        random: RandomSource
    ) {
        serverLevel.setBlockAndUpdate(pos, state.setValue(POWDERED, false))
        this.updateNeighbors(serverLevel, pos)
    }

    override fun getSignal(
        state: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        direction: Direction
    ): Int {
        return if (state.getValue(POWDERED)) 15 else 0
    }

    override fun getDirectSignal(
        state: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        direction: Direction
    ): Int {
        return if (state.getValue(POWDERED)) 15 else 0
    }

    override fun getShape(
        state: BlockState,
        blockView: BlockGetter,
        pos: BlockPos,
        collisionContext: CollisionContext
    ): VoxelShape {
        return when (state.getValue(FACING).axis) {
            Direction.Axis.X -> X_SHAPE
            Direction.Axis.Y -> Y_SHAPE
            Direction.Axis.Z -> Z_SHAPE
            null -> Y_SHAPE
        }
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)))
    }

    @Suppress("DEPRECATION")
    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, POWDERED, AXIS, FACING)
    }

    private fun updateNeighbors(level: Level, pos: BlockPos) {
        level.updateNeighborsAt(pos.below(), this)
    }

    private fun ping(
        level: Level,
        origin: BlockPos,
        state: BlockState,
        hitResult: BlockHitResult,
        player: Player
    ): Boolean {
        level.playSound(null, origin, type.activateSound, SoundSource.BLOCKS, 2.0f, 1.0f)

        val blockEntity: BlockEntity? = level.getBlockEntity(origin)

        if (blockEntity != null && !state.getValue(POWDERED) && blockEntity is EchoLocatorBlockEntity) {
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, origin)
            blockEntity.activate(level, origin)
            return true
        }

        return false
    }
}