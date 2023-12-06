package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.foundation.core.constant.EndpointKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceParentValDAO;
import com.moto.mvp.opiniongeneration.core.service.ReferenceParentValService;

import java.util.List;

public class ReferenceParentValEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.REFERENCE_PARENT_VAL_ENDPOINT.getEndPointKey();
    }

    public List<ReferenceParentValDAO> getAll()
    {
        return getService().getAll();
    }

    public List<ReferenceParentValDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<ReferenceParentValDAO> save( List<ReferenceParentValDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<ReferenceParentValDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private ReferenceParentValService getService()
    {
    return this.getService( ServiceKey.REFERENCE_PARENT_VAL_SERVICE.getServiceKey() );
    }
}
