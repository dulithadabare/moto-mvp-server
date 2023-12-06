package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.EngineTypeDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EngineTypeService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( EngineTypeService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.ENGINE_TYPE_SERVICE.getServiceKey();
    }

    public List<EngineTypeDAO> getAll()
    {
        return getHibernateResource().loadAll( EngineTypeDAO.class );
    }

    public List<EngineTypeDAO> getById( int entityId )
    {
        return getHibernateResource().load( EngineTypeDAO.class, entityId );
    }

    public List<EngineTypeDAO> save( List<EngineTypeDAO> entityList )
    {
        List<EngineTypeDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        return savedEntity;
    }

    public int delete( List<EngineTypeDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
