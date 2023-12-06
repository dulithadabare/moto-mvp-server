package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionRatingDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleConditionRatingService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleConditionRatingService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_CONDITION_RATING_SERVICE.getServiceKey();
    }

    public List<VehicleConditionRatingDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleConditionRatingDAO.class );
    }

    public List<VehicleConditionRatingDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleConditionRatingDAO.class, entityId );
    }

    public List<VehicleConditionRatingDAO> save( List<VehicleConditionRatingDAO> entityList )
    {
        List<VehicleConditionRatingDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_CONDITION_RATING_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehicleConditionRatingDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_CONDITION_RATING_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
