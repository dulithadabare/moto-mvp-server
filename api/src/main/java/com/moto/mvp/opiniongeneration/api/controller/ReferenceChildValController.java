package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.foundation.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.ReferenceChildValDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.ReferenceChildValEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ReferenceChildValController
{
    Logger logger = LoggerFactory.getLogger( ReferenceChildValController.class );

    private ReferenceChildValEndpoint endpoint;

    @Autowired
    public ReferenceChildValController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.REFERENCE_CHILD_VAL_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/referenceChildVals" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/referenceChildVals/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/referenceChildVals" )
    public HttpEntity<BasicResponse> save( @RequestBody List<ReferenceChildValDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/referenceChildVals" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<ReferenceChildValDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
