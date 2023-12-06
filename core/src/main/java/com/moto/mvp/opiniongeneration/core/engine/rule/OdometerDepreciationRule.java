package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.cache.OdometerDepreciationRuleCache;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.model.OpinionValue;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.VehicleValuation;

public class OdometerDepreciationRule extends AbstractBasicRule
{
    public OdometerDepreciationRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue )
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
        OdometerDepreciationRuleCache odometerDepreciationRuleCache = this.cacheManager.getCache( CacheKey.ODOMETER_DEPRECIATION_RULE_CACHE.toString() );

        int odometerDepreciationAmountPerKM = odometerDepreciationRuleCache.getAmount();
        int mileageDiff = this.opinionGenerationRequest.getVehicleDetails().getOdometerReading() - this.valuationReferencece.getVehicleDetails().getOdometerReading();
        double depreciation = mileageDiff * odometerDepreciationAmountPerKM;

        long newForcedSaleValue = this.opinionValue.getForcedSaleValue() - Math.round( depreciation );
        this.opinionValue.setForcedSaleValue( newForcedSaleValue );
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.ODOMETER_DEPRECIATION_RULE.getRuleKey();
    }

}
