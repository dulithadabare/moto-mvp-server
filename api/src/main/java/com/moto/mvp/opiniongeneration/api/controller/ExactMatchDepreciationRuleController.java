package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.ExactMatchDepreciationRuleDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.ExactMatchDepreciationRuleEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ExactMatchDepreciationRuleController
{
    Logger logger = LoggerFactory.getLogger( ExactMatchDepreciationRuleController.class );

    private ExactMatchDepreciationRuleEndpoint endpoint;

    @Autowired
    public ExactMatchDepreciationRuleController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.EXACT_MATCH_DEPRECIATION_RULE_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/exactMatchDepreciationRules" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/exactMatchDepreciationRules/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/exactMatchDepreciationRules" )
    public HttpEntity<BasicResponse> save( @RequestBody List<ExactMatchDepreciationRuleDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/exactMatchDepreciationRules" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<ExactMatchDepreciationRuleDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
