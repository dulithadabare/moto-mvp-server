package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionRatingDAO;
import com.moto.mvp.opiniongeneration.core.service.VehicleConditionRatingService;

import java.util.List;

public class VehicleConditionRatingEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_CONDITION_RATING_ENDPOINT.getEndPointKey();
    }

    public List<VehicleConditionRatingDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehicleConditionRatingDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehicleConditionRatingDAO> save( List<VehicleConditionRatingDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehicleConditionRatingDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehicleConditionRatingService getService()
    {
    return this.getService( ServiceKey.VEHICLE_CONDITION_RATING_SERVICE.getServiceKey() );
    }
}
