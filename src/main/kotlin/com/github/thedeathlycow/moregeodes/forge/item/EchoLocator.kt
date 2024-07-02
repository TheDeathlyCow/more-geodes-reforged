package com.github.thedeathlycow.moregeodes.forge.item

import com.github.thedeathlycow.moregeodes.forge.block.tag.MoreGeodesBlockTags
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class EchoLocator(
    properties: Properties,
    range: Int = BLOCK_RANGE,
    coolDown: Int = ECHO_LOCATOR_COOL_DOWN
) : CrystalLocator(properties, range, coolDown) {

    companion object {
        const val BLOCK_RANGE: Int = 8
        const val ECHO_LOCATOR_COOL_DOWN: Int = CRYSTAL_LOCATOR_COOL_DOWN * 4
    }

    override fun isPingableCrystal(locatorStack: ItemStack, serverLevel: ServerLevel, pos: BlockPos): Boolean {
        return serverLevel.getBlockState(pos).`is`(MoreGeodesBlockTags.ECHO_LOCATABLE)
    }

    override fun isTuned(stack: ItemStack, level: Level?): Boolean = true

    override fun applyCooldown(player: Player) {
        player.cooldowns.addCooldown(this, coolDown)
    }
}