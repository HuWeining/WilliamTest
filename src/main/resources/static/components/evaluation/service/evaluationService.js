app.factory('evaluationService', ['$http', function($http){
	var evaluationService = {};

	var _getTravelResourceItems = function(params){
		var promise = $http({
			method : 'GET',
			url : '/travelResource/findAllTravelResourceItems',
            params : params
		}).then(function(response) {
			return response;
		}, function errorCallback(response) {
			notifyError(response);
		});
		return promise;
	};



    var _findTravelPlanByTags = function (params) {
        var promise = $http({
            method : 'POST',
            url : '/recommendation/findTravelPlanByTags',
            params : params
        }).then(function(response) {
            return response;
        }, function errorCallback(response) {
            notifyError(response);
        });
        return promise;
    }


    evaluationService.getTravelResourceItems = _getTravelResourceItems;

	return evaluationService;
}]);