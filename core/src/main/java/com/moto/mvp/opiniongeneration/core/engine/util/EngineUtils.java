package com.moto.mvp.opiniongeneration.core.engine.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moto.mvp.opiniongeneration.core.model.DummyVehicleValuations;
import org.apache.logging.log4j.core.util.IOUtils;

import java.io.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EngineUtils
{
    public static int getDiffYears( LocalDate previousDate, LocalDate currentDate )
    {
        int diff = currentDate.getYear() - previousDate.getYear();

        if ( previousDate.getMonth().getValue() > currentDate.getMonth().getValue() )
        {
            diff--;
        }
        return diff;
    }

    private static Calendar getCalendar( Date date )
    {
        Calendar cal = Calendar.getInstance( Locale.US );
        cal.setTime( date );
        return cal;
    }

    public static DummyVehicleValuations loadDummyData()
    {
        DummyVehicleValuations dummyVehicleValuations = null;

        ObjectMapper mapper = new ObjectMapper();
        try
        {
            dummyVehicleValuations = mapper.readValue( getFile( "ValuationDummyData.json" ), DummyVehicleValuations.class );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return dummyVehicleValuations;
    }

    public static String getFile( String fileName )
    {
        //Get file from resources folder
        ClassLoader classLoader = EngineUtils.class.getClassLoader();
        InputStream stream = classLoader.getResourceAsStream( fileName );

        try
        {
            if ( stream == null )
            {
                throw new Exception( "Cannot find file " + fileName );
            }

            BufferedReader reader = new BufferedReader( new InputStreamReader( stream ) );
            return IOUtils.toString( reader );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        return null;
    }
}
