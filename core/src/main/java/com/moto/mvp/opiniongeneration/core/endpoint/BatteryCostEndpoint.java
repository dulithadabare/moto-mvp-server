package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.BatteryCostDAO;
import com.moto.mvp.opiniongeneration.core.service.BatteryCostService;

import java.util.List;

public class BatteryCostEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_BATTERY_COST_ENDPOINT.getEndPointKey();
    }

    public List<BatteryCostDAO> getAll()
    {
        return getService().getAll();
    }

    public List<BatteryCostDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<BatteryCostDAO> save( List<BatteryCostDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<BatteryCostDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private BatteryCostService getService()
    {
    return this.getService( ServiceKey.VEHICLE_BATTERY_COST_SERVICE.getServiceKey() );
    }
}
