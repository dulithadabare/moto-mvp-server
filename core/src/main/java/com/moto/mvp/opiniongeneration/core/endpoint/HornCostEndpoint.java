package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.HornCostDAO;
import com.moto.mvp.opiniongeneration.core.service.HornCostService;

import java.util.List;

public class HornCostEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_HORN_COST_ENDPOINT.getEndPointKey();
    }

    public List<HornCostDAO> getAll()
    {
        return getService().getAll();
    }

    public List<HornCostDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<HornCostDAO> save( List<HornCostDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<HornCostDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private HornCostService getService()
    {
    return this.getService( ServiceKey.VEHICLE_HORN_COST_SERVICE.getServiceKey() );
    }
}
