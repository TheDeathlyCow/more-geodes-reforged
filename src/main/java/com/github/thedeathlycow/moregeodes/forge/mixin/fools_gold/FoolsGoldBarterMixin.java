package com.github.thedeathlycow.moregeodes.forge.mixin.fools_gold;

import com.github.thedeathlycow.moregeodes.forge.entity.MoreGeodesMemoryModules;
import com.github.thedeathlycow.moregeodes.forge.item.tag.MoreGeodesItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(PiglinAi.class)
public abstract class FoolsGoldBarterMixin {

    @Shadow
    protected static void setAngerTarget(AbstractPiglin piglin, LivingEntity target) {
    }

    @Shadow
    protected static void broadcastAngerTarget(AbstractPiglin piglin, LivingEntity target) {
    }

    @Inject(
            method = "pickUpItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/piglin/PiglinAi;admireGoldItem(Lnet/minecraft/world/entity/LivingEntity;)V",
                    shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    private static void angerWhenRemembersFoolsGold(Piglin piglin, ItemEntity drop, CallbackInfo ci, ItemStack itemStack) {
        MemoryModuleType<Boolean> foolsGoldMemory = MoreGeodesMemoryModules.INSTANCE.getREMEMBERS_FOOLS_GOLD();
        Brain<Piglin> brain = piglin.getBrain();
        Optional<Boolean> memory = brain.getMemory(foolsGoldMemory);

        boolean remembersFoolsGold = brain.hasMemoryValue(foolsGoldMemory)
                && memory.isPresent()
                && memory.get();

        if (remembersFoolsGold && itemStack.is(MoreGeodesItemTags.INSTANCE.getFOOLS_FOLD())) {
            brain.eraseMemory(MemoryModuleType.ADMIRING_ITEM);
            angerAtNearbyPlayers(piglin);
        }
    }

    @Inject(
            method = "stopHoldingOffHandItem",
            at = @At("HEAD")
    )
    private static void angerPiglinWhenStoppedAdmiringFoolsGold(Piglin piglin, boolean isAdmiring, CallbackInfo ci) {
        if (!isAdmiring) {
            return;
        }

        ItemStack holding = piglin.getItemInHand(InteractionHand.OFF_HAND);

        if (holding.is(MoreGeodesItemTags.INSTANCE.getFOOLS_FOLD())) {
            angerAtNearbyPlayers(piglin);
            piglin.getBrain().setMemory(MoreGeodesMemoryModules.INSTANCE.getREMEMBERS_FOOLS_GOLD(), true);
        }
    }

    private static void angerAtNearbyPlayers(Piglin piglin) {
        Brain<Piglin> brain = piglin.getBrain();
        Optional<Player> rememberedPlayer = brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);

        rememberedPlayer.ifPresent(player -> {
            setAngerTarget(piglin, player);
            broadcastAngerTarget(piglin, player);
        });
    }

}
