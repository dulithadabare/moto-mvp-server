package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.ConditionType;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.model.AbstractOpinionValuation;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;

public class VehiclePaintFinishFixedCostRule extends AbstractBasicRule
{
    public VehiclePaintFinishFixedCostRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue )
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
        int deductibleAmount = getPartValue( this.valuationReferencece );
        int addAmount = getPartValue( this.opinionGenerationRequest );

        long newForcedSaleValue = this.opinionValue.getForcedSaleValue() - deductibleAmount + addAmount;

        this.opinionValue.setForcedSaleValue( newForcedSaleValue );
    }

    private int getPartValue( AbstractOpinionValuation abstractOpinionValuation ) throws OpinionGenerationException
    {
        SimpleCache<Integer> vehicleBatteryFixedCostCache = this.cacheManager.getCache( CacheKey.VEHICLE_PAINT_FINISH_FIXED_COST_CACHE.toString() );
        SimpleCache<Integer> vehiclePartMaxRatingCache = this.cacheManager.getCache( CacheKey.VEHICLE_PART_MAX_RATING_CACHE.toString() );

        int partCost = vehicleBatteryFixedCostCache.getValueFromKey( abstractOpinionValuation.getVehicleDetails().getVehiclePaintFinishType() );
        int partConditionRating = abstractOpinionValuation.getVehicleCondition().getGeneralConditionRatingMap().get( ConditionType.PAINT_FINISH_CONDITION );
        int partConditionMaxRating = vehiclePartMaxRatingCache.getValueFromKey( ConditionType.PAINT_FINISH_CONDITION );

        return Math.round( partCost * ( ( float ) partConditionRating / partConditionMaxRating ) );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.VEHICLE_PAINT_FINISH_FIXED_COST_RULE.getRuleKey();
    }

}
