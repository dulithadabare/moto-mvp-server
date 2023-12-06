package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.HornCostDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HornCostService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( HornCostService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_HORN_COST_SERVICE.getServiceKey();
    }

    public List<HornCostDAO> getAll()
    {
        return getHibernateResource().loadAll( HornCostDAO.class );
    }

    public List<HornCostDAO> getById( int entityId )
    {
        return getHibernateResource().load( HornCostDAO.class, entityId );
    }

    public List<HornCostDAO> save( List<HornCostDAO> entityList )
    {
        List<HornCostDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_HORN_FIXED_COST_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<HornCostDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_HORN_FIXED_COST_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
