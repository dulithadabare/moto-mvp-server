package com.moto.mvp.opiniongeneration.core.model;

import java.util.List;

public class AbstractOpinionValuation
{
    private VehicleDetails vehicleDetails;
    private VehicleCondition vehicleCondition;
    private List<VehicleExtra> vehicleExtraList;

    public VehicleDetails getVehicleDetails()
    {
        return vehicleDetails;
    }

    public void setVehicleDetails( VehicleDetails vehicleDetails )
    {
        this.vehicleDetails = vehicleDetails;
    }

    public VehicleCondition getVehicleCondition()
    {
        return vehicleCondition;
    }

    public void setVehicleCondition( VehicleCondition vehicleCondition )
    {
        this.vehicleCondition = vehicleCondition;
    }

    public List<VehicleExtra> getVehicleExtraList()
    {
        return vehicleExtraList;
    }

    public void setVehicleExtraList( List<VehicleExtra> vehicleExtraList )
    {
        this.vehicleExtraList = vehicleExtraList;
    }

    @Override
    public String toString()
    {
        return "AbstractOpinionValuation{" +
                "vehicleDetails=" + vehicleDetails +
                ", vehicleCondition=" + vehicleCondition +
                ", vehicleExtraList=" + vehicleExtraList +
                '}';
    }
}
