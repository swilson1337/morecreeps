package com.morecreepsrevival.morecreeps.common.capabilities;

public interface ILawyerFine
{
    void setFine(int fineIn);

    int getFine();

    void addFine(int fineToAdd);

    void takeFine(int fineToTake);
}
