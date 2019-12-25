package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.client.gui.GuiSneakySal;
import com.morecreepsrevival.morecreeps.client.gui.GuiTamableEntity;
import com.morecreepsrevival.morecreeps.common.capabilities.ILawyerFine;
import com.morecreepsrevival.morecreeps.common.capabilities.LawyerFineProvider;
import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageClearLawyerFine implements IMessage
{
    public MessageClearLawyerFine()
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

    public static class MessageHandler implements IMessageHandler<MessageClearLawyerFine, IMessage>
    {
        @SideOnly(Side.CLIENT) @Override
        public IMessage onMessage(MessageClearLawyerFine message, MessageContext context)
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> {
                ILawyerFine capability = Minecraft.getMinecraft().player.getCapability(LawyerFineProvider.capability, null);

                if (capability != null)
                {
                    capability.setFine(0);
                }
            });

            return null;
        }
    }
}
