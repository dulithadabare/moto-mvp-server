package com.moto.mvp.opiniongeneration.core.resource;

import com.moto.mvp.foundation.core.resource.AbstractResourceManager;
import com.moto.mvp.foundation.core.resource.BasicResource;
import com.moto.mvp.opiniongeneration.core.dao.*;
import com.moto.mvp.foundation.core.resource.HibernateResource;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager extends AbstractResourceManager
{

    @Override
    protected List<BasicResource> getResources()
    {
        List<BasicResource> resources = new ArrayList<>();

        resources.add( getHibernateResource() );

        return resources;
    }

    private HibernateResource getHibernateResource()
    {
        HibernateResource hibernateResource = new HibernateResource();
        hibernateResource.setHibernateConfigsPackages( getHibernateConfigsPackages() );

        return hibernateResource;
    }

    private List<Class<?>> getHibernateConfigsPackages()
    {
        List<Class<?>> packages = new ArrayList<>();

        packages.add( BatteryCostDAO.class );
        packages.add( BodyColourCostDAO.class );
        packages.add( BodyColourDAO.class );
        packages.add( CountryDAO.class );
        packages.add( DepreciationRuleDAO.class );
        packages.add( EngineTypeDAO.class );
        packages.add( ExactMatchDepreciationRuleDAO.class );
        packages.add( FlaggingRuleDAO.class );
        packages.add( HornCostDAO.class );
        packages.add( MarketAdjustmentRuleDAO.class );
        packages.add( OdometerDepreciationRuleDAO.class );
        packages.add( VehicleExtraCostDAO.class );
        packages.add( VehicleExtraDAO.class );
        packages.add( VehicleMakeDAO.class );
        packages.add( VehicleModelDAO.class );
        packages.add( VehicleOtherLightsCostDAO.class );
        packages.add( VehiclePaintFinishCostDAO.class );
        packages.add( VehiclePartPercentageRuleDAO.class );
        packages.add( VehicleStatusDAO.class );
        packages.add( VehicleTyreCostDAO.class );
        packages.add( InsuranceCoverRuleDAO.class );
        packages.add( VehicleConditionDAO.class );
        packages.add( VehicleConditionTypeDAO.class );
        packages.add( VehicleConditionRatingDAO.class );

        return packages;
    }
}
