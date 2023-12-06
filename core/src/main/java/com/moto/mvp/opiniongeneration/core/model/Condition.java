package com.moto.mvp.opiniongeneration.core.model;

public class Condition
{
    private String name;
    private String vehicleConditionCode;
    private int rating;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating( int rating )
    {
        this.rating = rating;
    }

    public String getVehicleConditionCode()
    {
        return vehicleConditionCode;
    }

    public void setVehicleConditionCode( String vehicleConditionCode )
    {
        this.vehicleConditionCode = vehicleConditionCode;
    }
}
