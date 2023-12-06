package com.moto.mvp.opiniongeneration.core.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class VehicleValuation extends AbstractOpinionValuation
{
    private int id;
    private LocalDate valuationDate;
    private Long forcedSaleValue;
    private Long averageMarketValue;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

   public LocalDate getValuationDate()
    {
        return valuationDate;
    }

    public void setValuationDate( LocalDate valuationDate )
    {
        this.valuationDate = valuationDate;
    }

    public Long getForcedSaleValue()
    {
        return forcedSaleValue;
    }

    public void setForcedSaleValue( Long forcedSaleValue )
    {
        this.forcedSaleValue = forcedSaleValue;
    }

    public Long getAverageMarketValue()
    {
        return averageMarketValue;
    }

    public void setAverageMarketValue( Long averageMarketValue )
    {
        this.averageMarketValue = averageMarketValue;
    }

    @Override
    public String toString()
    {
        String vehicleMake = getVehicleDetails().getVehicleMake() != null ? getVehicleDetails().getVehicleMake().getName() : "null";
        String vehicleModel = getVehicleDetails().getVehicleModel() != null ? getVehicleDetails().getVehicleModel().getName() : "null";
        String countryUsedIn = getVehicleDetails().getCountryUsedIn() != null ? getVehicleDetails().getCountryUsedIn().getName() : "null";
        String bodyColour = getVehicleDetails().getBodyColour() != null ? getVehicleDetails().getBodyColour().getName() : "null";

        return
                "Vehicle Make : " + vehicleMake + "\n" +
                "Vehicle Model : " + vehicleModel + "\n" +
                "Year of Manufacture : " + getVehicleDetails().getYearOfManufacture() + "\n" +
                "Chassis No : " + getVehicleDetails().getChassisNo() + "\n" +
                "Registration No : " + getVehicleDetails().getRegistrationNo() + "\n" +
                "Date of Registration : " + getVehicleDetails().getDateOfRegistration()+ "\n" +
                "Country Used In : " + countryUsedIn + "\n" +
                "Engine Capacity : " + getVehicleDetails().getEngineCapacity() + "\n" +
                "Odometer Reading : " + getVehicleDetails().getOdometerReading() + "\n" +
                "Body Colour : " + bodyColour + "\n" +
                "Vehicle Condition : " + getVehicleCondition().getGeneralConditionRatingMap().toString() + "\n" +
                "Tyre Condition : " + getVehicleCondition().getTyreDetailsConditionList().toString() + "\n" +
                "Valuation Date : " + this.valuationDate + "\n" +
                "Forced Sale Value : " + this.forcedSaleValue + "\n" +
                "Average Market Value : " + this.averageMarketValue + "\n";
    }
}
