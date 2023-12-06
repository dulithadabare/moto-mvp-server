package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "vehicle_condition", schema = "moto_mvp_db", catalog = "" )
public class VehicleConditionDAO
{
    private int id;
    private String code;
    private String name;
    private int vehicleConditionTypeId;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "vehicleConditionSequence" )
    @GenericGenerator( name = "vehicleConditionSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "vehicle_condition_sequence")
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
    @Column( name = "code" )
    public String getCode()
    {
        return code;
    }

    public void setCode( String code )
    {
        this.code = code;
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
        VehicleConditionDAO that = ( VehicleConditionDAO ) o;
        return id == that.id &&
                vehicleConditionTypeId == that.vehicleConditionTypeId &&
                Objects.equals( code, that.code ) &&
                Objects.equals( name, that.name );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, code, name, vehicleConditionTypeId );
    }
}
