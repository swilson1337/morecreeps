package com.morecreepsrevival.morecreeps.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class GuineaPigPickedUpStorage implements IStorage<IGuineaPigPickedUp>
{
    @Override
    public NBTBase writeNBT(Capability<IGuineaPigPickedUp> capability, IGuineaPigPickedUp instance, EnumFacing side)
    {
        return new NBTTagByte((byte)(instance.getPickedUp() ? 1 : 0));
    }

    @Override
    public void readNBT(Capability<IGuineaPigPickedUp> capability, IGuineaPigPickedUp instance, EnumFacing side, NBTBase nbtBase)
    {
        instance.setPickedUp(((NBTPrimitive)nbtBase).getByte() == 1);
    }
}
