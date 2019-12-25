package com.morecreepsrevival.morecreeps.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CaveDrumsStorage implements IStorage<ICaveDrums>
{
    @Override
    public NBTBase writeNBT(Capability<ICaveDrums> capability, ICaveDrums instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getDrumsTime());
    }

    @Override
    public void readNBT(Capability<ICaveDrums> capability, ICaveDrums instance, EnumFacing side, NBTBase nbtBase)
    {
        instance.setDrumsTime(((NBTPrimitive)nbtBase).getInt());
    }
}
