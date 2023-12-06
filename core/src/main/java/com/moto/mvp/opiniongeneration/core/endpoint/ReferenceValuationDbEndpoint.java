package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.foundation.core.constant.EndpointKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceValuationDbDAO;
import com.moto.mvp.opiniongeneration.core.service.ReferenceValuationDbService;

import java.util.List;

public class ReferenceValuationDbEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.REFERENCE_VALUATION_DB_ENDPOINT.getEndPointKey();
    }

    public List<ReferenceValuationDbDAO> getAll()
    {
        return getService().getAll();
    }

    public List<ReferenceValuationDbDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<ReferenceValuationDbDAO> save( List<ReferenceValuationDbDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<ReferenceValuationDbDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private ReferenceValuationDbService getService()
    {
    return this.getService( ServiceKey.REFERENCE_VALUATION_DB_SERVICE.getServiceKey() );
    }
}
