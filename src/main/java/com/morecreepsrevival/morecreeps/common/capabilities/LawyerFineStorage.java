package com.morecreepsrevival.morecreeps.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LawyerFineStorage implements IStorage<ILawyerFine>
{
    @Override
    public NBTBase writeNBT(Capability<ILawyerFine> capability, ILawyerFine instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getFine());
    }

    @Override
    public void readNBT(Capability<ILawyerFine> capability, ILawyerFine instance, EnumFacing side, NBTBase nbtBase)
    {
        instance.setFine(((NBTPrimitive)nbtBase).getInt());
    }
}
