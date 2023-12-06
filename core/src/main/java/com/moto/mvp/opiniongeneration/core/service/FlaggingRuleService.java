package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.FlaggingRuleDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FlaggingRuleService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( FlaggingRuleService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.FLAGGING_RULE_SERVICE.getServiceKey();
    }

    public List<FlaggingRuleDAO> getAll()
    {
        return getHibernateResource().loadAll( FlaggingRuleDAO.class );
    }

    public List<FlaggingRuleDAO> getById( int entityId )
    {
        return getHibernateResource().load( FlaggingRuleDAO.class, entityId );
    }

    public List<FlaggingRuleDAO> save( List<FlaggingRuleDAO> entityList )
    {
        List<FlaggingRuleDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.FLAGGING_RULE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<FlaggingRuleDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.FLAGGING_RULE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
