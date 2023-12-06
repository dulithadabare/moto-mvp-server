package com.moto.mvp.opiniongeneration.core.endpoint;

import com.moto.mvp.foundation.core.endpoint.AbstractBasicEndpoint;
import com.moto.mvp.opiniongeneration.core.constant.EndpointKey;
import com.moto.mvp.opiniongeneration.core.constant.ServiceKey;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationRequest;
import com.moto.mvp.opiniongeneration.core.model.OpinionValueRequestTest;
import com.moto.mvp.opiniongeneration.core.model.OpinionGenerationResponse;
import com.moto.mvp.opiniongeneration.core.service.OpinionGenerationService;

public class OpinionGenerationEndpoint extends AbstractBasicEndpoint
{
    @Override
    public String getKey()
    {
        return EndpointKey.OPINION_GENERATION_ENDPOINT.getEndPointKey();
    }

    public OpinionGenerationResponse getOpinionValue( OpinionGenerationRequest opinionGenerationRequest )
    {
        return opinionGenerationService().getOpinionValue( opinionGenerationRequest );
    }

    public OpinionGenerationResponse getOpinionValueTest( OpinionValueRequestTest opinionValueRequestTest )
    {
        return opinionGenerationService().getOpinionValueTest( opinionValueRequestTest );
    }

    private OpinionGenerationService opinionGenerationService()
    {
        return this.getService( ServiceKey.OPINION_GENERATION_SERVICE.getServiceKey() );
    }
}