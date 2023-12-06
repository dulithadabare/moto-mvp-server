package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.DepreciationRuleDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DepreciationRuleService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( DepreciationRuleService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.DEPRECIATION_RULE_SERVICE.getServiceKey();
    }

    public List<DepreciationRuleDAO> getAll()
    {
        return getHibernateResource().loadAll( DepreciationRuleDAO.class );
    }

    public List<DepreciationRuleDAO> getById( int entityId )
    {
        return getHibernateResource().load( DepreciationRuleDAO.class, entityId );
    }

    public List<DepreciationRuleDAO> save( List<DepreciationRuleDAO> entityList )
    {
        List<DepreciationRuleDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.DEPRECIATION_RULE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<DepreciationRuleDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.DEPRECIATION_RULE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
