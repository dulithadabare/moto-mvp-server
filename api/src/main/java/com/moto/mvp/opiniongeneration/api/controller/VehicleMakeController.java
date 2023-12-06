package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleMakeDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.VehicleMakeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class VehicleMakeController
{
    Logger logger = LoggerFactory.getLogger( VehicleMakeController.class );

    private VehicleMakeEndpoint endpoint;

    @Autowired
    public VehicleMakeController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.VEHICLE_MAKE_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/vehicleMakes" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/vehicleMakes/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/vehicleMakes" )
    public HttpEntity<BasicResponse> save( @RequestBody List<VehicleMakeDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/vehicleMakes" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<VehicleMakeDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
