package com.github.thedeathlycow.moregeodes.forge.mixin.fools_gold;

import com.github.thedeathlycow.moregeodes.forge.entity.MoreGeodesMemoryModules;
import com.github.thedeathlycow.moregeodes.forge.item.tag.MoreGeodesItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
            method = "wantsToPickup",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void piglinsRememberFoolsGold(Piglin pPiglin, ItemStack pStack, CallbackInfoReturnable<Boolean> cir) {
        Brain<Piglin> brain = pPiglin.getBrain();
        Optional<Boolean> memory = brain.getMemory(MoreGeodesMemoryModules.INSTANCE.getREMEMBERS_FOOLS_GOLD());


        boolean remembersFoolsGold = brain.hasMemoryValue(MoreGeodesMemoryModules.INSTANCE.getREMEMBERS_FOOLS_GOLD())
                && memory != null // NOSONAR - why tf is there a nullable optional
                && memory.isPresent()
                && memory.get();

        if (remembersFoolsGold && pStack.is(MoreGeodesItemTags.INSTANCE.getFOOLS_FOLD())) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "stopHoldingOffHandItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void angerPiglinWhenStoppedAdmiringFoolsGold(Piglin piglin, boolean isAdmiring, CallbackInfo ci) {
        if (!isAdmiring) {
            return;
        }

        ItemStack stack = piglin.getItemInHand(InteractionHand.OFF_HAND);

        if (stack.is(MoreGeodesItemTags.INSTANCE.getFOOLS_FOLD())) {
            angerAtNearbyPlayers(piglin);
            piglin.getBrain().setMemory(MoreGeodesMemoryModules.INSTANCE.getREMEMBERS_FOOLS_GOLD(), true);
            stack.setCount(0);
            piglin.swing(InteractionHand.OFF_HAND);
            ci.cancel();
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
