# Stakater-devops-labs

## Overview

The Lab for the DevOps Workshop with Kubernetes and Containers.

## Description

Nordmart is an online store web application built using Spring Boot, WildFly Swarm, Eclipse Vert.x, Node.js and AngularJS adopting the microservices architecture.

* **Web**: A Node.js/Angular front-end. Details can be found on this [link](final/stakater-nordmart-web/README.md)

* **API Gateway**: vert.x service aggregates API calls to back-end services and provides a condenses REST API for front-end. Details can be found on this [link](final/stakater-nordmart-gateway/README.md)

* **Catalog**: Spring Boot service exposing REST API for the product catalog and product information. Details can be found on this [link](final/stakater-nordmart-catalog/README.md).

* **Inventory**: WildFly Swarm service exposing REST API for product's inventory status. Details can be found on this [link](final/stakater-nordmart-inventory/README.md)

* **Cart**: Spring Boot service exposing REST API for shopping cart. Details can be found on this [link](final/stakater-nordmart-cart/README.md)

## Architecture

The diagram given below shows architecture of the application.

```text
                              +-------------+
                              |             |
                              |     Web     |
                              |             |
                              |   Node.js   |
                              |  AngularJS  |
                              +------+------+
                                     |
                                     v
                              +------+------+
                              |             |
                              | API Gateway |
                              |             |
                              |   Vert.x    |
                              |             |
                              +------+------+
                                     |
                 +---------+---------+-------------------+
                 v                   v                   v
          +------+------+     +------+------+     +------+------+
          |             |     |             |     |             |
          |   Catalog   |     |  Inventory  |     |     Cart    |
          |             |     |             |     |             |
          | Spring Boot |     |WildFly Swarm|     | Spring Boot |
          |             |     |             |     |             |
          +------+------+     +-------------+     +-------------+
                 |                                       |
                 v                                       v
          +------+------+                         +------+------+
          |             |                         |             |
          |    MySQL    |                         |    MySQL    |
          |             |                         |             |
          +-------------+                         +-------------+
```

## Views

The app contains two main screens.

* First one is the home screen which lists all the products. It fetches all the products from catalog and their available stock value from inventory and displays them. You can add an item to the cart using the respective `Add To Cart` button.
  ![home.png](docs/images/home.png)
* Second is the cart screen which shows the items currently in your cost and their cost.
  ![cart.png](docs/images/cart.png)

## Monitoring

Application can be monitored using these dashboards.

* Application dashboard metrices, it's config can be found [here](https://github.com/stakater/StakaterKubeHelmMonitoring/blob/azure-capability/manifests/grafanaDashboards/jvm.yaml):

  ![cart.png](docs/images/jvm.png)

* Catalog serivce dashboard, it's config can be found [here](https://github.com/stakater-lab/nordmart-dev-apps/blob/master/releases/catalog-service-dashboard.yaml):

  ![cart.png](docs/images/catalog-service.png)
   


## Deployment

To deploy all the apps follow the README of each project in the order given below:

* [Catalog](initial/stakater-nordmart-catalog/README.md)
* [Inventory](initial/stakater-nordmart-inventory/README.md)
* [Cart](initial/stakater-nordmart-cart/README.md)
* [Gateway](initial/stakater-nordmart-gateway/README.md)
* [Web](initial/stakater-nordmart-web/README.md)
