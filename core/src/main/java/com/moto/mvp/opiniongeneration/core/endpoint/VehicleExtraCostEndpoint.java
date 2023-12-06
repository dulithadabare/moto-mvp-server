package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleExtraCostDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleExtraCostService;

import java.util.List;

public class VehicleExtraCostEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_EXTRA_COST_ENDPOINT.getEndPointKey();
    }

    public List<VehicleExtraCostDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleExtraCostDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleExtraCostDAO> save( List<VehicleExtraCostDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleExtraCostDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleExtraCostService getService()
    {
    return this.getService( ServiceKey.VEHICLE_EXTRA_COST_SERVICE.getServiceKey() );
    }
}
