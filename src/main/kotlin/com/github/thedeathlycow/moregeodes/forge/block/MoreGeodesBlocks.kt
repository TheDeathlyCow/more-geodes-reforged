package com.github.thedeathlycow.moregeodes.forge.block

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import com.github.thedeathlycow.moregeodes.forge.sound.CrystalBlockSoundGroup
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.material.MaterialColor
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object MoreGeodesBlocks {

    val REGISTRY: DeferredRegister<Block> = DeferredRegister.create(
        ForgeRegistries.BLOCKS,
        MoreGeodesForge.MODID
    )

    val EMERALD_CRYSTAL_BLOCK by REGISTRY.registerObject("emerald_crystal_block") {
        CrystalBlock(
            CrystalBlockSoundGroup.EMERALD_SOUND_GROUP,
            BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_PURPLE)
                .strength(1.5f)
                .sound(SoundType.AMETHYST)
                .requiresCorrectToolForDrops()
        )
    }
}