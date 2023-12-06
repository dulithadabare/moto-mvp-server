package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionTypeDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleConditionTypeService;

import java.util.List;

public class VehicleConditionTypeEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_CONDITION_TYPE_ENDPOINT.getEndPointKey();
    }

    public List<VehicleConditionTypeDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleConditionTypeDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleConditionTypeDAO> save( List<VehicleConditionTypeDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleConditionTypeDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleConditionTypeService getService()
    {
    return this.getService( ServiceKey.VEHICLE_CONDITION_TYPE_SERVICE.getServiceKey() );
    }
}
