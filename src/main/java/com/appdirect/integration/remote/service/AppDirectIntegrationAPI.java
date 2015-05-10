package com.appdirect.integration.remote.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Anatoly Chernysh
 */
@Path("api/integration/v1")
public interface AppDirectIntegrationAPI {

    @GET
    @Path("events/{token}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String readEvent(@PathParam("token") String eventToken);

}