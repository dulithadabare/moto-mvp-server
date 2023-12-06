package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.DepreciationRuleDAO;
import com.moto.mvp.opiniongeneration.core.service.DepreciationRuleService;

import java.util.List;

public class DepreciationRuleEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.DEPRECIATION_RULE_ENDPOINT.getEndPointKey();
    }

    public List<DepreciationRuleDAO> getAll()
    {
        return getService().getAll();
    }

    public List<DepreciationRuleDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<DepreciationRuleDAO> save( List<DepreciationRuleDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<DepreciationRuleDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private DepreciationRuleService getService()
    {
    return this.getService( ServiceKey.DEPRECIATION_RULE_SERVICE.getServiceKey() );
    }
}
