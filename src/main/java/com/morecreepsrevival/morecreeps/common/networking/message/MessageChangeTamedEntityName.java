package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageChangeTamedEntityName implements IMessage
{
    private int entityId;

    private String name;

    public MessageChangeTamedEntityName()
    {
    }

    public MessageChangeTamedEntityName(int entityIdIn, String nameIn)
    {
        entityId = entityIdIn;

        name = nameIn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(entityId);

        ByteBufUtils.writeUTF8String(buf, name);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityId = buf.readInt();

        name = ByteBufUtils.readUTF8String(buf);
    }

    public static class MessageHandler implements IMessageHandler<MessageChangeTamedEntityName, IMessage>
    {
        @Override
        public IMessage onMessage(MessageChangeTamedEntityName message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().player;

            WorldServer world = player.getServerWorld();

            world.addScheduledTask(() -> {
                Entity entity = world.getEntityByID(message.entityId);

                if (entity instanceof EntityCreepBase)
                {
                    EntityCreepBase creep = (EntityCreepBase)entity;

                    if (creep.isPlayerOwner(player) && !message.name.isEmpty())
                    {
                        creep.setCreepName(message.name);
                    }
                }
            });

            return null;
        }
    }
}
