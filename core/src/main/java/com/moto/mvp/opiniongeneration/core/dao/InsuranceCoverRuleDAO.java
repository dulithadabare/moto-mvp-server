package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "insurance_cover_rule", schema = "moto_mvp_db", catalog = "" )
public class InsuranceCoverRuleDAO
{
    private int id;
    private String code;
    private String name;
    private Double amount;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "insuranceCoverRuleSequence" )
    @GenericGenerator( name = "insuranceCoverRuleSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "insurance_cover_rule_sequence")
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
    @Column( name = "amount" )
    public Double getAmount()
    {
        return amount;
    }

    public void setAmount( Double amount )
    {
        this.amount = amount;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        InsuranceCoverRuleDAO that = ( InsuranceCoverRuleDAO ) o;
        return id == that.id &&
                Objects.equals( code, that.code ) &&
                Objects.equals( name, that.name ) &&
                Objects.equals( amount, that.amount );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, code, name, amount );
    }
}
