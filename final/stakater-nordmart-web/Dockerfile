# base image
FROM node:8.9.3-alpine

# set working directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# add `/usr/src/app/node_modules/.bin` to $PATH
ENV PATH /usr/src/app/node_modules/.bin:$PATH

RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh

COPY . /usr/src/app/

RUN chown 1001:1001 /usr/src/app && \
    mkdir /.npm && chown 1001:1001 /.npm && \
    mkdir /.config && chown 1001:1001 /.config && \
    mkdir /.cache && chown 1001:1001 /.cache && \
    mkdir /.local && chown 1001:1001 /.local

USER 1001

RUN npm install

# start app
CMD ["npm", "start"]
