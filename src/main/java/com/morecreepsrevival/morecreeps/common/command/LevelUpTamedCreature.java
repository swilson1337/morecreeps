package com.morecreepsrevival.morecreeps.common.command;

import com.morecreepsrevival.morecreeps.common.networking.message.MessageLevelUpTamedEntity;
import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.IClientCommand;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LevelUpTamedCreature extends CommandBase implements IClientCommand
{
    @Override @Nonnull
    public String getName()
    {
        return "levelupcreature";
    }

    @Override @Nonnull
    public String getUsage(@Nullable ICommandSender sender)
    {
        return "/levelupcreature";
    }

    @Override
    public void execute(@Nullable MinecraftServer server, @Nullable ICommandSender sender, @Nullable String[] args)
    {
        Minecraft client = Minecraft.getMinecraft();

        if (client.objectMouseOver.entityHit instanceof EntityCreepBase && ((EntityCreepBase)client.objectMouseOver.entityHit).isTamed())
        {
            CreepsPacketHandler.INSTANCE.sendToServer(new MessageLevelUpTamedEntity(client.objectMouseOver.entityHit.getEntityId()));
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message)
    {
        return false;
    }
}
