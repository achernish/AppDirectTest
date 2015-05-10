package com.appdirect.controller;

import com.appdirect.integration.remote.type.ErrorCode;
import com.appdirect.integration.remote.type.Result;
import com.appdirect.integration.service.IntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anatoly Chernysh
 */
@RestController
@Slf4j
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    @RequestMapping("/handleEvent")
    public Result handleEvent(@RequestParam(value = "eventUrl") String eventUrl) {
        Result result;

        try {
            log.info("Event url: " + eventUrl);
            result = integrationService.handleEvent(eventUrl);
        } catch (RuntimeException e) {
            log.error("Error in handling event: ", e);

            result = new Result();
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.UNKNOWN_ERROR);
            result.setMessage(e.getMessage() != null ? e.getMessage() : e.toString());
        }
        log.info("Returning result={}", result);
        return result;
    }
}