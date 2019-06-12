package com.stakater.nordmart.gateway.handler;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.circuitbreaker.CircuitBreaker;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductHandler extends NordmartHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(ProductHandler.class);

    public final InventoryHandler inventoryHandler = new InventoryHandler();

    public void products(RoutingContext rc)
    {
        // Retrieve catalog
        client.get("/api/products").as(BodyCodec.jsonArray()).rxSend()
            .map(resp -> {
                if (resp.statusCode() != 200)
                {
                    throw new RuntimeException("Invalid response from the catalog: " + resp.statusCode());
                }
                return resp.body();
            })
            .flatMap(inventoryHandler::updateInventory)
            .subscribe(
                list -> rc.response().end(Json.encodePrettily(list)),
                error -> rc.response().end(new JsonObject().put("error", error.getMessage()).toString())
            );
    }

    public void setCircuit(CircuitBreaker circuit) {
        super.setCircuit(circuit);
        this.inventoryHandler.setCircuit(circuit);
    }
}
