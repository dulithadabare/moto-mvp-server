package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "reference_parent_val", schema = "moto_mvp_db", catalog = "" )
public class ReferenceParentValDAO
{
    private int id;
    private int referenceValuationDbId;
    private String value;
    private List<ReferenceChildValDAO> referenceMappingValList;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "referenceParentValSequence" )
    @GenericGenerator( name = "referenceParentValSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "reference_parent_val_sequence")
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
    @Column( name = "reference_valuation_db_id" )
    public int getReferenceValuationDbId()
    {
        return referenceValuationDbId;
    }

    public void setReferenceValuationDbId( int referenceValuationDbId )
    {
        this.referenceValuationDbId = referenceValuationDbId;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referenceParentValId", orphanRemoval = true, fetch=FetchType.EAGER)
    public List<ReferenceChildValDAO> getReferenceMappingValList()
    {
        return referenceMappingValList;
    }

    public void setReferenceMappingValList( List<ReferenceChildValDAO> referenceMappingValList )
    {
        this.referenceMappingValList = referenceMappingValList;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ReferenceParentValDAO that = ( ReferenceParentValDAO ) o;
        return id == that.id &&
                referenceValuationDbId == that.referenceValuationDbId &&
                Objects.equals( value, that.value );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, referenceValuationDbId, value );
    }
}
