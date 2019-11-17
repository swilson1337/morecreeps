package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSetEntityWanderState implements IMessage
{
    private int entityId;

    private int wanderState;

    public MessageSetEntityWanderState()
    {
    }

    public MessageSetEntityWanderState(int entityIdIn, int wanderStateIn)
    {
        entityId = entityIdIn;

        wanderState = wanderStateIn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(entityId);

        buf.writeShort(wanderState);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityId = buf.readInt();

        wanderState = buf.readShort();
    }

    public static class MessageHandler implements IMessageHandler<MessageSetEntityWanderState, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSetEntityWanderState message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().player;

            WorldServer world = player.getServerWorld();

            world.addScheduledTask(() -> {
                Entity entity = world.getEntityByID(message.entityId);

                if (entity instanceof EntityCreepBase)
                {
                    EntityCreepBase creep = (EntityCreepBase)entity;

                    if (creep.isPlayerOwner(player))
                    {
                        creep.setWanderState(message.wanderState);

                        switch (message.wanderState)
                        {
                            case 0:
                                player.sendMessage(new TextComponentString("\2473" + creep.getCreepName() + "\2476 will \247dSTAY\2476 right here."));

                                break;
                            case 1:
                                player.sendMessage(new TextComponentString("\2473" + creep.getCreepName() + "\2476 will \247dWANDER\2476 around and have fun."));

                                break;
                            case 2:
                                player.sendMessage(new TextComponentString("\2473" + creep.getCreepName() + "\2476 will \247dFIGHT\2476 and follow you!"));

                                break;
                            default:
                                break;
                        }
                    }
                }
            });

            return null;
        }
    }
}
