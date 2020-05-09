package com.morecreepsrevival.morecreeps.common.capabilities;

public class PlayerJumping implements IPlayerJumping
{
    private boolean jumping = false;

    public void setJumping(boolean jumpingIn)
    {
        jumping = jumpingIn;
    }

    public boolean getJumping()
    {
        return jumping;
    }
}
