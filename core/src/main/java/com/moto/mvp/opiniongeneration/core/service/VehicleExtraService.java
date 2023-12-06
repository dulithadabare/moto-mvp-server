package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleExtraDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleExtraService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleExtraService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_EXTRA_SERVICE.getServiceKey();
    }

    public List<VehicleExtraDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleExtraDAO.class );
    }

    public List<VehicleExtraDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleExtraDAO.class, entityId );
    }

    public List<VehicleExtraDAO> save( List<VehicleExtraDAO> entityList )
    {
        List<VehicleExtraDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        return savedEntity;
    }

    public int delete( List<VehicleExtraDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
