package com.stakater.nordmart.catalog;

import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CatalogApplication implements WebMvcConfigurer
{

    public static void main(String[] args)
    {
        SpringApplication.run(CatalogApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        // Register resource handler for images
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/")
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }
}
