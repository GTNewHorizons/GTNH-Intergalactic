package com.gtnewhorizons.gtnhintergalactic.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.github.bartimaeusnek.bartworks.common.configs.ConfigHandler;

import galaxyspace.core.config.GSConfigDimensions;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.asteroids.ConfigManagerAsteroids;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;

/**
 * Config file of this mod
 *
 * @author minecraft7771
 */
public class Config {

    /** Category for Space Elevator settigns */
    public static String CATEGORY_SPACE_ELEVATOR = "spaceElevator";

    // Space Elevator
    /** Whether to render the cable was block or with renderer */
    public static boolean isCableRenderingEnabled = true;

    // Dyson Swarm
    public static double destroyModule_a;
    public static double destroyModule_b;
    public static double destroyModuleBase_chance;
    public static int maxModules;
    public static double destroyModuleMaxCPS;
    public static int euPerModule;
    public static String[] powerFactors;
    public static double powerFactorDefault;
    public static int coolantConsumption;
    public static FluidStack coolantFluid;

    /**
     * Read the configuration file
     *
     * @param configFile File to be read
     */
    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        Property prop;

        isCableRenderingEnabled = configuration.getBoolean(
                "isCableRenderingEnabled",
                CATEGORY_SPACE_ELEVATOR,
                isCableRenderingEnabled,
                "If the Space Elevator should use it's fancy renderer, or simple block renderer");

        prop = configuration.get("dysonSwarm", "destroyModule_a", 0.00005).setRequiresMcRestart(true);
        prop.comment = "Each hour, n of m modules are destroyed according to this formula: n = m * (2 * base_chance) / (exp(-a * (m - 1))+exp(b * cps)), where cps is computation per second. This sets the parameter a.";
        prop.setMinValue(0.0).setMaxValue(1.0);
        destroyModule_a = prop.getDouble();

        prop = configuration.get("dysonSwarm", "destroyModule_b", 0.00003).setRequiresMcRestart(true);
        prop.comment = "Each hour, n of m modules are destroyed according to this formula: n = m * (2 * base_chance) / (exp(-a * (m - 1))+exp(b * cps)), where cps is computation per second. This sets the parameter b.";
        prop.setMinValue(0.0).setMaxValue(1.0);
        destroyModule_b = prop.getDouble();

        prop = configuration.get("dysonSwarm", "destroyModuleBase_chance", 0.066).setRequiresMcRestart(true);
        prop.comment = "Each hour, n of m modules are destroyed according to this formula: n = m * (2 * base_chance) / (exp(-a * (m - 1))+exp(b * cps)), where cps is computation per second. This sets the parameter base_chance.";
        prop.setMinValue(0.0).setMaxValue(1.0);
        destroyModuleBase_chance = prop.getDouble();

        prop = configuration.get("dysonSwarm", "maxModules", 10000).setRequiresMcRestart(true);
        prop.comment = "The maximum number of modules the dyson swarm can take";
        prop.setMinValue(1).setMaxValue(Integer.MAX_VALUE);
        maxModules = prop.getInt();

        prop = configuration.get("dysonSwarm", "destroyModuleMaxCPS", 100000.0).setRequiresMcRestart(true);
        prop.comment = "The maximum computation per second that will help prevent modules from collision";
        prop.setMinValue(0.0).setMaxValue(Double.MAX_VALUE);
        destroyModuleMaxCPS = prop.getDouble();

        prop = configuration.get("dysonSwarm", "euPerModule", 10000000);
        prop.comment = "How much EU the Dyson Swarm Command Center produces per module per tick. Default is 10,000,000 EU/t";
        prop.setMinValue(1).setMaxValue(Integer.MAX_VALUE);
        euPerModule = prop.getInt();

        prop = configuration.get(
                "dysonSwarm",
                "powerFactors",
                new String[] { "0:1.0", // Overworld
                        GSConfigDimensions.dimensionIDMakemake + ":0.15", // Makemake
                        ConfigManagerCore.idDimensionMoon + ":1.0", // Moon
                        ConfigManagerMars.dimensionIDMars + ":0.81", // Mars
                        ConfigManagerAsteroids.dimensionIDAsteroids + ":0.61", // Asteroids
                        GSConfigDimensions.dimensionIDCentauriBb + ":2.28", // A Centauri Bb
                        GSConfigDimensions.dimensionIDBarnardaC + ":2.31", // Barnarda C
                        GSConfigDimensions.dimensionIDKuiperBelt + ":0.16", // Kuiper Belt
                        GSConfigDimensions.dimensionIDEuropa + ":0.44", // Europa
                        GSConfigDimensions.dimensionIDIo + ":0.44", // Io
                        GSConfigDimensions.dimensionIDMercury + ":1.61", // Mercury
                        GSConfigDimensions.dimensionIDPhobos + ":0.81", // Phobos
                        GSConfigDimensions.dimensionIDVenus + ":1.76", // Venus
                        GSConfigDimensions.dimensionIDDeimos + ":0.81", // Deimos
                        GSConfigDimensions.dimensionIDEnceladus + ":0.32", // Enceladus
                        GSConfigDimensions.dimensionIDCeres + ":0.6", // Ceres
                        GSConfigDimensions.dimensionIDGanymede + ":0.44", // Ganymede
                        GSConfigDimensions.dimensionIDTitan + ":0.32", // Titan
                        GSConfigDimensions.dimensionIDCallisto + ":0.32", // Callisto
                        GSConfigDimensions.dimensionIDOberon + ":0.23", // Oberon
                        GSConfigDimensions.dimensionIDProteus + ":0.23", // Proteus
                        GSConfigDimensions.dimensionIDTriton + ":0.18", // Triton
                        GSConfigDimensions.dimensionIDPluto + ":0.16", // Pluto
                        ConfigHandler.ross128BAID + ":1.12", // Ross128ba
                        ConfigHandler.ross128BID + ":1.12", // Ross128b
                        GSConfigDimensions.dimensionIDBarnardaE + ":1.41", // Barnarda E
                        GSConfigDimensions.dimensionIDBarnardaF + ":1.26", // Barnarda F
                        GSConfigDimensions.dimensionIDHaumea + ":0.15", // Haumea
                        GSConfigDimensions.dimensionIDVegaB + ":1.98", // Vega B
                        GSConfigDimensions.dimensionIDTCetiE + ":1.34", // T Ceti E
                        GSConfigDimensions.dimensionIDMiranda + ":0.23", // Miranda
                        "SS_Overworld:1.1", // Earth Space Station
                        "SS_planet.mars:0.89", // Mars Space Station
                        "SS_planet.venus:1.94", // Venus Space Station
                        "SS_planet.jupiter:0.48", // Jupiter Space Station
                        "SS_planet.saturn:0.36", // Saturn Space Station
                        "SS_planet.uranus:0.25", // Uranus Space Station
                        "SS_planet.neptune:0.2", // Neptune Space Station
                        "UW_Garden:0.01", // Garden World
                        "UW_Mining:0.01", // Mining World
                        "UW_Void:0.01" // Void World
                });
        prop.setRequiresMcRestart(true);
        prop.comment = "Define a power factor for each dimension ID The total energy output of Dyson Swarm multiblocks is multiplied by these values. Format is \"DIMID:FACTOR\" (DIMID for Space Stations is \"SS_unlocalizedNameOfBodyToOrbit\", for Utility Worlds dimensions \"UW_Garden\", \"UW_Mining\" and \"UW_Void\")";
        powerFactors = prop.getStringList();

        prop = configuration.get("dysonSwarm", "powerFactorDefault", 0.0);
        prop.comment = "If a power factor for a dimensions is not set, this value will be used.";
        prop.setMinValue(0.0);
        powerFactorDefault = prop.getDouble();

        prop = configuration.get("dysonSwarm", "coolantConsumption", 3600000);
        prop.comment = "How much coolant is consumed per hour.";
        prop.setMinValue(0);
        coolantConsumption = prop.getInt();

        prop = configuration.get("dysonSwarm", "coolantFluid", "cryotheum");
        prop.comment = "Name of the coolant";
        Fluid cryotheum = FluidRegistry.getFluid(prop.getString()) != null ? FluidRegistry.getFluid(prop.getString()) // GTPP
                : FluidRegistry.getFluid("ic2coolant");
        coolantFluid = new FluidStack(cryotheum, coolantConsumption);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
