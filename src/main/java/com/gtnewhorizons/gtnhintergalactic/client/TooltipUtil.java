package com.gtnewhorizons.gtnhintergalactic.client;

import static com.gtnewhorizon.gtnhlib.util.AnimatedTooltipHandler.*;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SimpleReloadableResourceManager;

import com.gtnewhorizons.gtnhintergalactic.client.lore.LoreHandler;
import com.gtnewhorizons.gtnhintergalactic.item.IGItems;
import com.gtnewhorizons.gtnhintergalactic.tile.multi.TileEntityPlanetaryGasSiphon;
import com.gtnewhorizons.gtnhintergalactic.tile.multi.elevator.TileEntitySpaceElevator;

/**
 * Utility functions for tooltips
 *
 * @author minecraft7771
 */
public class TooltipUtil {

    /**
     * Initialize the tooltip utilities
     */
    public static void postInit() {
        ((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager())
                .registerReloadListener(new LoreHandler());
        LoreHandler.registerLoreHolder(TileEntityPlanetaryGasSiphon.class);
        LoreHandler.registerLoreHolder(TileEntitySpaceElevator.class);

        Supplier<String> AUTHOR_GLOWREDMAN = chain(
                translatedText("ig.structure.author"),
                text(" "),
                animatedText(
                        "glowredman",
                        1,
                        300,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE,
                        DARK_BLUE + OBFUSCATED));

        addItemTooltip(IGItems.PlanetaryGasSiphon, AUTHOR_GLOWREDMAN);
        addItemTooltip(IGItems.DysonSwarmController,AUTHOR_GLOWREDMAN);
    }
}
