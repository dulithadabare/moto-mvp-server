package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehiclePaintFinishCostDAO;
import com.moto.mvp.opiniongeneration.core.service.VehiclePaintFinishCostService;

import java.util.List;

public class VehiclePaintFinishCostEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_PAINT_FINISH_COST_ENDPOINT.getEndPointKey();
    }

    public List<VehiclePaintFinishCostDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehiclePaintFinishCostDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehiclePaintFinishCostDAO> save( List<VehiclePaintFinishCostDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehiclePaintFinishCostDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehiclePaintFinishCostService getService()
    {
    return this.getService( ServiceKey.VEHICLE_PAINT_FINISH_COST_SERVICE.getServiceKey() );
    }
}
