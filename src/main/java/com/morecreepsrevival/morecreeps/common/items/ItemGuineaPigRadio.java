package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityGuineaPig;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import com.morecreepsrevival.morecreeps.common.capabilities.GuineaPigPickedUpProvider;
import com.morecreepsrevival.morecreeps.common.capabilities.IGuineaPigPickedUp;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemGuineaPigRadio extends CreepsItem
{
    public ItemGuineaPigRadio()
    {
        super("guinea_pig_radio");

        setMaxStackSize(1);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        if (!player.isSneaking())
        {
            if (player.isRiding())
            {
                player.sendMessage(new TextComponentString("Get off that creature before using the Guinea Pig Radio!"));
            }
            else
            {
                IGuineaPigPickedUp capability = player.getHeldItem(hand).getCapability(GuineaPigPickedUpProvider.capability, null);

                if (capability != null)
                {
                    if (capability.getPickedUp())
                    {
                        for (Entity entity : player.getPassengers())
                        {
                            if (entity instanceof EntityGuineaPig)
                            {
                                entity.dismountRidingEntity();
                            }
                        }

                        capability.setPickedUp(false);
                    }
                    else
                    {
                        player.playSound(CreepsSoundHandler.guineaPigRadioSound, 1.0f, 1.0f);

                        for (EntityGuineaPig guineaPig : world.getEntities(EntityGuineaPig.class, (entity) -> entity != null && entity.isPlayerOwner(player) && !entity.isRiding() && !player.equals(entity.getRidingEntity())))
                        {
                            guineaPig.copyLocationAndAnglesFrom(player);

                            guineaPig.startRiding(player, true);
                        }

                        capability.setPickedUp(true);
                    }
                }
            }
        }

        return super.onItemRightClick(world, player, hand);
    }
}
