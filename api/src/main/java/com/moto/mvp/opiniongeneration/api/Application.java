package com.moto.mvp.opiniongeneration.api;

import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by dulithadabare on 12/17/19.
 */

@SpringBootApplication( exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class },
        scanBasePackages = { "com.moto.mvp.opiniongeneration.api", "com.moto.mvp.opiniongeneration.core" } )
public class Application
{
    private Logger logger = LoggerFactory.getLogger( Application.class );

    @Autowired
    private MotoPlatform motoPlatform;

    public static void main( String[] args )
    {
        SpringApplication.run( Application.class, args );
    }

    @PostConstruct
    private void postConstruct()
    {
        this.motoPlatform.init();
    }

    @PreDestroy
    public void onExit()
    {
        this.motoPlatform.shutdown();
        logger.info( "###STOPPING###" );

    }
}
