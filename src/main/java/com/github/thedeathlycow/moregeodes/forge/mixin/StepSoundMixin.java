package com.github.thedeathlycow.moregeodes.forge.mixin;

import com.github.thedeathlycow.moregeodes.forge.block.CrystalBlock;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class StepSoundMixin {

    @Shadow
    private float crystalSoundIntensity;

    @Shadow
    private int lastCrystalSoundPlayTick;

    @Shadow
    public int tickCount;

    @Shadow @Final
    protected RandomSource random;

    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Inject(
            method = "playAmethystStepSound",
            at = @At("HEAD")
    )
    private void entityStepSoundMixin(BlockState state, CallbackInfo ci) {
        var block = state.getBlock();
        if (block instanceof CrystalBlock crystalBlock && this.tickCount >= this.lastCrystalSoundPlayTick + 20) {
            this.crystalSoundIntensity *= (float)Math.pow(0.997D, this.tickCount - this.lastCrystalSoundPlayTick);
            this.crystalSoundIntensity = Math.min(1.0F, this.crystalSoundIntensity + 0.07F);
            float f = 0.5F + this.crystalSoundIntensity * this.random.nextFloat() * 1.2F;
            float f1 = 0.1F + this.crystalSoundIntensity * 1.2F;
            this.playSound(crystalBlock.getSoundGroup().getChimeSound(), f1, f);
            this.lastCrystalSoundPlayTick = this.tickCount;
        }
    }
}
