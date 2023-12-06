package com.moto.mvp.opiniongeneration.core.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "flagged_status", schema = "moto_mvp_db", catalog = "" )
public class FlaggedStatusDAO
{
    private int code;
    private String name;
    private String message;

    @Id
    @Column( name = "code" )
    public int getCode()
    {
        return code;
    }

    public void setCode( int code )
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
    @Column( name = "message" )
    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        FlaggedStatusDAO that = ( FlaggedStatusDAO ) o;
        return code == that.code &&
                Objects.equals( name, that.name ) &&
                Objects.equals( message, that.message );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( code, name, message );
    }
}
