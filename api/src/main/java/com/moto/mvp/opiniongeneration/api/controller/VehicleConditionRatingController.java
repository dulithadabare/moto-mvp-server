package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.VehicleConditionRatingDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.VehicleConditionRatingEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class VehicleConditionRatingController
{
    Logger logger = LoggerFactory.getLogger( VehicleConditionRatingController.class );

    private VehicleConditionRatingEndpoint endpoint;

    @Autowired
    public VehicleConditionRatingController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.VEHICLE_CONDITION_RATING_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/vehicleConditionRatings" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/vehicleConditionRatings/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/vehicleConditionRatings" )
    public HttpEntity<BasicResponse> save( @RequestBody List<VehicleConditionRatingDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/vehicleConditionRatings" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<VehicleConditionRatingDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
