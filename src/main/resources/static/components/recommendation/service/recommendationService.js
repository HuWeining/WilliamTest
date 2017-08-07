app.factory('recommendationService', ['$http', function($http){
	var recommendationService = {};

	var travelResourceItemStore;

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

    var _putTravelResourceItems = function(travelResourceItem){
        travelResourceItemStore = travelResourceItem;
    }

    var _getTravelResourceItems = function(travelResourceItem){
        return travelResourceItemStore;
    }

    var _buildTempTravelPlan = function(params){
        var promise = $http({
            method : 'POST',
            url : '/recommendation/buildTempTravelPlan',
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
    recommendationService.putTravelResourceItems = _putTravelResourceItems;
    recommendationService.getTravelResourceItems = _getTravelResourceItems;
    recommendationService.buildTempTravelPlan = _buildTempTravelPlan;
	return recommendationService;
}]);