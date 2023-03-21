package com.gtnewhorizons.gtnhintergalactic.render;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import gregtech.common.misc.spaceprojects.interfaces.ISpaceProject;

/**
 * Renderer that is used to render the space projects in the skybox
 *
 * @author minecraft7771
 */
public class RenderSkyBox extends IRenderHandler {

    /** Cached list of space project which the player has finished */
    static public List<ISpaceProject> finishedProjects = new ArrayList<>();
    /** Original skybox renderer of the world */
    private final IRenderHandler originalRenderHandler;

    /**
     * Create a new sky box renderer, which can wrap around an existing renderer
     *
     * @param originalRenderHandler Original skybox renderer of the world
     */
    public RenderSkyBox(IRenderHandler originalRenderHandler) {
        this.originalRenderHandler = originalRenderHandler;
    }

    /**
     *
     * @param partialTicks Ticks between render calls
     * @param world        World in which the client currently is
     * @param mc           Instance of the game
     */
    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        if (originalRenderHandler != null) {
            originalRenderHandler.render(partialTicks, world, mc);
        }
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        RenderHelper.disableStandardItemLighting();

        int index = 1;
        for (ISpaceProject project : finishedProjects) {
            GL11.glPushMatrix();
            GL11.glScalef(0.3f, 0.3f, 0.3f);
            GL11.glRotatef(10.0f, 50.0f, 1.0f, 0.0f);
            GL11.glRotatef(75.0f, 1.0f, 0.0f, 0.0f);
            this.drawTexture(project.getTexture().getLocation(), 7.0, index);
            GL11.glPopMatrix();
            index++;
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDepthMask(true);
    }

    /**
     * The texture is drawn on the XZ plane, centered on (0, -100 * (index - 1), 0)
     *
     * @param texture the texture to be drawn
     * @param radius  half the side-length of the texture
     * @param index   Index of the drawn texture, influences the position on the plane
     */
    private void drawTexture(ResourceLocation texture, double radius, int index) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-radius - 100 * (index - 1), -100.0, radius, 0.0, 1.0);
        tessellator.addVertexWithUV(radius - 100 * (index - 1), -100.0, radius, 1.0, 1.0);
        tessellator.addVertexWithUV(radius - 100 * (index - 1), -100.0, -radius, 1.0, 0.0);
        tessellator.addVertexWithUV(-radius - 100 * (index - 1), -100.0, -radius, 0.0, 0.0);
        tessellator.draw();
    }
}
