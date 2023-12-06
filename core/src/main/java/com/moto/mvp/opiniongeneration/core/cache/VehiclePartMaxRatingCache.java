package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.ConditionType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehiclePartMaxRatingCache extends AbstractBasicCache
{
    private Map<String, Integer> data = new ConcurrentHashMap<>();
    private Map<String, String> queryMap = new HashMap<>(  );

    public VehiclePartMaxRatingCache()
    {
        queryMap.put( ConditionType.ENGINE_CONDITION, "SELECT MAX(rating) FROM engine_condition" );
        queryMap.put( ConditionType.CLUTCH_CONDITION, "SELECT MAX(rating) FROM clutch_condition" );
        queryMap.put( ConditionType.GEAR_BOX_CONDITION, "SELECT MAX(rating) FROM gear_box_condition" );
        queryMap.put( ConditionType.SHAFTING_CONDITION, "SELECT MAX(rating) FROM shafting_condition" );
        queryMap.put( ConditionType.DIFF_CONDITION, "SELECT MAX(rating) FROM diff_condition" );
        queryMap.put( ConditionType.GEAR_SELECTION_CONDITION, "SELECT MAX(rating) FROM gear_selection_condition" );
        queryMap.put( ConditionType.FRONT_SUSPENSION_CONDITION, "SELECT MAX(rating) FROM suspension_condition" );
        queryMap.put( ConditionType.REAR_SUSPENSION_CONDITION, "SELECT MAX(rating) FROM suspension_condition" );
        queryMap.put( ConditionType.STEERING_CONDITION, "SELECT MAX(rating) FROM steering_condition" );
        queryMap.put( ConditionType.FOOT_BRAKE_CONDITION, "SELECT MAX(rating) FROM brakes_condition" );
        queryMap.put( ConditionType.PARKING_BRAKE_CONDITION, "SELECT MAX(rating) FROM brakes_condition" );
        queryMap.put( ConditionType.STARTER_ELECTRICAL_CONDITION, "SELECT MAX(rating) FROM electrical_condition" );
        queryMap.put( ConditionType.ALTERNATOR_ELECTRICAL_CONDITION, "SELECT MAX(rating) FROM electrical_condition" );
        queryMap.put( ConditionType.OBLIGATORY_LIGHTS_ELECTRICAL_CONDITION, "SELECT MAX(rating) FROM electrical_condition" );
        queryMap.put( ConditionType.METERS_ELECTRICAL_CONDITION, "SELECT MAX(rating) FROM electrical_condition" );
        queryMap.put( ConditionType.HORNS_ELECTRICAL_CONDITION, "SELECT MAX(rating) FROM electrical_condition" );
        queryMap.put( ConditionType.BATTERY_ELECTRICAL_CONDITION, "SELECT MAX(rating) FROM electrical_condition" );
        queryMap.put( ConditionType.OTHER_LIGHTS_ELECTRICAL_CONDITION, "SELECT MAX(rating) FROM electrical_condition" );
        queryMap.put( ConditionType.CHASSIS_CONDITION, "SELECT MAX(rating) FROM chassis_condition" );
        queryMap.put( ConditionType.PAINT_FINISH_CONDITION, "SELECT MAX(rating) FROM paint_finish_condition" );
    }

    @Override
    public void load()
    {
        logger.info( "START LOADING CACHE : " + getKey() );
        data = new ConcurrentHashMap<>();

        for ( String key : queryMap.keySet() )
        {
            String query = queryMap.get( key );

            List<Object[]> rulesList = getHibernateResource().executeQuery( query );

            for ( Object rule: rulesList )
            {
                Integer maxRating = (Integer) rule;
                data.put( key, maxRating );
            }
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.VEHICLE_PART_MAX_RATING_CACHE.getCacheKey();
    }

    public Integer getValueFromKey( String key )
    {
        return data.get( key );
    }
}
