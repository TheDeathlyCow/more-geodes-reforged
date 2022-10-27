package com.github.thedeathlycow.moregeodes.forge.block

import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty

class CrystalClusterBlock(
    soundGroup: CrystalBlockSoundGroup,
    properties: Properties
) : CrystalBlock(soundGroup, properties), SimpleWaterloggedBlock {

    companion object {
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val FACING: DirectionProperty = BlockStateProperties.FACING
    }


}