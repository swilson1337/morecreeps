package com.morecreepsrevival.morecreeps.common.capabilities;

public class GuineaPigPickedUp implements IGuineaPigPickedUp
{
    private boolean pickedUp = false;

    public void setPickedUp(boolean pickedUpIn)
    {
        pickedUp = pickedUpIn;
    }

    public boolean getPickedUp()
    {
        return pickedUp;
    }
}
