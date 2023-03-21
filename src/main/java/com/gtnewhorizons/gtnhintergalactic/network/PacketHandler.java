package com.gtnewhorizons.gtnhintergalactic.network;

import com.gtnewhorizon.gtnhintergalactic.Tags;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Handler used for packet transmission
 *
 * @author minecraft7771
 */
public class PacketHandler {

    /** Network wrapper instance that will be used for packet transmission */
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE
            .newSimpleChannel(Tags.MODID.toLowerCase());

    /**
     * Initialize the possible packets
     */
    public static void initPackets() {
        INSTANCE.registerMessage(PacketReceiveSpaceProjects.class, PacketReceiveSpaceProjects.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(PacketRequestSpaceProjects.class, PacketRequestSpaceProjects.class, 2, Side.SERVER);
    }
}
