package com.stakater.nordmart.repository;

import com.stakater.nordmart.BaseTest;
import com.stakater.nordmart.catalog.domain.Product;
import com.stakater.nordmart.catalog.repository.ProductRepository;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductRepositoryTest extends BaseTest
{
    @Autowired
    private ProductRepository repository;

    @Test
    public void shouldFetchAllProducts() {

        Spliterator<Product> products = repository.findAll().spliterator();
        List<Product> productsList = StreamSupport.stream(products, false).collect(Collectors.toList());

        assert productsList.size() == 7;
    }
}
