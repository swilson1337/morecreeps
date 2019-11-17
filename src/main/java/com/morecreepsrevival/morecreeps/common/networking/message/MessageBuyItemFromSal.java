package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBuyItemFromSal implements IMessage
{
    private int entityId;

    private int itemId;

    public MessageBuyItemFromSal()
    {
    }

    public MessageBuyItemFromSal(int entityIdIn, int itemIdIn)
    {
        entityId = entityIdIn;

        itemId = itemIdIn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(entityId);

        buf.writeShort(itemId);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityId = buf.readInt();

        itemId = buf.readShort();
    }

    public static class MessageHandler implements IMessageHandler<MessageBuyItemFromSal, IMessage>
    {
        @Override
        public IMessage onMessage(MessageBuyItemFromSal message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().player;

            WorldServer world = player.getServerWorld();

            world.addScheduledTask(() -> {
                Entity entity = world.getEntityByID(message.entityId);

                if (entity instanceof EntitySneakySal)
                {
                    ((EntitySneakySal)entity).buyItem(player, message.itemId);
                }
            });

            return null;
        }
    }
}
