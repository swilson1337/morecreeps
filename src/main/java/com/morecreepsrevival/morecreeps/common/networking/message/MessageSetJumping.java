package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.capabilities.IPlayerJumping;
import com.morecreepsrevival.morecreeps.common.capabilities.PlayerJumpingProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSetJumping implements IMessage
{
    private boolean jumping;

    public MessageSetJumping()
    {
    }

    public MessageSetJumping(boolean jumpingIn)
    {
        jumping = jumpingIn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(jumping);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        jumping = buf.readBoolean();
    }

    public static class MessageHandler implements IMessageHandler<MessageSetJumping, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSetJumping message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().player;

            WorldServer world = player.getServerWorld();

            world.addScheduledTask(() -> {
                IPlayerJumping capability = player.getCapability(PlayerJumpingProvider.capability, null);

                if (capability != null)
                {
                    capability.setJumping(message.jumping);
                }
            });

            return null;
        }
    }
}
