package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleStatusDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleStatusCache extends AbstractBasicCache
{
    private Map<String, VehicleStatusDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, VehicleStatusDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<VehicleStatusDAO> vehicleStatusDAOList = getHibernateResource().loadAll( VehicleStatusDAO.class );

        for ( VehicleStatusDAO vehicleStatusDAO : vehicleStatusDAOList )
        {
            this.codeCache.put( vehicleStatusDAO.getCode(), vehicleStatusDAO );
            this.idCache.put( vehicleStatusDAO.getId(), vehicleStatusDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.VEHICLE_STATUS_CACHE.getCacheKey();
    }

    public VehicleStatusDAO getVehicleStatus( String code )
    {
        return this.codeCache.get( code );
    }

    public VehicleStatusDAO getVehicleStatus( int id )
    {
        return this.idCache.get( id );
    }

}
