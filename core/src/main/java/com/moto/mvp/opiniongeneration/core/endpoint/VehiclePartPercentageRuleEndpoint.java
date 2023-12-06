package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehiclePartPercentageRuleDAO;
import com.moto.mvp.opiniongeneration.core.service.VehiclePartPercentageRuleService;

import java.util.List;

public class VehiclePartPercentageRuleEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.VEHICLE_PART_PERCENTAGE_RULE_ENDPOINT.getEndPointKey();
    }

    public List<VehiclePartPercentageRuleDAO> getAll()
    {
        return getService().getAll();
    }

    public List<VehiclePartPercentageRuleDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<VehiclePartPercentageRuleDAO> save( List<VehiclePartPercentageRuleDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<VehiclePartPercentageRuleDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private VehiclePartPercentageRuleService getService()
    {
    return this.getService( ServiceKey.VEHICLE_PART_PERCENTAGE_RULE_SERVICE.getServiceKey() );
    }
}
