package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.cache.AbstractCacheManager;
import com.moto.mvp.foundation.core.cache.SimpleCache;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.opiniongeneration.core.constant.CacheKey;
import com.moto.mvp.opiniongeneration.core.constant.ConditionType;
import com.moto.mvp.opiniongeneration.core.constant.RuleKey;
import com.moto.mvp.opiniongeneration.core.model.*;

import java.util.HashSet;
import java.util.Set;

public class VehicleConditionRule extends AbstractBasicRule
{
    private final String  PREVIOUS_VEHICLE_LOG_PREFIX = "PREVIOUS VEHICLE PART VALUE SUM";
    private final String  CURRENT_VEHICLE_LOG_PREFIX = "CURRENT VEHICLE PART VALUE SUM";
    private Set<String> exclusionSet = new HashSet<>(  );

    public VehicleConditionRule( AbstractCacheManager cacheManager, OpinionGenerationRequest opinionGenerationRequest, VehicleValuation mostMatchedVehicleValuation, OpinionValue opinionValue )
    {
        super( cacheManager, opinionGenerationRequest, mostMatchedVehicleValuation, opinionValue );
        exclusionSet.add( ConditionType.BATTERY_ELECTRICAL_CONDITION );
        exclusionSet.add( ConditionType.HORNS_ELECTRICAL_CONDITION );
        exclusionSet.add( ConditionType.OTHER_LIGHTS_ELECTRICAL_CONDITION );
        exclusionSet.add( ConditionType.PAINT_FINISH_CONDITION );
    }

    @Override
    public void apply() throws OpinionGenerationException
    {
        beforeApplying();
        adjust();
        afterApplying();
    }

    @Override
    protected void adjust() throws OpinionGenerationException
    {
        double refVehiclePartValue = getSumOfPartValues( this.valuationReferencece.getVehicleCondition(), true );
        double currentVehiclePartValue = getSumOfPartValues( this.opinionGenerationRequest.getVehicleCondition(), false );

        long newForcedSaleValue = Math.round( this.opinionValue.getForcedSaleValue() * ( currentVehiclePartValue / refVehiclePartValue ) );

        String string = "NEW FORCED SALE VALUE " +
                newForcedSaleValue +
                " = " +
                "PREVIOUS FORCED SALE VALUE " +
                this.opinionValue.getForcedSaleValue() +
                " * " +
                PREVIOUS_VEHICLE_LOG_PREFIX +
                " " +
                refVehiclePartValue +
                " / " +
                CURRENT_VEHICLE_LOG_PREFIX +
                " " +
                currentVehiclePartValue;
        logger.info( string );

        this.opinionValue.setForcedSaleValue( newForcedSaleValue );
    }

    private double getSumOfPartValues( VehicleCondition vehicleCondition, boolean isPreviousVehicle ) throws OpinionGenerationException
    {
        SimpleCache<Double> vehiclePartPercentageCache = this.cacheManager.getCache( CacheKey.VEHICLE_PART_PERCENTAGE_RULE_CACHE.toString() );
        SimpleCache<Integer> vehiclePartMaxRatingCache = this.cacheManager.getCache( CacheKey.VEHICLE_PART_MAX_RATING_CACHE.toString() );

        String PREFIX = " ";
        StringBuilder logStringBuilder = new StringBuilder();

        if ( isPreviousVehicle )
        {
            logStringBuilder.append( PREVIOUS_VEHICLE_LOG_PREFIX );
        }
        else
        {
            logStringBuilder.append( CURRENT_VEHICLE_LOG_PREFIX );
        }
        logStringBuilder.append( " = " );

        double vehiclePartValue = 0.0;
        for ( String conditionCode : vehicleCondition.getGeneralConditionRatingMap().keySet() )
        {
            if ( exclusionSet.contains( conditionCode ) )
            {
                continue;
            }

            int conditionRating = vehicleCondition.getGeneralConditionRatingMap().get( conditionCode );
            double conditionPartPercentage = vehiclePartPercentageCache.getValueFromKey( conditionCode );
            int conditionMaxRating = vehiclePartMaxRatingCache.getValueFromKey( conditionCode );

            logStringBuilder.append( PREFIX );
            logStringBuilder.append( conditionCode );
            logStringBuilder.append( " ( " );
            logStringBuilder.append( conditionPartPercentage );
            logStringBuilder.append( " * " );
            logStringBuilder.append( conditionRating );
            logStringBuilder.append( " / " );
            logStringBuilder.append( conditionMaxRating );
            PREFIX = " + ";

            vehiclePartValue += conditionPartPercentage * ( ( double ) conditionRating / conditionMaxRating );
        }

        logger.info( logStringBuilder.toString() );

        return vehiclePartValue;
    }

    @Override
    public String getRuleName()
    {
        return RuleKey.VEHICLE_CONDITION_RULE.getRuleKey();
    }

}
