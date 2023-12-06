package com.moto.mvp.opiniongeneration.core.cache;

import com.moto.mvp.foundation.core.cache.AbstractBasicCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceParentValDAO;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceChildValDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ValuationDbMappingCache extends AbstractBasicCache
{
    private Map<String, List<String>> mappingCache = new ConcurrentHashMap<>();
    private Map<String,  String> reverseMappingCache = new ConcurrentHashMap<>();

    @Override
    public void load()
    {

        logger.info( "START LOADING CACHE : " + getKey() );
        List<ReferenceParentValDAO> referenceParentValDAOList = getHibernateResource().loadWithRestriction( ReferenceParentValDAO.class, "referenceValuationDbId", 1 );

        for (  ReferenceParentValDAO referenceParentValDAO : referenceParentValDAOList )
        {
            this.mappingCache.put( referenceParentValDAO.getValue().trim(),
                                   referenceParentValDAO.getReferenceMappingValList()
                                           .stream()
                                           .map( ReferenceChildValDAO::getValue )
                                           .map( String::trim )
                                           .collect( Collectors.toCollection(ArrayList::new) ) );

            referenceParentValDAO.getReferenceMappingValList()
                    .forEach( ref -> this.reverseMappingCache.put(
                            ref.getValue(),
                            referenceParentValDAO.getValue() ) );
        }
        logger.info( "FINISH LOADING CACHE : " + getKey() );
    }

    @Override
    public String getKey()
    {
        return CacheKey.VALUATION_DB_MAPPING_CACHE.getCacheKey();
    }

    public  List<String> getChildMappingList( String mappedValue )
    {
        return this.mappingCache.get( mappedValue );
    }

    public  String getParentMappingFromChildValue( String childMappingValue )
    {
        return this.reverseMappingCache.get( childMappingValue );
    }

}
