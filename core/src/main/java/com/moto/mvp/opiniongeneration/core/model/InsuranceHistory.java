package com.moto.mvp.opiniongeneration.core.model;

import java.util.Date;

public class InsuranceHistory
{
    private Long valueOnPreviousRenewal;
    private Date previousRenewalDate;

    public Long getValueOnPreviousRenewal()
    {
        return valueOnPreviousRenewal;
    }

    public void setValueOnPreviousRenewal( Long valueOnPreviousRenewal )
    {
        this.valueOnPreviousRenewal = valueOnPreviousRenewal;
    }

    public Date getPreviousRenewalDate()
    {
        return previousRenewalDate;
    }

    public void setPreviousRenewalDate( Date previousRenewalDate )
    {
        this.previousRenewalDate = previousRenewalDate;
    }
}
