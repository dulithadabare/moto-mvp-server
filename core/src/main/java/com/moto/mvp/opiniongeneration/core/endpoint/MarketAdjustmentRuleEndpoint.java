package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.MarketAdjustmentRuleDAO;
import com.moto.mvp.opiniongeneration.core.service.MarketAdjustmentRuleService;

import java.util.List;

public class MarketAdjustmentRuleEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.MARKET_ADJUSTMENT_RULE_ENDPOINT.getEndPointKey();
    }

    public List<MarketAdjustmentRuleDAO> getAll()
    {
        return getService().getAll();
    }

    public List<MarketAdjustmentRuleDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<MarketAdjustmentRuleDAO> save( List<MarketAdjustmentRuleDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<MarketAdjustmentRuleDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private MarketAdjustmentRuleService getService()
    {
    return this.getService( ServiceKey.MARKET_ADJUSTMENT_RULE_SERVICE.getServiceKey() );
    }
}
