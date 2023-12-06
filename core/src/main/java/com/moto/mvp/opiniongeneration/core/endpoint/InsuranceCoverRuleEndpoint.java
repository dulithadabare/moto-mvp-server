package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.InsuranceCoverRuleDAO;
import com.moto.mvp.opiniongeneration.core.service.InsuranceCoverRuleService;

import java.util.List;

public class InsuranceCoverRuleEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.INSURANCE_COVER_RULE_ENDPOINT.getEndPointKey();
    }

    public List<InsuranceCoverRuleDAO> getAll()
    {
        return getService().getAll();
    }

    public List<InsuranceCoverRuleDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<InsuranceCoverRuleDAO> save( List<InsuranceCoverRuleDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<InsuranceCoverRuleDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private InsuranceCoverRuleService getService()
    {
    return this.getService( ServiceKey.INSURANCE_COVER_RULE_SERVICE.getServiceKey() );
    }
}
