package com.moto.mvp.opiniongeneration.core.model;

import com.moto.mvp.opiniongeneration.core.dao.InsuranceCoverRuleDAO;

import java.util.List;

public class OpinionGenerationRequest extends AbstractOpinionValuation
{

    private List<InsuranceHistory> vehicleInsuranceHistoryList;
    private List<String> coverList;
    private boolean useCustomCovers;
    private List<InsuranceCoverRuleDAO> customCoverList;
    private int loggedInUserId;

    public List<InsuranceHistory> getVehicleInsuranceHistoryList()
    {
        return vehicleInsuranceHistoryList;
    }

    public void setVehicleInsuranceHistoryList( List<InsuranceHistory> vehicleInsuranceHistoryList )
    {
        this.vehicleInsuranceHistoryList = vehicleInsuranceHistoryList;
    }

    public List<String> getCoverList()
    {
        return coverList;
    }

    public void setCoverList( List<String> coverList )
    {
        this.coverList = coverList;
    }

    public boolean isUseCustomCovers()
    {
        return useCustomCovers;
    }

    public void setUseCustomCovers( boolean useCustomCovers )
    {
        this.useCustomCovers = useCustomCovers;
    }

    public List<InsuranceCoverRuleDAO> getCustomCoverList()
    {
        return customCoverList;
    }

    public void setCustomCoverList( List<InsuranceCoverRuleDAO> customCoverList )
    {
        this.customCoverList = customCoverList;
    }

    public int getLoggedInUserId()
    {
        return loggedInUserId;
    }

    public void setLoggedInUserId( int loggedInUserId )
    {
        this.loggedInUserId = loggedInUserId;
    }
}
