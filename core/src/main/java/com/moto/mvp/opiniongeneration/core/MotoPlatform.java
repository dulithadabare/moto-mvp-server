package com.moto.mvp.opiniongeneration.core;

import com.moto.mvp.foundation.core.endpoint.BasicEndpoint;
import com.moto.mvp.foundation.core.service.BasicService;
import com.moto.mvp.opiniongeneration.core.cache.*;
import com.moto.mvp.opiniongeneration.core.resource.ResourceManager;
import com.moto.mvp.opiniongeneration.core.endpoint.*;
import com.moto.mvp.opiniongeneration.core.service.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component( "MotoPlatform" )
public class MotoPlatform
{
    private boolean initialized;
    private Properties properties;
    private Map<String, BasicService> services;
    private Map<String, BasicEndpoint> endpoints;
    private ResourceManager resourceManager;
    private CacheManager cacheManager;


    public MotoPlatform()
    {
        Properties properties = new Properties();
        System.getenv().forEach( properties::put );

        System.out.println("HIBERNATE_RESOURCE_DB_URL : " + System.getenv( "HIBERNATE_RESOURCE_DB_URL" ));

        this.initialized = false;
        this.properties = properties;
        this.resourceManager = new ResourceManager();
        this.cacheManager = new CacheManager();
        this.services = new HashMap<>();
        this.endpoints = new HashMap<>();

    }

    public void init()
    {
        initResourceManager();
        initCacheManager();
        initServices();
        initEndpoints();
        this.initialized = true;
    }

    private void initResourceManager()
    {
        this.resourceManager.init( this.properties );
    }

    private void initCacheManager()
    {
        this.cacheManager.init( this.resourceManager );
    }

    private void initServices()
    {
        this.services = getServices().stream()
                .distinct()
                .collect( Collectors.toConcurrentMap( BasicService::getKey, Function.identity() ) );

        for( String key : this.services.keySet() )
        {
            BasicService service = this.services.get( key );
            service.setResourceManager( this.resourceManager );
            service.setCacheManager( this.cacheManager );
        }
    }

    private void initEndpoints()
    {
        List<BasicEndpoint> endpoints = getEndPoints();
        for( BasicEndpoint endpoint : endpoints )
        {
            endpoint.setServices( this.services );
        }

        this.endpoints = endpoints.stream()
                .distinct()
                .collect( Collectors.toConcurrentMap( BasicEndpoint::getKey, Function.identity() ) );
    }

    private List<BasicService> getServices()
    {
        List<BasicService> basicServices = new ArrayList<>();

        basicServices.add( new OpinionGenerationService() );
        basicServices.add( new DepreciationRuleService() );
        basicServices.add( new BatteryCostService() );
        basicServices.add( new BodyColourCostService() );
        basicServices.add( new ExactMatchDepreciationRuleService() );
        basicServices.add( new FlaggingRuleService() );
        basicServices.add( new HornCostService() );
        basicServices.add( new MarketAdjustmentRuleService() );
        basicServices.add( new OdometerDepreciationRuleService() );
        basicServices.add( new VehicleExtraCostService() );
        basicServices.add( new VehicleOtherLightsCostService() );
        basicServices.add( new VehiclePaintFinishCostService() );
        basicServices.add( new VehiclePartPercentageRuleService() );
        basicServices.add( new VehicleTyreCostService() );
        basicServices.add( new VehicleMakeService() );
        basicServices.add( new VehicleModelService() );
        basicServices.add( new EngineTypeService() );
        basicServices.add( new CountryService() );
        basicServices.add( new BodyColourService() );
        basicServices.add( new VehicleStatusService() );
        basicServices.add( new VehicleExtraService() );
        basicServices.add( new InsuranceCoverRuleService() );
        basicServices.add( new VehicleConditionService() );
        basicServices.add( new VehicleConditionTypeService() );
        basicServices.add( new VehicleConditionRatingService() );

        return basicServices;
    }

    private List<BasicEndpoint> getEndPoints()
    {
        List<BasicEndpoint> basicEndpoints = new ArrayList<>();

        basicEndpoints.add( new OpinionGenerationEndpoint() );
        basicEndpoints.add( new DepreciationRuleEndpoint() );
        basicEndpoints.add( new BatteryCostEndpoint() );
        basicEndpoints.add( new BodyColourCostEndpoint() );
        basicEndpoints.add( new ExactMatchDepreciationRuleEndpoint() );
        basicEndpoints.add( new FlaggingRuleEndpoint() );
        basicEndpoints.add( new HornCostEndpoint() );
        basicEndpoints.add( new MarketAdjustmentRuleEndpoint() );
        basicEndpoints.add( new OdometerDepreciationRuleEndpoint() );
        basicEndpoints.add( new VehicleExtraCostEndpoint() );
        basicEndpoints.add( new VehicleOtherLightsCostEndpoint() );
        basicEndpoints.add( new VehiclePaintFinishCostEndpoint() );
        basicEndpoints.add( new VehiclePartPercentageRuleEndpoint() );
        basicEndpoints.add( new VehicleTyreCostEndpoint() );
        basicEndpoints.add( new VehicleMakeEndpoint() );
        basicEndpoints.add( new VehicleModelEndpoint() );
        basicEndpoints.add( new EngineTypeEndpoint() );
        basicEndpoints.add( new CountryEndpoint() );
        basicEndpoints.add( new BodyColourEndpoint() );
        basicEndpoints.add( new VehicleStatusEndpoint() );
        basicEndpoints.add( new VehicleExtraEndpoint() );
        basicEndpoints.add( new InsuranceCoverRuleEndpoint());
        basicEndpoints.add( new VehicleConditionEndpoint());
        basicEndpoints.add( new VehicleConditionTypeEndpoint());
        basicEndpoints.add( new VehicleConditionRatingEndpoint());

        return basicEndpoints;
    }

    public <E extends BasicEndpoint> E getEndpoint( String key )
    {
        if( this.initialized )
        {
            E endpoint = ( E ) this.endpoints.get( key );

            return endpoint;
        }

        return null;
    }

    public void shutdown()
    {
        this.resourceManager.shutdown();
    }

}
