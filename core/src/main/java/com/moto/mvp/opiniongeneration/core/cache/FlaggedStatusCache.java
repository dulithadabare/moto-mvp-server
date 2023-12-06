package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.FlaggedStatusDAO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlaggedStatusCache extends AbstractBasicCache
{
    private Map<Integer, FlaggedStatusDAO> data = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<FlaggedStatusDAO> flaggingRuleDAOList = getHibernateResource().loadAll( FlaggedStatusDAO.class );

        for ( FlaggedStatusDAO flaggingRuleDAO : flaggingRuleDAOList )
        {
            this.data.put( flaggingRuleDAO.getCode(), flaggingRuleDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.FLAGGED_STATUS_CACHE.getCacheKey();
    }

    public FlaggedStatusDAO getFlaggedStatus( int code )
    {
        return this.data.get( code );
    }
}