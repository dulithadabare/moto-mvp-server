package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleModelDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleModelService;

import java.util.List;

public class VehicleModelEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_MODEL_ENDPOINT.getEndPointKey();
    }

    public List<VehicleModelDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleModelDAO> getByMakeId( int makeId )
    {
        return getService().getByMakeId( makeId );
    }

    public List<VehicleModelDAO> save( List<VehicleModelDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleModelDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleModelService getService()
    {
    return this.getService( ServiceKey.VEHICLE_MODEL_SERVICE.getServiceKey() );
    }
}
