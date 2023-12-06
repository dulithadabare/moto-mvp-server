package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleConditionService;

import java.util.List;

public class VehicleConditionEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_CONDITION_ENDPOINT.getEndPointKey();
    }

    public List<VehicleConditionDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleConditionDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleConditionDAO> save( List<VehicleConditionDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleConditionDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleConditionService getService()
    {
    return this.getService( ServiceKey.VEHICLE_CONDITION_SERVICE.getServiceKey() );
    }
}
