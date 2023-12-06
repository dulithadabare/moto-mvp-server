package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.engine.util.EngineUtils;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;

import java.time.LocalDate;

public class DepreciationRule extends AbstractBasicRule
{
    public DepreciationRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue )
    {
        super( cacheManager, opinionGenerationRequest, mostMatchedVehicleValuation, opinionValue );
    }

    @Override
    public void apply() throws OpinionGenerationException
    {
        beforeApplying();
        adjust();
        afterApplying();
    }

    @Override
    protected void adjust() throws OpinionGenerationException
    {
        SimpleCache<Integer> depreciationRulesCache = this.cacheManager.getCache( CacheKey.DEPRECIATION_RULE_CACHE.toString() );

        StringBuilder loggerSB = new StringBuilder(  );

        String depreciationCacheKey = this.opinionGenerationRequest.getVehicleDetails().getVehicleStatus().getCode() + "-" + this.valuationReferencece.getVehicleDetails().getVehicleStatus().getCode();
        int depreciationAmount = depreciationRulesCache.getValueFromKey( depreciationCacheKey );
        loggerSB.append( LOGGER_PREFIX ).append( "Selected DEPRECIATION RULE : " ).append( depreciationCacheKey ).append( " : " ).append( depreciationAmount ).append( "\n" );

        if ( this.valuationReferencece.getValuationDate() == null )
        {
            String message = "VALUATION REFERENCE : VALUATION DATE IS NULL";
            loggerSB.append( LOGGER_PREFIX ).append( message ).append( "\n" );
            throw new OpinionGenerationException( message );
        }

        int duration = EngineUtils.getDiffYears( this.valuationReferencece.getValuationDate(), LocalDate.now() );
        double depreciation = this.opinionValue.getForcedSaleValue() * ( depreciationAmount / 100.0 ) * duration;
        long newForcedSaleValue = this.opinionValue.getForcedSaleValue() - Math.round( depreciation );

        logger.info( loggerSB.toString() );
        ruleLoggerSB.append( loggerSB );

        this.opinionValue.setForcedSaleValue( newForcedSaleValue );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.DEPRECIATION_RULE.getRuleKey();
    }

}
