package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.BodyColourDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BodyColourCache extends AbstractBasicCache
{
    private Map<String, BodyColourDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, BodyColourDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<BodyColourDAO> bodyColourDAOList = getHibernateResource().loadAll( BodyColourDAO.class );

        for ( BodyColourDAO bodyColourDAO : bodyColourDAOList )
        {
            this.codeCache.put( bodyColourDAO.getCode(), bodyColourDAO );
            this.idCache.put( bodyColourDAO.getId(), bodyColourDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.BODY_COLOUR_CACHE.getCacheKey();
    }

    public BodyColourDAO getBodyColour( String code )
    {
        return this.codeCache.get( code );
    }

    public BodyColourDAO getBodyColour( int id )
    {
        return this.idCache.get( id );
    }

}
