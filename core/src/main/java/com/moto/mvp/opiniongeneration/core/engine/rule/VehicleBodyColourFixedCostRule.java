package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;

public class VehicleBodyColourFixedCostRule extends AbstractBasicRule
{
    public VehicleBodyColourFixedCostRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue )
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
        SimpleCache<Integer> vehicleBatteryFixedCostCache = this.cacheManager.getCache( CacheKey.VEHICLE_BODY_COLOUR_FIXED_COST_CACHE.toString() );

        String mmBodyColourCode = this.valuationReferencece.getVehicleDetails().getBodyColour().getCode();

        if ( mmBodyColourCode == null )
        {
            mmBodyColourCode = "PLW";
        }

        int deductibleAmount = vehicleBatteryFixedCostCache.getValueFromKey( mmBodyColourCode );;

        String ogReqBodyColourCode =  this.opinionGenerationRequest.getVehicleDetails().getBodyColour().getCode();

        if ( ogReqBodyColourCode == null )
        {
            ogReqBodyColourCode = "PLW";
        }

        int addAmount = vehicleBatteryFixedCostCache.getValueFromKey( ogReqBodyColourCode );

        long newForcedSaleValue = this.opinionValue.getForcedSaleValue() - deductibleAmount + addAmount;

        this.opinionValue.setForcedSaleValue( newForcedSaleValue );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.VEHICLE_BODY_COLOUR_FIXED_COST_RULE.getRuleKey();
    }

}