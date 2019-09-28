package com.morecreepsrevival.morecreeps.common.capabilities;

public class LawyerFine implements ILawyerFine
{
    private int fine = 0;

    public void setFine(int fineIn)
    {
        fine = Math.max(0, fineIn);
    }

    public int getFine()
    {
        return fine;
    }

    public void addFine(int fineToAdd)
    {
        setFine(fine + fineToAdd);
    }

    public void takeFine(int fineToTake)
    {
        setFine(fine - fineToTake);
    }
}
