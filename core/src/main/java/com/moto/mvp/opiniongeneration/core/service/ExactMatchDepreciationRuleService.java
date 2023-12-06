package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.ExactMatchDepreciationRuleDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExactMatchDepreciationRuleService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( ExactMatchDepreciationRuleService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.EXACT_MATCH_DEPRECIATION_RULE_SERVICE.getServiceKey();
    }

    public List<ExactMatchDepreciationRuleDAO> getAll()
    {
        return getHibernateResource().loadAll( ExactMatchDepreciationRuleDAO.class );
    }

    public List<ExactMatchDepreciationRuleDAO> getById( int entityId )
    {
        return getHibernateResource().load( ExactMatchDepreciationRuleDAO.class, entityId );
    }

    public List<ExactMatchDepreciationRuleDAO> save( List<ExactMatchDepreciationRuleDAO> entityList )
    {
        List<ExactMatchDepreciationRuleDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.EXACT_MATCH_DEPRECIATION_RULE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<ExactMatchDepreciationRuleDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.EXACT_MATCH_DEPRECIATION_RULE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
