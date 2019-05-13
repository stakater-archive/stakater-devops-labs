FROM gcr.io/distroless/java:8

LABEL name="Cart Service" \
      maintainer="Stakater <stakater@aurorasolutions.io>" \
      vendor="Stakater" \
      release="1" \
      summary="Project containing cart service for coolstore"

USER 1001

ENV HOME=/opt/app
WORKDIR $HOME

# Expose the port on which your service will run
EXPOSE 8080

# NOTE we assume there's only 1 jar in the target dir
COPY target/*.jar $HOME/artifacts/app.jar

CMD ["artifacts/app.jar"]