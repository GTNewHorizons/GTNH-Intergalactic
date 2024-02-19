package galaxyspace.core.recipe;

import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import galaxyspace.core.config.GSConfigDimensions;
import galaxyspace.core.register.GSBlocks;
import gregtech.api.GregTech_API;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;

public class SpaceStationRecipes {

    public static void loadRecipes() {
        if (GSConfigDimensions.enableMarsSS) {
            HashMap<Object, Integer> inputMap = new HashMap<>();
            inputMap.put(new ItemStack(GSBlocks.MetalsBlock, 1, 9), 231); // Decorative Copper Block
            inputMap.put(new ItemStack(Blocks.glass_pane), 6);
            inputMap.put("circuitAdvanced", 4);
            inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 13), 1); // HV Machine Hull
            GalacticraftRegistry.registerSpaceStation(
                new SpaceStationType(
                    GSConfigDimensions.dimensionIDMarsOrbit,
                    ConfigManagerMars.dimensionIDMars,
                    new SpaceStationRecipe(inputMap)));
        }
        if (GSConfigDimensions.enableJupiterSS) {
            HashMap<Object, Integer> inputMap = new HashMap<>();
            inputMap.put(new ItemStack(GSBlocks.MetalsBlock, 1, 5), 231); // Decorative Nickel Block
            inputMap.put(new ItemStack(Blocks.glass_pane), 6);
            inputMap.put("circuitData", 4);
            inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 14), 1); // EV Machine Hull
            GalacticraftRegistry.registerSpaceStation(
                new SpaceStationType(
                    GSConfigDimensions.dimensionIDJupiterOrbit,
                    GSConfigDimensions.dimensionIDJupiter,
                    new SpaceStationRecipe(inputMap)));
        }
        if (GSConfigDimensions.enableVenusSS) {
            HashMap<Object, Integer> inputMap = new HashMap<>();
            inputMap.put(new ItemStack(GSBlocks.MetalsBlock, 1, 6), 231); // Decorative Oriharukon Block
            inputMap.put(new ItemStack(Blocks.glass_pane), 6);
            inputMap.put("circuitElite", 4);
            inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 15), 1); // IV Machine Hull
            GalacticraftRegistry.registerSpaceStation(
                new SpaceStationType(
                    GSConfigDimensions.dimensionIDVenusOrbit,
                    GSConfigDimensions.dimensionIDVenus,
                    new SpaceStationRecipe(inputMap)));
        }
        if (GSConfigDimensions.enableSaturnSS) {
            HashMap<Object, Integer> inputMap = new HashMap<>();
            inputMap.put(new ItemStack(GSBlocks.MetalsBlock, 1, 4), 231); // Decorative Mithril Block
            inputMap.put(new ItemStack(Blocks.glass_pane), 6);
            inputMap.put("circuitMaster", 4);
            inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 16), 1); // LuV Machine Hull
            GalacticraftRegistry.registerSpaceStation(
                new SpaceStationType(
                    GSConfigDimensions.dimensionIDSaturnOrbit,
                    GSConfigDimensions.dimensionIDSaturn,
                    new SpaceStationRecipe(inputMap)));
        }
        if (GSConfigDimensions.enableUranusSS) {
            HashMap<Object, Integer> inputMap = new HashMap<>();
            inputMap.put(new ItemStack(GSBlocks.MetalsBlock, 1, 8), 231); // Decorative Tungsten Block
            inputMap.put(new ItemStack(Blocks.glass_pane), 6);
            inputMap.put("circuitMaster", 4);
            inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 16), 1); // LuV Machine Hull
            GalacticraftRegistry.registerSpaceStation(
                new SpaceStationType(
                    GSConfigDimensions.dimensionIDUranusOrbit,
                    GSConfigDimensions.dimensionIDUranus,
                    new SpaceStationRecipe(inputMap)));
        }
        if (GSConfigDimensions.enableNeptuneSS) {
            HashMap<Object, Integer> inputMap = new HashMap<>();
            inputMap.put(new ItemStack(GSBlocks.MetalsBlock, 1, 1), 231); // Decorative Adamantium Block
            inputMap.put(new ItemStack(Blocks.glass_pane), 6);
            inputMap.put("circuitUltimate", 4);
            inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 17), 1); // ZPM Machine Hull
            GalacticraftRegistry.registerSpaceStation(
                new SpaceStationType(
                    GSConfigDimensions.dimensionIDNeptuneOrbit,
                    GSConfigDimensions.dimensionIDNeptune,
                    new SpaceStationRecipe(inputMap)));
        }
    }
}
