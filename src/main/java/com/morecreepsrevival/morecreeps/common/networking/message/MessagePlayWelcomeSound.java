package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessagePlayWelcomeSound implements IMessage
{
    public MessagePlayWelcomeSound()
    {
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
    }

    public static class MessageHandler implements IMessageHandler<MessagePlayWelcomeSound, IMessage>
    {
        @SideOnly(Side.CLIENT) @Override
        public IMessage onMessage(MessagePlayWelcomeSound message, MessageContext context)
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> {
                if (MoreCreepsConfig.playWelcomeSound)
                {
                    minecraft.player.playSound(CreepsSoundHandler.welcomeSound, 1.0f, 1.0f);
                }
            });

            return null;
        }
    }
}
