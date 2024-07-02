package com.github.thedeathlycow.moregeodes.forge.config

import com.github.thedeathlycow.moregeodes.forge.MoreGeodesForge
import net.minecraftforge.fml.loading.FMLPaths
import java.io.*
import java.util.*

class MoreGeodesConfig {

    companion object {
        private const val EMERALD_GEODES_KEY = "generate_emerald_geodes"
        private const val QUARTZ_GEODES_KEY = "generate_quartz_geodes"
        private const val DIAMOND_GEODES_KEY = "generate_diamond_geodes"
        private const val ECHO_GEODES_KEY = "generate_echo_geodes"
        private const val LAPIS_GEODES_KEY = "generate_lapis_geodes"
        private const val GYPSUM_PATCHES_KEY = "generate_gypsum_patches"
        private const val CERTUS_GEODES_KEY = "generate_certus_geodes"
        private const val BISMUTH_GEODES_KEY = "generate_bismuth_geodes"
    }

    private val defaultProperties: Properties = Properties()

    private var properties: Properties = Properties(defaultProperties)


    fun loadConfig() {
        this.initDefaultConfig()

        this.properties = Properties(this.defaultProperties)

        val configFile = this.getConfigFile()

        try {
            FileReader(configFile).use { reader ->
                this.properties.load(reader)
            }
        } catch (e: FileNotFoundException) {
            this.generateDefaultConfig(configFile)
        } catch (e: IOException) {
            MoreGeodesForge.LOGGER.error("Could not load More Geodes config, using default config instead. Failed with exception: $e")
            return
        }
        MoreGeodesForge.LOGGER.info("Loaded More Geodes config!")
    }

    fun checkConfig(path: String): Boolean {
        val rawValue: String? = properties.getProperty(path)

        if (rawValue === null) {
            return false
        }

        return java.lang.Boolean.parseBoolean(rawValue)
    }

    private fun generateDefaultConfig(configFile: File) {
        try {
            FileWriter(configFile)
                .use { writer ->
                    this.defaultProperties.store(
                        writer, "Default config file for More Geodes." +
                                "See the readme on GitHub for more information: https://github.com/TheDeathlyCow/more-geodes/blob/main/README.md " +
                                "Should match the Fabric version"
                    )
                }
        } catch (e: IOException) {
            MoreGeodesForge.LOGGER.error("Error creating default More Geodes config: $e")
            return
        }

        MoreGeodesForge.LOGGER.info("Generated default config for More Geodes")

    }


    private fun initDefaultConfig() {
        defaultProperties.setProperty(EMERALD_GEODES_KEY, "true")
        defaultProperties.setProperty(QUARTZ_GEODES_KEY, "true")
        defaultProperties.setProperty(DIAMOND_GEODES_KEY, "true")
        defaultProperties.setProperty(ECHO_GEODES_KEY, "true")
        defaultProperties.setProperty(LAPIS_GEODES_KEY, "true")
        defaultProperties.setProperty(GYPSUM_PATCHES_KEY, "true")
        defaultProperties.setProperty(CERTUS_GEODES_KEY, "true")
        defaultProperties.setProperty(BISMUTH_GEODES_KEY, "true")
    }

    private fun getConfigFile(): File {
        val path = FMLPaths.CONFIGDIR
            .get()
            .resolve("geodes.properties")

        val file = path.toFile()
        assert(file.isFile)
        return file
    }

}