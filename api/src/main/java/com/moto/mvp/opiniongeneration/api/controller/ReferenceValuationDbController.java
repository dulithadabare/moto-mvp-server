package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.foundation.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceValuationDbDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.ReferenceValuationDbEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ReferenceValuationDbController
{
    Logger logger = LoggerFactory.getLogger( ReferenceValuationDbController.class );

    private ReferenceValuationDbEndpoint endpoint;

    @Autowired
    public ReferenceValuationDbController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.REFERENCE_VALUATION_DB_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/referenceValuationDbs" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/referenceValuationDbs/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/referenceValuationDbs" )
    public HttpEntity<BasicResponse> save( @RequestBody List<ReferenceValuationDbDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/referenceValuationDbs" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<ReferenceValuationDbDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
