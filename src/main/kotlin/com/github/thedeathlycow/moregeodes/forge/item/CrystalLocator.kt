package com.github.thedeathlycow.moregeodes.forge.item

import com.github.thedeathlycow.moregeodes.forge.entity.EchoDisplay
import com.github.thedeathlycow.moregeodes.forge.entity.MoreGeodesEntityTypes
import com.github.thedeathlycow.moregeodes.forge.sound.MoreGeodesSoundEvents
import com.github.thedeathlycow.moregeodes.forge.tuning.Tuning
import com.github.thedeathlycow.moregeodes.forge.util.minus
import com.github.thedeathlycow.moregeodes.forge.util.plus
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import kotlin.math.sqrt

open class CrystalLocator(
    properties: Properties,
    private val range: Int = CRYSTAL_LOCATOR_RANGE,
    protected val coolDown: Int = CRYSTAL_LOCATOR_COOL_DOWN
) : Item(properties) {

    companion object {
        const val CRYSTAL_LOCATOR_RANGE = 48
        const val CRYSTAL_LOCATOR_COOL_DOWN = 20
    }

    open fun isTuned(stack: ItemStack, level: Level?) = false

    protected open fun isPingableCrystal(
        locatorStack: ItemStack,
        serverLevel: ServerLevel,
        pos: BlockPos
    ) = false

    protected open fun applyCooldown(player: Player) {
        val cooldowns = player.cooldowns
        cooldowns.addCooldown(MoreGeodesItems.CRYSTAL_LOCATOR, coolDown)
        cooldowns.addCooldown(MoreGeodesItems.TUNED_CRYSTAL_LOCATOR, coolDown)
    }

    override fun use(
        level: Level,
        player: Player,
        hand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(hand)

        if (!level.isClientSide) {
            val blocksPinged: Int = activate(level as ServerLevel, player.blockPosition(), stack)

            if (blocksPinged > 0) {
                stack.hurtAndBreak(1, player) {
                    it.broadcastBreakEvent(hand)
                }
            }

            this.applyCooldown(player)
            player.playNotifySound(
                MoreGeodesSoundEvents.ITEM_CRYSTAL_LOCATOR_USE,
                player.soundSource,
                1f, 1f
            )
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide)
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: Level?,
        pTooltipComponents: MutableList<Component>,
        pIsAdvanced: TooltipFlag
    ) {
        if (!this.isTuned(pStack, pLevel)) {
            pTooltipComponents.add(Tuning.UNTUNED.description)
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced)
    }

    private fun activate(
        serverLevel: ServerLevel,
        origin: BlockPos,
        stack: ItemStack
    ): Int {
        val scanVector = BlockPos(this.range, this.range, this.range)

        val from = origin - scanVector
        val to = origin + scanVector

        val rangeSquared = this.range * this.range;
        var blocksPinged = 0

        for (pos: BlockPos in BlockPos.betweenClosed(from, to)) {
            val distanceToPosSquared = origin.distSqr(pos)
            if (distanceToPosSquared < rangeSquared) {
                blocksPinged += this.tryPing(serverLevel, pos, stack, distanceToPosSquared);
            }
        }

        return blocksPinged
    }

    private fun tryPing(
        serverLevel: ServerLevel,
        pos: BlockPos,
        stack: ItemStack,
        distanceToPosSquared: Double
    ): Int {
        if (isPingableCrystal(stack, serverLevel, pos)) {
            val delay = Mth.ceil(sqrt(distanceToPosSquared))
            highlightCrystal(serverLevel, pos, serverLevel.getBlockState(pos), delay)
            return 1
        }
        return 0
    }

    private fun highlightCrystal(
        level: ServerLevel,
        pos: BlockPos,
        state: BlockState,
        delay: Int
    ) {
        val blockDisplay: EchoDisplay? = MoreGeodesEntityTypes.ECHO_DISPLAY.create(level)

        blockDisplay?.let {
            it.setBlockState(state)
            it.setPos(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
            it.setGlowDelay(delay)

            level.addFreshEntity(it)
        }
    }

}