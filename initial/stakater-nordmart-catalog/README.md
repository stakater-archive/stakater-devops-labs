# stakater-nordmart-catalog

## Overview

A maven spring boot catalog application for the product catalog and product information retrieval.

## Dependencies

It requires following things to be installed:

* Java: ^8.0
* Maven

## Deployment strategy

### Local deployment

To run the application locally use the command given below:

```bash
mvn spring-boot:run
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
  docker build -t catalog .
  ```

* Finally run the image by executing

  ```bash
  docker run -d --name catalog --network nordmart-apps -p 8080:8080 catalog
  ```

### Helm Charts

To deploy using helm, see the sample HelmRelease [here](https://github.com/stakater-lab/nordmart-dev-apps/blob/master/releases/catalog-helm-release.yaml)

## Prometheus

### Prometheus Dependencies

The following dependencies are needed to expose micrometer and application metrics

```xml
<dependencies>
    <!-- For micrometer support -->
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-core</artifactId>
        <version>1.1.4</version>
    </dependency>
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
        <version>1.1.4</version>
    </dependency>
</dependencies>
```

### Configuration

Add the following properties to `application.properties` to expose the micrometer endpoint.

```bash
management.endpoint.metrics.enabled=true
management.endpoints.web.exposure.include=*
management.endpoint.prometheus.enabled=true
management.metrics.export.prometheus.enabled=true
```

### Adding micrometer registry

Add the MeterRegistry bean to your spring boot application by adding the follwoing snippet to your SpringBootApplication class.

```java
    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "common-service");
    }
```

This will help you create custom metrics within the application

### Counter

To count the number of times an operation has been performed, just create a `io.micrometer.core.instrument.Counter` variable by doing

```java
Counter.builder("count_metric_name").description("Description of metric").register(meterRegistry);
```

### Time Measurement

To add metrics that keeps track of processing time taken by a piece of code, follow the following snippet:

```java
private final Timer timer = Timer.builder("metricsname").tag("tagKey", "tagValue").register(meterRegistry);
long start = System.nanoTime();
...your code here
timer.record(System.nanoTime() - start, TimeUnit.NANOSECONDS);
```

### Gauge

A gauge is a handle to get the current value. Typical examples for gauges would be the size of a collection or map.
To create a gauge metric just do

```java
AtomicInteger myCount = meterRegistry.gauge("gauge_value", new AtomicInteger(0));
```

and then you can just set the value as it changes using

```java
myCount.set(myList.size());
```

### Monitoring

Dasbhoards given below can be used to monitor application by configuring them in Monitoring stack. If monitoring stack is not already configured use guidelines given in this [link](https://playbook.stakater.com/content/processes/bootstrapping/deploying-stack-on-azure.html) to configure it. 

* Catalog service metrics dashboard can be configured using this [config](https://github.com/stakater-lab/nordmart-dev-apps/blob/master/releases/catalog-service-dashboard.yaml).

  ![catalog-service.png](docs/images/catalog-service.png)
