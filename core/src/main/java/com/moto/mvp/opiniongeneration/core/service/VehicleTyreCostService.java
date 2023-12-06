package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleTyreCostDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleTyreCostService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleTyreCostService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_TYRE_COST_SERVICE.getServiceKey();
    }

    public List<VehicleTyreCostDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleTyreCostDAO.class );
    }

    public List<VehicleTyreCostDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleTyreCostDAO.class, entityId );
    }

    public List<VehicleTyreCostDAO> save( List<VehicleTyreCostDAO> entityList )
    {
        List<VehicleTyreCostDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_TYRE_FIXED_COST_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehicleTyreCostDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_TYRE_FIXED_COST_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
