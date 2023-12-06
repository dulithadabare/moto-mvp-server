package com.moto.mvp.opiniongeneration.core.model;

public class OpinionGenerationResponse
{
    private final OpinionValue opinionValue;
    private final OpinionGenerationStatus opinionGenerationStatus;
    private final InsurancePremiumValue insurancePremiumValue;
    private String logs;

    public OpinionGenerationResponse()
    {
        this.opinionValue = new OpinionValue();
        this.opinionGenerationStatus = new OpinionGenerationStatus();
        this.insurancePremiumValue = new InsurancePremiumValue();
    }

    public OpinionValue getOpinionValue()
    {
        return opinionValue;
    }

    public OpinionGenerationStatus getOpinionGenerationStatus()
    {
        return opinionGenerationStatus;
    }

    public InsurancePremiumValue getInsurancePremiumValue()
    {
        return insurancePremiumValue;
    }

    public String getLogs()
    {
        return logs;
    }

    public void setLogs( String logs )
    {
        this.logs = logs;
    }
}
