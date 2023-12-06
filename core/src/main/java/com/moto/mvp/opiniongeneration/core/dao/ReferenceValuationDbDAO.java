package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "reference_valuation_db", schema = "moto_mvp_db", catalog = "" )
public class ReferenceValuationDbDAO
{
    private int id;
    private String name;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "referenceValuationDbSequence" )
    @GenericGenerator( name = "referenceValuationDbSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "reference_valuation_db_sequence")
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

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ReferenceValuationDbDAO that = ( ReferenceValuationDbDAO ) o;
        return id == that.id &&
                Objects.equals( name, that.name );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name );
    }
}
