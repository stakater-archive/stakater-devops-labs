package com.stakater.nordmart.gateway.router;

import com.stakater.nordmart.gateway.handler.CartHandler;
import com.stakater.nordmart.gateway.handler.ProductHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.CorsHandler;

public class NordmartRouter
{
    private Router router;

    public NordmartRouter(Vertx vertex, ProductHandler productHandler, CartHandler cartHandler)
    {
        router = Router.router(vertex);
        router.route().handler(CorsHandler.create("*")
            .allowedMethod(HttpMethod.GET)
            .allowedMethod(HttpMethod.POST)
            .allowedMethod(HttpMethod.DELETE)
            .allowedHeader("Access-Control-Allow-Method")
            .allowedHeader("Access-Control-Allow-Origin")
            .allowedHeader("Access-Control-Allow-Credentials")
            .allowedHeader("Content-Type")
            .allowedHeader("x-request-id")
            .allowedHeader("x-b3-traceid")
            .allowedHeader("x-b3-spanid")
            .allowedHeader("x-b3-parentspanid")
            .allowedHeader("x-b3-sampled")
            .allowedHeader("x-b3-flags")
            .allowedHeader("x-ot-span-context"));


        router.get("/health").handler(ctx -> ctx.response().end(new JsonObject().put("status", "UP").toString()));
        router.get("/api/products").handler(productHandler::products);
        router.get("/api/cart/:cartId").handler(cartHandler::getCart);
        router.post("/api/cart/:cartId/:itemId/:quantity").handler(cartHandler::addToCart);
        router.delete("/api/cart/:cartId/:itemId/:quantity").handler(cartHandler::deleteFromCart);
    }

    public Router getRouter() {
        return this.router;
    }

}
