package com.morecreepsrevival.morecreeps.common.capabilities;

public class CaveDrums implements ICaveDrums
{
    private int drumsTime = 0;

    public void setDrumsTime(int drumsTimeIn)
    {
        drumsTime = drumsTimeIn;
    }

    public int getDrumsTime()
    {
        return drumsTime;
    }
}
