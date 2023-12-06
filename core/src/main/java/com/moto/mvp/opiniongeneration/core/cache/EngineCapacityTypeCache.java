package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.EngineCapacityTypeDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EngineCapacityTypeCache extends AbstractBasicCache
{
    private Map<String, EngineCapacityTypeDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, EngineCapacityTypeDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {
        logger.info( "START LOADING CACHE : " + getKey() );
        List<EngineCapacityTypeDAO> engineCapacityTypeDAOList = getHibernateResource().loadAll( EngineCapacityTypeDAO.class );

        for ( EngineCapacityTypeDAO engineCapacityTypeDAO : engineCapacityTypeDAOList )
        {
            this.codeCache.put( engineCapacityTypeDAO.getCode(), engineCapacityTypeDAO );
            this.idCache.put( engineCapacityTypeDAO.getId(), engineCapacityTypeDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.ENGINE_CAPACITY_TYPE_CACHE.getCacheKey();
    }

    public EngineCapacityTypeDAO getEngineCapacityType( String code )
    {
        return this.codeCache.get( code );
    }

    public EngineCapacityTypeDAO getEngineCapacityType( int id )
    {
        return this.idCache.get( id );
    }

}
