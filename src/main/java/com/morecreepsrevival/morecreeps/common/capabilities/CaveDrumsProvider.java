package com.morecreepsrevival.morecreeps.common.capabilities;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;

public class CaveDrumsProvider implements ICapabilityProvider
{
    @CapabilityInject(ICaveDrums.class)
    public static Capability<ICaveDrums> capability;

    private ICaveDrums instance;

    public CaveDrumsProvider()
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
}
