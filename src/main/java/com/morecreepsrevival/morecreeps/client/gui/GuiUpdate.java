package com.morecreepsrevival.morecreeps.client.gui;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraftforge.common.ForgeVersion;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class GuiUpdate extends GuiYesNo
{
    private final ForgeVersion.CheckResult checkResult;

    public GuiUpdate(ForgeVersion.CheckResult result)
    {
        super(null, "A newer version of More Creeps and Weirdos is available!" + ((result.target != null) ? (" (" + result.target.toString() + ")") : ""), "Would you like to download the update?", 0);

        checkResult = result;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 144, "Don't show again for this update."));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
                if (checkResult.url != null)
                {
                    try
                    {
                        Desktop desktop = Desktop.getDesktop();

                        desktop.browse(new URI(checkResult.url));
                    }
                    catch (Exception ignored)
                    {
                    }
                }

                mc.displayGuiScreen(null);

                break;
            case 1:
                mc.displayGuiScreen(null);

                break;
            case 200:
                if (checkResult.target != null)
                {
                    MoreCreepsConfig.hideUpdateGuiForVersion(checkResult.target.toString());
                }

                mc.displayGuiScreen(null);

                break;
            default:
                break;
        }
    }
}
