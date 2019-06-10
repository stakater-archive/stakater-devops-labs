package com.stakater.nordmart.gateway;


import com.stakater.nordmart.gateway.client.ClientFactory;
import com.stakater.nordmart.gateway.config.Config;
import com.stakater.nordmart.gateway.handler.CartHandler;
import com.stakater.nordmart.gateway.handler.ProductHandler;
import com.stakater.nordmart.gateway.router.NordmartRouter;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.rxjava.circuitbreaker.CircuitBreaker;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.servicediscovery.ServiceDiscovery;
import rx.Single;

public class GatewayVerticle extends AbstractVerticle
{
    private final ProductHandler productHandler = new ProductHandler();
    private final CartHandler cartHandler = new CartHandler();

    @Override
    public void start()
    {
        Config config = Config.getConfig();
        CircuitBreaker circuit = CircuitBreaker.create("inventory-circuit-breaker", vertx,
            new CircuitBreakerOptions()
                .setFallbackOnFailure(true)
                .setMaxFailures(3)
                .setResetTimeout(5000)
                .setTimeout(1000)
        );

        productHandler.setCircuit(circuit);
        cartHandler.setCircuit(circuit);

        Router router = new NordmartRouter(vertx, productHandler, cartHandler).getRouter();

        ServiceDiscovery.create(vertx, discovery -> {
            ClientFactory clientFactory = new ClientFactory(discovery, vertx, config);
            // Zip all 3 requests
            Single.zip(clientFactory.getCatalogClient(), clientFactory.getInventoryClient(),
                clientFactory.getCartClient(), (c, i, ct) -> {
                // When everything is done
                productHandler.setClient(c);
                productHandler.inventoryHandler.setClient(i);
                cartHandler.setClient(ct);
                return vertx.createHttpServer()
                    .requestHandler(router::accept)
                    .listen(config.getServerPort());
            }).subscribe();
        });
    }

}
