# Scenario

## Nordmart Apps

Nordmart consists of five apps

- [Catalog](https://github.com/stakater-lab/stakater-nordmart-catalog) is a spring boot api that provides information about all the different products such as their name, desc, price etc.
- [Inventory](https://github.com/stakater-lab/stakater-nordmart-inventory) is a Spring boot + Wildfly-swarm Api that keeps track of stock of different products.
- [Cart](https://github.com/stakater-lab/stakater-nordmart-cart) is a spring boot app that keeps track of products being purchased by user.
- [Gateway](https://github.com/stakater-lab/stakater-nordmart-gateway) is a vertx app that condenses the Catalog, Cart and Inventory api to be used by front-end.
- [Web](https://github.com/stakater-lab/stakater-nordmart-web) is the front-end app built using angular JS. It has two views, the home view lists all the products and the cart view lists product added to cart by the user.

## Problem

Right now the front-end app has the images for the products. And when it receives products form backend it finds image based on product name and displays it. This is wrong as when we need to update products i.e add new product or update image of an older product, we have to modify both the Web and Catalog Projects and re-deploy them which demolishes the point of using microservices.

## Solution

Catalog service should save the images of Products and serve them to customers.

## Task Breakdown

The task can be split as follows:

- Update Catalog
  - We should update the Product Domain to have a new field named `fileName` to save product image filename.
  - We should save the images for different products with correct filename in src/main/resources/images directory.
  - We should add a resource handler in the Spring boot main application to server the images.
  - Update `import.sql` and add filename to all the queries
- Update Cart
  We should update Product wrapper in the cart project to return filename as well(It will receive it from catalog).
- Update gateway
  Gateway has handlers written for all the different APIs. The ProductHandler needs a new method to proxy requests for images coming from front-end to catalog service. The router needs a new route named `/api/images/:fileName` which should trigger the new image method in product handler and forward request to `/images/:fileName`.
- Update Frontend to display images based on fileName property.
  - The home page needs to be updated to render image from backend. The baseURL of api would need to be made available by doing:
    - Expose baseURL from catalog service in `catalog.js`.
    - Add baseURL to scope in `homeController` using catalog's baseURL.
    - Update URL in `home.html` to point to correct endpoint.
  - The cart page needs to be updated to render image from backend. The baseURL of api would need to be made available by doing:
    - Expose baseURL from cart service `cart.js`.
    - Add baseURL to scope in `cartController` using cart's baseURL.
    - Update URL in `cart.html` to point to correct endpoint.
