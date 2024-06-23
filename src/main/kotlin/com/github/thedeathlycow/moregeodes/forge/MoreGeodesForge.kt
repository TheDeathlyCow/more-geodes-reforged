package com.github.thedeathlycow.moregeodes.forge

import com.github.thedeathlycow.moregeodes.forge.block.MoreGeodesBlocks
import com.github.thedeathlycow.moregeodes.forge.block.entity.MoreGeodesBlockEntityTypes
import com.github.thedeathlycow.moregeodes.forge.client.EchoDisplayRenderer
import com.github.thedeathlycow.moregeodes.forge.config.MoreGeodesConfig
import com.github.thedeathlycow.moregeodes.forge.entity.MoreGeodesEntityTypes
import com.github.thedeathlycow.moregeodes.forge.entity.MoreGeodesMemoryModules
import com.github.thedeathlycow.moregeodes.forge.item.MoreGeodesCreativeTabs
import com.github.thedeathlycow.moregeodes.forge.item.MoreGeodesItems
import com.github.thedeathlycow.moregeodes.forge.sound.EchoGeodeBlockSoundEvents
import com.github.thedeathlycow.moregeodes.forge.sound.MoreGeodesSoundEvents
import com.github.thedeathlycow.moregeodes.forge.tuning.Tunings
import com.github.thedeathlycow.moregeodes.forge.world.event.MoreGeodesGameEvents
import com.github.thedeathlycow.moregeodes.forge.world.modifications.MoreGeodesBiomeModifiers
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.ComposterBlock
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(MoreGeodesForge.MODID)
object MoreGeodesForge {

    const val MODID = "geodes"

    val LOGGER: Logger = LogManager.getLogger(MODID)

    val CONFIG: MoreGeodesConfig = MoreGeodesConfig()

    fun resourceLocation(path: String): ResourceLocation = ResourceLocation(MODID, path)

    init {
        MoreGeodesSoundEvents.REGISTRY.register(MOD_BUS)
        EchoGeodeBlockSoundEvents.REGISTRY.register(MOD_BUS)
        MoreGeodesBlocks.REGISTRY.register(MOD_BUS)
        MoreGeodesItems.REGISTRY.register(MOD_BUS)
        MoreGeodesGameEvents.REGISTRY.register(MOD_BUS)
        MoreGeodesBlockEntityTypes.REGISTRY.register(MOD_BUS)
        MoreGeodesMemoryModules.REGISTRY.register(MOD_BUS)
        MoreGeodesBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(MOD_BUS)
        MoreGeodesCreativeTabs.REGISTRY.register(MOD_BUS)
        MoreGeodesEntityTypes.REGISTRY.register(MOD_BUS)


        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
            }
        )

        MOD_BUS.addListener(::onCommonSetup)
        MOD_BUS.register(Tunings)
    }

    private fun onCommonSetup(event: FMLCommonSetupEvent) {
        LOGGER.info("Setting up More Geodes Reforged")
        CONFIG.loadConfig()

        ComposterBlock.COMPOSTABLES.put(MoreGeodesItems.GYPSUM_SHARD, 1.0f)
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.info("Setting up More Geodes Reforged Client")
        EntityRenderers.register(MoreGeodesEntityTypes.ECHO_DISPLAY) {
            EchoDisplayRenderer(it)
        }
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.info("Setting up More Geodes Reforged Server")
    }

}