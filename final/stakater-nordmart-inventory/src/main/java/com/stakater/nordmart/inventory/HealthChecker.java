package com.stakater.nordmart.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HealthChecker
{
    @GET
    @Path("/healthCheck")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAvailability()
    {
        return "{ 'STATUS' : 'UP' }";
    }
}
