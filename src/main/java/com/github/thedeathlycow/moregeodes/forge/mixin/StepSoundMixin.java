package com.github.thedeathlycow.moregeodes.forge.mixin;

import com.github.thedeathlycow.moregeodes.forge.block.CrystalBlock;
import com.github.thedeathlycow.moregeodes.forge.block.tag.MoreGeodesBlockTags;
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
    private void customGeodeEntityStepSound(BlockState state, CallbackInfo ci) {
        if (state.is(MoreGeodesBlockTags.CUSTOM_CRYSTAL_SOUND_BLOCKS) && this.tickCount >= this.lastCrystalSoundPlayTick + 20) {
            if (state.getBlock() instanceof CrystalBlock crystalBlock) {
                this.crystalSoundIntensity *= (float)Math.pow(0.997D, this.tickCount - this.lastCrystalSoundPlayTick);
                this.crystalSoundIntensity = Math.min(1.0F, this.crystalSoundIntensity + 0.07F);

                float volume = 0.1F + this.crystalSoundIntensity * 1.2F;
                float pitch = 0.5F + this.crystalSoundIntensity * this.random.nextFloat() * 1.2F;

                this.playSound(crystalBlock.getSoundGroup().getChimeSound(), volume, pitch);
                this.lastCrystalSoundPlayTick = this.tickCount;
            }
        }
    }
}
