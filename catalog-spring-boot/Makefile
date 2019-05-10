.ONESHELL:
SHELL := /bin/bash
OS := $(shell uname)

deploy:
	cd deployment/kubernetes

	REPLACE_IMAGE="s/{IMAGE}/$${IMAGE_NAME//\//\\/}:$(IMAGE_TAG)/g"
	REPLACE_NAMESPACE="s/{NAMESPACE}/$(NAMESPACE)/g"

	sed $$REPLACE_IMAGE deployment.tmpl.yaml | sed $${REPLACE_NAMESPACE} | kubectl apply -f -
	sed $${REPLACE_NAMESPACE} service.tmpl.yaml | kubectl apply -f -