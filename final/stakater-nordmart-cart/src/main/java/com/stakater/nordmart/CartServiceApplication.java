package com.stakater.nordmart;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.io.IOException;
import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CartServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CartServiceApplication.class, args);
    }

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor()
    {

        return new RequestInterceptor()
        {
            @Override
            public void apply(RequestTemplate requestTemplate)
            {
                try
                {
                    HashMap result = new ObjectMapper().readValue(requestTemplate.body(), HashMap.class);

                    result.forEach((key, value) -> {
                        requestTemplate.header(key.toString(), value.toString());
                    });
                    requestTemplate.body(null);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        };
    }
}
