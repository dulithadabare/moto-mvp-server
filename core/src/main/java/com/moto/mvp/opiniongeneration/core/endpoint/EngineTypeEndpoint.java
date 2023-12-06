package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.EngineTypeDAO;
import com.moto.mvp.opiniongeneration.core.service.EngineTypeService;

import java.util.List;

public class EngineTypeEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.ENGINE_TYPE_ENDPOINT.getEndPointKey();
    }

    public List<EngineTypeDAO> getAll()
    {
        return getService().getAll();
    }

    public List<EngineTypeDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<EngineTypeDAO> save( List<EngineTypeDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<EngineTypeDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private EngineTypeService getService()
    {
    return this.getService( ServiceKey.ENGINE_TYPE_SERVICE.getServiceKey() );
    }
}
