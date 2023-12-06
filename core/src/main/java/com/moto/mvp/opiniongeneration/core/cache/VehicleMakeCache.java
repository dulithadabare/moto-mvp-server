package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleMakeDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleMakeCache extends AbstractBasicCache
{
    private Map<String, VehicleMakeDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, VehicleMakeDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<VehicleMakeDAO> vehicleMakeList = getHibernateResource().loadAll( VehicleMakeDAO.class );

        for ( VehicleMakeDAO vehicleMakeDAO : vehicleMakeList )
        {
            this.codeCache.put( vehicleMakeDAO.getCode(), vehicleMakeDAO );
            this.idCache.put( vehicleMakeDAO.getId(), vehicleMakeDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.VEHICLE_MAKE_CACHE.getCacheKey();
    }

    public VehicleMakeDAO getVehicleMake( String code )
    {
        return this.codeCache.get( code );
    }

    public VehicleMakeDAO getVehicleMake( int id )
    {
        return this.idCache.get( id );
    }

}
