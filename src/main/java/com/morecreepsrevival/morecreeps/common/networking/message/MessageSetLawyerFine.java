package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.capabilities.ILawyerFine;
import com.morecreepsrevival.morecreeps.common.capabilities.LawyerFineProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSetLawyerFine implements IMessage
{
    private int fineAmt;

    public MessageSetLawyerFine()
    {
    }

    public MessageSetLawyerFine(int fineAmtIn)
    {
        fineAmt = fineAmtIn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(fineAmt);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        fineAmt = buf.readInt();
    }

    public static class MessageHandler implements IMessageHandler<MessageSetLawyerFine, IMessage>
    {
        @SideOnly(Side.CLIENT) @Override
        public IMessage onMessage(MessageSetLawyerFine message, MessageContext context)
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> {
                ILawyerFine capability = Minecraft.getMinecraft().player.getCapability(LawyerFineProvider.capability, null);

                if (capability != null)
                {
                    capability.setFine(message.fineAmt);
                }
            });

            return null;
        }
    }
}
