package com.gtnewhorizon.gtnhintergalactic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.gtnhintergalactic.block.IGBlocks;
import com.gtnewhorizon.gtnhintergalactic.item.IGItems;
import com.gtnewhorizon.gtnhintergalactic.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
        modid = Tags.MODID,
        version = Tags.VERSION,
        name = Tags.MODNAME,
        acceptedMinecraftVersions = "[1.7.10]",
        dependencies = "required-after:GalacticraftCore@[3.0.36,);" + "required-after:GalacticraftMars;"
                + "required-after:gregtech;"
                + "required-after:gtnhlib@[0.0.8.1,);"
                + "required-after:tectech;"
                + "required-after:structurelib;"
                + "required-after:galaxyspace;"
                + "after:bartworks;"
                + "after:GoodGenerator;"
                + "after:miscutils;"
                + "after:dreamcraft;"
                + "after:openmodularturrets;"
                + "after:EMT;"
                + "after:IronChest;")
public class GTNHIntergalactic {

    /** Logger used by this mod */
    public static final Logger LOG = LogManager.getLogger(Tags.MODID);
    /** Prefix for assets */
    public static final String ASSET_PREFIX = "gtnhintergalactic";
    /** Creative tab for mod items */
    public static CreativeTabs tab;
    /** Mod instance */
    @Mod.Instance(Tags.MODID)
    public static GTNHIntergalactic instance;
    /** Proxy used for loading */
    @SidedProxy(
            clientSide = "com.gtnewhorizon.gtnhintergalactic.proxy.ClientProxy",
            serverSide = "com.gtnewhorizon.gtnhintergalactic.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        tab = new CreativeTabs(CreativeTabs.getNextID(), Tags.MODNAME) {

            @Override
            public Item getTabIconItem() {
                return IGItems.SpaceElevatorController.getItem();
            }
        };
        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    @Mod.EventHandler
    public void onMissingMapping(FMLMissingMappingsEvent event) {
        for (FMLMissingMappingsEvent.MissingMapping mapping : event.getAll()) {
            if (mapping.type == GameRegistry.Type.BLOCK) {
                switch (mapping.name) {
                    case "GalaxySpace:spaceelevatorparts":
                        mapping.remap(IGBlocks.SpaceElevatorCasing);
                        break;
                    case "GalaxySpace:spaceelevatormotors":
                        mapping.remap(IGBlocks.SpaceElevatorMotor);
                        break;
                    case "GalaxySpace:spaceelevatorcable":
                        mapping.remap(IGBlocks.SpaceElevatorCable);
                        break;
                    default:
                        mapping.warn();
                        break;
                }
            } else if (mapping.type == GameRegistry.Type.ITEM) {
                switch (mapping.name) {
                    case "GalaxySpace:spaceelevatorparts":
                        mapping.remap(Item.getItemFromBlock(IGBlocks.SpaceElevatorCasing));
                        break;
                    case "GalaxySpace:spaceelevatormotors":
                        mapping.remap(Item.getItemFromBlock(IGBlocks.SpaceElevatorMotor));
                        break;
                    case "GalaxySpace:spaceelevatorcable":
                        mapping.remap(Item.getItemFromBlock(IGBlocks.SpaceElevatorCable));
                        break;
                    case "GalaxySpace:SpaceElevatorParts":
                        mapping.remap(IGItems.SpaceElevatorItems);
                        break;
                    default:
                        mapping.warn();
                        break;
                }
            }
        }
    }

    public void markTextureUsed(IIcon o) {
        proxy.markTextureUsed(o);
    }
}