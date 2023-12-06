package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationStatus;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBasicRule implements BasicRule
{
    protected AbstractCacheManager cacheManager;
    protected OpinionGenerationRequest opinionGenerationRequest;
    protected VehicleValuation valuationReferencece;
    protected OpinionValue opinionValue;
    protected OpinionGenerationStatus opinionGenerationStatus;

    protected Logger logger = LoggerFactory.getLogger( "OpinionGeneration" );
    protected StringBuilder ruleLoggerSB = new StringBuilder(  );
    protected final String LOGGER_PREFIX = "OPINION GENERATION ENGINE : ";
    protected final String CURRENCY_SUFFIX = "LKR";

    public AbstractBasicRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation valuationReferencece, OpinionValue opinionValue )
    {
        this.cacheManager = cacheManager;
        this.opinionGenerationRequest = opinionGenerationRequest;
        this.valuationReferencece = valuationReferencece;
        this.opinionValue = opinionValue;
    }

    protected void beforeApplying()
    {
        StringBuilder loggerSB = new StringBuilder(  );

        //        logger.info( LOGGER_PREFIX + "Applying " + getRuleName() );
        loggerSB.append( LOGGER_PREFIX );
        loggerSB.append( "Applying " );
        loggerSB.append( getRuleName() );
        logger.info( loggerSB.toString() );
        loggerSB.append( "\n" );

        this.ruleLoggerSB.append( loggerSB );
    }

    protected void afterApplying()
    {
        StringBuilder loggerSB = new StringBuilder(  );

        loggerSB.append( LOGGER_PREFIX );
        loggerSB.append( "Vehicle Forced Sale Value after applying " ).append( getRuleName() );
        loggerSB.append( " : " );
        loggerSB.append( this.opinionValue.getForcedSaleValue() ).append( " " ).append( CURRENCY_SUFFIX );
        loggerSB.append( "\n" );

        loggerSB.append( LOGGER_PREFIX );
        loggerSB.append( "Vehicle Average Market Value after applying " ).append( getRuleName() );
        loggerSB.append( " : " );
        loggerSB.append( this.opinionValue.getAverageMarketValue() ).append( " " ).append( CURRENCY_SUFFIX );
        loggerSB.append( "\n" );

        loggerSB.append( LOGGER_PREFIX );
        loggerSB.append( "Finished applying " ).append( getRuleName() );
        loggerSB.append( "\n" );

        logger.info( loggerSB.toString() );

        this.ruleLoggerSB.append( loggerSB );
    }

    protected abstract void adjust() throws OpinionGenerationException;

    public AbstractCacheManager getCacheManager()
    {
        return cacheManager;
    }

    public void setCacheManager( AbstractCacheManager cacheManager )
    {
        this.cacheManager = cacheManager;
    }

    public OpinionGenerationRequest getOpinionGenerationRequest()
    {
        return opinionGenerationRequest;
    }

    public void setOpinionGenerationRequest( OpinionGenerationRequest opinionGenerationRequest )
    {
        this.opinionGenerationRequest = opinionGenerationRequest;
    }

    public VehicleValuation getValuationReferencece()
    {
        return valuationReferencece;
    }

    public void setValuationReferencece( VehicleValuation valuationReferencece )
    {
        this.valuationReferencece = valuationReferencece;
    }

    public OpinionValue getOpinionValue()
    {
        return opinionValue;
    }

    public void setOpinionValue( OpinionValue opinionValue )
    {
        this.opinionValue = opinionValue;
    }

    public String getRuleLog()
    {
        return this.ruleLoggerSB.toString();
    }
}
