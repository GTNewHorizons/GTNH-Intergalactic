package com.gtnewhorizons.gtnhintergalactic.client;

import galaxyspace.core.register.GSBlocks;
import gregtech.api.enums.Textures.BlockIcons;
import gregtech.api.enums.Textures.BlockIcons.CustomIcon;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.interfaces.ITexture;
import gregtech.api.render.TextureFactory;

/**
 * Textures used for MTEs are defined here
 *
 * @author minecraft7771
 */
public class IGTextures implements Runnable {

    public static IIconContainer SIPHON_OVERLAY_FRONT_GLOW;
    public static IIconContainer SIPHON_OVERLAY_FRONT_ACTIVE_GLOW;
    public static ITexture SIPHON_OVERLAY_FRONT;

    public static IIconContainer DYSON_OVERLAY_FRONT_GLOW;
    public static IIconContainer DYSON_OVERLAY_FRONT_ACTIVE_GLOW;
    public static ITexture DYSON_OVERLAY_FRONT;
    public static ITexture DYSON_OVERLAY_FRONT_ACTIVE;

    public static final int CASING_INDEX_RECEIVER = 150;
    public static final int CASING_INDEX_COMMAND = 151;
    public static final int CASING_INDEX_LAUNCH = 152;
    public static final int CASING_INDEX_FLOOR = 153;
    public static final int ADVANCED_MACHINE_FRAME_INDEX = 154;

    /**
     * Register all used textures
     */
    @Override
    public void run() {
        SIPHON_OVERLAY_FRONT = TextureFactory.of(new CustomIcon("iconsets/OVERLAY_FRONT_PLANETARYSIPHON"));
        SIPHON_OVERLAY_FRONT_GLOW = new CustomIcon("iconsets/OVERLAY_FRONT_PLANETARYSIPHON_GLOW");
        SIPHON_OVERLAY_FRONT_ACTIVE_GLOW = new CustomIcon("iconsets/OVERLAY_FRONT_PLANETARYSIPHON_ACTIVE_GLOW");

        DYSON_OVERLAY_FRONT = TextureFactory.of(new CustomIcon("iconsets/OVERLAY_FRONT_DYSONSPHERE"));
        DYSON_OVERLAY_FRONT_ACTIVE = TextureFactory.of(new CustomIcon("iconsets/OVERLAY_FRONT_DYSONSPHERE_ACTIVE"));
        DYSON_OVERLAY_FRONT_GLOW = new CustomIcon("iconsets/OVERLAY_FRONT_DYSONSPHERE_GLOW");
        DYSON_OVERLAY_FRONT_ACTIVE_GLOW = new CustomIcon("iconsets/OVERLAY_FRONT_DYSONSPHERE_ACTIVE_GLOW");

        // TODO Migrate these blocks out of GalaxySpace
        BlockIcons.setCasingTextureForId(CASING_INDEX_RECEIVER, TextureFactory.of(GSBlocks.DysonSwarmBlocks, 0));
        BlockIcons.setCasingTextureForId(CASING_INDEX_LAUNCH, TextureFactory.of(GSBlocks.DysonSwarmBlocks, 2));
        BlockIcons.setCasingTextureForId(CASING_INDEX_COMMAND, TextureFactory.of(GSBlocks.DysonSwarmBlocks, 5));
        BlockIcons.setCasingTextureForId(CASING_INDEX_FLOOR, TextureFactory.of(GSBlocks.DysonSwarmBlocks, 9));
        BlockIcons.setCasingTextureForId(ADVANCED_MACHINE_FRAME_INDEX, TextureFactory.of(GSBlocks.MachineFrames));
    }
}
