package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;

public class MarketAdjustmentRuleEntity
{
    private Integer makeId;
    private Integer modelId;
    private Integer yearOfManufacture;
    private Integer engineTypeId;
    private Integer engineCapacityCc;
    private Integer bodyColourId;
    private Integer countryOfOriginId;
    private boolean isPercentage;
    private Integer amount;

    private final Integer ANY = null;

    public boolean applyRule( OpinionGenerationRequest opinionGenerationRequest )
    {
        return checkRule( opinionGenerationRequest );
    }

    private boolean checkRule( OpinionGenerationRequest opinionGenerationRequest )
    {
        return checkMake( opinionGenerationRequest );
    }

    private boolean checkMake( OpinionGenerationRequest opinionGenerationRequest )
    {
        if( this.makeId == ANY || opinionGenerationRequest.getVehicleDetails().getVehicleMake().getId() == this.makeId )
        {
            return checkModel( opinionGenerationRequest );
        }
        return false;
    }

    private boolean checkModel( OpinionGenerationRequest opinionGenerationRequest )
    {
        if( this.modelId == ANY || opinionGenerationRequest.getVehicleDetails().getVehicleModel().getId() == this.modelId )
        {
            return checkYearOfManufacture( opinionGenerationRequest );
        }
        return false;
    }

    private boolean checkYearOfManufacture( OpinionGenerationRequest opinionGenerationRequest )
    {
        if( this.yearOfManufacture == ANY || opinionGenerationRequest.getVehicleDetails().getYearOfManufacture() == this.yearOfManufacture )
        {
            return checkEngineType( opinionGenerationRequest );
        }
        return false;
    }

    private boolean checkEngineType( OpinionGenerationRequest opinionGenerationRequest )
    {
        if( this.engineTypeId == ANY || opinionGenerationRequest.getVehicleDetails().getEngineType().getId() == this.engineTypeId )
        {
            return checkEngineCapacityCC( opinionGenerationRequest );
        }
        return false;
    }

    private boolean checkEngineCapacityCC( OpinionGenerationRequest opinionGenerationRequest )
    {
        if( this.engineCapacityCc == ANY || opinionGenerationRequest.getVehicleDetails().getEngineCapacity() == this.engineCapacityCc )
        {
            return checkBodyColour( opinionGenerationRequest );
        }
        return false;
    }

    private boolean checkBodyColour( OpinionGenerationRequest opinionGenerationRequest )
    {
        if( this.getBodyColourId() == ANY || opinionGenerationRequest.getVehicleDetails().getBodyColour().getId() == this.bodyColourId )
        {
            return checkCountryOfOrigin( opinionGenerationRequest );
        }
        return false;
    }

    private boolean checkCountryOfOrigin( OpinionGenerationRequest opinionGenerationRequest )
    {
        if( this.countryOfOriginId == ANY || opinionGenerationRequest.getVehicleDetails().getCountryOfOrigin().getId() == this.countryOfOriginId )
        {
            return true;
        }
        return false;
    }


    public Integer getMakeId()
    {
        return makeId;
    }

    public void setMakeId( Integer makeId )
    {
        this.makeId = makeId;
    }

    public Integer getModelId()
    {
        return modelId;
    }

    public void setModelId( Integer modelId )
    {
        this.modelId = modelId;
    }

    public Integer getYearOfManufacture()
    {
        return yearOfManufacture;
    }

    public void setYearOfManufacture( Integer yearOfManufacture )
    {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Integer getEngineTypeId()
    {
        return engineTypeId;
    }

    public void setEngineTypeId( Integer engineTypeId )
    {
        this.engineTypeId = engineTypeId;
    }

    public Integer getEngineCapacityCc()
    {
        return engineCapacityCc;
    }

    public void setEngineCapacityCc( Integer engineCapacityCc )
    {
        this.engineCapacityCc = engineCapacityCc;
    }

    public Integer getBodyColourId()
    {
        return bodyColourId;
    }

    public void setBodyColourId( Integer bodyColourId )
    {
        this.bodyColourId = bodyColourId;
    }

    public Integer getCountryOfOriginId()
    {
        return countryOfOriginId;
    }

    public void setCountryOfOriginId( Integer countryOfOriginId )
    {
        this.countryOfOriginId = countryOfOriginId;
    }

    public boolean isPercentage()
    {
        return isPercentage;
    }

    public void setPercentage( boolean percentage )
    {
        isPercentage = percentage;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount( Integer amount )
    {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return "MAKE : " + this.makeId + " " +
                "MODEL : " + this.modelId + " " +
                "YEAR : " + this.yearOfManufacture + " " +
                "ENGINE TYPE : " + this.engineTypeId + " " +
                "ENGINE CAPACITY CC : " + this.engineCapacityCc + " " +
                "BODY COLOUR : " + this.bodyColourId + " " +
                "COUNTRY : " + this.countryOfOriginId + " " +
                "IS PERCENTAGE : " + this.isPercentage + " " +
                "AMOUNT : " + this.amount
                ;
    }
}
