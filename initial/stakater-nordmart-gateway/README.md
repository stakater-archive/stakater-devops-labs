# stakater-nordmart-gateway

## Overview

A maven vertx gateway application that aggregates API calls to backend services by providing a condensed REST API for front-end.

## Dependencies

It requires following things to be installed:

* Java: ^8.0.
* Maven

## Deployment strategy

### Local deployment

To run the application locally use the command given below:

```bash
mvn vertx:run
```

### Docker

To deploy app inside a docker container

* Create a network if it doesn't already exist by executing

  ```bash
  docker network create --driver bridge nordmart-apps
  ```

* Build jar file of the app by executing

  ```bash
  mvn clean package vertx:package
  ```

* Next build the image using

  ```bash
  docker build -t gateway .
  ```

* Finally run the image by executing

  ```bash
  docker run -d --name gateway --network nordmart-apps -p 8083:8080 -e CART_API_HOST=cart -e CART_API_PORT=8082 -e CATALOG_API_HOST=catalog -e CATALOG_API_PORT=8080 -e INVENTORY_API_HOST=inventory -e INVENTORY_API_PORT=8081 -e HTTP_PORT=8080 -e DISABLE_CART_DISCOVERY=false gateway
  ```

### Helm Charts

#### Pre-requisites

Helm operator needs to to be running inside the cluster. Helm operator is deployed by Stakater Global Stack, deployment guidelines are provided in this [link](https://playbook.stakater.com/content/processes/bootstrapping/deploying-stack-on-azure.html)

#### Helm chart deployment

To create helm release of this application using the command given below:

kubectl apply -f [helm-release](https://github.com/stakater-lab/nordmart-dev-apps/blob/master/releases/gateway-helm-release.yaml).yaml -n <namespace-name>
