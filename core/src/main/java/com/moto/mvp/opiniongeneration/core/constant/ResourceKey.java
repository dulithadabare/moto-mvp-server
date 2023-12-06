package com.moto.mvp.opiniongeneration.core.constant;

public enum ResourceKey
{
    HIBERNATE_RESOURCE,
    AWS_S3_RESOURCE;

    public String getResourceKey()
    {
        return this.toString();
    }
}
