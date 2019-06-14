# stakater-nordmart-web

## Overview

A node js web application.

It requires following things to be installed:

* Node: ^8.0.
* NPM.

## Deployment strategy

### Local deployment

To run the application locally first install all the dependencies by executing

```bash
npm install
```

and then start the application using:

```bash
npm start
```

### Docker

To deploy app inside a docker container

* Create a network if it doesn't already exist by executing

  ```bash
  docker network create --driver bridge nordmart-apps
  ```

* Next build the image using

  ```bash
  docker build -t web .
  ```

* Finally run the image by executing

  ```bash
  docker run -d --name web --network nordmart-apps -e PORT=4200 -e SECURE_GW_ENDPOINT="gateway:8080" -p 4200:4200 web
  ```

### Helm Charts

#### Pre-requisites

Helm operator needs to to be running inside the cluster. Helm operator is deployed by Stakater Global Stack, deployment guidelines are provided in this [link](https://playbook.stakater.com/content/processes/bootstrapping/deploying-stack-on-azure.html)

#### Helm chart deployment

To create helm release of this application using the command given below:

kubectl apply -f [helm-release](https://github.com/stakater-lab/nordmart-dev-apps/blob/master/releases/web-helm-release.yaml).yaml -n <namespace-name>