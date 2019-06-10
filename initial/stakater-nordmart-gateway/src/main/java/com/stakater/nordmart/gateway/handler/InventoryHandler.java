package com.stakater.nordmart.gateway.handler;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Single;

public class InventoryHandler extends NordmartHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(InventoryHandler.class);

    public Single<List<JsonObject>> updateInventory(JsonArray products)
    {
        return Observable.from(products)
            .cast(JsonObject.class)
            .flatMapSingle(product ->
                circuit.rxExecuteCommandWithFallback(
                    future ->
                        client.get("/api/inventory/" + product.getString("itemId"))
                            .as(BodyCodec.jsonObject())
                            .rxSend()
                            .map(resp -> {
                                if (resp.statusCode() != 200)
                                {
                                    LOG.warn("Inventory error for {}: status code {}", product.getString("itemId"),
                                        resp.statusCode());
                                    return product.copy();
                                }
                                return this.setAvailability(product.copy(), resp.body().getInteger("quantity"));
                            })
                            .subscribe(future::complete, future::fail),
                    error -> {
                        LOG.error("Inventory error for {}: {}", product.getString("itemId"), error.getMessage());
                        return product;
                    }
                )
            )
            .toList().toSingle();
    }

    private JsonObject setAvailability(JsonObject product, int quantity)
    {
        return product.put("availability", new JsonObject().put("quantity", quantity));
    }


}
