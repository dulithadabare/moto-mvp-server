package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceValuationDbDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReferenceValuationDbService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( ReferenceValuationDbService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.REFERENCE_VALUATION_DB_SERVICE.getServiceKey();
    }

    public List<ReferenceValuationDbDAO> getAll()
    {
        return getHibernateResource().loadAll( ReferenceValuationDbDAO.class );
    }

    public List<ReferenceValuationDbDAO> getById( int entityId )
    {
        return getHibernateResource().load( ReferenceValuationDbDAO.class, entityId );
    }

    public List<ReferenceValuationDbDAO> save( List<ReferenceValuationDbDAO> entityList )
    {
        List<ReferenceValuationDbDAO> savedEntity = getHibernateResource().saveBatch( entityList );

//        this.cacheManager.load( CacheKey.VALUATION_DB_MAPPING_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<ReferenceValuationDbDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

//        this.cacheManager.load( CacheKey.VALUATION_DB_MAPPING_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
