package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.FlaggingRuleDAO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FlaggingRuleCache extends AbstractBasicCache
{
    private Map<Integer, FlaggingRuleDAO> data = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<FlaggingRuleDAO> flaggingRuleDAOList = getHibernateResource().loadAll( FlaggingRuleDAO.class );

        for ( FlaggingRuleDAO flaggingRuleDAO : flaggingRuleDAOList )
        {
            this.data.put( flaggingRuleDAO.getFlaggedStatusCode(), flaggingRuleDAO );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.FLAGGING_RULE_CACHE.getCacheKey();
    }

    public Set<Integer> getFlaggingRuleKeySet()
    {
        return this.data.keySet();
    }

    public FlaggingRuleDAO getFlaggingRule( int flaggedStatusCode )
    {
        return this.data.get( flaggedStatusCode );
    }
}