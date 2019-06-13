'use strict';

angular.module("app")

.factory('catalog', ['$http', '$q', 'NORDMART_CONFIG', 'Auth', '$location', function($http, $q, NORDMART_CONFIG, $auth, $location) {
	var factory = {}, products, baseUrl;

	if ($location.protocol() === 'https') {
		baseUrl = (NORDMART_CONFIG.SECURE_API_ENDPOINT.startsWith("https://") ? NORDMART_CONFIG.SECURE_API_ENDPOINT : "https://" + NORDMART_CONFIG.SECURE_API_ENDPOINT + '.' + $location.host().replace(/^.*?\.(.*)/g,"$1")) + '/api/products';
	} else {
		baseUrl = (NORDMART_CONFIG.API_ENDPOINT.startsWith("http://") ? NORDMART_CONFIG.API_ENDPOINT : "http://" + NORDMART_CONFIG.API_ENDPOINT + '.' + $location.host().replace(/^.*?\.(.*)/g,"$1")) + '/api/products';
	}

	factory.getProducts = function() {
		var deferred = $q.defer();
        if (products) {
            deferred.resolve(products);
        } else {
            $http({
                method: 'GET',
								url: baseUrl
            }).then(function(resp) {
                products = resp.data;
                deferred.resolve(resp.data);
            }, function(err) {
                deferred.reject(err);
            });
        }
	   return deferred.promise;
	};

	return factory;
}]);
