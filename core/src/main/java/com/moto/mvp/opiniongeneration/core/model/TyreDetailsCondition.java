package com.moto.mvp.opiniongeneration.core.model;

public class TyreDetailsCondition
{
    private int size;
    private int wearPercentage;

    public int getSize()
    {
        return size;
    }

    public void setSize( int size )
    {
        this.size = size;
    }

    public int getWearPercentage()
    {
        return wearPercentage;
    }

    public void setWearPercentage( int wearPercentage )
    {
        this.wearPercentage = wearPercentage;
    }

    @Override
    public String toString()
    {
        return "TyreDetailsCondition{" +
                "size=" + size +
                ", wearPercentage=" + wearPercentage +
                '}';
    }
}
