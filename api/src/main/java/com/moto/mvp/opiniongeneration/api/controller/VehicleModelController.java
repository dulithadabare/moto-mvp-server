package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleModelDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.VehicleModelEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class VehicleModelController
{
    Logger logger = LoggerFactory.getLogger( VehicleModelController.class );

    private VehicleModelEndpoint endpoint;

    @Autowired
    public VehicleModelController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.VEHICLE_MODEL_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/vehicleModels" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/vehicleModels/{makeId}" )
    public HttpEntity<BasicResponse> getByMakeId( @PathVariable int makeId )
    {
        logger.info( String.valueOf( makeId ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getByMakeId( makeId )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/vehicleModels" )
    public HttpEntity<BasicResponse> save( @RequestBody List<VehicleModelDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/vehicleModels" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<VehicleModelDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
