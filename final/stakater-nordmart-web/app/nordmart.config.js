var config = {
    API_ENDPOINT: 'http://localhost:8081',
    SECURE_API_ENDPOINT: 'secure-gateway-' + process.env.SECURE_NORDMART_GW_SERVICE,
    SSO_ENABLED: process.env.SSO_URL ? true : false
};

if (process.env.NORDMART_GW_ENDPOINT != null) {
    config.API_ENDPOINT = process.env.NORDMART_GW_ENDPOINT;
} else if (process.env.NORDMART_GW_SERVICE != null) {
    config.API_ENDPOINT = process.env.NORDMART_GW_SERVICE + '-' + process.env.KUBERNETES_NAMESPACE;
}

if (process.env.SECURE_NORDMART_GW_ENDPOINT != null) {
    config.SECURE_API_ENDPOINT = process.env.SECURE_NORDMART_GW_ENDPOINT;
} else if (process.env.SECURE_NORDMART_GW_SERVICE != null) {
    config.SECURE_API_ENDPOINT = process.env.SECURE_NORDMART_GW_SERVICE + '-' + process.env.KUBERNETES_NAMESPACE;
}

module.exports = config;
