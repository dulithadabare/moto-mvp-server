package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.cache.MarketAdjustmentRuleCache;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;

public class MarketAdjustmentRule extends AbstractBasicRule
{
    public MarketAdjustmentRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue )
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
        final String RULE_APPLIED = "APPLIED : ";
        final String RULE_NOT_APPLIED = "NOT APPLIED : ";

        MarketAdjustmentRuleCache marketAdjustmentRuleCache = this.cacheManager.getCache( CacheKey.MARKET_ADJUSTMENT_RULE_CACHE.toString() );

        StringBuilder loggerSB = new StringBuilder();
        long adjustableAmountTotal = 0L;

        for ( MarketAdjustmentRuleEntity marketAdjustmentRuleEntity : marketAdjustmentRuleCache.getData() )
        {
            if ( marketAdjustmentRuleEntity.applyRule( this.opinionGenerationRequest ) )
            {
                adjustableAmountTotal += getAdjustableAmount( marketAdjustmentRuleEntity );
                loggerSB.append( RULE_APPLIED );
            }
            else
            {
                loggerSB.append( RULE_NOT_APPLIED );
            }
            loggerSB.append( marketAdjustmentRuleEntity.toString() );
            loggerSB.append( "\n" );
        }

        this.opinionValue.setAverageMarketValue( this.opinionValue.getForcedSaleValue() + adjustableAmountTotal );

        ruleLoggerSB.append( loggerSB );
        logger.info( loggerSB.toString() );

    }

    private long getAdjustableAmount( MarketAdjustmentRuleEntity marketAdjustmentRuleEntity )
    {
        double adjustableAmount;
        if ( marketAdjustmentRuleEntity.isPercentage() )
        {
            adjustableAmount = this.opinionValue.getForcedSaleValue() * ( marketAdjustmentRuleEntity.getAmount() ) / ( ( double ) 100 );
        }
        else
        {
            adjustableAmount = marketAdjustmentRuleEntity.getAmount();
        }

        return Math.round( adjustableAmount );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.MARKET_ADJUSTMENT_RULE.getRuleKey();
    }

}
