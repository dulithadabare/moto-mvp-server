package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.EngineTypeDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EngineTypeCache extends AbstractBasicCache
{
    private Map<String, EngineTypeDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, EngineTypeDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<EngineTypeDAO> engineTypeDAOList = getHibernateResource().loadAll( EngineTypeDAO.class );

        for ( EngineTypeDAO engineTypeDAO : engineTypeDAOList )
        {
            this.codeCache.put( engineTypeDAO.getCode(), engineTypeDAO );
            this.idCache.put( engineTypeDAO.getId(), engineTypeDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.ENGINE_TYPE_CACHE.getCacheKey();
    }

    public EngineTypeDAO getEngineType( String code )
    {
        return this.codeCache.get( code );
    }

    public EngineTypeDAO getEngineType( int id )
    {
        return this.idCache.get( id );
    }

}
