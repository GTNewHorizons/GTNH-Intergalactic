package galaxyspace.core.recipe;

import java.util.HashMap;

import galaxyspace.core.config.GSConfigDimensions;
import galaxyspace.core.register.GSBlocks;
import gregtech.api.GregTech_API;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class SpaceStationRecipes {

    public static void loadRecipes() {
        if(GSConfigDimensions.enableMarsSS) {
            HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
            inputMap.put(new ItemStack(GSBlocks.MetalsBlock, 1, 9), 230);
            inputMap.put(new ItemStack(Blocks.glass_pane), 6);
            inputMap.put("circuitAdvanced", 4);
            inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 13), 1); //HV Machine Hull
            GalacticraftRegistry.registerSpaceStation(new SpaceStationType(GSConfigDimensions.dimensionIDMarsOrbit, ConfigManagerMars.dimensionIDMars, new SpaceStationRecipe(inputMap)));
        }
        if(GSConfigDimensions.enableVenusSS) {
            HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
            inputMap.put(new ItemStack(GSBlocks.MetalsBlock), 230);
            inputMap.put(new ItemStack(Blocks.glass_pane), 6);
            inputMap.put("circuitElite", 4);
            inputMap.put(new ItemStack(GregTech_API.sBlockMachines, 1, 15), 1); //IV Machine Hull
            GalacticraftRegistry.registerSpaceStation(new SpaceStationType(GSConfigDimensions.dimensionIDVenusOrbit, GSConfigDimensions.dimensionIDVenus, new SpaceStationRecipe(inputMap)));
        }
//        if(GSConfigDimensions.enableJupiterSS) {
//            HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
//        	inputMap.put("logWood", 1);
//        	GalacticraftRegistry.registerSpaceStation(new SpaceStationType(GSConfigDimensions.dimensionIDJupiterOrbit, GSConfigDimensions.dimensionIDJupiter, new SpaceStationRecipe(inputMap)));
//        }
    }

}
