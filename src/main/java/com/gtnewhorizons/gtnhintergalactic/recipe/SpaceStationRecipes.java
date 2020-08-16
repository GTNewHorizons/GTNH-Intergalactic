package galaxyspace.core.recipe;

import java.util.HashMap;

import galaxyspace.core.config.GSConfigDimensions;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;

public class SpaceStationRecipes {

    public static void loadRecipes() {
        if (GSConfigDimensions.enableVenusSS) {
            HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
            inputMap.put("ingotLead", 64);
            inputMap.put("ingotTitanium", 16);
            inputMap.put("waferAdvanced", 2);
            inputMap.put("ingotDesh", 12);
            GalacticraftRegistry.registerSpaceStation(new SpaceStationType(GSConfigDimensions.dimensionIDVenusOrbit, GSConfigDimensions.dimensionIDVenus, new SpaceStationRecipe(inputMap)));
        }
        if (GSConfigDimensions.enableMarsSS) {
            HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
            inputMap.put("ingotCopper", 64);
            inputMap.put("ingotTitanium", 4);
            inputMap.put("waferAdvanced", 2);
            inputMap.put("ingotDesh", 12);
            GalacticraftRegistry.registerSpaceStation(new SpaceStationType(GSConfigDimensions.dimensionIDMarsOrbit, ConfigManagerMars.dimensionIDMars, new SpaceStationRecipe(inputMap)));
        }
    }

}
