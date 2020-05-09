package com.morecreepsrevival.morecreeps.client.gui;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import com.morecreepsrevival.morecreeps.common.helpers.InventoryHelper;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageBuyItemFromSal;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageRipOffSal;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class GuiSneakySal extends GuiScreen
{
    private final EntitySneakySal sal;

    private final static ResourceLocation backgroundTexture = new ResourceLocation(MoreCreepsAndWeirdos.modid, "textures/gui/gui-screensal.png");

    private int lastItem = 0;

    public GuiSneakySal(EntitySneakySal salIn)
    {
        sal = salIn;
    }

    @Override
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);

        buttonList.clear();

        byte byte0 = -18;

        int[] shopItems = sal.getShopItems();

        lastItem = shopItems[0];

        float salePrice = sal.getSalePrice();

        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 158 + byte0, 98, 20, "RIPOFF SAL"));

        buttonList.add(new GuiButton(1, width / 2 + 2, height / 4 + 158 + byte0, 98, 20, "DONE"));

        buttonList.add(new GuiButton(2, width / 2 - 170, height / 4 + 8 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[0]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[0]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(3, width / 2 + 2, height / 4 + 8 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[1]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[1]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(4, width / 2 - 170, height / 4 + 35 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[2]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[2]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(5, width / 2 + 2, height / 4 + 35 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[3]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[3]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(6, width / 2 - 170, height / 4 + 65 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[4]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[4]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(7, width / 2 + 2, height / 4 + 65 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[5]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[5]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(8, width / 2 - 170, height / 4 + 95 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[6]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[6]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(9, width / 2 + 2, height / 4 + 95 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[7]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[7]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(10, width / 2 - 170, height / 4 + 125 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[8]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[8]].getUnlocalizedName() + ".name")));

        buttonList.add(new GuiButton(11, width / 2 + 2, height / 4 + 125 + byte0, 155, 20, "\2472$\2476" + Math.round(EntitySneakySal.itemPrices[shopItems[9]] * salePrice) + " \247f" + I18n.format(EntitySneakySal.itemDefinitions[shopItems[9]].getUnlocalizedName() + ".name")));
    }

    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (!button.enabled)
        {
            return;
        }

        mc.displayGuiScreen(null);

        if (button.id == 0)
        {
            CreepsPacketHandler.INSTANCE.sendToServer(new MessageRipOffSal(sal.getEntityId()));

            return;
        }
        else if (button.id > 1 && button.id < 12)
        {
            CreepsPacketHandler.INSTANCE.sendToServer(new MessageBuyItemFromSal(sal.getEntityId(), button.id - 2));
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (sal.getShopItems()[0] != lastItem)
        {
            initGui();
        }

        drawWorldBackground(1);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        mc.renderEngine.bindTexture(backgroundTexture);

        drawTexturedModalRect(20, 20, 0, 0, width + 400, height);

        drawCenteredString(fontRenderer, "\2475******* \247fWELCOME TO SAL'S SHOP \2475*******", width / 2, height / 4 - 40, 0xffffff);

        drawCenteredString(fontRenderer, "\247eYour cash: \2472$\2476" + InventoryHelper.getItemCount(mc.player.inventory, CreepsItemHandler.money), width / 2, height / 4 - 25, 0xffffff);

        if (sal.isBlackFriday())
        {
            drawCenteredString(fontRenderer, "IT'S BLACK FRIDAY - 50% OFF", width / 2, height / 4 - 55, 0xffffff);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
