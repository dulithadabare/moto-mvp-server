package com.moto.mvp.opiniongeneration.core.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "engine_capacity_type", schema = "moto_mvp_db", catalog = "" )
public class EngineCapacityTypeDAO extends VehicleDetailDAO
{
    @Id
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
        EngineCapacityTypeDAO that = ( EngineCapacityTypeDAO ) o;
        return id == that.id &&
                Objects.equals( code, that.code ) &&
                Objects.equals( name, that.name );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, code, name );
    }
}
