package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleClassDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleClassCache extends AbstractBasicCache
{
    private Map<String, VehicleClassDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, VehicleClassDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<VehicleClassDAO> vehicleClassList = getHibernateResource().loadAll( VehicleClassDAO.class );

        for ( VehicleClassDAO vehicleClassDAO : vehicleClassList )
        {
            this.codeCache.put( vehicleClassDAO.getCode(), vehicleClassDAO );
            this.idCache.put( vehicleClassDAO.getId(), vehicleClassDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.VEHICLE_CLASS_CACHE.getCacheKey();
    }

    public VehicleClassDAO getVehicleClass( String code )
    {
        return this.codeCache.get( code );
    }

    public VehicleClassDAO getVehicleClass( int id )
    {
        return this.idCache.get( id );
    }

}
