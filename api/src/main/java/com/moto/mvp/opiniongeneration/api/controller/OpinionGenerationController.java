package com.moto.mvp.opiniongeneration.api.controller;

import com.moto.mvp.foundation.api.basic.BasicResponse;
import com.moto.mvp.foundation.api.utils.ExecutionUtils;
import com.moto.mvp.opiniongeneration.core.MotoPlatform;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.endpoint.OpinionGenerationEndpoint;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.OpinionValueRequestTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opinions")
public class OpinionGenerationController
{
    Logger logger = LoggerFactory.getLogger( OpinionGenerationController.class );

    private OpinionGenerationEndpoint opinionGenerationEndpoint;

    @Autowired
    public OpinionGenerationController( MotoPlatform motoPlatform )
    {
        opinionGenerationEndpoint = motoPlatform.getEndpoint( EndpointKey.OPINION_GENERATION_ENDPOINT.getEndPointKey() );
    }

    @CrossOrigin
    @PostMapping( "/generate" )
    public HttpEntity<BasicResponse> getOpinionValue( @RequestBody OpinionGenerationRequest opinionGenerationRequest )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
                () -> new BasicResponse( opinionGenerationEndpoint.getOpinionValue( opinionGenerationRequest )
                )
        ) );
    }

    @CrossOrigin
    @PostMapping( "/generateTest" )
    public HttpEntity<BasicResponse> getOpinionValue( @RequestBody OpinionValueRequestTest opinionValueRequestTest )
    {
        return new HttpEntity<>( ExecutionUtils.execute(
                () -> new BasicResponse( opinionGenerationEndpoint.getOpinionValueTest( opinionValueRequestTest )
                )
        ) );
    }
}
