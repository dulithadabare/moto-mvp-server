package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleTyreCostDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleTyreCostService;

import java.util.List;

public class VehicleTyreCostEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_TYRE_COST_ENDPOINT.getEndPointKey();
    }

    public List<VehicleTyreCostDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleTyreCostDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleTyreCostDAO> save( List<VehicleTyreCostDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleTyreCostDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleTyreCostService getService()
    {
    return this.getService( ServiceKey.VEHICLE_TYRE_COST_SERVICE.getServiceKey() );
    }
}
