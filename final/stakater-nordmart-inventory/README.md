# stakater-nordmart-inventory

## Overview

A maven wildfly inventory application that exposes product's inventory status on REST API endpoints.

## Dependencies

It requires following things to be installed:

* Java: ^8.0
* Maven

## Deployment strategy

### Local deployment

To run the application locally use the command given below:

```bash
mvn clean wildfly-swarm:run
```

### Docker

To deploy app inside a docker container

* Create a network if it doesn't already exist by executing

  ```bash
  docker network create --driver bridge nordmart-apps
  ```

* Build jar file of the app by executing

  ```bash
  mvn clean package wildfly-swarm:package
  ```

* Next build the image using

  ```bash
  docker build -t inventory .
  ```

* Finally run the image by executing

  ```bash
  docker run -d --name inventory --network nordmart-apps -p 8081:8080 inventory
  ```

### Helm Charts

To deploy using helm, see the sample HelmRelease [here](https://github.com/stakater-lab/nordmart-dev-apps/blob/master/releases/inventory-helm-release.yaml)
