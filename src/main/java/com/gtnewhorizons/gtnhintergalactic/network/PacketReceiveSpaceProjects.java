package com.gtnewhorizons.gtnhintergalactic.network;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import com.gtnewhorizons.gtnhintergalactic.GTNHIntergalactic;
import com.gtnewhorizons.gtnhintergalactic.render.RenderSkyBox;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import gregtech.common.misc.spaceprojects.SpaceProjectManager;
import io.netty.buffer.ByteBuf;

/**
 * Packet that will be received on client to get all finished projects of the player for this dimension
 *
 * @author minecraft7771
 */
public class PacketReceiveSpaceProjects implements IMessage, IMessageHandler<PacketReceiveSpaceProjects, IMessage> {

    /** List of projects which the player has finished in this dimension */
    private List<String> finishedProjects;

    /**
     * Parameterless constructor, for forge message creation. Parameters will be read via {@link #fromBytes(ByteBuf)}
     */
    public PacketReceiveSpaceProjects() {

    }

    /**
     * Create new message that will send the finished projects to client
     *
     * @param finishedProjects List of finished projects
     */
    public PacketReceiveSpaceProjects(List<String> finishedProjects) {
        this.finishedProjects = finishedProjects;
    }

    /**
     * Load the payload from the byte buffer
     *
     * @param buf Byte buffer with payload
     */
    @SuppressWarnings("unchecked")
    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            byte[] ba = new byte[buf.capacity()];
            buf.getBytes(0, ba);
            InputStream byteArrayInputStream = new ByteArrayInputStream(ba);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object data = objectInputStream.readObject();
            finishedProjects = (List<String>) data;
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
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(finishedProjects);
            objectOutputStream.flush();
            buf.writeBytes(byteArrayOutputStream.toByteArray());
        } catch (Exception ex) {
            GTNHIntergalactic.LOG.error(ex.toString());
        }
    }

    /**
     * Callback that will be invoked when the message has reached the client
     *
     * @param message Message for the client
     * @param context Context of the message
     * @return Null, the server doesn't care if we received this
     */
    @Override
    public IMessage onMessage(PacketReceiveSpaceProjects message, MessageContext context) {
        try {
            RenderSkyBox.finishedProjects = message.finishedProjects.stream().map(SpaceProjectManager::getProject)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            GTNHIntergalactic.LOG.error(ex.toString());
        }
        return null;
    }
}
