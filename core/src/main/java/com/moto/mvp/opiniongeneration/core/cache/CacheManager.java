package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.BasicCache;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import java.util.ArrayList;
import java.util.List;

public class CacheManager extends AbstractCacheManager
{
    @Override
    protected List<BasicCache> getCaches()
    {
        List<BasicCache> cacheList = new ArrayList<>(  );

        cacheList.add( new SimpleCache<Integer>( CacheKey.DEPRECIATION_RULE_CACHE.getCacheKey(), "SELECT \n" +
                "CONCAT( (SELECT code FROM vehicle_status  VS WHERE VS.id = DR.current_vehicle_status_id), '-', (SELECT code FROM vehicle_status  VS WHERE VS.id = DR.previous_vehicle_status_id) ) cache_key,\n" +
                "amount \n" +
                "FROM depreciation_rule DR"  ) );
        cacheList.add( new SimpleCache<Integer>( CacheKey.VEHICLE_EXTRA_FIXED_COST_CACHE.getCacheKey(), "SELECT code, amount FROM vehicle_extra VE, vehicle_extra_cost VEC WHERE VE.id = VEC.vehicle_extra_id" ) );
        cacheList.add( new SimpleCache<Integer>( CacheKey.VEHICLE_BATTERY_FIXED_COST_CACHE.getCacheKey(), "SELECT name, amount FROM battery_cost" ) );
        cacheList.add( new SimpleCache<Integer>( CacheKey.VEHICLE_HORN_FIXED_COST_CACHE.getCacheKey(), "SELECT name, amount FROM horn_cost" ) );
        cacheList.add( new SimpleCache<Integer>( CacheKey.VEHICLE_TYRE_FIXED_COST_CACHE.getCacheKey(), "SELECT size, amount FROM vehicle_tyre_cost" ) );
        cacheList.add( new SimpleCache<Integer>( CacheKey.VEHICLE_OTHER_LIGHTS_FIXED_COST_CACHE.getCacheKey(), "SELECT name, amount FROM vehicle_other_lights_cost" ) );
        cacheList.add( new SimpleCache<Integer>( CacheKey.VEHICLE_PAINT_FINISH_FIXED_COST_CACHE.getCacheKey(), "SELECT name, amount FROM vehicle_paint_finish_cost" ) );
        cacheList.add( new SimpleCache<Integer>( CacheKey.VEHICLE_BODY_COLOUR_FIXED_COST_CACHE.getCacheKey(), "SELECT code, amount FROM body_colour BC, body_colour_cost BCC WHERE BC.id = BCC.body_colour_id" ) );
        cacheList.add( new SimpleCache<Double>( CacheKey.VEHICLE_PART_PERCENTAGE_RULE_CACHE.getCacheKey(), "SELECT code, amount FROM vehicle_part_percentage_rule" ) );
        cacheList.add( new SimpleCache<Double>( CacheKey.INSURANCE_COVER_RULE_CACHE.getCacheKey(), "SELECT code, amount FROM insurance_cover_rule" ) );
        cacheList.add( new SimpleCache<Integer>( CacheKey.VEHICLE_PART_MAX_RATING_CACHE.getCacheKey(), "SELECT VC.code code, MAX(VCR.rating) max_rating FROM vehicle_condition VC, vehicle_condition_rating VCR where VC.vehicle_condition_type_id= VCR.vehicle_condition_type_id GROUP BY VC.code" ) );
        cacheList.add( new OdometerDepreciationRuleCache() );
//        cacheList.add( new FlaggingRuleCache() );
        cacheList.add( new ExactMatchDepreciationRuleCache() );
//        cacheList.add( new MarketAdjustmentRuleCache() );
//        cacheList.add( new VehiclePartMaxRatingCache() );

        return cacheList;
    }
}
