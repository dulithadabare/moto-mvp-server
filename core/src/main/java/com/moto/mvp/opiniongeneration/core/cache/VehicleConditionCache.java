package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionDAO;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleConditionCache extends AbstractBasicCache
{
    private Map<String, VehicleConditionDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, VehicleConditionDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<VehicleConditionDAO> vehicleConditionDAOList = getHibernateResource().loadAll( VehicleConditionDAO.class );

        for ( VehicleConditionDAO vehicleConditionDAO : vehicleConditionDAOList )
        {
            this.codeCache.put( vehicleConditionDAO.getCode(), vehicleConditionDAO );
            this.idCache.put( vehicleConditionDAO.getId(), vehicleConditionDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.VEHICLE_CONDITION_CACHE.getCacheKey();
    }

    public VehicleConditionDAO getVehicleCondition( String code )
    {
        return this.codeCache.get( code );
    }

    public VehicleConditionDAO getVehicleCondition( int id )
    {
        return this.idCache.get( id );
    }

}
