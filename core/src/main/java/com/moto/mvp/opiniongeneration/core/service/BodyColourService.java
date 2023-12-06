package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.BodyColourDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BodyColourService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( BodyColourService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.BODY_COLOUR_SERVICE.getServiceKey();
    }

    public List<BodyColourDAO> getAll()
    {
        return getHibernateResource().loadAll( BodyColourDAO.class );
    }

    public List<BodyColourDAO> getById( int entityId )
    {
        return getHibernateResource().load( BodyColourDAO.class, entityId );
    }

    public List<BodyColourDAO> save( List<BodyColourDAO> entityList )
    {
        List<BodyColourDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.BODY_COLOUR_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<BodyColourDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.BODY_COLOUR_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
