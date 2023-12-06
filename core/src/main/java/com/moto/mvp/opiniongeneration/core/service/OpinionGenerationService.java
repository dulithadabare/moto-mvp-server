package com.moto.mvp.opiniongeneration.core.service;

import com.moto.mvp.foundation.core.constant.Status;
import com.moto.mvp.foundation.core.exception.OpinionGenerationException;
import com.moto.mvp.foundation.core.service.AbstractBasicService;
import com.moto.mvp.opiniongeneration.core.constant.ResourceKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.engine.OpinionGenerationEngine;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationResponse;
import com.moto.mvp.foundation.core.resource.HibernateResource;
import com.moto.mvp.opiniongeneration.core.model.OpinionValueRequestTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpinionGenerationService extends AbstractBasicService
{
    private Logger logger = LoggerFactory.getLogger( "OpinionGeneration" );

    @Override
    public String getKey()
    {
        return ServiceKey.OPINION_GENERATION_SERVICE.getServiceKey();
    }

    public OpinionGenerationResponse getOpinionValue( OpinionGenerationRequest ogReq )
    {
        OpinionGenerationResponse ogRes = new OpinionGenerationResponse(  );

        OpinionGenerationEngine opinionGenerationEngine = new OpinionGenerationEngine( this.cacheManager, this.resourceManager, ogReq );

        try
        {
            ogRes = opinionGenerationEngine.generateOpinionValueResponse();
        }
        catch ( OpinionGenerationException | NullPointerException e )
        {
            ogRes.getOpinionGenerationStatus().setStatus( Status.OPINION_GENERATION_ERROR.getCode() );
            ogRes.getOpinionGenerationStatus().setMessage( e.getMessage() );
        }

        ogRes.setLogs( opinionGenerationEngine.getLogs() );

        return ogRes;
    }

    public OpinionGenerationResponse getOpinionValueTest( OpinionValueRequestTest opinionValueRequestTest )
    {
        OpinionGenerationEngine opinionGenerationEngine = new OpinionGenerationEngine( this.cacheManager, this.resourceManager, opinionValueRequestTest.getOpinionGenerationRequest() );

        return opinionGenerationEngine.generateOpinionValueResponseTest( opinionValueRequestTest.getMostMatchingValuation() );
    }

    private HibernateResource getHibernateResource()
    {
        return this.resourceManager.getResource( ResourceKey.HIBERNATE_RESOURCE.getResourceKey() );
    }
}
