package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleExtraDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleExtraService;

import java.util.List;

public class VehicleExtraEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_EXTRA_ENDPOINT.getEndPointKey();
    }

    public List<VehicleExtraDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleExtraDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleExtraDAO> save( List<VehicleExtraDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleExtraDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleExtraService getService()
    {
    return this.getService( ServiceKey.VEHICLE_EXTRA_SERVICE.getServiceKey() );
    }
}
