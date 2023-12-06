package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.foundation.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceParentValDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.ReferenceParentValEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ReferenceParentValController
{
    Logger logger = LoggerFactory.getLogger( ReferenceParentValController.class );

    private ReferenceParentValEndpoint endpoint;

    @Autowired
    public ReferenceParentValController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.REFERENCE_PARENT_VAL_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/referenceParentVals" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/referenceParentVals/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/referenceParentVals" )
    public HttpEntity<BasicResponse> save( @RequestBody List<ReferenceParentValDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/referenceParentVals" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<ReferenceParentValDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
