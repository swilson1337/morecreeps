package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.client.particles.FxBlood;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSendBloodEffect implements IMessage
{
    private int entityId;

    public MessageSendBloodEffect()
    {
    }

    public MessageSendBloodEffect(int entityIdIn)
    {
        entityId = entityIdIn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(entityId);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityId = buf.readInt();
    }

    public static class MessageHandler implements IMessageHandler<MessageSendBloodEffect, IMessage>
    {
        @SideOnly(Side.CLIENT) @Override
        public IMessage onMessage(MessageSendBloodEffect message, MessageContext context)
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> {
                Entity entity = minecraft.world.getEntityByID(message.entityId);

                if (entity != null)
                {
                    for (int i = 0; i < 10; i++)
                    {
                        minecraft.effectRenderer.addEffect(new FxBlood(minecraft.world, entity.posX, entity.posY + 1.0d, entity.posZ, 0.255f));
                    }
                }
            });

            return null;
        }
    }
}
