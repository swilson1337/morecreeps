package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessagePlayBattleCastleSound implements IMessage
{
    public MessagePlayBattleCastleSound()
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

    public static class MessageHandler implements IMessageHandler<MessagePlayBattleCastleSound, IMessage>
    {
        @SideOnly(Side.CLIENT) @Override
        public IMessage onMessage(MessagePlayBattleCastleSound message, MessageContext context)
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> minecraft.player.playSound(CreepsSoundHandler.battleCastleSound, 1.0f, 1.0f));

            return null;
        }
    }
}
