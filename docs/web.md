# Web

## Overview
A node js web application.

It requires following things to be installed:

* Node: ^8.0.
* NPM.

## Deployment strategy

### Local deployment

This section provides step by step guidelines on how to run the application:

* Install all the dependencies:

```bash
npm install
```

* To run the application:

```bash
npm start
```

### Helm Charts

If you have configured helm on your cluster, you can deploy web microservice using our generic `Application` chart from our public chart repository and deploy it via helm using below mentioned commands

Note:
The default values are placed inside [values.yaml](deployment/values.yaml]).

```bash
helm repo add stakater https://stakater.github.io/stakater-charts

helm repo update

helm install --name web --namespace nordmart-store stakater/application -f deployment/values.yaml
```

