package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleMakeDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleMakeService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleMakeService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_MAKE_SERVICE.getServiceKey();
    }

    public List<VehicleMakeDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleMakeDAO.class );
    }

    public List<VehicleMakeDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleMakeDAO.class, entityId );
    }

    public List<VehicleMakeDAO> save( List<VehicleMakeDAO> entityList )
    {
        List<VehicleMakeDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_MAKE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehicleMakeDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_MAKE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
