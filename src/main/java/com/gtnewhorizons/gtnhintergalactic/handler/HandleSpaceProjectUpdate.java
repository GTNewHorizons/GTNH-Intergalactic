package com.gtnewhorizons.gtnhintergalactic.handler;

import micdoodle8.mods.galacticraft.core.client.SkyProviderOverworld;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.common.DimensionManager;

import com.gtnewhorizons.gtnhintergalactic.network.PacketHandler;
import com.gtnewhorizons.gtnhintergalactic.network.PacketRequestSpaceProjects;
import com.gtnewhorizons.gtnhintergalactic.render.RenderSkyBox;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Handler that updates the finished space projects on client side for rendering
 *
 * @author minecraft7771
 */
public class HandleSpaceProjectUpdate {

    /**
     * Callback that will be invoked, when the client has changed their dimension
     *
     * @param event Event details
     */
    @SubscribeEvent
    public void onDimensionChanged(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.player.isClientWorld()) {
            PacketHandler.INSTANCE.sendToServer(
                    new PacketRequestSpaceProjects(DimensionManager.getProvider(event.toDim).getDimensionName()));
        }
    }

    /**
     * Callback that will be invoked, when the client has joined the world
     *
     * @param event Event details
     */
    @SubscribeEvent
    public void onWorldEntered(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player.isClientWorld()) {
            PacketHandler.INSTANCE.sendToServer(
                    new PacketRequestSpaceProjects(
                            DimensionManager.getProvider(event.player.dimension).getDimensionName()));
        }
    }

    /**
     * Callback that will be invoked, when the client ticks
     *
     * @param event Event details
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onSkyRendererTick(final TickEvent.ClientTickEvent event) {
        final WorldClient world = FMLClientHandler.instance().getWorldClient();
        if (world == null) {
            return;
        }
        WorldProvider provider = world.provider;
        if (provider instanceof WorldProviderSurface) {
            if (!(provider.getSkyRenderer() instanceof RenderSkyBox)) {
                provider.setSkyRenderer(new RenderSkyBox(new SkyProviderOverworld()));
            }
        } else if (!(provider instanceof WorldProviderEnd)) {
            if (!(provider.getSkyRenderer() instanceof RenderSkyBox)) {
                provider.setSkyRenderer(new RenderSkyBox(provider.getSkyRenderer()));
            }
        }
    }
}
