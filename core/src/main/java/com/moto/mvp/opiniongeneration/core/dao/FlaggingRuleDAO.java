package com.moto.mvp.opiniongeneration.core.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "flagging_rule", schema = "moto_mvp_db", catalog = "" )
public class FlaggingRuleDAO
{
    private int id;
    private int flaggedStatusCode;
    private int thresholdLowerBound;
    private int thresholdUpperBound;
    private boolean isInternalReview;
    private boolean isExternalReview;
    private boolean isPercentage;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "flaggingRuleSequence" )
    @GenericGenerator( name = "flaggingRuleSequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "flagging_rule_sequence")
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
    @Column( name = "flagged_status_code" )
    public int getFlaggedStatusCode()
    {
        return flaggedStatusCode;
    }

    public void setFlaggedStatusCode( int flaggedStatusCode )
    {
        this.flaggedStatusCode = flaggedStatusCode;
    }

    @Basic
    @Column( name = "threshold_lower_bound" )
    public int getThresholdLowerBound()
    {
        return thresholdLowerBound;
    }

    public void setThresholdLowerBound( int thresholdLowerBound )
    {
        this.thresholdLowerBound = thresholdLowerBound;
    }

    @Basic
    @Column( name = "threshold_upper_bound" )
    public int getThresholdUpperBound()
    {
        return thresholdUpperBound;
    }

    public void setThresholdUpperBound( int thresholdUpperBound )
    {
        this.thresholdUpperBound = thresholdUpperBound;
    }

    @Basic
    @Column( name = "is_internal_review" )
    public boolean isInternalReview()
    {
        return isInternalReview;
    }

    public void setInternalReview( boolean internalReview )
    {
        isInternalReview = internalReview;
    }

    @Basic
    @Column( name = "is_external_review" )
    public boolean isExternalReview()
    {
        return isExternalReview;
    }

    public void setExternalReview( boolean externalReview )
    {
        isExternalReview = externalReview;
    }

    @Basic
    @Column( name = "is_percentage" )
    public boolean isPercentage()
    {
        return isPercentage;
    }

    public void setPercentage( boolean percentage )
    {
        isPercentage = percentage;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        FlaggingRuleDAO that = ( FlaggingRuleDAO ) o;
        return id == that.id &&
                flaggedStatusCode == that.flaggedStatusCode &&
                thresholdLowerBound == that.thresholdLowerBound &&
                thresholdUpperBound == that.thresholdUpperBound &&
                isInternalReview == that.isInternalReview &&
                isExternalReview == that.isExternalReview &&
                isPercentage == that.isPercentage;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, flaggedStatusCode, thresholdLowerBound, thresholdUpperBound, isInternalReview, isExternalReview, isPercentage );
    }
}
