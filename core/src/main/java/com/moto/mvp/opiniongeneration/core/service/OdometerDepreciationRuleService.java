package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.OdometerDepreciationRuleDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OdometerDepreciationRuleService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( OdometerDepreciationRuleService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.ODOMETER_DEPRECIATION_RULE_SERVICE.getServiceKey();
    }

    public List<OdometerDepreciationRuleDAO> getAll()
    {
        return getHibernateResource().loadAll( OdometerDepreciationRuleDAO.class );
    }

    public List<OdometerDepreciationRuleDAO> getById( int entityId )
    {
        return getHibernateResource().load( OdometerDepreciationRuleDAO.class, entityId );
    }

    public List<OdometerDepreciationRuleDAO> save( List<OdometerDepreciationRuleDAO> entityList )
    {
        List<OdometerDepreciationRuleDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.ODOMETER_DEPRECIATION_RULE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<OdometerDepreciationRuleDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.ODOMETER_DEPRECIATION_RULE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
