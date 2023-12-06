package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.OdometerDepreciationRuleDAO;
import com.moto.mvp.opiniongeneration.core.service.OdometerDepreciationRuleService;

import java.util.List;

public class OdometerDepreciationRuleEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.ODOMETER_DEPRECIATION_RULE_ENDPOINT.getEndPointKey();
    }

    public List<OdometerDepreciationRuleDAO> getAll()
    {
        return getService().getAll();
    }

    public List<OdometerDepreciationRuleDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<OdometerDepreciationRuleDAO> save( List<OdometerDepreciationRuleDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<OdometerDepreciationRuleDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private OdometerDepreciationRuleService getService()
    {
    return this.getService( ServiceKey.ODOMETER_DEPRECIATION_RULE_SERVICE.getServiceKey() );
    }
}
