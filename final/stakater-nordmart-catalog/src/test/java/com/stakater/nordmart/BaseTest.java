package com.stakater.nordmart;


import com.stakater.nordmart.catalog.CatalogApplication;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatalogApplication.class)
public abstract class BaseTest {

}