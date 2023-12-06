package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleStatusDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleStatusService;

import java.util.List;

public class VehicleStatusEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_STATUS_ENDPOINT.getEndPointKey();
    }

    public List<VehicleStatusDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleStatusDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleStatusDAO> save( List<VehicleStatusDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleStatusDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleStatusService getService()
    {
    return this.getService( ServiceKey.VEHICLE_STATUS_SERVICE.getServiceKey() );
    }
}
