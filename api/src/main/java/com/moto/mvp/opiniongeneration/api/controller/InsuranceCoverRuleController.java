package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.InsuranceCoverRuleDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.InsuranceCoverRuleEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class InsuranceCoverRuleController
{
    Logger logger = LoggerFactory.getLogger( InsuranceCoverRuleController.class );

    private InsuranceCoverRuleEndpoint endpoint;

    @Autowired
    public InsuranceCoverRuleController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.INSURANCE_COVER_RULE_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/insuranceCoverRules" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/insuranceCoverRules/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/insuranceCoverRules" )
    public HttpEntity<BasicResponse> save( @RequestBody List<InsuranceCoverRuleDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/insuranceCoverRules" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<InsuranceCoverRuleDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
