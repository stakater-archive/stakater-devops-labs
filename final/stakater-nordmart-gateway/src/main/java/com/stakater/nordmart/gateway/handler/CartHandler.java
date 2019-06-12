package com.stakater.nordmart.gateway.handler;

import io.vertx.core.AsyncResult;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Future;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartHandler extends NordmartHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(CartHandler.class);

    public void getCart(RoutingContext rc)
    {
        String cartId = rc.request().getParam("cartId");

        circuit.executeWithFallback(
            future -> {
                client.get("/api/cart/" + cartId).as(BodyCodec.jsonObject())
                    .send(ar -> {
                        handleResponse(ar, rc, future);
                    });
            }, v -> new JsonObject());
    }

    public void addToCart(RoutingContext rc)
    {
        String cartId = rc.request().getParam("cartId");
        String itemId = rc.request().getParam("itemId");
        String quantity = rc.request().getParam("quantity");

        circuit.executeWithFallback(
            future -> {
                client.post("/api/cart/" + cartId + "/" + itemId + "/" + quantity)
                    .as(BodyCodec.jsonObject())
                    .send(ar -> {
                        handleResponse(ar, rc, future);
                    });
            }, v -> new JsonObject());
    }

    public void deleteFromCart(RoutingContext rc)
    {
        String cartId = rc.request().getParam("cartId");
        String itemId = rc.request().getParam("itemId");
        String quantity = rc.request().getParam("quantity");

        circuit.executeWithFallback(
            future -> {
                client.delete("/api/cart/" + cartId + "/" + itemId + "/" + quantity)
                    .as(BodyCodec.jsonObject())
                    .send(ar -> {
                        handleResponse(ar, rc, future);
                    });
            }, v -> new JsonObject());
    }

    private void handleResponse(AsyncResult<HttpResponse<JsonObject>> response, RoutingContext rc, Future<JsonObject>
        future)
    {
        if (response.succeeded())
        {
            rc.response().end(response.result().body().toString());
            future.complete();
        }
        else
        {
            rc.response().end(new JsonObject().toString());
            future.fail(response.cause());
        }
    }
}
