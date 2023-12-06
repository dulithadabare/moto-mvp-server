package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.CountryDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CountryService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( CountryService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.COUNTRY_SERVICE.getServiceKey();
    }

    public List<CountryDAO> getAll()
    {
        return getHibernateResource().loadAll( CountryDAO.class );
    }

    public List<CountryDAO> getById( int entityId )
    {
        return getHibernateResource().load( CountryDAO.class, entityId );
    }

    public List<CountryDAO> save( List<CountryDAO> entityList )
    {
        List<CountryDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.COUNTRY_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<CountryDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.COUNTRY_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
