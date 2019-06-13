FROM gcr.io/distroless/java:8

LABEL name="Inventory Service" \
      maintainer="Stakater <stakater@aurorasolutions.io>" \
      vendor="Stakater" \
      release="1" \
      summary="Project containing gateway service with vertix for Nordmart"

USER root

ENV HOME=/opt/app
WORKDIR $HOME

# Expose the port on which your service will run
EXPOSE 8080

# NOTE we assume there's only 1 jar in the target dir
COPY target/*.jar $HOME/artifacts/app.jar

CMD ["artifacts/app.jar"]