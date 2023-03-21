package com.gtnewhorizons.gtnhintergalactic.proxy;

import net.minecraft.util.IIcon;
import net.minecraftforge.common.MinecraftForge;

import com.gtnewhorizons.gtnhintergalactic.block.IGBlocks;
import com.gtnewhorizons.gtnhintergalactic.config.Config;
import com.gtnewhorizons.gtnhintergalactic.item.IGItems;
import com.gtnewhorizons.gtnhintergalactic.loader.MachineLoader;
import com.gtnewhorizons.gtnhintergalactic.loader.RecipeLoader;
import com.gtnewhorizons.gtnhintergalactic.nei.IMCForNEI;
import com.gtnewhorizons.gtnhintergalactic.network.PacketHandler;
import com.gtnewhorizons.gtnhintergalactic.recipe.IG_RecipeAdder;
import com.gtnewhorizons.gtnhintergalactic.recipe.MachineRecipes;
import com.gtnewhorizons.gtnhintergalactic.recipe.SpaceProjectRegistration;
import com.gtnewhorizons.gtnhintergalactic.tile.TileEntitySpaceElevatorCable;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;

/**
 * Proxy used by both, the server and the client to load stuff
 *
 * @author minecraft7771
 */
public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        PacketHandler.initPackets();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        IMCForNEI.IMCSender();
        if (Textures.BlockIcons.casingTexturePages[32] == null) {
            Textures.BlockIcons.casingTexturePages[32] = new ITexture[128];
        }
        IGItems.init();
        IGBlocks.init();
        new MachineLoader().run();
        IG_RecipeAdder.init();
        GameRegistry.registerTileEntity(TileEntitySpaceElevatorCable.class, "Space Elevator Cable");
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        new RecipeLoader().run();
        new SpaceProjectRegistration().run();
        new MachineRecipes().run();
        IG_RecipeAdder.postInit();
    }

    /**
     * Register an object as event handler
     *
     * @param obj Event handler
     */
    protected void registerEventHandler(Object obj) {
        FMLCommonHandler.instance().bus().register(obj);
        MinecraftForge.EVENT_BUS.register(obj);
    }

    /**
     * Mark a texture as used, to prevent hodgepodge from optimizing it
     *
     * @param o Textured to be used
     */
    public void markTextureUsed(IIcon o) {}
}
