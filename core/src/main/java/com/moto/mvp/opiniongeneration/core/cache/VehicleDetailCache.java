package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.opiniongeneration.core.dao.VehicleDetailDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleDetailCache<T extends VehicleDetailDAO> extends AbstractBasicCache
{
    private Map<String, T> codeCache = new ConcurrentHashMap<>();
    private Map<String, T> nameCache = new ConcurrentHashMap<>();
    private Map<Integer, T> idCache = new ConcurrentHashMap<>();

    private final Class<T> type;
    private final String cacheKey;

    public VehicleDetailCache( Class<T> type, String cacheKey )
    {
        this.type = type;
        this.cacheKey = cacheKey;
    }

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<T> vehicleDetailDAOList = getHibernateResource().loadAll( type );

        for ( T vehicleDetailDAO : vehicleDetailDAOList )
        {
            this.codeCache.put( vehicleDetailDAO.getCode(), vehicleDetailDAO );
            this.nameCache.put( vehicleDetailDAO.getName(), vehicleDetailDAO );
            this.idCache.put( vehicleDetailDAO.getId(), vehicleDetailDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return this.cacheKey;
    }

    public T getVehicleDetail( String code )
    {
        return this.codeCache.get( code );
    }

    public T getVehicleDetailByName( String name )
    {
        return this.nameCache.get( name );
    }

    public T getVehicleDetail( int id )
    {
        return this.idCache.get( id );
    }

}
