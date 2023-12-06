package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleExtraCostDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleExtraCostService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleExtraCostService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_EXTRA_COST_SERVICE.getServiceKey();
    }

    public List<VehicleExtraCostDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleExtraCostDAO.class );
    }

    public List<VehicleExtraCostDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleExtraCostDAO.class, entityId );
    }

    public List<VehicleExtraCostDAO> save( List<VehicleExtraCostDAO> entityList )
    {
        List<VehicleExtraCostDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_EXTRA_FIXED_COST_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehicleExtraCostDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_EXTRA_FIXED_COST_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
