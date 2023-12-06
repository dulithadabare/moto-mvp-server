package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.ResourceKey;
import com.moto.mvp.foundation.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.dao.VehiclePartPercentageRuleDAO;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehiclePartPercentageRuleService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( VehiclePartPercentageRuleService.class );

    @Override
    public String getKey()
    {
        return ServiceKey.VEHICLE_PART_PERCENTAGE_RULE_SERVICE.getServiceKey();
    }

    public List<VehiclePartPercentageRuleDAO> getAll()
    {
        return getHibernateResource().loadAll( VehiclePartPercentageRuleDAO.class );
    }

    public List<VehiclePartPercentageRuleDAO> getById( int entityId )
    {
        return getHibernateResource().load( VehiclePartPercentageRuleDAO.class, entityId );
    }

    public List<VehiclePartPercentageRuleDAO> save( List<VehiclePartPercentageRuleDAO> entityList )
    {
        List<VehiclePartPercentageRuleDAO> savedEntity = getHibernateResource().saveBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_PART_PERCENTAGE_RULE_CACHE.toString() );

        return savedEntity;
    }

    public int delete( List<VehiclePartPercentageRuleDAO> entityList )
    {
        int deleteSuccess = getHibernateResource().deleteBatch( entityList );

        this.cacheManager.load( CacheKey.VEHICLE_PART_PERCENTAGE_RULE_CACHE.toString() );

        return deleteSuccess;
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
