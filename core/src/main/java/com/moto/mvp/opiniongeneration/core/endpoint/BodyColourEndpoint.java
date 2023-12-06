package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.BodyColourDAO;
import com.moto.mvp.opiniongeneration.core.service.BodyColourService;

import java.util.List;

public class BodyColourEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.BODY_COLOUR_ENDPOINT.getEndPointKey();
    }

    public List<BodyColourDAO> getAll()
    {
        return getService().getAll();
    }

    public List<BodyColourDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<BodyColourDAO> save( List<BodyColourDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<BodyColourDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private BodyColourService getService()
    {
    return this.getService( ServiceKey.BODY_COLOUR_SERVICE.getServiceKey() );
    }
}
