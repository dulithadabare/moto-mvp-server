package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.BatteryCostDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BatteryCostService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( BatteryCostService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_BATTERY_COST_SERVICE.getServiceKey();
    }

    public List<BatteryCostDAO> getAll()
    {
        return getHibernateResource().loadAll( BatteryCostDAO.class );
    }

    public List<BatteryCostDAO> getById( int entityId )
    {
        return getHibernateResource().load( BatteryCostDAO.class, entityId );
    }

    public List<BatteryCostDAO> save( List<BatteryCostDAO> entityList )
    {
        List<BatteryCostDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_BATTERY_FIXED_COST_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<BatteryCostDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_BATTERY_FIXED_COST_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
