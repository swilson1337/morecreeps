package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLevelUpTamedEntity implements IMessage
{
    private int entityId;

    public MessageLevelUpTamedEntity()
    {
    }

    public MessageLevelUpTamedEntity(int entityIdIn)
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

    public static class MessageHandler implements IMessageHandler<MessageLevelUpTamedEntity, IMessage>
    {
        @Override
        public IMessage onMessage(MessageLevelUpTamedEntity message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().player;

            WorldServer world = player.getServerWorld();

            world.addScheduledTask(() -> {
                if (!world.getWorldInfo().areCommandsAllowed())
                {
                    return;
                }

                Entity entity = world.getEntityByID(message.entityId);

                if (entity instanceof EntityCreepBase)
                {
                    EntityCreepBase creep = (EntityCreepBase)entity;

                    if (creep.isPlayerOwner(player) && creep.getLevel() < creep.getMaxLevel())
                    {
                        while (creep.getLevel() < creep.getMaxLevel())
                        {
                            creep.addTotalDamage(creep.getLevelDamage());
                        }
                    }
                }
            });
            return null;
        }
    }
}
