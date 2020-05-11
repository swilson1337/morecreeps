package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageDismountEntity implements IMessage
{
    private int entityId;

    public MessageDismountEntity()
    {
    }

    public MessageDismountEntity(int entityIdIn)
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

    public static class MessageHandler implements IMessageHandler<MessageDismountEntity, IMessage>
    {
        @Override
        public IMessage onMessage(MessageDismountEntity message, MessageContext context)
        {
            if (context.side == Side.SERVER)
            {
                EntityPlayerMP player = context.getServerHandler().player;

                WorldServer world = player.getServerWorld();

                world.addScheduledTask(() -> {
                    Entity entity = world.getEntityByID(message.entityId);

                    if (entity instanceof EntityCreepBase)
                    {
                        EntityCreepBase creep = (EntityCreepBase)entity;

                        if (player.isPassenger(entity))
                        {
                            creep.dismountRidingEntity();
                        }
                    }
                });
            }
            else if (context.side == Side.CLIENT)
            {
                Minecraft minecraft = Minecraft.getMinecraft();

                minecraft.addScheduledTask(() -> {
                    Entity entity = minecraft.world.getEntityByID(message.entityId);

                    if (entity instanceof EntityCreepBase)
                    {
                        EntityCreepBase creep = (EntityCreepBase)entity;

                        if (minecraft.player.isPassenger(entity))
                        {
                            creep.dismountRidingEntity();
                        }
                    }
                });
            }

            return null;
        }
    }
}
