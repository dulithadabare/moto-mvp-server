package com.moto.mvp.opiniongeneration.core.model;

import com.moto.mvp.opiniongeneration.core.dao.*;

import java.time.LocalDate;
import java.util.Date;

public class VehicleDetails
{
    private String chassisNo;
    private String registrationNo;
    private VehicleMakeDAO vehicleMake;
    private VehicleModelDAO vehicleModel;
    private int yearOfManufacture;
    private LocalDate dateOfRegistration;
    private CountryDAO countryUsedIn;
    private CountryDAO countryOfOrigin;
    private EngineTypeDAO engineType;
    private int engineCapacity;
    private EngineCapacityTypeDAO engineCapacityType;
    private VehicleStatusDAO vehicleStatus;
    private int odometerReading;
    private BodyColourDAO bodyColour;
    private String vehicleBatteryType;
    private String vehicleHornType;
    private String vehiclePaintFinishType;

    public String getChassisNo()
    {
        return chassisNo;
    }

    public void setChassisNo( String chassisNo )
    {
        this.chassisNo = chassisNo;
    }

    public String getRegistrationNo()
    {
        return registrationNo;
    }

    public void setRegistrationNo( String registrationNo )
    {
        this.registrationNo = registrationNo;
    }

    public VehicleMakeDAO getVehicleMake()
    {
        return vehicleMake;
    }

    public void setVehicleMake( VehicleMakeDAO vehicleMake )
    {
        this.vehicleMake = vehicleMake;
    }

    public VehicleModelDAO getVehicleModel()
    {
        return vehicleModel;
    }

    public void setVehicleModel( VehicleModelDAO vehicleModel )
    {
        this.vehicleModel = vehicleModel;
    }

    public int getYearOfManufacture()
    {
        return yearOfManufacture;
    }

    public void setYearOfManufacture( int yearOfManufacture )
    {
        this.yearOfManufacture = yearOfManufacture;
    }

    public LocalDate getDateOfRegistration()
    {
        return dateOfRegistration;
    }

    public void setDateOfRegistration( LocalDate dateOfRegistration )
    {
        this.dateOfRegistration = dateOfRegistration;
    }

    public CountryDAO getCountryUsedIn()
    {
        return countryUsedIn;
    }

    public void setCountryUsedIn( CountryDAO countryUsedIn )
    {
        this.countryUsedIn = countryUsedIn;
    }

    public CountryDAO getCountryOfOrigin()
    {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin( CountryDAO countryOfOrigin )
    {
        this.countryOfOrigin = countryOfOrigin;
    }

    public EngineTypeDAO getEngineType()
    {
        return engineType;
    }

    public void setEngineType( EngineTypeDAO engineType )
    {
        this.engineType = engineType;
    }

    public int getEngineCapacity()
    {
        return engineCapacity;
    }

    public void setEngineCapacity( int engineCapacity )
    {
        this.engineCapacity = engineCapacity;
    }

    public EngineCapacityTypeDAO getEngineCapacityType()
    {
        return engineCapacityType;
    }

    public void setEngineCapacityType( EngineCapacityTypeDAO engineCapacityType )
    {
        this.engineCapacityType = engineCapacityType;
    }

    public VehicleStatusDAO getVehicleStatus()
    {
        return vehicleStatus;
    }

    public void setVehicleStatus( VehicleStatusDAO vehicleStatus )
    {
        this.vehicleStatus = vehicleStatus;
    }

    public int getOdometerReading()
    {
        return odometerReading;
    }

    public void setOdometerReading( int odometerReading )
    {
        this.odometerReading = odometerReading;
    }

    public BodyColourDAO getBodyColour()
    {
        return bodyColour;
    }

    public void setBodyColour( BodyColourDAO bodyColour )
    {
        this.bodyColour = bodyColour;
    }

    public String getVehicleBatteryType()
    {
        return vehicleBatteryType;
    }

    public void setVehicleBatteryType( String vehicleBatteryType )
    {
        this.vehicleBatteryType = vehicleBatteryType;
    }

    public String getVehicleHornType()
    {
        return vehicleHornType;
    }

    public void setVehicleHornType( String vehicleHornType )
    {
        this.vehicleHornType = vehicleHornType;
    }

    public String getVehiclePaintFinishType()
    {
        return vehiclePaintFinishType;
    }

    public void setVehiclePaintFinishType( String vehiclePaintFinishType )
    {
        this.vehiclePaintFinishType = vehiclePaintFinishType;
    }

    @Override
    public String toString()
    {
        return "VehicleDetails{" +
                "chassisNo='" + chassisNo + '\'' +
                ", registrationNo='" + registrationNo + '\'' +
                ", vehicleMake=" + vehicleMake +
                ", vehicleModel=" + vehicleModel +
                ", yearOfManufacture=" + yearOfManufacture +
                ", dateOfRegistration=" + dateOfRegistration +
                ", countryUsedIn=" + countryUsedIn +
                ", countryOfOrigin=" + countryOfOrigin +
                ", engineType=" + engineType +
                ", engineCapacity=" + engineCapacity +
                ", engineCapacityType=" + engineCapacityType +
                ", vehicleStatus=" + vehicleStatus +
                ", odometerReading=" + odometerReading +
                ", bodyColour=" + bodyColour +
                ", vehicleBatteryType='" + vehicleBatteryType + '\'' +
                ", vehicleHornType='" + vehicleHornType + '\'' +
                ", vehiclePaintFinishType='" + vehiclePaintFinishType + '\'' +
                '}';
    }
}
