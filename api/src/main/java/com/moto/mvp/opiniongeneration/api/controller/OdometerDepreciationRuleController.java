package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.OdometerDepreciationRuleDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.OdometerDepreciationRuleEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class OdometerDepreciationRuleController
{
    Logger logger = LoggerFactory.getLogger( OdometerDepreciationRuleController.class );

    private OdometerDepreciationRuleEndpoint endpoint;

    @Autowired
    public OdometerDepreciationRuleController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.ODOMETER_DEPRECIATION_RULE_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/odometerDepreciationRules" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/odometerDepreciationRules/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/odometerDepreciationRules" )
    public HttpEntity<BasicResponse> save( @RequestBody List<OdometerDepreciationRuleDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/odometerDepreciationRules" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<OdometerDepreciationRuleDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
