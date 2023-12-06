package com.moto.mvp.opiniongeneration.core.model;

import com.moto.mvp.foundation.core.constant.Status;
import com.moto.mvp.opiniongeneration.core.dao.FlaggedStatusDAO;

public class OpinionGenerationStatus
{
    private int status;
    private String message;
    private boolean isFlagged;
    private FlaggedStatusDAO flaggedStatus;

    public OpinionGenerationStatus()
    {
        this.status = Status.OPINION_GENERATION_SUCCESS.getCode();
        this.message = Status.OPINION_GENERATION_SUCCESS.name();
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus( int status )
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public boolean isFlagged()
    {
        return isFlagged;
    }

    public void setFlagged( boolean flagged )
    {
        isFlagged = flagged;
    }

    public FlaggedStatusDAO getFlaggedStatus()
    {
        return flaggedStatus;
    }

    public void setFlaggedStatus( FlaggedStatusDAO flaggedStatus )
    {
        this.flaggedStatus = flaggedStatus;
    }
}
