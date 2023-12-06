package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleModelDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleModelService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleModelService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_MODEL_SERVICE.getServiceKey();
    }

    public List<VehicleModelDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleModelDAO.class );
    }

    public List<VehicleModelDAO> getByMakeId( int makeId )
    {
        return getHibernateResource().loadWithRestriction( VehicleModelDAO.class, "makeId", makeId );
    }

    public List<VehicleModelDAO> save( List<VehicleModelDAO> entityList )
    {
        List<VehicleModelDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_MODEL_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehicleModelDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_MODEL_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
