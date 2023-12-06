package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.EngineCapacityTypeDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EngineCapacityTypeService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( EngineCapacityTypeService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.ENGINE_CAPACITY_TYPE_SERVICE.getServiceKey();
    }

    public List<EngineCapacityTypeDAO> getAll()
    {
        return getHibernateResource().loadAll( EngineCapacityTypeDAO.class );
    }

    public List<EngineCapacityTypeDAO> getById( int entityId )
    {
        return getHibernateResource().load( EngineCapacityTypeDAO.class, entityId );
    }

    public List<EngineCapacityTypeDAO> save( List<EngineCapacityTypeDAO> entityList )
    {
        List<EngineCapacityTypeDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.ENGINE_CAPACITY_TYPE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<EngineCapacityTypeDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.ENGINE_CAPACITY_TYPE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
