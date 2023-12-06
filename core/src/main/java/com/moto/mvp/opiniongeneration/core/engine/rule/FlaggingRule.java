package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.constant.FlaggingStatus;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.cache.FlaggedStatusCache;
import com.moto.mvp.opiniongeneration.core.cache.FlaggingRuleCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.dao.FlaggedStatusDAO;
import com.moto.mvp.opiniongeneration.core.dao.FlaggingRuleDAO;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationStatus;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;

public class FlaggingRule extends AbstractBasicRule
{
    private OpinionGenerationStatus opinionGenerationStatus;

    public FlaggingRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue, OpinionGenerationStatus opinionGenerationStatus )
    {
        super( cacheManager, opinionGenerationRequest, mostMatchedVehicleValuation, opinionValue );
        this.opinionGenerationStatus = opinionGenerationStatus;
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
        FlaggingRuleCache flaggingRuleCache = this.cacheManager.getCache( CacheKey.FLAGGING_RULE_CACHE.toString() );
        FlaggedStatusCache flaggedStatusCache = this.cacheManager.getCache( CacheKey.FLAGGED_STATUS_CACHE.toString() );

        StringBuilder loggerSB = new StringBuilder(  );

        long generatedValue = this.opinionValue.getAverageMarketValue();
        long previousRenewalValue = this.opinionGenerationRequest.getVehicleInsuranceHistoryList().get( 0 ).getValueOnPreviousRenewal();

        long diff = Math.abs( generatedValue - previousRenewalValue );

        int selectedFlag = 0;

        for ( Integer flaggingRuleKey : flaggingRuleCache.getFlaggingRuleKeySet() )
        {
            FlaggingRuleDAO flaggingRuleDAO = flaggingRuleCache.getFlaggingRule( flaggingRuleKey );

            long lowerBoundAmount = flaggingRuleDAO.isPercentage() ? Math.round( generatedValue * flaggingRuleDAO.getThresholdLowerBound() / 100.0 )
                    : flaggingRuleDAO.getThresholdLowerBound();
            long upperBoundAmount = flaggingRuleDAO.isPercentage() ? Math.round( generatedValue * flaggingRuleDAO.getThresholdUpperBound() / 100.0 )
                    : flaggingRuleDAO.getThresholdUpperBound();

            if ( diff > lowerBoundAmount && diff <= upperBoundAmount )
            {
                selectedFlag = flaggingRuleDAO.getFlaggedStatusCode();
            }
        }

        if ( selectedFlag == FlaggingStatus.RED_FLAG )
        {
            this.opinionGenerationStatus.setFlagged( true );
        }

        FlaggedStatusDAO flaggedStatus = flaggedStatusCache.getFlaggedStatus( selectedFlag );

        loggerSB.append( LOGGER_PREFIX ).append( "FLAGGED : " ).append( flaggedStatus.getName() ).append( "\n" );
        logger.info( loggerSB.toString() );

        ruleLoggerSB.append( loggerSB );

        this.opinionGenerationStatus.setFlaggedStatus( flaggedStatus );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.FLAGGING_RULE.getRuleKey();
    }

}
