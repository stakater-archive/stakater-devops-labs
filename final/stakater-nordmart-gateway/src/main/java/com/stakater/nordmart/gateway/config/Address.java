package com.stakater.nordmart.gateway.config;

public class Address
{
    private String host = "localhost";
    private Integer port = 8080;

    Address(String host, String port)
    {
        if (host != null && !host.isEmpty())
        {
            this.host = host;
        }

        if (port !=null && !port.isEmpty())
        {
            try
            {
                this.port = Integer.parseInt(port);
            } catch (NumberFormatException ignored)
            {
            }
        }
    }

    public String getHost()
    {
        return host;
    }

    public Integer getPort()
    {
        return port;
    }
}
