package com.autobox.demo.core.resource;

import com.moto.mvp.foundation.core.resource.HibernateResource;

import static org.junit.Assert.assertEquals;

public class HibernateResourceTest
{

    private HibernateResource hibernateResource;

    /*@Before
    public void initResource()
    {
        hibernateResource = new HibernateResource();

        Properties properties = new Properties();

        properties.setProperty( "DB_CONNECTOR_DRIVER_CLASS", "org.mariadb.jdbc.Driver" );
        properties.setProperty( "DB_URL", "jdbc:mariadb://localhost:3306/autobox_demo_db" );
        properties.setProperty( "DB_USERNAME", "demoroot" );
        properties.setProperty( "DB_PASSWORD", "demoroot" );
        properties.setProperty( "DB_DIALECT", "org.hibernate.dialect.MariaDB103Dialect" );

        hibernateResource.init( properties );
    }

    @Test
    public void loadValuationEntityTest()
    {

        List<String> attributeList = new ArrayList<>();

        attributeList.add("frontWheelList");
        attributeList.add("rearWheelList");
        attributeList.add("spareWheelList");
        attributeList.add("vehicleImageList");

        List<ValuationEntity> valuationEntityList = hibernateResource.loadAll( ValuationEntity.class );

        for ( ValuationEntity valuationEntity : valuationEntityList )
        {
            assertEquals( new Double( 4500000.0 ), valuationEntity.getVehicleValue() );
        }
    }*/

}
