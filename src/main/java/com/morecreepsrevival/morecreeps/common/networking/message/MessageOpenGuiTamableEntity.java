package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.client.gui.GuiTamableEntity;
import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageOpenGuiTamableEntity implements IMessage
{
    private int entityId;

    public MessageOpenGuiTamableEntity()
    {
    }

    public MessageOpenGuiTamableEntity(int entityIdIn)
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

    public static class MessageHandler implements IMessageHandler<MessageOpenGuiTamableEntity, IMessage>
    {
        @SideOnly(Side.CLIENT) @Override
        public IMessage onMessage(MessageOpenGuiTamableEntity message, MessageContext context)
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> {
                EntityCreepBase entity = (EntityCreepBase)minecraft.world.getEntityByID(message.entityId);

                if (entity != null)
                {
                    minecraft.displayGuiScreen(new GuiTamableEntity(entity));
                }
            });

            return null;
        }
    }
}
