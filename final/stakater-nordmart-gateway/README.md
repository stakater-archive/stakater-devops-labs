# stakater-nordmart-gateway

A maven vertx gateway app for lab

## Deployment strategy

### Helm Charts

If you have configured helm on your cluster, you can deploy gateway microservice using our generic `Application` chart from our public chart repository and deploy it via helm using below mentioned commands

Note:
The default values are placed inside [values.yaml](deployment/values.yaml]).

```bash
helm repo add stakater https://stakater.github.io/stakater-charts

helm repo update

helm install --name gateway --namespace nordmart-store stakater/application -f deployment/values.yaml
```
