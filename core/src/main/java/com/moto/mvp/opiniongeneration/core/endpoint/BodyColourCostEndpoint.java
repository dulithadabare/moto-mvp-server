package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.BodyColourCostDAO;
import com.moto.mvp.opiniongeneration.core.service.BodyColourCostService;

import java.util.List;

public class BodyColourCostEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_BODY_COLOUR_COST_ENDPOINT.getEndPointKey();
    }

    public List<BodyColourCostDAO> getAll()
    {
        return getService().getAll();
    }

    public List<BodyColourCostDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<BodyColourCostDAO> save( List<BodyColourCostDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<BodyColourCostDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private BodyColourCostService getService()
    {
    return this.getService( ServiceKey.VEHICLE_BODY_COLOUR_COST_SERVICE.getServiceKey() );
    }
}
