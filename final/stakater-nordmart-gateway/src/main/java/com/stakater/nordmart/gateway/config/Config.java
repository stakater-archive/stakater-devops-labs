package com.stakater.nordmart.gateway.config;

public class Config
{
    private Address catalogAddress;
    private Address inventoryAddress;
    private Address cartAddress;
    private Integer serverPort;
    private Boolean disableCartDiscovery;

    public static Config getConfig()
    {
        Config config = new Config();
        config.cartAddress = new Address(System.getenv("CART_API_HOST"), System.getenv("CART_API_PORT"));

        config.catalogAddress = new Address(System.getenv("CATALOG_API_HOST"), "8082");

        config.inventoryAddress = new Address(System.getenv("INVENTORY_API_HOST"),
            "9001");

        try
        {
            config.serverPort = Integer.parseInt(System.getenv("HTTP_PORT"));
        } catch (NumberFormatException ignored)
        {
            config.serverPort = 8081;
        }

        config.disableCartDiscovery = Boolean.parseBoolean(System.getenv("DISABLE_CART_DISCOVERY"));

        return config;
    }

    public Address getCatalogAddress()
    {
        return catalogAddress;
    }

    public Address getInventoryAddress()
    {
        return inventoryAddress;
    }

    public Address getCartAddress()
    {
        return cartAddress;
    }

    public Integer getServerPort()
    {
        return serverPort;
    }

    public Boolean isDisableCartDiscovery()
    {
        return disableCartDiscovery;
    }
}
