package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.MarketAdjustmentRuleDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MarketAdjustmentRuleService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( MarketAdjustmentRuleService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.MARKET_ADJUSTMENT_RULE_SERVICE.getServiceKey();
    }

    public List<MarketAdjustmentRuleDAO> getAll()
    {
        return getHibernateResource().loadAll( MarketAdjustmentRuleDAO.class );
    }

    public List<MarketAdjustmentRuleDAO> getById( int entityId )
    {
        return getHibernateResource().load( MarketAdjustmentRuleDAO.class, entityId );
    }

    public List<MarketAdjustmentRuleDAO> save( List<MarketAdjustmentRuleDAO> entityList )
    {
        List<MarketAdjustmentRuleDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.MARKET_ADJUSTMENT_RULE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<MarketAdjustmentRuleDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.MARKET_ADJUSTMENT_RULE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
