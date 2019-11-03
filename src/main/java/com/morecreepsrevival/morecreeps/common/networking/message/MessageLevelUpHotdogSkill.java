package com.morecreepsrevival.morecreeps.common.networking.message;

import com.morecreepsrevival.morecreeps.common.entity.EntityHotdog;
import com.morecreepsrevival.morecreeps.common.helpers.InventoryHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLevelUpHotdogSkill implements IMessage
{
    private int entityId;

    private String skill;

    public MessageLevelUpHotdogSkill()
    {
    }

    public MessageLevelUpHotdogSkill(int entityIdIn, String skillIn)
    {
        entityId = entityIdIn;

        skill = skillIn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(entityId);

        ByteBufUtils.writeUTF8String(buf, skill);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityId = buf.readInt();

        skill = ByteBufUtils.readUTF8String(buf);
    }

    public static class MessageHandler implements IMessageHandler<MessageLevelUpHotdogSkill, IMessage>
    {
        @Override
        public IMessage onMessage(MessageLevelUpHotdogSkill message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().player;

            WorldServer world = player.getServerWorld();

            world.addScheduledTask(() -> {
                Entity entity = world.getEntityByID(message.entityId);

                if (entity instanceof EntityHotdog)
                {
                    EntityHotdog hotdog = (EntityHotdog)entity;

                    if (hotdog.isPlayerOwner(player) && hotdog.canLevelSkill(message.skill) && InventoryHelper.takeItem(player.inventory, Items.BONE, 5))
                    {
                        hotdog.levelUpSkill(message.skill);
                    }
                }
            });

            return null;
        }
    }
}
