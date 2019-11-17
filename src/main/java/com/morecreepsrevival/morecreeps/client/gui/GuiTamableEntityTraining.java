package com.morecreepsrevival.morecreeps.client.gui;

import com.morecreepsrevival.morecreeps.common.networking.message.MessageLevelUpHotdogSkill;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import com.morecreepsrevival.morecreeps.common.helpers.InventoryHelper;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageLevelUpGuineaPigSkill;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import org.lwjgl.input.Keyboard;

public class GuiTamableEntityTraining extends GuiScreen
{
    private final EntityCreepBase entity;

    public GuiTamableEntityTraining(EntityCreepBase entityIn)
    {
        entity = entityIn;
    }

    @Override
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);

        buttonList.clear();

        byte byte0 = -16;

        buttonList.add(new GuiButton(2, width / 2 - 110, height / 4 + 8 + byte0, 98, 20, "\2476<-\247f ATTACK \2476->"));

        buttonList.add(new GuiButton(3, width / 2 + 12, height / 4 + 8 + byte0, 98, 20, "\2476>> \247f DEFENSE \2476<<"));

        buttonList.add(new GuiButton(4, width / 2 - 110, height / 4 + 65 + byte0, 98, 20, "\2476++\247f HEALING \2476++"));

        buttonList.add(new GuiButton(5, width / 2 + 12, height / 4 + 65 + byte0, 98, 20, "\2476((\247f SPEED \2476))"));

        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 158 + byte0, 98, 20, "BACK"));

        buttonList.add(new GuiButton(1, width / 2 + 2, height / 4 + 158 + byte0, 98, 20, "DONE"));
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

        switch (button.id)
        {
            case 0:
                mc.displayGuiScreen(new GuiTamableEntity(entity));

                break;
            case 1:
                mc.displayGuiScreen(null);

                break;
            case 2:
                levelSkill("attack");

                break;
            case 3:
                levelSkill("defend");

                break;
            case 4:
                levelSkill("healing");

                break;
            case 5:
                levelSkill("speed");

                break;
            default:
                break;
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
        for (GuiButton button : buttonList)
        {
            switch (button.id)
            {
                case 2:
                    button.enabled = (entity.getSkillAttack() < 5);

                    break;
                case 3:
                    button.enabled = (entity.getSkillDefend() < 5);

                    break;
                case 4:
                    button.enabled = (entity.getSkillHealing() < 5);

                    break;
                case 5:
                    button.enabled = (entity.getSkillSpeed() < 5);

                    break;
                default:
                    break;
            }
        }

        byte byte0 = -16;

        int k = 0;

        drawWorldBackground(1);

        String creepType = entity.getCreepTypeName();

        drawCenteredString(fontRenderer, "\2476" + entity.getCreepName() + "\'s TRAINING", width / 2, height / 4 - 40, 0xffffff);

        drawCenteredString(fontRenderer, "\247f" + creepType + " LEVEL: \2473" + entity.getLevel(), width / 2, height / 4 - 25, 0xffffff);

        drawString(fontRenderer, buildStat(entity.getSkillAttack()), width / 2 - 107 + k, height / 4 + 38 + byte0, 0xff8d13);

        drawString(fontRenderer, buildStat(entity.getSkillDefend()), width / 2 + 16 + k, height / 4 + 38 + byte0, 0xff8d13);

        drawString(fontRenderer, buildStat(entity.getSkillHealing()), width / 2 - 107 + k, height / 4 + 95 + byte0, 0xff8d13);

        drawString(fontRenderer, buildStat(entity.getSkillSpeed()), width / 2 + 16 + k, height / 4 + 95 + byte0, 0xff8d13);

        switch (creepType)
        {
            case "Guinea Pig":
                drawCenteredString(fontRenderer, "\247fWHEAT REMAINING: \2473" + InventoryHelper.getItemCount(mc.player.inventory, Items.WHEAT), width / 2 + 2 + k, height / 4 + 120 + byte0, 0xff8d13);

                drawCenteredString(fontRenderer, "\2476Each level costs five wheat", width / 2 + 2 + k, height / 4 + 140 + byte0, 0xff8d13);

                break;
            case "Hotdog":
                drawCenteredString(fontRenderer, "\247fBONES REMAINING: \2473" + InventoryHelper.getItemCount(mc.player.inventory, Items.BONE), width / 2 + 2 + k, height / 4 + 120 + byte0, 0xff8d13);

                drawCenteredString(fontRenderer, "\2476Each level costs five bones", width / 2 + 2 + k, height / 4 + 140 + byte0, 0xff8d13);

                break;
            default:
                break;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private String buildStat(int i)
    {
        StringBuilder builder = new StringBuilder();

        for (int j = 0; j < i; j++)
        {
            builder.append("\2473(*) ");
        }

        if (i < 5)
        {
            for (int k = i; k < 5; k++)
            {
                builder.append("\2478(*) ");
            }
        }

        return builder.toString().trim();
    }

    private void levelSkill(String skill)
    {
        int requiredLevel = entity.getRequiredLevelForSkill(skill);

        switch (entity.getCreepTypeName())
        {
            case "Guinea Pig":
                if (InventoryHelper.getItemCount(mc.player.inventory, Items.WHEAT) < 5)
                {
                    mc.player.playSound(CreepsSoundHandler.guineaPigNoWheatSound, 1.0f, 1.0f);

                    return;
                }

                if (entity.getLevel() < requiredLevel)
                {
                    switch (requiredLevel)
                    {
                        case 5:
                            mc.player.playSound(CreepsSoundHandler.guineaPig5LevelSound, 1.0f, 1.0f);

                            break;
                        case 10:
                            mc.player.playSound(CreepsSoundHandler.guineaPig10LevelSound, 1.0f, 1.0f);

                            break;
                        case 15:
                            mc.player.playSound(CreepsSoundHandler.guineaPig15LevelSound, 1.0f, 1.0f);

                            break;
                        case 20:
                            mc.player.playSound(CreepsSoundHandler.guineaPig20LevelSound, 1.0f, 1.0f);

                            break;
                        default:
                            break;
                    }

                    return;
                }

                CreepsPacketHandler.INSTANCE.sendToServer(new MessageLevelUpGuineaPigSkill(entity.getEntityId(), skill));

                mc.player.playSound(CreepsSoundHandler.guineaPigTrainSound, 1.0f, 1.0f);

                break;
            case "Hotdog":
                if (InventoryHelper.getItemCount(mc.player.inventory, Items.BONE) < 5)
                {
                    mc.player.playSound(CreepsSoundHandler.hotdogNoBonesSound, 1.0f, 1.0f);

                    return;
                }

                if (entity.getLevel() < requiredLevel)
                {
                    switch (requiredLevel)
                    {
                        case 5:
                            mc.player.playSound(CreepsSoundHandler.hotdog5LevelSound, 1.0f, 1.0f);

                            break;
                        case 10:
                            mc.player.playSound(CreepsSoundHandler.hotdog10LevelSound, 1.0f, 1.0f);

                            break;
                        case 15:
                            mc.player.playSound(CreepsSoundHandler.hotdog15LevelSound, 1.0f, 1.0f);

                            break;
                        case 20:
                            mc.player.playSound(CreepsSoundHandler.hotdog20LevelSound, 1.0f, 1.0f);

                            break;
                        default:
                            break;
                    }

                    return;
                }

                CreepsPacketHandler.INSTANCE.sendToServer(new MessageLevelUpHotdogSkill(entity.getEntityId(), skill));

                mc.player.playSound(CreepsSoundHandler.hotdogTrainSound, 1.0f, 1.0f);

                break;
            default:
                break;
        }
    }
}
