package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.CountryDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CountryCache extends AbstractBasicCache
{
    private Map<String, CountryDAO> codeCache = new ConcurrentHashMap<>();
    private Map<Integer, CountryDAO> idCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<CountryDAO> countryDAOList = getHibernateResource().loadAll( CountryDAO.class );

        for ( CountryDAO countryDAO : countryDAOList )
        {
            this.codeCache.put( countryDAO.getCode(), countryDAO );
            this.idCache.put( countryDAO.getId(), countryDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.COUNTRY_CACHE.getCacheKey();
    }

    public CountryDAO getCountry( String code )
    {
        return this.codeCache.get( code );
    }

    public CountryDAO getCountry( int id )
    {
        return this.idCache.get( id );
    }

}
