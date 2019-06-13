var express = require('express'),
    http = require('http'),
    request = require('request'),
    fs = require('fs'),
    app = express(),
    path = require("path"),
    keycloakConfig = require('./app/keycloak.config.js'),
    nordmartConfig = require('./app/nordmart.config.js'),
    Keycloak = require('keycloak-connect'),
    cors = require('cors');


var port = process.env.PORT || process.env.OPENSHIFT_NODEJS_PORT || 4200,
    ip = process.env.IP || process.env.OPENSHIFT_NODEJS_IP || '0.0.0.0',
    secport = process.env.PORT || process.env.OPENSHIFT_NODEJS_PORT || 8443;

// Enable CORS support
app.use(cors());

// error handling
app.use(function(err, req, res, next) {
    console.error(err.stack);
    res.status(500).send('Something bad happened!');
});

// keycloak config server
app.get('/keycloak.json', function(req, res, next) {
    res.json(keycloakConfig);
});
// nordmart config server
app.get('/nordmart.json', function(req, res, next) {
    res.json(nordmartConfig);
});

// health Check
app.get('/health', function(req, res, next) {
    res.status(200).send('{"STATUS" : "UP"}')
});

app.use(express.static(path.join(__dirname, '/views')));
app.use('/app', express.static(path.join(__dirname, '/app')));
app.use('/bower_components', express.static(path.join(__dirname, '/bower_components')));

console.log("nordmart config: " + JSON.stringify(nordmartConfig));
console.log("keycloak config: " + JSON.stringify(keycloakConfig));


http.createServer(app).listen(port);

console.log('HTTP Server running on http://%s:%s', ip, port);

module.exports = app;