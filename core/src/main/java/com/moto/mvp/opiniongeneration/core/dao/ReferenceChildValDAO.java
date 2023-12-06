package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "reference_child_val", schema = "moto_mvp_db", catalog = "" )
public class ReferenceChildValDAO
{
    private int id;
    private int referenceParentValId;
    private String value;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "referenceChildValSequence" )
    @GenericGenerator( name = "referenceChildValSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "reference_child_val_sequence")
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
    @Column( name = "reference_parent_val_id" )
    public int getReferenceParentValId()
    {
        return referenceParentValId;
    }

    public void setReferenceParentValId( int referenceMappedValId )
    {
        this.referenceParentValId = referenceMappedValId;
    }

    @Basic
    @Column( name = "value" )
    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ReferenceChildValDAO that = ( ReferenceChildValDAO ) o;
        return id == that.id &&
                referenceParentValId == that.referenceParentValId &&
                Objects.equals( value, that.value );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, referenceParentValId, value );
    }
}
