package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleMakeDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleMakeService;

import java.util.List;

public class VehicleMakeEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_MAKE_ENDPOINT.getEndPointKey();
    }

    public List<VehicleMakeDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleMakeDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleMakeDAO> save( List<VehicleMakeDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleMakeDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleMakeService getService()
    {
    return this.getService( ServiceKey.VEHICLE_MAKE_SERVICE.getServiceKey() );
    }
}
