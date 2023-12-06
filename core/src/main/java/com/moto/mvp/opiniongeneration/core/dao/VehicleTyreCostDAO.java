package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "vehicle_tyre_cost", schema = "moto_mvp_db", catalog = "" )
public class VehicleTyreCostDAO
{
    private int id;
    private String size;
    private Integer amount;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "vehicleTyreCostSequence" )
    @GenericGenerator( name = "vehicleTyreCostSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "vehicle_tyre_cost_sequence")
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
    @Column( name = "size" )
    public String getSize()
    {
        return size;
    }

    public void setSize( String size )
    {
        this.size = size;
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
        VehicleTyreCostDAO that = ( VehicleTyreCostDAO ) o;
        return id == that.id &&
                Objects.equals( size, that.size ) &&
                Objects.equals( amount, that.amount );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, size, amount );
    }
}
