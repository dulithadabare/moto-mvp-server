package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.foundation.core.constant.EndpointKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.EngineCapacityTypeDAO;
import com.moto.mvp.opiniongeneration.core.service.EngineCapacityTypeService;

import java.util.List;

public class EngineCapacityTypeEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.ENGINE_CAPACITY_TYPE_ENDPOINT.getEndPointKey();
    }

    public List<EngineCapacityTypeDAO> getAll()
    {
        return getService().getAll();
    }

    public List<EngineCapacityTypeDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<EngineCapacityTypeDAO> save( List<EngineCapacityTypeDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<EngineCapacityTypeDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private EngineCapacityTypeService getService()
    {
    return this.getService( ServiceKey.ENGINE_CAPACITY_TYPE_SERVICE.getServiceKey() );
    }
}
