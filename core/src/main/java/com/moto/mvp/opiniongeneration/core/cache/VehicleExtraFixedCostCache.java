package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleExtraFixedCostCache extends AbstractBasicCache
{
    private Map<String, Integer> data = new ConcurrentHashMap<>(  );

    VehicleExtraFixedCostCache()
    {
        this.query = "SELECT \n" +
                "code, \n" +
                "amount \n" +
                "FROM \n" +
                "vehicle_extra VE, \n" +
                "vehicle_extra_cost VEC \n" +
                "WHERE VE.id = VEC.vehicle_extra_id;";
    }

    @Override
    public void load()
    {
        List<Object[]> rulesList = getHibernateResource().executeQuery( this.query );

        for ( Object[] rule: rulesList )
        {
            String cacheKey = (String) rule[0];
            Integer amount = (Integer) rule[1];
            data.put( cacheKey, amount );
        }
    }

    @Override
    public String getKey()
    {
        return CacheKey.VEHICLE_EXTRA_FIXED_COST_CACHE.getCacheKey();
    }

    public Integer getValueFromKey( String key )
    {
        return data.get( key );
    }
}

