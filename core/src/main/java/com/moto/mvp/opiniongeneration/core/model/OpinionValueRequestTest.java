package com.moto.mvp.opiniongeneration.core.model;

public class OpinionValueRequestTest
{
    private OpinionGenerationRequest opinionGenerationRequest;
    private VehicleValuation mostMatchingValuation;

    public OpinionGenerationRequest getOpinionGenerationRequest()
    {
        return opinionGenerationRequest;
    }

    public void setOpinionGenerationRequest( OpinionGenerationRequest opinionGenerationRequest )
    {
        this.opinionGenerationRequest = opinionGenerationRequest;
    }

    public VehicleValuation getMostMatchingValuation()
    {
        return mostMatchingValuation;
    }

    public void setMostMatchingValuation( VehicleValuation mostMatchingValuation )
    {
        this.mostMatchingValuation = mostMatchingValuation;
    }
}
