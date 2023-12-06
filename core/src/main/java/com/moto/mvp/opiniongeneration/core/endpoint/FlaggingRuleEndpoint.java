package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.FlaggingRuleDAO;
import com.moto.mvp.opiniongeneration.core.service.FlaggingRuleService;

import java.util.List;

public class FlaggingRuleEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.FLAGGING_RULE_ENDPOINT.getEndPointKey();
    }

    public List<FlaggingRuleDAO> getAll()
    {
        return getService().getAll();
    }

    public List<FlaggingRuleDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<FlaggingRuleDAO> save( List<FlaggingRuleDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<FlaggingRuleDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private FlaggingRuleService getService()
    {
    return this.getService( ServiceKey.FLAGGING_RULE_SERVICE.getServiceKey() );
    }
}
