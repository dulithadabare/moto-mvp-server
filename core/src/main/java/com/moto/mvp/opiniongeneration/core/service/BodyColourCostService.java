package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.BodyColourCostDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BodyColourCostService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( BodyColourCostService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_BODY_COLOUR_COST_SERVICE.getServiceKey();
    }

    public List<BodyColourCostDAO> getAll()
    {
        return getHibernateResource().loadAll( BodyColourCostDAO.class );
    }

    public List<BodyColourCostDAO> getById( int entityId )
    {
        return getHibernateResource().load( BodyColourCostDAO.class, entityId );
    }

    public List<BodyColourCostDAO> save( List<BodyColourCostDAO> entityList )
    {
        List<BodyColourCostDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_BODY_COLOUR_FIXED_COST_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<BodyColourCostDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_BODY_COLOUR_FIXED_COST_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
