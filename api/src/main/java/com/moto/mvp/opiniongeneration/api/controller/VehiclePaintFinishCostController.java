package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.VehiclePaintFinishCostDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.VehiclePaintFinishCostEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class VehiclePaintFinishCostController
{
    Logger logger = LoggerFactory.getLogger( VehiclePaintFinishCostController.class );

    private VehiclePaintFinishCostEndpoint endpoint;

    @Autowired
    public VehiclePaintFinishCostController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.VEHICLE_PAINT_FINISH_COST_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/vehiclePaintFinishCosts" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/vehiclePaintFinishCosts/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/vehiclePaintFinishCosts" )
    public HttpEntity<BasicResponse> save( @RequestBody List<VehiclePaintFinishCostDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/vehiclePaintFinishCosts" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<VehiclePaintFinishCostDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
