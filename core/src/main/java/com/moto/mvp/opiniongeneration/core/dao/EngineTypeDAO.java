package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "engine_type", schema = "moto_mvp_db", catalog = "" )
public class EngineTypeDAO extends VehicleDetailDAO
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "engineTypeSequence" )
    @GenericGenerator( name = "engineTypeSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "engine_type_sequence")
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
        EngineTypeDAO that = ( EngineTypeDAO ) o;
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
