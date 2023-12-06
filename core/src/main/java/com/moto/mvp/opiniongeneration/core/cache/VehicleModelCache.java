package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleModelDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleModelCache extends AbstractBasicCache
{
    private Map<String, VehicleModelDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, VehicleModelDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<VehicleModelDAO> vehicleModelList = getHibernateResource().loadAll( VehicleModelDAO.class );

        for ( VehicleModelDAO vehicleModel : vehicleModelList )
        {
            this.codeCache.put( vehicleModel.getCode(), vehicleModel );
            this.idCache.put( vehicleModel.getId(), vehicleModel );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.VEHICLE_MODEL_CACHE.getCacheKey();
    }

    public VehicleModelDAO getVehicleModel( String code )
    {
        return this.codeCache.get( code );
    }

    public VehicleModelDAO getVehicleModel( int id )
    {
        return this.idCache.get( id );
    }

}
