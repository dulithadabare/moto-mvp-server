package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "vehicle_condition_rating", schema = "moto_mvp_db", catalog = "" )
public class VehicleConditionRatingDAO
{
    private int id;
    private String name;
    private int rating;
    private int vehicleConditionTypeId;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "vehicleConditionRatingSequence" )
    @GenericGenerator( name = "vehicleConditionRatingSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "vehicle_condition_rating_sequence")
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
    @Column( name = "rating" )
    public int getRating()
    {
        return rating;
    }

    public void setRating( int rating )
    {
        this.rating = rating;
    }

    @Basic
    @Column( name = "vehicle_condition_type_id" )
    public int getVehicleConditionTypeId()
    {
        return vehicleConditionTypeId;
    }

    public void setVehicleConditionTypeId( int vehicleConditionTypeId )
    {
        this.vehicleConditionTypeId = vehicleConditionTypeId;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        VehicleConditionRatingDAO that = ( VehicleConditionRatingDAO ) o;
        return id == that.id &&
                rating == that.rating &&
                vehicleConditionTypeId == that.vehicleConditionTypeId &&
                Objects.equals( name, that.name );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, rating, vehicleConditionTypeId );
    }
}
