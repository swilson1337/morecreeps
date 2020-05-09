package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.client.gui.GuiSneakySal;
import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageOpenGuiSneakySal implements IMessage
{
    private int salEntityId;

    public MessageOpenGuiSneakySal()
    {
    }

    public MessageOpenGuiSneakySal(int salEntityIdIn)
    {
        salEntityId = salEntityIdIn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(salEntityId);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        salEntityId = buf.readInt();
    }

    public static class MessageHandler implements IMessageHandler<MessageOpenGuiSneakySal, IMessage>
    {
        @SideOnly(Side.CLIENT) @Override
        public IMessage onMessage(MessageOpenGuiSneakySal message, MessageContext context)
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> {
                EntitySneakySal sal = (EntitySneakySal)minecraft.world.getEntityByID(message.salEntityId);

                if (sal != null)
                {
                    minecraft.player.playSound(CreepsSoundHandler.salGreetingSound, 1.0f, 1.0f);

                    minecraft.displayGuiScreen(new GuiSneakySal(sal));
                }
            });

            return null;
        }
    }
}
