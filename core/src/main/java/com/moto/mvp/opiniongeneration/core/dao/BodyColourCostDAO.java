package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "body_colour_cost", schema = "moto_mvp_db", catalog = "" )
public class BodyColourCostDAO
{
    private int id;
    private Integer bodyColourId;
    private Integer amount;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "bodyColourCostSequence" )
    @GenericGenerator( name = "bodyColourCostSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "body_colour_cost_sequence")
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
        BodyColourCostDAO that = ( BodyColourCostDAO ) o;
        return id == that.id &&
                Objects.equals( bodyColourId, that.bodyColourId ) &&
                Objects.equals( amount, that.amount );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, bodyColourId, amount );
    }
}
