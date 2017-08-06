app.factory('recommendationService', ['$http', function($http){
	var recommendationService = {};

	var _getAllTravelSite = function(params){
		var promise = $http({
			method : 'GET',
			url : '/travelResource/findAllTravelSite',
            params : params
		}).then(function(response) {
			return response;
		}, function errorCallback(response) {
			notifyError(response);
		});
		return promise;
	};

    var _findTravelResourceItemByTravelSiteId = function(params){
        var promise = $http({
            method : 'POST',
            url : '/travelResource/findTravelResourceItemByTravelSiteId',
            params : params
        }).then(function(response) {
            return response;
        }, function errorCallback(response) {
            notifyError(response);
        });
        return promise;
    };



    recommendationService.getAllTravelSite = _getAllTravelSite;
    recommendationService.findTravelResourceItemByTravelSiteId = _findTravelResourceItemByTravelSiteId;
	return recommendationService;
}]);