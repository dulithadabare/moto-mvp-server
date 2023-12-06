package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import java.util.List;

public class OdometerDepreciationRuleCache extends AbstractBasicCache
{
    private int amount;

    public OdometerDepreciationRuleCache()
    {
        this.query = "SELECT amount FROM odometer_depreciation_rule";
    }

    @Override
    public void load()
    {
        logger.info( "START LOADING CACHE : " + getKey() );
        List<Object[]> rulesList = getHibernateResource().executeQuery( this.query );

        for ( Object rule: rulesList )
        {
            this.amount = (Integer) rule;
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.ODOMETER_DEPRECIATION_RULE_CACHE.getCacheKey();
    }

    public int getAmount()
    {
        return this.amount;
    }
}

