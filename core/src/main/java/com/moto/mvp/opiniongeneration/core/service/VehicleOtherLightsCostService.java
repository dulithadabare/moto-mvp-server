package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleOtherLightsCostDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleOtherLightsCostService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleOtherLightsCostService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_OTHER_LIGHTS_COST_SERVICE.getServiceKey();
    }

    public List<VehicleOtherLightsCostDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleOtherLightsCostDAO.class );
    }

    public List<VehicleOtherLightsCostDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleOtherLightsCostDAO.class, entityId );
    }

    public List<VehicleOtherLightsCostDAO> save( List<VehicleOtherLightsCostDAO> entityList )
    {
        List<VehicleOtherLightsCostDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_OTHER_LIGHTS_FIXED_COST_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehicleOtherLightsCostDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_OTHER_LIGHTS_FIXED_COST_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
