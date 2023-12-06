package com.moto.mvp.opiniongeneration.core.model;

import java.util.List;

public class InsurancePremiumValue
{
    private double insurancePremiumValue;
    private List<String> usedCovers;

    public double getInsurancePremiumValue()
    {
        return insurancePremiumValue;
    }

    public void setInsurancePremiumValue( double insurancePremiumValue )
    {
        this.insurancePremiumValue = insurancePremiumValue;
    }

    public List<String> getUsedCovers()
    {
        return usedCovers;
    }

    public void setUsedCovers( List<String> usedCovers )
    {
        this.usedCovers = usedCovers;
    }
}
