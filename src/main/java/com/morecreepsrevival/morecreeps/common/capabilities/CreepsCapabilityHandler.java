package com.morecreepsrevival.morecreeps.common.capabilities;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = MoreCreepsAndWeirdos.modid)
public class CreepsCapabilityHandler
{
    public static void registerCapabilities()
    {
        CapabilityManager.INSTANCE.register(IGuineaPigPickedUp.class, new GuineaPigPickedUpStorage(), GuineaPigPickedUp::new);

        CapabilityManager.INSTANCE.register(ILawyerFine.class, new LawyerFineStorage(), LawyerFine::new);

        CapabilityManager.INSTANCE.register(ICaveDrums.class, new CaveDrumsStorage(), CaveDrums::new);

        CapabilityManager.INSTANCE.register(IPlayerJumping.class, new PlayerJumpingStorage(), PlayerJumping::new);
    }

    @SubscribeEvent
    public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event)
    {
        Item item = event.getObject().getItem();

        if (item == CreepsItemHandler.guineaPigRadio)
        {
            event.addCapability(new ResourceLocation(MoreCreepsAndWeirdos.modid, "guinea_pig_picked_up"), new GuineaPigPickedUpProvider());
        }
    }

    @SubscribeEvent
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        Entity entity = event.getObject();

        if (entity instanceof EntityPlayer)
        {
            event.addCapability(new ResourceLocation(MoreCreepsAndWeirdos.modid, "lawyer_fine"), new LawyerFineProvider());

            event.addCapability(new ResourceLocation(MoreCreepsAndWeirdos.modid, "caveman_club_drums"), new CaveDrumsProvider());

            event.addCapability(new ResourceLocation(MoreCreepsAndWeirdos.modid, "player_jumping"), new PlayerJumpingProvider());
        }
    }
}
