package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceChildValDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReferenceChildValService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( ReferenceChildValService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.REFERENCE_CHILD_VAL_SERVICE.getServiceKey();
    }

    public List<ReferenceChildValDAO> getAll()
    {
        return getHibernateResource().loadAll( ReferenceChildValDAO.class );
    }

    public List<ReferenceChildValDAO> getById( int entityId )
    {
        return getHibernateResource().load( ReferenceChildValDAO.class, entityId );
    }

    public List<ReferenceChildValDAO> save( List<ReferenceChildValDAO> entityList )
    {
        List<ReferenceChildValDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VALUATION_DB_MAPPING_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<ReferenceChildValDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VALUATION_DB_MAPPING_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
