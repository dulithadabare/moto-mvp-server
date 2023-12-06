package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionTypeDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleConditionTypeService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehicleConditionTypeService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_CONDITION_TYPE_SERVICE.getServiceKey();
    }

    public List<VehicleConditionTypeDAO> getAll()
    {
        return getHibernateResource().loadAll( VehicleConditionTypeDAO.class );
    }

    public List<VehicleConditionTypeDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehicleConditionTypeDAO.class, entityId );
    }

    public List<VehicleConditionTypeDAO> save( List<VehicleConditionTypeDAO> entityList )
    {
        List<VehicleConditionTypeDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        return savedEntity;
    }

    public int delete( List<VehicleConditionTypeDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
    return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
