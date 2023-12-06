package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.foundation.core.constant.EndpointKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceChildValDAO;
import com.moto.mvp.opiniongeneration.core.service.ReferenceChildValService;

import java.util.List;

public class ReferenceChildValEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.REFERENCE_CHILD_VAL_ENDPOINT.getEndPointKey();
    }

    public List<ReferenceChildValDAO> getAll()
    {
        return getService().getAll();
    }

    public List<ReferenceChildValDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<ReferenceChildValDAO> save( List<ReferenceChildValDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<ReferenceChildValDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private ReferenceChildValService getService()
    {
    return this.getService( ServiceKey.REFERENCE_CHILD_VAL_SERVICE.getServiceKey() );
    }
}
