package com.moto.mvp.opiniongeneration.core.engine.rule;

import com.moto.mvp.foundation.core.exception.OpinionGenerationException;

public interface BasicRule
{
    void apply() throws OpinionGenerationException;

    String getRuleName();
}
