package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehiclePaintFinishCostDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehiclePaintFinishCostService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehiclePaintFinishCostService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_PAINT_FINISH_COST_SERVICE.getServiceKey();
    }

    public List<VehiclePaintFinishCostDAO> getAll()
    {
        return getHibernateResource().loadAll( VehiclePaintFinishCostDAO.class );
    }

    public List<VehiclePaintFinishCostDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehiclePaintFinishCostDAO.class, entityId );
    }

    public List<VehiclePaintFinishCostDAO> save( List<VehiclePaintFinishCostDAO> entityList )
    {
        List<VehiclePaintFinishCostDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_PAINT_FINISH_FIXED_COST_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehiclePaintFinishCostDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_PAINT_FINISH_FIXED_COST_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
