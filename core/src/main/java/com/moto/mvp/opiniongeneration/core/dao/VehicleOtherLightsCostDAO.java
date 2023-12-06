package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "vehicle_other_lights_cost", schema = "moto_mvp_db", catalog = "" )
public class VehicleOtherLightsCostDAO
{
    private int id;
    private String name;
    private Integer amount;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "vehicleOtherLightsCostSequence" )
    @GenericGenerator( name = "vehicleOtherLightsCostSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "vehicle_other_lights_cost_sequence")
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
    @Column( name = "name" )
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
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
        VehicleOtherLightsCostDAO that = ( VehicleOtherLightsCostDAO ) o;
        return id == that.id &&
                Objects.equals( name, that.name ) &&
                Objects.equals( amount, that.amount );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, amount );
    }
}
