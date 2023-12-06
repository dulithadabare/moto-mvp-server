package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.engine.rule.MarketAdjustmentRuleEntity;
import java.util.ArrayList;
import java.util.List;

public class MarketAdjustmentRuleCache extends AbstractBasicCache
{
    private List<MarketAdjustmentRuleEntity> data;

    public MarketAdjustmentRuleCache()
    {
        this.query = "SELECT make_id, model_id, year_of_manufacture, engine_type_id, engine_capacity, body_colour_id, country_used_in_id, is_percentage, amount FROM market_adjustment_rule";
    }

    @Override
    public void load()
    {
        logger.info( "START LOADING CACHE : " + getKey() );

        data = new ArrayList<>(  );

        List<Object[]> rulesList = getHibernateResource().executeQuery( this.query );

        for ( Object[] rule: rulesList )
        {
            int count = 0;

            MarketAdjustmentRuleEntity marketAdjustmentRuleEntity = new MarketAdjustmentRuleEntity();
            marketAdjustmentRuleEntity.setMakeId( (Integer) rule[count++] );
            marketAdjustmentRuleEntity.setModelId( (Integer) rule[count++] );
            marketAdjustmentRuleEntity.setYearOfManufacture( (Integer) rule[count++] );
            marketAdjustmentRuleEntity.setEngineTypeId( (Integer) rule[count++] );
            marketAdjustmentRuleEntity.setEngineCapacityCc( (Integer) rule[count++] );
            marketAdjustmentRuleEntity.setBodyColourId( (Integer) rule[count++] );
            marketAdjustmentRuleEntity.setCountryOfOriginId( (Integer) rule[count++] );
            marketAdjustmentRuleEntity.setPercentage( (Boolean ) rule[count++] );
            marketAdjustmentRuleEntity.setAmount( (Integer ) rule[count++] );

            this.data.add( marketAdjustmentRuleEntity );
        }

        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.MARKET_ADJUSTMENT_RULE_CACHE.getCacheKey();
    }

    public List<MarketAdjustmentRuleEntity> getData()
    {
        return this.data;
    }
}
