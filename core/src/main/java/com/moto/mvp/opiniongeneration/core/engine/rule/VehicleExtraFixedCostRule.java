package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.VehicleExtra;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;

public class VehicleExtraFixedCostRule extends AbstractBasicRule
{
    public VehicleExtraFixedCostRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue )
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
        SimpleCache<Integer> vehicleExtraFixedCostCache = this.cacheManager.getCache( CacheKey.VEHICLE_EXTRA_FIXED_COST_CACHE.toString() );

        int deductibleAmount = 0;
        for ( VehicleExtra vehicleExtra : this.valuationReferencece.getVehicleExtraList() )
        {
            String cacheKey = vehicleExtra.getCode();
            int extraCost = vehicleExtraFixedCostCache.getValueFromKey( cacheKey );
            deductibleAmount += extraCost;
        }

        int addAmount = 0;
        for ( VehicleExtra vehicleExtra : this.opinionGenerationRequest.getVehicleExtraList() )
        {
            String cacheKey = vehicleExtra.getCode();
            int extraCost = vehicleExtraFixedCostCache.getValueFromKey( cacheKey );
            addAmount += extraCost;
        }

        long newForcedSaleValue = this.opinionValue.getForcedSaleValue() - deductibleAmount + addAmount;

        this.opinionValue.setForcedSaleValue( newForcedSaleValue );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.VEHICLE_EXTRA_FIXED_COST_RULE.getRuleKey();
    }

}
