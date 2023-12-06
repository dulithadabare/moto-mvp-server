package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.foundation.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.dao.EngineCapacityTypeDAO;
import com.moto.mvp.opiniongeneration.core.endpoint.EngineCapacityTypeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class EngineCapacityTypeController
{
    Logger logger = LoggerFactory.getLogger( EngineCapacityTypeController.class );

    private EngineCapacityTypeEndpoint endpoint;

    @Autowired
    public EngineCapacityTypeController( MotoPlatform motoPlatform )
    {
        endpoint = motoPlatform.getEndpoint( EndpointKey.ENGINE_CAPACITY_TYPE_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @GetMapping( "/engineCapacityTypes" )
    public HttpEntity<BasicResponse> getAll()
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getAll()
                )
            ) );
    }

    @CrossOrigin
    @GetMapping( "/engineCapacityTypes/{id}" )
    public HttpEntity<BasicResponse> getById( @PathVariable int id )
    {
        logger.info( String.valueOf( id ) );
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.getById( id )
            )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/engineCapacityTypes" )
    public HttpEntity<BasicResponse> save( @RequestBody List<EngineCapacityTypeDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.save( entityList )
            )
        ) );
    }

    @CrossOrigin
    @DeleteMapping( "/engineCapacityTypes" )
    public HttpEntity<BasicResponse> delete( @RequestBody List<EngineCapacityTypeDAO> entityList )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
            () -> new BasicResponse( endpoint.delete( entityList )
            )
        ) );
    }
}
