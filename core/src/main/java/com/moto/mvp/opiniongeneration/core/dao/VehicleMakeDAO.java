package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "vehicle_make", schema = "moto_mvp_db", catalog = "" )
public class VehicleMakeDAO extends VehicleDetailDAO
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "vehicleMakeSequence" )
    @GenericGenerator( name = "vehicleMakeSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "vehicle_make_sequence")
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

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        VehicleMakeDAO that = ( VehicleMakeDAO ) o;
        return id == that.id &&
                Objects.equals( code, that.code ) &&
                Objects.equals( name, that.name );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, code, name );
    }

    @Override
    public String toString()
    {
        return "VehicleMakeDAO{" +
                "name='" + name + '\'' +
                '}';
    }
}
