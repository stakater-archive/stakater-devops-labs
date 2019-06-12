package com.stakater.nordmart.gateway.client;

import com.stakater.nordmart.gateway.config.Config;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.servicediscovery.ServiceDiscovery;
import io.vertx.rxjava.servicediscovery.types.HttpEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Single;

public class ClientFactory
{
    private static final Logger LOG = LoggerFactory.getLogger(ClientFactory.class);

    private ServiceDiscovery discovery;
    private Vertx vertx;
    private Config config;

    public ClientFactory(ServiceDiscovery discovery, Vertx vertx, Config config) {
        this.discovery = discovery;
        this.vertx = vertx;
        this.config = config;
    }

    public Single<WebClient> getCatalogClient() {
        return HttpEndpoint.rxGetWebClient(discovery, rec -> rec.getName().equals("catalog"))
            .onErrorReturn(t -> WebClient.create(vertx, new WebClientOptions()
                .setDefaultHost(config.getCatalogAddress().getHost())
                .setDefaultPort(config.getCatalogAddress().getPort())));
    }

    public Single<WebClient> getInventoryClient() {
        return HttpEndpoint.rxGetWebClient(discovery,
            rec -> rec.getName().equals("inventory"))
            .onErrorReturn(t -> WebClient.create(vertx, new WebClientOptions()
                .setDefaultHost(config.getInventoryAddress().getHost())
                .setDefaultPort(config.getInventoryAddress().getPort())));
    }

    public Single<WebClient> getCartClient() {
        // Cart lookup
        Single<WebClient> cartDiscoveryRequest;
        if (config.isDisableCartDiscovery())
        {
            LOG.info("Disable Cart discovery");
            cartDiscoveryRequest = Single.just(null);
        }
        else
        {
            cartDiscoveryRequest = HttpEndpoint
                .rxGetWebClient(discovery, rec -> rec.getName().equals("cart"))
                .onErrorReturn(t -> WebClient.create(vertx, new WebClientOptions()
                    .setDefaultHost(config.getCartAddress().getHost())
                    .setDefaultPort(config.getCartAddress().getPort())));
        }
        return cartDiscoveryRequest;
    }
}
