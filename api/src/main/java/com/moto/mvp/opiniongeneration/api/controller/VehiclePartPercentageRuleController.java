package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.VehiclePartPercentageRuleDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.VehiclePartPercentageRuleEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class VehiclePartPercentageRuleController
{
    Logger logger = LoggerFactory.getLogger( VehiclePartPercentageRuleController.class );

    private VehiclePartPercentageRuleEndpoint endpoint;

    @Autowired
    public VehiclePartPercentageRuleController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.VEHICLE_PART_PERCENTAGE_RULE_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/vehiclePartPercentageRules" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/vehiclePartPercentageRules/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/vehiclePartPercentageRules" )
    public HttpEntity<BasicResponse> save( @RequestBody List<VehiclePartPercentageRuleDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/vehiclePartPercentageRules" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<VehiclePartPercentageRuleDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
