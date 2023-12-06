package com.moto.mvp.opiniongeneration.core.model;

import java.util.List;
import java.util.Map;

public class VehicleCondition
{
    private Map<String,Integer> generalConditionRatingMap;
    private List<TyreDetailsCondition> tyreDetailsConditionList;


    public List<TyreDetailsCondition> getTyreDetailsConditionList()
    {
        return tyreDetailsConditionList;
    }

    public void setTyreDetailsConditionList( List<TyreDetailsCondition> tyreDetailsConditionList )
    {
        this.tyreDetailsConditionList = tyreDetailsConditionList;
    }

    public Map<String, Integer> getGeneralConditionRatingMap()
    {
        return generalConditionRatingMap;
    }

    public void setGeneralConditionRatingMap( Map<String, Integer> generalConditionRatingMap )
    {
        this.generalConditionRatingMap = generalConditionRatingMap;
    }

    @Override
    public String toString()
    {
        return "VehicleCondition{" +
                "generalConditionRatingMap=" + generalConditionRatingMap +
                ", tyreDetailsConditionList=" + tyreDetailsConditionList +
                '}';
    }
}
