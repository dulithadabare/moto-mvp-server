package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.InsuranceCoverRuleDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InsuranceCoverRuleService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( InsuranceCoverRuleService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.INSURANCE_COVER_RULE_SERVICE.getServiceKey();
    }

    public List<InsuranceCoverRuleDAO> getAll()
    {
        return getHibernateResource().loadAll( InsuranceCoverRuleDAO.class );
    }

    public List<InsuranceCoverRuleDAO> getById( int entityId )
    {
        return getHibernateResource().load( InsuranceCoverRuleDAO.class, entityId );
    }

    public List<InsuranceCoverRuleDAO> save( List<InsuranceCoverRuleDAO> entityList )
    {
        List<InsuranceCoverRuleDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.INSURANCE_COVER_RULE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<InsuranceCoverRuleDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.INSURANCE_COVER_RULE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
