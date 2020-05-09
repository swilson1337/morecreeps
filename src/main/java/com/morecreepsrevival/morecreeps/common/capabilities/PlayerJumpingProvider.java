package com.morecreepsrevival.morecreeps.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;

public class PlayerJumpingProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IPlayerJumping.class)
    public static Capability<IPlayerJumping> capability;

    private IPlayerJumping instance;

    public PlayerJumpingProvider()
    {
        if (capability != null)
        {
            instance = capability.getDefaultInstance();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capabilityIn, EnumFacing facing)
    {
        return (capabilityIn == capability);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capabilityIn, EnumFacing facing)
    {
        if (capabilityIn == capability)
        {
            return capability.cast(instance);
        }

        return null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return capability.getStorage().writeNBT(capability, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbtBase)
    {
        capability.getStorage().readNBT(capability, instance, null, nbtBase);
    }
}
