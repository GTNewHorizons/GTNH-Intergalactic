package com.gtnewhorizons.gtnhintergalactic.config;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.gtnhlib.config.Config;
import com.gtnewhorizons.gtnhintergalactic.GTNHIntergalactic;

import gregtech.api.enums.Mods;

@Config(modid = Mods.Names.G_T_N_H_INTERGALACTIC, filename = "gtnhintergalactic")
public class IGConfig {

    public static SpaceElevator spaceElevator = new SpaceElevator();
    public static DysonSwarm dysonSwarm = new DysonSwarm();

    @Config.Comment("Space Elevator section")
    public static class SpaceElevator {

        @Config.Comment("If true, the Space Elevator will use it's fancy renderer, otherwise a simple block renderer")
        @Config.DefaultBoolean(true)
        public boolean isCableRenderingEnabled;
    }

    @Config.Comment("Dyson Swarm section")
    public static class DysonSwarm {

        @Config.Comment({ "Each hour, n of m modules are destroyed according to this formula:",
                " n = m * (2 * base_chance) / (exp(-a * (m - 1))+exp(b * cps)), where cps is computation per second.",
                "This sets the parameter a." })
        @Config.DefaultDouble(0.00005)
        @Config.RangeDouble(min = 0, max = 1)
        @Config.RequiresMcRestart
        public double destroyModuleA;
        @Config.Comment({ "Each hour, n of m modules are destroyed according to this formula:",
                " n = m * (2 * base_chance) / (exp(-a * (m - 1))+exp(b * cps)), where cps is computation per second.",
                "This sets the parameter b." })
        @Config.DefaultDouble(0.00003)
        @Config.RangeDouble(min = 0, max = 1)
        @Config.RequiresMcRestart
        public double destroyModuleB;
        @Config.Comment({ "Each hour, n of m modules are destroyed according to this formula:",
                " n = m * (2 * base_chance) / (exp(-a * (m - 1))+exp(b * cps)), where cps is computation per second.",
                "This sets the parameter base_chance." })
        @Config.DefaultDouble(0.066)
        @Config.RangeDouble(min = 0, max = 1)
        @Config.RequiresMcRestart
        public double destroyModuleChance;
        @Config.Comment("The maximum number of modules the dyson swarm can take")
        @Config.DefaultInt(10000)
        @Config.RangeInt(min = 1)
        @Config.RequiresMcRestart
        public int maxModules;
        @Config.Comment("The maximum computation per second that will help prevent modules from collision")
        @Config.DefaultDouble(100000.0)
        @Config.RangeDouble(min = 0)
        @Config.RequiresMcRestart
        public double destroyModuleMaxCPS;
        @Config.Comment("How much EU the Dyson Swarm Command Center produces per module per tick")
        @Config.DefaultInt(10000000)
        @Config.RangeInt(min = 1)
        @Config.RequiresMcRestart
        public int euPerModule;
        @Config.Comment({ "Define a power factor for each dimension ID.",
                "The total energy output of Dyson Swarm multiblocks is multiplied by these values.",
                "Format is \"DIMID:FACTOR\"", "DIMIDs for Space Stations are \"SS_unlocalizedNameOfBodyToOrbit\"",
                "DIMIDs for Utility Worlds dimensions are \"UW_Garden\", \"UW_Mining\" and \"UW_Void\"" })
        @Config.DefaultStringList({ "0:1.0", // Overworld
                "-1011:0.15", // Makemake
                "-28:1.0", // Moon
                "-29:0.81", // Mars
                "-30:0.61", // Asteroids
                "-1021:2.28", // A Centauri Bb
                "-1022:2.31", // Barnarda C
                "-1009:0.16", // Kuiper Belt
                "-1014:0.44", // Europa
                "-1013:0.44", // Io
                "-1005:1.61", // Mercury
                "-1011:0.81", // Phobos
                "-1006:1.76", // Venus
                "-1012:0.81", // Deimos
                "-1016:0.32", // Enceladus
                "-1007:0.6", // Ceres
                "-1015:0.44", // Ganymede
                "-1017:0.32", // Titan
                "-1024:0.32", // Callisto
                "-1018:0.23", // Oberon
                "-1019:0.23", // Proteus
                "-1020:0.18", // Triton
                "-1008:0.16", // Pluto
                "63:1.12", // Ross128ba
                "64:1.12", // Ross128b
                "-1023:1.41", // Barnarda E
                "-1026:1.26", // Barnarda F
                "-1025:0.15", // Haumea
                "-1027:1.98", // Vega B
                "-1028:1.34", // T Ceti E
                "-1029:0.23", // Miranda
                "SS_Overworld:1.1", // Earth Space Station
                "SS_planet.mars:0.89", // Mars Space Station
                "SS_planet.venus:1.94", // Venus Space Station
                "SS_planet.jupiter:0.48", // Jupiter Space Station
                "SS_planet.saturn:0.36", // Saturn Space Station
                "SS_planet.uranus:0.25", // Uranus Space Station
                "SS_planet.neptune:0.2", // Neptune Space Station
                "UW_Garden:0.01", // Garden World
                "UW_Mining:0.01", // Mining World
                "UW_Void:0.01", // Void World
        })
        @Config.RequiresMcRestart
        public String[] powerFactors;
        @Config.Comment("If a power factor for a dimension is not set, this value will be used")
        @Config.RangeDouble(min = 0.0)
        @Config.RequiresMcRestart
        public double powerFactorDefault;
        @Config.Comment("How much coolant is consumed per hour")
        @Config.DefaultInt(3600000)
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int coolantConsumption;
        @Config.Comment({ "Name of the coolant to use", "Will fallback to IC2 Coolant if this name is invalid" })
        @Config.DefaultString("cryotheum")
        @Config.RequiresMcRestart
        public String coolantFluid;
        @Config.Ignore
        private Fluid cachedCoolantFluid;

        public FluidStack getCoolantStack() {
            if (cachedCoolantFluid == null) {
                cachedCoolantFluid = FluidRegistry.getFluid(coolantFluid);
                if (cachedCoolantFluid == null) {
                    cachedCoolantFluid = FluidRegistry.getFluid("ic2coolant"); // fallback
                }
            }
            return new FluidStack(cachedCoolantFluid, coolantConsumption);
        }

        @Config.Ignore
        private Map<String, Double> cachedPowerFactors;

        public double getPowerFactor(String dimID) {
            if (cachedPowerFactors == null) {
                cachedPowerFactors = new HashMap<>();
                for (String s : powerFactors) {
                    String[] parts = s.split(":");
                    try {
                        cachedPowerFactors.put(parts[0], Double.parseDouble(parts[1]));
                    } catch (Exception e) {
                        GTNHIntergalactic.LOG.error("Error while trying to parse \"" + s + "\"!");
                        e.printStackTrace();
                    }
                }
            }
            Double value = cachedPowerFactors.get(dimID);
            return value != null ? value : powerFactorDefault;
        }
    }
}
