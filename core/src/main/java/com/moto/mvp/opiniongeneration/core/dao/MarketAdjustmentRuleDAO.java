package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "market_adjustment_rule", schema = "moto_mvp_db", catalog = "" )
public class MarketAdjustmentRuleDAO
{
    private int id;
    private Integer makeId;
    private Integer modelId;
    private Integer yearOfManufacture;
    private Integer engineTypeId;
    private Integer engineCapacity;
    private Integer bodyColourId;
    private Integer countryUsedInId;
    private boolean isPercentage;
    private Integer amount;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "marketAdjustmentRuleSequence" )
    @GenericGenerator( name = "marketAdjustmentRuleSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "market_adjustment_rule_sequence")
    })
    @Column( name = "id" )
    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    @Basic
    @Column( name = "make_id" )
    public Integer getMakeId()
    {
        return makeId;
    }

    public void setMakeId( Integer makeId )
    {
        this.makeId = makeId;
    }

    @Basic
    @Column( name = "model_id" )
    public Integer getModelId()
    {
        return modelId;
    }

    public void setModelId( Integer modelId )
    {
        this.modelId = modelId;
    }

    @Basic
    @Column( name = "year_of_manufacture" )
    public Integer getYearOfManufacture()
    {
        return yearOfManufacture;
    }

    public void setYearOfManufacture( Integer yearOfManufacture )
    {
        this.yearOfManufacture = yearOfManufacture;
    }

    @Basic
    @Column( name = "engine_type_id" )
    public Integer getEngineTypeId()
    {
        return engineTypeId;
    }

    public void setEngineTypeId( Integer engineTypeId )
    {
        this.engineTypeId = engineTypeId;
    }

    @Basic
    @Column( name = "engine_capacity" )
    public Integer getEngineCapacity()
    {
        return engineCapacity;
    }

    public void setEngineCapacity( Integer engineCapacityCc )
    {
        this.engineCapacity = engineCapacityCc;
    }

    @Basic
    @Column( name = "body_colour_id" )
    public Integer getBodyColourId()
    {
        return bodyColourId;
    }

    public void setBodyColourId( Integer bodyColourId )
    {
        this.bodyColourId = bodyColourId;
    }

    @Basic
    @Column( name = "country_used_in_id" )
    public Integer getCountryUsedInId()
    {
        return countryUsedInId;
    }

    public void setCountryUsedInId( Integer countryUsedInId )
    {
        this.countryUsedInId = countryUsedInId;
    }

    @Basic
    @Column( name = "is_percentage" )
    public boolean getIsPercentage()
    {
        return isPercentage;
    }

    public void setIsPercentage( boolean isPercentage )
    {
        this.isPercentage = isPercentage;
    }

    @Basic
    @Column( name = "amount" )
    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount( Integer amount )
    {
        this.amount = amount;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        MarketAdjustmentRuleDAO that = ( MarketAdjustmentRuleDAO ) o;
        return id == that.id &&
                Objects.equals( makeId, that.makeId ) &&
                Objects.equals( modelId, that.modelId ) &&
                Objects.equals( yearOfManufacture, that.yearOfManufacture ) &&
                Objects.equals( engineTypeId, that.engineTypeId ) &&
                Objects.equals( engineCapacity, that.engineCapacity ) &&
                Objects.equals( bodyColourId, that.bodyColourId ) &&
                Objects.equals( countryUsedInId, that.countryUsedInId ) &&
                Objects.equals( isPercentage, that.isPercentage ) &&
                Objects.equals( amount, that.amount );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, makeId, modelId, yearOfManufacture, engineTypeId, engineCapacity, bodyColourId, countryUsedInId, isPercentage, amount );
    }
}
