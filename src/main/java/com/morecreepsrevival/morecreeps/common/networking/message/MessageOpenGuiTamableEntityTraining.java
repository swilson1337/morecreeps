package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.client.gui.GuiTamableEntityTraining;
import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageOpenGuiTamableEntityTraining implements IMessage
{
    private int entityId;

    public MessageOpenGuiTamableEntityTraining()
    {
    }

    public MessageOpenGuiTamableEntityTraining(int entityIdIn)
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

    public static class MessageHandler implements IMessageHandler<MessageOpenGuiTamableEntityTraining, IMessage>
    {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(MessageOpenGuiTamableEntityTraining message, MessageContext context)
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> {
                EntityCreepBase entity = (EntityCreepBase)minecraft.world.getEntityByID(message.entityId);

                if (entity != null)
                {
                    minecraft.displayGuiScreen(new GuiTamableEntityTraining(entity));
                }
            });

            return null;
        }
    }
}
