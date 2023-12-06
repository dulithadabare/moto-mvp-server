package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceParentValDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReferenceParentValService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( ReferenceParentValService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.REFERENCE_PARENT_VAL_SERVICE.getServiceKey();
    }

    public List<ReferenceParentValDAO> getAll()
    {
        return getHibernateResource().loadAll( ReferenceParentValDAO.class );
    }

    public List<ReferenceParentValDAO> getById( int entityId )
    {
        return getHibernateResource().load( ReferenceParentValDAO.class, entityId );
    }

    public List<ReferenceParentValDAO> save( List<ReferenceParentValDAO> entityList )
    {
        List<ReferenceParentValDAO> savedEntity = getHibernateResource().saveBatch( entityList );

//        this.cacheManager.load( CacheKey.VALUATION_DB_MAPPING_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<ReferenceParentValDAO> entityList )
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
