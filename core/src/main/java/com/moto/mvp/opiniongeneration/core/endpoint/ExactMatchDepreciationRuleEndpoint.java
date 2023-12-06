package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.ExactMatchDepreciationRuleDAO;
import com.moto.mvp.opiniongeneration.core.service.ExactMatchDepreciationRuleService;

import java.util.List;

public class ExactMatchDepreciationRuleEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.EXACT_MATCH_DEPRECIATION_RULE_ENDPOINT.getEndPointKey();
    }

    public List<ExactMatchDepreciationRuleDAO> getAll()
    {
        return getService().getAll();
    }

    public List<ExactMatchDepreciationRuleDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<ExactMatchDepreciationRuleDAO> save( List<ExactMatchDepreciationRuleDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<ExactMatchDepreciationRuleDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private ExactMatchDepreciationRuleService getService()
    {
    return this.getService( ServiceKey.EXACT_MATCH_DEPRECIATION_RULE_SERVICE.getServiceKey() );
    }
}
