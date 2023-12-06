package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.model.*;

import java.util.List;

public class VehicleTyreFixedCostRule extends AbstractBasicRule
{
    public VehicleTyreFixedCostRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue )
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
        long deductibleAmount = getTyreValue( this.valuationReferencece.getVehicleCondition() );
        long addAmount = getTyreValue( this.opinionGenerationRequest.getVehicleCondition() );
        long newForcedSaleValue = this.opinionValue.getForcedSaleValue() - deductibleAmount + addAmount;

        this.opinionValue.setForcedSaleValue( newForcedSaleValue );
    }

    private long getTyreValue( VehicleCondition vehicleCondition ) throws OpinionGenerationException
    {
        SimpleCache<Integer> vehicleTyreFixedCostCache = this.cacheManager.getCache( CacheKey.VEHICLE_TYRE_FIXED_COST_CACHE.toString() );

        double tyreValue = 0.0;

        List<TyreDetailsCondition> tyreDetailsConditionList = vehicleCondition.getTyreDetailsConditionList();

        for ( TyreDetailsCondition tyreDetailsCondition : tyreDetailsConditionList )
        {
            String tyreCacheKey = String.valueOf( tyreDetailsCondition.getSize() );
            int tyreCost = vehicleTyreFixedCostCache.getValueFromKey( tyreCacheKey );

            tyreValue += tyreCost * ( 100 - tyreDetailsCondition.getWearPercentage() ) / 100.0;
        }

        return Math.round( tyreValue );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.VEHICLE_TYRE_FIXED_COST_RULE.getRuleKey();
    }

}
