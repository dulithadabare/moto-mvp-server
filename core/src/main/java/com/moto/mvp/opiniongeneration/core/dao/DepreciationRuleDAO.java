package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "depreciation_rule", schema = "moto_mvp_db", catalog = "" )
public class DepreciationRuleDAO
{
    private int id;
    private Integer currentVehicleStatusId;
    private Integer previousVehicleStatusId;
    private Integer amount;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "depreciationRuleSequence" )
    @GenericGenerator( name = "depreciationRuleSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "depreciation_rule_sequence")
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
    @Column( name = "current_vehicle_status_id" )
    public Integer getCurrentVehicleStatusId()
    {
        return currentVehicleStatusId;
    }

    public void setCurrentVehicleStatusId( Integer currentVehicleStatusId )
    {
        this.currentVehicleStatusId = currentVehicleStatusId;
    }

    @Basic
    @Column( name = "previous_vehicle_status_id" )
    public Integer getPreviousVehicleStatusId()
    {
        return previousVehicleStatusId;
    }

    public void setPreviousVehicleStatusId( Integer previousVehicleStatusId )
    {
        this.previousVehicleStatusId = previousVehicleStatusId;
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
        DepreciationRuleDAO that = ( DepreciationRuleDAO ) o;
        return id == that.id &&
                Objects.equals( currentVehicleStatusId, that.currentVehicleStatusId ) &&
                Objects.equals( previousVehicleStatusId, that.previousVehicleStatusId ) &&
                Objects.equals( amount, that.amount );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, currentVehicleStatusId, previousVehicleStatusId, amount );
    }
}
