package com.gtnewhorizons.gtnhintergalactic.network;

import java.util.stream.Collectors;

import net.minecraft.entity.player.EntityPlayerMP;

import com.gtnewhorizons.gtnhintergalactic.GTNHIntergalactic;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import gregtech.common.misc.spaceprojects.SpaceProjectManager;
import gregtech.common.misc.spaceprojects.interfaces.ISpaceProject;
import io.netty.buffer.ByteBuf;

/**
 * Packet that will be sent to the server to get all finished projects of the player for this dimension
 *
 * @author minecraft7771
 */
public class PacketRequestSpaceProjects implements IMessage, IMessageHandler<PacketRequestSpaceProjects, IMessage> {

    /** Name of the dimension for which the space projects will be gotten */
    String dimensionName;

    /**
     * Parameterless constructor, for forge message creation. Parameters will be read via {@link #fromBytes(ByteBuf)}
     */
    public PacketRequestSpaceProjects() {

    }

    /**
     * Create new message that will request the finished projects from the server
     *
     * @param dimensionName Name of the dimension for which the project list should be gotten
     */
    public PacketRequestSpaceProjects(String dimensionName) {
        // Some dimension names consist of multiple words, we need to make them into one
        this.dimensionName = dimensionName.replace(" ", "");
    }

    /**
     * Load the payload from the byte buffer
     *
     * @param buf Byte buffer with payload
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            dimensionName = ByteBufUtils.readUTF8String(buf);
        } catch (Exception ex) {
            GTNHIntergalactic.LOG.error(ex.toString());
        }
    }

    /**
     * Write the payload to the byte buffer
     *
     * @param buf Byte buffer that will get the payload
     */
    @Override
    public void toBytes(ByteBuf buf) {
        try {
            ByteBufUtils.writeUTF8String(buf, dimensionName);
        } catch (Exception ex) {
            GTNHIntergalactic.LOG.error(ex.toString());
        }
    }

    /**
     * Callback that will be invoked when the message has reached the server
     *
     * @param message Message for the server
     * @param context Context of the message
     * @return Response for the client
     */
    @Override
    public IMessage onMessage(PacketRequestSpaceProjects message, MessageContext context) {
        try {
            EntityPlayerMP player = context.getServerHandler().playerEntity;
            PacketReceiveSpaceProjects messageToClient = new PacketReceiveSpaceProjects(
                    SpaceProjectManager.getTeamSpaceProjects(player.getPersistentID()).stream()
                            .filter(ISpaceProject::isFinished)
                            .filter(project -> project.getProjectLocation().getName().equals(message.dimensionName))
                            .map(ISpaceProject::getProjectName).collect(Collectors.toList()));
            PacketHandler.INSTANCE.sendTo(messageToClient, player);
        } catch (Exception ex) {
            GTNHIntergalactic.LOG.error(ex.toString());
        }
        return null;
    }
}
