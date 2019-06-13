# stakater-nordmart-inventory

## Overview

A maven wildfly inventory application that exposes product's inventory status on REST API endpoints.

## Dependencies

It requires following things to be installed:

* Java: ^8.0
* Maven

## Deployment strategy

### Local deployment

This section provides step by step guidelines on how to run the application:

* To run the application use the command given below:

```bash
clean package wildfly-swarm:run
```

### Helm Charts

If you have configured helm on your cluster, you can deploy inventory microservice using our generic `Application` chart from our public chart repository and deploy it via helm using below mentioned commands

Note:
The default values are placed inside [values.yaml](deployment/values.yaml]).

```bash
helm repo add stakater https://stakater.github.io/stakater-charts

helm repo update

helm install --name inventory --namespace nordmart-store stakater/application -f deployment/values.yaml
```

