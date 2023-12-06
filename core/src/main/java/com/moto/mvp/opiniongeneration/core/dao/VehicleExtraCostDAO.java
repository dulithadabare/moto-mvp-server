package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "vehicle_extra_cost", schema = "moto_mvp_db", catalog = "" )
public class VehicleExtraCostDAO
{
    private int id;
    private Integer amount;
    private Integer vehicleExtraId;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "vehicleExtraCostSequence" )
    @GenericGenerator( name = "vehicleExtraCostSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "vehicle_extra_cost_sequence")
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
        VehicleExtraCostDAO that = ( VehicleExtraCostDAO ) o;
        return id == that.id &&
                Objects.equals( amount, that.amount );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, amount );
    }

    @Basic
    @Column( name = "vehicle_extra_id" )
    public Integer getVehicleExtraId()
    {
        return vehicleExtraId;
    }

    public void setVehicleExtraId( Integer vehicleExtraId )
    {
        this.vehicleExtraId = vehicleExtraId;
    }
}
