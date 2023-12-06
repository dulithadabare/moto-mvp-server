package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleOtherLightsCostDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.VehicleOtherLightsCostEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class VehicleOtherLightsCostController
{
    Logger logger = LoggerFactory.getLogger( VehicleOtherLightsCostController.class );

    private VehicleOtherLightsCostEndpoint endpoint;

    @Autowired
    public VehicleOtherLightsCostController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.VEHICLE_OTHER_LIGHTS_COST_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/vehicleOtherLightsCosts" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/vehicleOtherLightsCosts/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/vehicleOtherLightsCosts" )
    public HttpEntity<BasicResponse> save( @RequestBody List<VehicleOtherLightsCostDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/vehicleOtherLightsCosts" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<VehicleOtherLightsCostDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
