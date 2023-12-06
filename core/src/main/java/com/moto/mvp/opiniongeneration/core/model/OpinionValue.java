package com.moto.mvp.opiniongeneration.core.model;

public class OpinionValue
{
    private Long forcedSaleValue;
    private Long averageMarketValue;

    public Long getForcedSaleValue()
    {
        return forcedSaleValue;
    }

    public void setForcedSaleValue( Long forcedSaleValue )
    {
        this.forcedSaleValue = forcedSaleValue;
    }

    public Long getAverageMarketValue()
    {
        return averageMarketValue;
    }

    public void setAverageMarketValue( Long averageMarketValue )
    {
        this.averageMarketValue = averageMarketValue;
    }
}
