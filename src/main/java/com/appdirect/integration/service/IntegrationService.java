package com.appdirect.integration.service;


import com.appdirect.integration.remote.type.Result;

/**
 * @author Anatoly Chernysh
 */
public interface IntegrationService {

    public Result handleEvent(String eventUrl);
}
