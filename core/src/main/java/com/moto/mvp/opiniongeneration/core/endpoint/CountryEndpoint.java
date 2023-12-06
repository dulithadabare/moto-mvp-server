package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.CountryDAO;
import com.moto.mvp.opiniongeneration.core.service.CountryService;

import java.util.List;

public class CountryEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.COUNTRY_ENDPOINT.getEndPointKey();
    }

    public List<CountryDAO> getAll()
    {
        return getService().getAll();
    }

    public List<CountryDAO> getById( int entityId )
    {
        return getService().getById( entityId );
    }

    public List<CountryDAO> save( List<CountryDAO> entityList )
    {
        return getService().save( entityList );
    }

    public int delete( List<CountryDAO> entityList )
    {
        return getService().delete( entityList );
    }

    private CountryService getService()
    {
    return this.getService( ServiceKey.COUNTRY_SERVICE.getServiceKey() );
    }
}
