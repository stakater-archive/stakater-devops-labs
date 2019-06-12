package com.stakater.nordmart.catalog.controller;

import com.stakater.nordmart.catalog.domain.Product;
import com.stakater.nordmart.catalog.repository.ProductRepository;
import com.stakater.nordmart.catalog.common.IstioHeaders;
import com.stakater.nordmart.catalog.common.Utils;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/products")
public class CatalogController {

    private static final Logger LOG = LoggerFactory.getLogger(CatalogController.class);

    private final ProductRepository repository;
    private final Counter requests;
    private final AtomicInteger productCount;
    private final Timer timer;

    public CatalogController(ProductRepository repository, MeterRegistry meterRegistry) {
        this.repository = repository;
        this.requests = Counter.builder("count_requests_total")
            .description("Total count requests.")
            .register(meterRegistry);
        timer = Timer.builder("catalogController").tag("method", "getAll").register(meterRegistry);
        this.productCount = meterRegistry.gauge("product_count", new AtomicInteger(0));

    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAll() {
        requests.increment();

        long start = System.nanoTime();

        IstioHeaders istioHeaders = new IstioHeaders(Utils.getCurrentHttpRequest());
        LOG.info(istioHeaders.toString());

        Spliterator<Product> products = repository.findAll().spliterator();
        List<Product> productList = StreamSupport.stream(products, false).collect(Collectors.toList());

        timer.record(System.nanoTime() - start, TimeUnit.NANOSECONDS);
        productCount.set(productList.size());
        return productList;
    }
}
