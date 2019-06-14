# stakater-nordmart-cart

## Overview

A maven spring boot cart application.

## Dependencies

It requires following things to be installed:

* Java: ^8.0.
* Maven

## Deployment strategy

### Local deployment

To run the application locally use the command given below:

```bash
mvn clean spring-boot:run
```

### Docker

To deploy app inside a docker container

* Create a network if it doesn't already exist by executing

  ```bash
  docker network create --driver bridge nordmart-apps
  ```

* Build jar file of the app by executing

  ```bash
  mvn clean package
  ```

* Next build the image using

  ```bash
  docker build -t cart .
  ```

* Finally run the image by executing

  ```bash
  docker run -d --name cart --network nordmart-apps -p 8082:8080 -e CATALOG_ENDPOINT="catalog:8080" cart
  ```

  Note:
  Make sure that catalog is running before running this.

### Helm Charts

#### Pre-requisites

Helm operator needs to to be running inside the cluster. Helm operator is deployed by Stakater Global Stack, deployment guidelines are provided in this [link](https://playbook.stakater.com/content/processes/bootstrapping/deploying-stack-on-azure.html)

#### Helm chart deployment

To create helm release of this application using the command given below:

kubectl apply -f [helm-release](https://github.com/stakater-lab/nordmart-dev-apps/blob/master/releases/cart-helm-release.yaml).yaml -n <namespace-name>
