package com.stakater.nordmart.catalog.repository;

import com.stakater.nordmart.catalog.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
