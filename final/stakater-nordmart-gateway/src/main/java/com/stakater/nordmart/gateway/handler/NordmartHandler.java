package com.stakater.nordmart.gateway.handler;

import io.vertx.rxjava.circuitbreaker.CircuitBreaker;
import io.vertx.rxjava.ext.web.client.WebClient;

public class NordmartHandler
{
    WebClient client;
    CircuitBreaker circuit;

    public void setCircuit(CircuitBreaker circuit) {
        this.circuit = circuit;
    }

    public void setClient(WebClient client) {
        this.client = client;
    }
}
