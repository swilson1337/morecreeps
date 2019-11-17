package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRipOffSal implements IMessage
{
    private int entityId;

    public MessageRipOffSal()
    {
    }

    public MessageRipOffSal(int entityIdIn)
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

    public static class MessageHandler implements IMessageHandler<MessageRipOffSal, IMessage>
    {
        @Override
        public IMessage onMessage(MessageRipOffSal message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().player;

            WorldServer world = player.getServerWorld();

            world.addScheduledTask(() -> {
                Entity entity = world.getEntityByID(message.entityId);

                if (entity instanceof EntitySneakySal)
                {
                    ((EntitySneakySal)entity).ripOff();
                }
            });

            return null;
        }
    }
}
