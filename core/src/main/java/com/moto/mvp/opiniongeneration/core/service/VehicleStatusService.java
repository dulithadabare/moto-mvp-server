package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleStatusDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleStatusService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleStatusService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_STATUS_SERVICE.getServiceKey();
    }

    public List<VehicleStatusDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleStatusDAO.class );
    }

    public List<VehicleStatusDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleStatusDAO.class, entityId );
    }

    public List<VehicleStatusDAO> save( List<VehicleStatusDAO> entityList )
    {
        List<VehicleStatusDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_STATUS_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehicleStatusDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_STATUS_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
