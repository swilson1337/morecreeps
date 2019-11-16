package com.morecreepsrevival.morecreeps.client.gui;

import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class GuiSneakySal extends GuiScreen
{
    private final EntitySneakySal sal;

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

        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 158 + byte0, 98, 20, "RIPOFF SAL"));

        buttonList.add(new GuiButton(1, width / 2 + 2, height / 4 + 158 + byte0, 98, 20, "DONE"));

        buttonList.add(new GuiButton(2, width / 2 - 170, height / 4 + 8 + byte0, 155, 20, "Slot 0"));

        buttonList.add(new GuiButton(3, width / 2 + 2, height / 4 + 8 + byte0, 155, 20, "Slot 1"));

        buttonList.add(new GuiButton(4, width / 2 - 170, height / 4 + 35 + byte0, 155, 20, "Slot 2"));

        buttonList.add(new GuiButton(5, width / 2 + 2, height / 4 + 35 + byte0, 155, 20, "Slot 3"));

        buttonList.add(new GuiButton(6, width / 2 - 170, height / 4 + 65 + byte0, 155, 20, "Slot 4"));

        buttonList.add(new GuiButton(7, width / 2 + 2, height / 4 + 65 + byte0, 155, 20, "Slot 5"));

        buttonList.add(new GuiButton(8, width / 2 - 170, height / 4 + 95 + byte0, 155, 20, "Slot 6"));

        buttonList.add(new GuiButton(9, width / 2 + 2, height / 4 + 95 + byte0, 155, 20, "Slot 7"));

        buttonList.add(new GuiButton(10, width / 2 - 170, height / 4 + 125 + byte0, 155, 20, "Slot 8"));

        buttonList.add(new GuiButton(11, width / 2 + 2, height / 4 + 125 + byte0, 155, 20, "Slot 9"));

        Minecraft.getMinecraft().player.playSound(CreepsSoundHandler.salGreetingSound, 1.0f, 1.0f);
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
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
