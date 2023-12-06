package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleConditionService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleConditionService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_CONDITION_SERVICE.getServiceKey();
    }

    public List<VehicleConditionDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleConditionDAO.class );
    }

    public List<VehicleConditionDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleConditionDAO.class, entityId );
    }

    public List<VehicleConditionDAO> save( List<VehicleConditionDAO> entityList )
    {
        List<VehicleConditionDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_CONDITION_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehicleConditionDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_CONDITION_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
