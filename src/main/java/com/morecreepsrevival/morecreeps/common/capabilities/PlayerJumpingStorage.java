package com.morecreepsrevival.morecreeps.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PlayerJumpingStorage implements IStorage<IPlayerJumping>
{
    @Override
    public NBTBase writeNBT(Capability<IPlayerJumping> capability, IPlayerJumping instance, EnumFacing side)
    {
        return new NBTTagByte((byte)(instance.getJumping() ? 1 : 0));
    }

    @Override
    public void readNBT(Capability<IPlayerJumping> capability, IPlayerJumping instance, EnumFacing side, NBTBase nbtBase)
    {
        instance.setJumping(((NBTPrimitive)nbtBase).getByte() == 1);
    }
}
