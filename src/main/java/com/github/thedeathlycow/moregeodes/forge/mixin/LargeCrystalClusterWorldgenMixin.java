package com.github.thedeathlycow.moregeodes.forge.mixin;

import com.github.thedeathlycow.moregeodes.forge.block.CrystalBlock;
import com.github.thedeathlycow.moregeodes.forge.block.CrystalClusterBlock;
import com.github.thedeathlycow.moregeodes.forge.block.LargeCrystalClusterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SimpleBlockFeature.class)
public class LargeCrystalClusterWorldgenMixin {

    @Inject(
            method = "place",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/WorldGenLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    private void placeLargeCrystalsProperly(FeaturePlaceContext<SimpleBlockConfiguration> ctx, CallbackInfoReturnable<Boolean> cir, SimpleBlockConfiguration simpleblockconfiguration, WorldGenLevel worldgenlevel, BlockPos blockpos, BlockState blockstate) {
        Block block = blockstate.getBlock();
        if (block instanceof LargeCrystalClusterBlock) {
            Direction facing = blockstate.getValue(CrystalClusterBlock.Companion.getFACING());
            if (!worldgenlevel.getBlockState(blockpos.relative(facing)).isAir()) {
                cir.setReturnValue(false);
                return;
            }

            LargeCrystalClusterBlock.Companion.placeAt(worldgenlevel, blockstate, blockpos, Block.UPDATE_CLIENTS);

            cir.setReturnValue(true);
        }
    }

}
