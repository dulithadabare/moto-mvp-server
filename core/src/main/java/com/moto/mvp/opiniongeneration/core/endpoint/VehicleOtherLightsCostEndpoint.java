package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleOtherLightsCostDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleOtherLightsCostService;

import java.util.List;

public class VehicleOtherLightsCostEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_OTHER_LIGHTS_COST_ENDPOINT.getEndPointKey();
    }

    public List<VehicleOtherLightsCostDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleOtherLightsCostDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleOtherLightsCostDAO> save( List<VehicleOtherLightsCostDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleOtherLightsCostDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleOtherLightsCostService getService()
    {
    return this.getService( ServiceKey.VEHICLE_OTHER_LIGHTS_COST_SERVICE.getServiceKey() );
    }
}
