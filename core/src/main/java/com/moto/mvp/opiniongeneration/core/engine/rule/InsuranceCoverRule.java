package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.model.InsurancePremiumValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;

public class InsuranceCoverRule extends AbstractBasicRule
{
    private InsurancePremiumValue insurancePremiumValue;

    public InsuranceCoverRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue, InsurancePremiumValue insurancePremiumValue )
    {
        super( cacheManager, opinionGenerationRequest, mostMatchedVehicleValuation, opinionValue );
        this.insurancePremiumValue = insurancePremiumValue;
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
        SimpleCache<Double> insuranceCoverRuleCache = this.cacheManager.getCache( CacheKey.INSURANCE_COVER_RULE_CACHE.toString() );

        boolean useCustomCovers = opinionGenerationRequest.isUseCustomCovers();
        long averageMarketValue = opinionValue.getAverageMarketValue();

        double insurancePremium = 0.0;
        StringBuilder loggerSB = new StringBuilder();
        if ( useCustomCovers )
        {
            insurancePremium = opinionGenerationRequest.getCustomCoverList().stream().mapToDouble(
                    ( cover ) ->
                    {
                        double coverPremium = averageMarketValue * cover.getAmount();
                        loggerSB.append( LOGGER_PREFIX );
                        loggerSB.append( "Cover Premium for " );
                        loggerSB.append( cover.getCode() );
                        loggerSB.append( " after applying " );
                        loggerSB.append( cover.getAmount() );
                        loggerSB.append( "%" );
                        loggerSB.append( " : " );
                        loggerSB.append( coverPremium );
                        loggerSB.append( "\n" );
                        return coverPremium;
                    }
            ).sum();
        }
        else
        {
            for ( String coverCode : opinionGenerationRequest.getCoverList() )
            {
                double coverAmount = insuranceCoverRuleCache.getValueFromKey( coverCode.trim() );
                double coverPremium = averageMarketValue * coverAmount;
                loggerSB.append( LOGGER_PREFIX );
                loggerSB.append( "Cover Premium for " );
                loggerSB.append( coverCode );
                loggerSB.append( " after applying " );
                loggerSB.append( coverAmount );
                loggerSB.append( "%" );
                loggerSB.append( " : " );
                loggerSB.append( coverPremium );
                loggerSB.append( "\n" );

                insurancePremium += coverPremium;
            }
        }

        logger.info( loggerSB.toString() );
        ruleLoggerSB.append( loggerSB );

        this.insurancePremiumValue.setInsurancePremiumValue( insurancePremium );
        this.insurancePremiumValue.setUsedCovers( opinionGenerationRequest.getCoverList() );

    }

    @Override
    protected void afterApplying()
    {
        StringBuilder loggerSB = new StringBuilder(  );

        loggerSB.append( LOGGER_PREFIX );
        loggerSB.append( "Vehicle Premium Value after applying " ).append( getRuleName() ).append( " : " );
        loggerSB.append( this.insurancePremiumValue.getInsurancePremiumValue() ).append( " " ).append( CURRENCY_SUFFIX );
        loggerSB.append( "\n" );

        loggerSB.append( LOGGER_PREFIX );
        loggerSB.append( "Finished applying " ).append( getRuleName() );
        loggerSB.append( "\n" );

        logger.info( loggerSB.toString() );

        ruleLoggerSB.append( loggerSB );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.INSURANCE_COVER_RULE.getRuleKey();
    }

}
