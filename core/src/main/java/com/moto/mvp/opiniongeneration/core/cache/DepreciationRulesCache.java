package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DepreciationRulesCache extends AbstractBasicCache
{
    private Map<String, Integer> data = new ConcurrentHashMap<>(  );

    DepreciationRulesCache()
    {
        this.query = "SELECT \n" +
                "CONCAT( (SELECT code FROM vehicle_status  VS WHERE VS.id = DR.current_vehicle_status_id), '-', (SELECT code FROM vehicle_status  VS WHERE VS.id = DR.previous_vehicle_status_id) ) cache_key,\n" +
                "amount \n" +
                "FROM depreciation_rule DR";
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
        return CacheKey.DEPRECIATION_RULE_CACHE.getCacheKey();
    }

    public Integer getValueFromKey( String key )
    {
        return data.get( key );
    }
}
