package com.moto.mvp.opiniongeneration.core.engine;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.foundation.core.constant.CacheKey;
import com.moto.mvp.foundation.core.constant.Status;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.foundation.core.resource.AbstractResourceManager;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import com.moto.mvp.opiniongeneration.core.cache.*;
import com.moto.mvp.opiniongeneration.core.constant.ConditionType;
import com.moto.mvp.opiniongeneration.core.constant.ResourceKey;
import com.moto.mvp.opiniongeneration.core.dao.*;
import com.moto.mvp.opiniongeneration.core.engine.rule.*;
import com.moto.mvp.opiniongeneration.core.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OpinionGenerationEngine
{
    private final AbstractCacheManager cacheManager;
    private final AbstractResourceManager resourceManager;

    private final Logger logger = LoggerFactory.getLogger( "OpinionGeneration" );
    private StringBuilder loggerSB;

    private OpinionGenerationRequest ogReq;
    private OpinionGenerationResponse ogRes;
    private VehicleValuation valuationReference;

    public OpinionGenerationEngine( AbstractCacheManager cacheManager, AbstractResourceManager resourceManager, OpinionGenerationRequest ogReq )
    {
        this.cacheManager = cacheManager;
        this.resourceManager = resourceManager;
        this.ogReq = ogReq;
        this.ogRes = new OpinionGenerationResponse();
        this.loggerSB = new StringBuilder();
    }

    public OpinionGenerationResponse generateOpinionValueResponse() throws OpinionGenerationException
    {
        String message = "";

        this.valuationReference = loadValuationReference( this.ogReq );

        if ( this.valuationReference != null )
        {
            validate( this.ogReq );
            validate( this.valuationReference );
            generateOpinionValue();
        }
        else
        {
            message = "MOST MATCHING REFERENCE NOT FOUND";

            throw new OpinionGenerationException( message );
        }

        return this.ogRes;

    }

    public OpinionGenerationResponse generateOpinionValueResponseTest( VehicleValuation valuationReference )
    {
        this.valuationReference = valuationReference;

        generateOpinionValue();

        return this.ogRes;

    }

    private void generateOpinionValue()
    {
        loggerSB.append( " VALUATION REFERENCE : " );
        loggerSB.append( "\n" );
        loggerSB.append( this.valuationReference.toString() );
        loggerSB.append( "\n" );

        this.ogRes.getOpinionValue().setForcedSaleValue( this.valuationReference.getForcedSaleValue() );
        this.ogRes.getOpinionValue().setAverageMarketValue( this.valuationReference.getForcedSaleValue() );

        List<AbstractBasicRule> ruleList = new ArrayList<>();

        ruleList.add( new DepreciationRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new OdometerDepreciationRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new VehicleBatteryFixedCostRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new VehicleBodyColourFixedCostRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new VehicleConditionRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new VehicleExtraFixedCostRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new VehicleHornFixedCostRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new VehicleOtherLightsFixedCostRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new VehiclePaintFinishFixedCostRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new VehicleTyreFixedCostRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new MarketAdjustmentRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue() ) );
        ruleList.add( new FlaggingRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue(), this.ogRes.getOpinionGenerationStatus() ) );
        ruleList.add( new InsuranceCoverRule( this.cacheManager, this.ogReq, this.valuationReference, this.ogRes.getOpinionValue(), this.ogRes.getInsurancePremiumValue() ) );

        for ( AbstractBasicRule rule : ruleList )
        {
            try
            {
                rule.apply();
                loggerSB.append( rule.getRuleLog() );
            }
            catch ( OpinionGenerationException e )
            {
                e.printStackTrace();

                String message = e.getMessage() != null ? e.getMessage() : Status.OPINION_GENERATION_ERROR.name();

                this.ogRes.getOpinionGenerationStatus().setStatus( Status.OPINION_GENERATION_ERROR.getCode() );
                this.ogRes.getOpinionGenerationStatus().setMessage( message );

                break;
            }
        }

    }

    private void validate( AbstractOpinionValuation abstractOpinionValuation ) throws OpinionGenerationException
    {
        VehicleDetailCache<BodyColourDAO> bodyColourCache = this.cacheManager.getCache( CacheKey.BODY_COLOUR_CACHE.toString() );

        VehicleDetails vehicleDetails = abstractOpinionValuation.getVehicleDetails();

        if ( vehicleDetails.getBodyColour() == null )
        {
            vehicleDetails.setBodyColour( bodyColourCache.getVehicleDetail( "PLW" ) );
        }
        if ( vehicleDetails.getVehicleBatteryType() == null )
        {
            vehicleDetails.setVehicleBatteryType( "CAR" );
        }
        if ( vehicleDetails.getVehicleHornType() == null )
        {
            vehicleDetails.setVehicleHornType( "OEM" );
        }
        if ( vehicleDetails.getVehiclePaintFinishType() == null )
        {
            vehicleDetails.setVehiclePaintFinishType( "OEM" );
        }

        VehicleCondition vehicleCondition = abstractOpinionValuation.getVehicleCondition();

        Map<String, Integer> generalConditionRatingMap = vehicleCondition.getGeneralConditionRatingMap();

        validateCondition( generalConditionRatingMap, ConditionType.ALTERNATOR_ELECTRICAL_CONDITION );
        validateCondition( generalConditionRatingMap, ConditionType.BATTERY_ELECTRICAL_CONDITION );
        validateCondition( generalConditionRatingMap, ConditionType.OTHER_LIGHTS_ELECTRICAL_CONDITION );
    }

    private void validateCondition( Map<String, Integer> generalConditionRatingMap, String conditionKey ) throws OpinionGenerationException
    {
        SimpleCache<Integer> vehiclePartMaxRatingCache = this.cacheManager.getCache( com.moto.mvp.opiniongeneration.core.constant.CacheKey.VEHICLE_PART_MAX_RATING_CACHE.toString() );

        if ( !generalConditionRatingMap.containsKey( conditionKey ) )
        {
            int rating = vehiclePartMaxRatingCache.getValueFromKey( conditionKey );
            generalConditionRatingMap.put( conditionKey, rating );
        }
    }

    private VehicleValuation loadValuationReference( OpinionGenerationRequest opinionGenerationReq ) throws OpinionGenerationException
    {
        VehicleValuation vehicleValuation = null;

        Map<String, String> paramMap = new HashMap<>(  );

        String simpleSqlQuery = getSimpleSQLQuery(paramMap, opinionGenerationReq);
        List<Object[]> valuationReferenceList = getHibernateResource().loadValuationReference( simpleSqlQuery, paramMap );
        vehicleValuation = mapVehicleValuation( valuationReferenceList );

       /* ValuationDbMappingCache valuationDbMappingCache = this.cacheManager.getCache( CacheKey.VALUATION_DB_MAPPING_CACHE.toString() );

        String reqVehicleMakeName = opinionGenerationReq.getVehicleDetails().getVehicleMake().getName();
        String reqVehicleModelName = opinionGenerationReq.getVehicleDetails().getVehicleModel().getName();
        String reqYearOfManufacture = String.valueOf( opinionGenerationReq.getVehicleDetails().getYearOfManufacture() );
        int reqEngineCapacity = opinionGenerationReq.getVehicleDetails().getEngineCapacity();
        String engineCapacityTypeCode = opinionGenerationReq.getVehicleDetails().getEngineCapacityType().getCode();




        Map<String, String> strictVehicleMakeParameterMap = getStrictVehicleDetailParameterMap( "vehicleMake", reqVehicleMakeName, valuationDbMappingCache.getChildMappingList( reqVehicleMakeName ) );
        Map<String, String> strictVehicleModelParameterMap = getStrictVehicleDetailParameterMap( "vehicleModel", reqVehicleModelName, valuationDbMappingCache.getChildMappingList( reqVehicleModelName ) );
        Map<String, String> engineCapacityParameterMap = getEngineCapacityParameterMap( reqEngineCapacity, 100, engineCapacityTypeCode );

        String strictSqlQuery = getStrictSQLQuery( strictVehicleMakeParameterMap, strictVehicleModelParameterMap, engineCapacityParameterMap );
        List<Object[]> strictMatchingValuationRefList = getHibernateResource().loadValuationReferenceWithMapping( strictSqlQuery, strictVehicleMakeParameterMap, strictVehicleModelParameterMap, reqYearOfManufacture, engineCapacityParameterMap );

        vehicleValuation = mapVehicleValuation( valuationReferenceList );

        if ( vehicleValuation == null )
        {
            logger.info( " Valuation Ref not found for strict query. Retrying with most matching query" );

            Map<String, String> vehicleMakeParameterMap = getVehicleDetailParameterMap( "vehicleMake", reqVehicleMakeName, valuationDbMappingCache.getChildMappingList( reqVehicleMakeName ) );
            Map<String, String> vehicleModelParameterMap = getVehicleDetailParameterMap( "vehicleModel", reqVehicleModelName, valuationDbMappingCache.getChildMappingList( reqVehicleModelName ) );

            String sqlQuery = getSQLQuery( vehicleMakeParameterMap, vehicleModelParameterMap, engineCapacityParameterMap );

            List<Object[]> mostMatchingValuationRefList = getHibernateResource().loadValuationReference( sqlQuery, vehicleMakeParameterMap, vehicleModelParameterMap, reqYearOfManufacture, engineCapacityParameterMap );

            vehicleValuation = mapVehicleValuation( mostMatchingValuationRefList );

        }*/

        return vehicleValuation;

    }

    private VehicleValuation mapVehicleValuation( List<Object[]> mostMatchingValuationRefList ) throws OpinionGenerationException
    {
        VehicleValuation vehicleValuation = null;

        VehicleDetailCache<VehicleMakeDAO> vehicleMakeCache = this.cacheManager.getCache( CacheKey.VEHICLE_MAKE_CACHE.toString() );
        VehicleDetailCache<VehicleModelDAO> vehicleModelCache = this.cacheManager.getCache( CacheKey.VEHICLE_MODEL_CACHE.toString() );
        VehicleDetailCache<VehicleClassDAO> vehicleClassCache = this.cacheManager.getCache( CacheKey.VEHICLE_CLASS_CACHE.toString() );
        VehicleDetailCache<CountryDAO> countryCache = this.cacheManager.getCache( CacheKey.COUNTRY_CACHE.toString() );
        VehicleDetailCache<EngineTypeDAO> engineTypeCache = this.cacheManager.getCache( CacheKey.ENGINE_TYPE_CACHE.toString() );
        VehicleDetailCache<EngineCapacityTypeDAO> engineCapacityTypeCache = this.cacheManager.getCache( CacheKey.ENGINE_CAPACITY_TYPE_CACHE.toString() );
        VehicleDetailCache<VehicleStatusDAO> vehicleStatusCache = this.cacheManager.getCache( CacheKey.VEHICLE_STATUS_CACHE.toString() );
        VehicleDetailCache<BodyColourDAO> bodyColourCache = this.cacheManager.getCache( CacheKey.BODY_COLOUR_CACHE.toString() );
        VehicleConditionCache vehicleConditionCache = this.cacheManager.getCache( CacheKey.VEHICLE_CONDITION_CACHE.toString() );
        SimpleCache<Integer> vehicleConditionRatingCache = this.cacheManager.getCache( CacheKey.VEHICLE_CONDITION_RATING_CACHE.toString() );
        SimpleCache<Integer> vehiclePartMaxRatingCache = this.cacheManager.getCache( com.moto.mvp.opiniongeneration.core.constant.CacheKey.VEHICLE_PART_MAX_RATING_CACHE.toString() );

        for ( Object[] ref : mostMatchingValuationRefList )
        {
            int col = 0;

            Map<String, String> vehicleConditionMap = new HashMap<>();
            List<Integer> frontTyreWearPercentageList = new ArrayList<>();
            List<Integer> rearTyreWearPercentageList = new ArrayList<>();

            String chassisNo = ( String ) ref[col++];
            String registrationNo = ( String ) ref[col++];
            String vehicleMake = ( String ) ref[col++];
            String vehicleModel = ( String ) ref[col++];
            Integer yearOfManufacture = ( Integer ) ref[col++];
            LocalDate dateOfRegistration = ( LocalDate ) ref[col++];
            String countryUsedIn = ( String ) ref[col++];
            String engineType = ( String ) ref[col++];
            Integer engineCapacity = ( Integer ) ref[col++];
            Integer odometerReading = ( Integer ) ref[col++];
            String bodyColour = ( String ) ref[col++];
            vehicleConditionMap.put( ConditionType.CHASSIS_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.PAINT_FINISH_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.ENGINE_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.CLUTCH_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.GEAR_BOX_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.SHAFTING_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.DIFF_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.GEAR_SELECTION_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.FRONT_SUSPENSION_CONDITION, ( String ) ref[col++] );
            String frontSuspCondition2 = ( String ) ref[col++];
            vehicleConditionMap.put( ConditionType.REAR_SUSPENSION_CONDITION, ( String ) ref[col++] );
            String rearSuspCondition2 = ( String ) ref[col++];
            vehicleConditionMap.put( ConditionType.STEERING_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.FOOT_BRAKE_CONDITION, ( String ) ref[col++] );
            String footBrakesCondition2 = ( String ) ref[col++];
            vehicleConditionMap.put( ConditionType.PARKING_BRAKE_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.STARTER_ELECTRICAL_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.OTHER_LIGHTS_ELECTRICAL_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.METERS_ELECTRICAL_CONDITION, ( String ) ref[col++] );
            vehicleConditionMap.put( ConditionType.HORNS_ELECTRICAL_CONDITION, ( String ) ref[col++] );
            Integer frontTyreSize = ( Integer ) ref[col++];
            frontTyreWearPercentageList.add( ( Integer ) ref[col++] );
            frontTyreWearPercentageList.add( ( Integer ) ref[col++] );
            Integer rearTyreSize = ( Integer ) ref[col++];
            rearTyreWearPercentageList.add( ( Integer ) ref[col++] );
            rearTyreWearPercentageList.add( ( Integer ) ref[col++] );
            rearTyreWearPercentageList.add( ( Integer ) ref[col++] );
            rearTyreWearPercentageList.add( ( Integer ) ref[col++] );
            rearTyreWearPercentageList.add( ( Integer ) ref[col++] );
            rearTyreWearPercentageList.add( ( Integer ) ref[col++] );
            rearTyreWearPercentageList.add( ( Integer ) ref[col++] );
            String extras = ( String ) ref[col++];
            LocalDate valuationDate = ( LocalDate ) ref[col++];
            Double forcedSaleValue = ( Double ) ref[col++];
            Double averageMarketValue = ( Double ) ref[col++];

            /* Set Vehicle Details */

            VehicleDetails vehicleDetails = new VehicleDetails();
            vehicleDetails.setChassisNo( chassisNo );
            vehicleDetails.setRegistrationNo( registrationNo );
            vehicleDetails.setVehicleMake( vehicleMakeCache.getVehicleDetailByName( getReverseMappedValue( vehicleMake ) ) );
            vehicleDetails.setVehicleModel( vehicleModelCache.getVehicleDetailByName( getReverseMappedValue( vehicleModel ) ) );
            vehicleDetails.setYearOfManufacture( yearOfManufacture );
            vehicleDetails.setDateOfRegistration( dateOfRegistration );
            vehicleDetails.setCountryOfOrigin( countryCache.getVehicleDetailByName( getReverseMappedValue( countryUsedIn ) ) );
            vehicleDetails.setCountryUsedIn( countryCache.getVehicleDetailByName( getReverseMappedValue( countryUsedIn ) ) );
            vehicleDetails.setEngineType( engineTypeCache.getVehicleDetailByName( getReverseMappedValue( engineType ) ) );
            vehicleDetails.setEngineCapacity( engineCapacity );
            vehicleDetails.setEngineCapacityType( engineCapacityTypeCache.getVehicleDetail( "CC" ) );
            vehicleDetails.setOdometerReading( odometerReading != null ? odometerReading : 3000 );
            vehicleDetails.setBodyColour( bodyColourCache.getVehicleDetailByName( getReverseMappedValue( bodyColour ) ) );
            vehicleDetails.setVehicleStatus( vehicleStatusCache.getVehicleDetail( getVehicleStatusCode( registrationNo ) ) );

            VehicleCondition vehicleCondition = new VehicleCondition();

            /* Set Tyre Details and Tyre Wear Percentages */

            List<TyreDetailsCondition> tyreDetailsConditionList = new ArrayList<>();

            for ( Integer tyreWearPercentage : frontTyreWearPercentageList )
            {
                if ( tyreWearPercentage != null && frontTyreSize != null )
                {
                    TyreDetailsCondition tyreDetailsCondition = new TyreDetailsCondition();
                    tyreDetailsCondition.setSize( frontTyreSize );
                    tyreDetailsCondition.setWearPercentage( tyreWearPercentage );

                    tyreDetailsConditionList.add( tyreDetailsCondition );
                }
            }

            for ( Integer tyreWearPercentage : rearTyreWearPercentageList )
            {
                if ( tyreWearPercentage != null && rearTyreSize != null )
                {
                    TyreDetailsCondition tyreDetailsCondition = new TyreDetailsCondition();
                    tyreDetailsCondition.setSize( rearTyreSize );
                    tyreDetailsCondition.setWearPercentage( tyreWearPercentage );

                    tyreDetailsConditionList.add( tyreDetailsCondition );
                }
            }

            vehicleCondition.setTyreDetailsConditionList( tyreDetailsConditionList );

            /* Set Vehicle General Condition List*/

            Map<String, Integer> generalConditionRatingMap = new HashMap<>();

            for ( String conditionKey : vehicleConditionMap.keySet() )
            {
                VehicleConditionDAO vehicleConditionDAO = vehicleConditionCache.getVehicleCondition( conditionKey );
                String name = getReverseMappedValue( vehicleConditionMap.get( conditionKey ) );

                String cacheKey = null;
                int rating = 0;

                if ( !name.equals( "." ) )
                {
                    cacheKey = vehicleConditionDAO.getVehicleConditionTypeId() + "-" + name;
                    try
                    {
                        rating = vehicleConditionRatingCache.getValueFromKey( cacheKey );
                    }
                    catch ( OpinionGenerationException e )
                    {
                        rating = vehiclePartMaxRatingCache.getValueFromKey( conditionKey );
                        logger.info( "Cache Key invalid - " + cacheKey + " for condition " + conditionKey );
                        logger.info( "Most Matching Reference : Vehicle Condition from DB for " + conditionKey + " INVALID. Using rating " + rating );
                    }
                }
                else
                {
                    rating = vehiclePartMaxRatingCache.getValueFromKey( conditionKey );
                    logger.info( "Most Matching Reference : Vehicle Condition from DB for " + conditionKey + " INVALID. Using rating " + rating );
                }

                generalConditionRatingMap.put( conditionKey, rating );
            }

            vehicleCondition.setGeneralConditionRatingMap( generalConditionRatingMap );

            /* Set Vehicle Extra List */

            List<VehicleExtra> vehicleExtraList = new ArrayList<>();

            /*
             * TODO : Since Batch Rewnewals do not use Extras, implement this later
             *  and modify VehicleExtraFixedCostRule to account for empty VehicleExtra List in the OpinionGenerationRequest
             * */


            long forcedSaleValueTemp;

            try
            {
                forcedSaleValueTemp = Math.round( forcedSaleValue );
            }
            catch ( NumberFormatException e )
            {
                logger.info( "Most Matching Reference : Forced Sale Value from DB ( " + forcedSaleValue + "  ) is INVALID");
                forcedSaleValueTemp = 0;
            }

            long averageMarketValueTemp = Math.round( averageMarketValue);

            if ( forcedSaleValueTemp == 0.0 && averageMarketValueTemp == 0.0 )
            {
                logger.info( "Most Matching Reference : Forced Sale Value and Average Market Value is INVALID. Retrying with new Reference. ");
                continue;
            }
            else if ( forcedSaleValueTemp == 0.0 )
            {
                forcedSaleValueTemp = averageMarketValueTemp;
            }

            vehicleValuation = new VehicleValuation();

            vehicleValuation.setVehicleDetails( vehicleDetails );
            vehicleValuation.setVehicleCondition( vehicleCondition );
            vehicleValuation.setVehicleExtraList( vehicleExtraList );
            vehicleValuation.setValuationDate( valuationDate );
            vehicleValuation.setForcedSaleValue( forcedSaleValueTemp );
            vehicleValuation.setAverageMarketValue( averageMarketValueTemp );

            return vehicleValuation;
        }

        return vehicleValuation;
    }

    private int getTyreWearPercentage( String tyreWearPercentageFromDb )
    {
        tyreWearPercentageFromDb = tyreWearPercentageFromDb.replaceAll( "'", "" );

        return Integer.parseInt( tyreWearPercentageFromDb );
    }

    private int getTyreSize( String tyreSizeFromDb )
    {
        int defaultValue = 15;

        tyreSizeFromDb = tyreSizeFromDb.replaceAll( "'", "" );

        try
        {
           return Integer.parseInt( tyreSizeFromDb );
        }
        catch ( NumberFormatException e )
        {
            logger.error( e.getMessage() );
            logger.info( "Most Matching Reference : Tyre Size from DB : " + tyreSizeFromDb + " INVALID. Using Value " + defaultValue );
            return defaultValue;
        }

    }

    private String getVehicleStatusCode( String registrationNoFromDB )
    {
        if ( registrationNoFromDB.equals( "U/R" ) || registrationNoFromDB.equals( "U.R." ) )
        {
            return "UU";
        }
        return "UR";
    }

    private int getEngineCapacity( String engineCapacityFromDb )
    {
        engineCapacityFromDb = engineCapacityFromDb.toLowerCase();
        engineCapacityFromDb = engineCapacityFromDb.replaceAll( "\\s", "" );
        int capacityTypeIndex = engineCapacityFromDb.indexOf( "cc" );
        String engineCapacity = engineCapacityFromDb.substring( 0, capacityTypeIndex );

        return Integer.parseInt( engineCapacity );
    }

    private int getOdometerReading( String odometerReadingFromDb )
    {
        int defaultValue = 3000;
        odometerReadingFromDb = odometerReadingFromDb.toLowerCase();
        odometerReadingFromDb = odometerReadingFromDb.replaceAll( "\\s", "" );
        int unitIndex = odometerReadingFromDb.indexOf( "km" );
        String odometerReading = unitIndex >= 0 ? odometerReadingFromDb.substring( 0, unitIndex ) : odometerReadingFromDb;

        try
        {
            return Integer.parseInt( odometerReading );
        }
        catch ( NumberFormatException e )
        {
            logger.error( e.getMessage() );
            logger.info( "Most Matching Reference : Odometer Reading from DB : " + odometerReading + " INVALID. Using Value " + defaultValue );
            return defaultValue;
        }
    }

    private String getReverseMappedValue( String dbValue )
    {
        ValuationDbMappingCache valuationDbMappingCache = this.cacheManager.getCache( CacheKey.VALUATION_DB_MAPPING_CACHE.toString() );
        String reversedMappedValue = valuationDbMappingCache.getParentMappingFromChildValue( dbValue );
        return reversedMappedValue != null ? reversedMappedValue : dbValue;
    }

    private Map<String, String> getEngineCapacityParameterMap( int engineCapacity, int engineCapacityRange, String engineCapacityType )
    {
        Map<String, String> engineCapacityParameterMap = new HashMap<>();

        int upperThreshold = engineCapacity + engineCapacityRange;
        int lowerThreshold = engineCapacity - engineCapacityRange;

        List<String> engineCapacityList = IntStream.range( lowerThreshold, upperThreshold + 1 ).mapToObj( i -> i + engineCapacityType ).collect( Collectors.toList() );

        for ( int i = 1; i <= engineCapacityList.size(); i++ )
        {
            String paramName = "engineCapacityWithType" + i;
            String paramValue = engineCapacityList.get( i - 1 );

            engineCapacityParameterMap.put( paramName, paramValue );
        }

        return engineCapacityParameterMap;
    }

    private Map<String, String> getVehicleDetailParameterMap( String vehicleDetail, String parent, List<String> childList )
    {
        Map<String, String> vehicleDetailParameterMap = new HashMap<>();

        String parentParamName = vehicleDetail + "Parent";
        String parentParamValue = "%" + parent + "%";

        vehicleDetailParameterMap.put( parentParamName, parentParamValue );

        if ( childList != null )
        {
            for ( int i = 1; i <= childList.size(); i++ )
            {
                String paramName = vehicleDetail + "Child" + i;
                String paramValue = "%" + childList.get( i - 1 ) + "%";
                vehicleDetailParameterMap.put( paramName, paramValue );
            }
        }

        return vehicleDetailParameterMap;
    }

    private Map<String, String> getStrictVehicleDetailParameterMap( String vehicleDetail, String parent, List<String> childList )
    {
        Map<String, String> vehicleDetailParameterMap = new HashMap<>();

        String parentParamName = vehicleDetail + "Parent";
        String parentParamValue = "" + parent + "";

        vehicleDetailParameterMap.put( parentParamName, parentParamValue );

        if ( childList != null )
        {
            for ( int i = 1; i <= childList.size(); i++ )
            {
                String paramName = vehicleDetail + "Child" + i;
                String paramValue = "" + childList.get( i - 1 ) + "";
                vehicleDetailParameterMap.put( paramName, paramValue );
            }
        }

        return vehicleDetailParameterMap;
    }

    private String getSimpleSQLQuery( Map<String, String> paramMap, OpinionGenerationRequest opinionGenerationReq)
    {
        return getSelectClause() + getSimpleWhereClause( paramMap, opinionGenerationReq);
    }

    private String getStrictSQLQuery( Map<String, String> vehicleMakeParameterMap, Map<String, String> vehicleModelParameterMap, Map<String, String> engineCapacityParameterMap )
    {
        return getSelectClause() + getStrictWhereClause( vehicleMakeParameterMap, vehicleModelParameterMap, engineCapacityParameterMap );
    }

    private String getSQLQuery( Map<String, String> vehicleMakeParameterMap, Map<String, String> vehicleModelParameterMap, Map<String, String> engineCapacityParameterMap )
    {
        return getSelectClause() + getWhereClause( vehicleMakeParameterMap, vehicleModelParameterMap, engineCapacityParameterMap );
    }

    private String getSimpleWhereClause( Map<String, String> paramMap, OpinionGenerationRequest opinionGenerationReq )
    {
        paramMap.put( "vehicleMake", opinionGenerationReq.getVehicleDetails().getVehicleMake().getName() );
        paramMap.put( "vehicleModel", opinionGenerationReq.getVehicleDetails().getVehicleModel().getName() );
        paramMap.put( "yearOfManufacture", String.valueOf( opinionGenerationReq.getVehicleDetails().getYearOfManufacture() ) );
        paramMap.put( "engineCc", String.valueOf( opinionGenerationReq.getVehicleDetails().getEngineCapacity() ) );

        return " WHERE \n" +
                " make = :vehicleMake\n" +
                " AND model = :vehicleModel\n" +
                " AND year_of_manufacture = :yearOfManufacture \n" +
                " AND engine_capacity = :engineCc\n" +
                " ORDER BY date_of_valuation DESC";
    }

    private String getWhereClause( Map<String, String> vehicleMakeParameterMap, Map<String, String> vehicleModelParameterMap, Map<String, String> engineCapacityParameterMap )
    {
        return "WHERE \n" +
                " ( " + getVehicleDetailWhereClause( "ms", "Make", vehicleMakeParameterMap ) + " ) \n" +
                " AND ( " + getVehicleDetailWhereClause( "ms", "TypeVehicle", vehicleModelParameterMap ) + " ) \n" +
                " AND TRIM( ms.year ) = :yearOfManufacture \n" +
                " AND UCASE( TRIM( ms.Engine_cc ) ) IN  ( " + getEngineCapacityRangeClause( engineCapacityParameterMap ) + " ) \n" +
                " AND ms.Chassis_no = dt.Chassis_no\n" +
                " AND ms.Chassis_no = cs.Chassis_no\n" +
                " ORDER BY STR_TO_DATE(ms.Date, '%e/%m/%Y') DESC";
    }

    private String getStrictWhereClause( Map<String, String> strictVehicleMakeParameterMap, Map<String, String> strictVehicleModelParameterMap, Map<String, String> engineCapacityParameterMap )
    {
        return "WHERE \n" +
                " ( " + getStrictVehicleDetailWhereClause( "ms", "Make", strictVehicleMakeParameterMap ) + " ) \n" +
                " AND ( " + getStrictVehicleDetailWhereClause( "ms", "TypeVehicle", strictVehicleModelParameterMap ) + " ) \n" +
                " AND TRIM( ms.year ) = :yearOfManufacture \n" +
                " AND UCASE( TRIM( ms.Engine_cc ) ) IN  ( " + getEngineCapacityRangeClause( engineCapacityParameterMap ) + " ) \n" +
                " AND ms.Chassis_no = dt.Chassis_no\n" +
                " AND ms.Chassis_no = cs.Chassis_no\n" +
                " ORDER BY STR_TO_DATE(ms.Date, '%e/%m/%Y') DESC";
    }

    private String getVehicleDetailWhereClause( String tableAlias, String colName, Map<String, String> vehicleDetailParameterMap )
    {
        StringBuilder clauseSql = new StringBuilder();

        String delimiter = " ";

        for ( String paramName : vehicleDetailParameterMap.keySet() )
        {
            clauseSql.append( delimiter );
            clauseSql.append( "UCASE( " );
            clauseSql.append( tableAlias );
            clauseSql.append( "." ).append( colName );
            clauseSql.append( " ) " );
            clauseSql.append( " LIKE " ).append( ":" ).append( paramName );

            delimiter = " OR ";
        }

        return clauseSql.toString();
    }

    private String getStrictVehicleDetailWhereClause( String tableAlias, String colName, Map<String, String> vehicleDetailParameterMap )
    {
        StringBuilder clauseSql = new StringBuilder();

        String delimiter = " ";

        for ( String paramName : vehicleDetailParameterMap.keySet() )
        {
            clauseSql.append( delimiter );
            clauseSql.append( "UCASE( " );
            clauseSql.append( "TRIM( " );
            clauseSql.append( tableAlias );
            clauseSql.append( "." ).append( colName );
            clauseSql.append( " ) " );
            clauseSql.append( " ) " );
            clauseSql.append( " = " ).append( ":" ).append( paramName );

            delimiter = " OR ";
        }

        return clauseSql.toString();
    }

    private String getEngineCapacityRangeClause( Map<String, String> engineCapacityParameterMap )
    {
        StringBuilder engineCCList = new StringBuilder();
        String delimiter = " ";

        for ( String paramName : engineCapacityParameterMap.keySet() )
        {
            engineCCList.append( delimiter );
            engineCCList.append( ":" );
            engineCCList.append( paramName );
            delimiter = " ,";
        }

        return engineCCList.toString();
    }

    private String getSelectClause()
    {
        return "SELECT chassis_no,\n" +
                "       registration_no,\n" +
                "       make,\n" +
                "       model,\n" +
                "       year_of_manufacture,\n" +
                "       date_of_registration,\n" +
                "       country_used_in,\n" +
                "       engine_type,\n" +
                "       engine_capacity,\n" +
                "       odometer_reading,\n" +
                "       body_colour,\n" +
                "       chassis_condition,\n" +
                "       paint_finish_condition,\n" +
                "       engine_condition,\n" +
                "       clutch_condition,\n" +
                "       gear_box_condition,\n" +
                "       shafting_condition,\n" +
                "       diff_condition,\n" +
                "       gear_selection_condition,\n" +
                "       front_suspension_condition,\n" +
                "       front_suspension_condition_2,\n" +
                "       rear_suspension_condition,\n" +
                "       rear_suspension_condition_2,\n" +
                "       steering_condition,\n" +
                "       foot_brake_condition,\n" +
                "       foot_brake_condition_2,\n" +
                "       parking_brake_condition,\n" +
                "       starter_electrical_condition,\n" +
                "       other_lights_electrical_condition,\n" +
                "       meters_electrical_condition,\n" +
                "       horns_electrical_condition,\n" +
                "       f_tyre_size,\n" +
                "       f_tyre_wear_percent1,\n" +
                "       f_tyre_wear_percent2,\n" +
                "       r_tyre_size,\n" +
                "       r_tyre_wear_percent1,\n" +
                "       r_tyre_wear_percent2,\n" +
                "       r_tyre_wear_percent3,\n" +
                "       r_tyre_wear_percent4,\n" +
                "       r_tyre_wear_percent5,\n" +
                "       r_tyre_wear_percent6,\n" +
                "       r_tyre_wear_percent7,\n" +
                "       extras,\n" +
                "       date_of_valuation,\n" +
                "       forced_sale_value,\n" +
                "       average_market_value\n" +
                " FROM cp_ref.cp_vprs_v1_ref cvv1r \n";
    }

    public String getLogs()
    {
        return this.loggerSB.toString();
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }

}
