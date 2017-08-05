app.factory('guestService', ['$http', function($http){
	var loginService = {};

	var _getGuestList = function(params){
		var promise = $http({
			method : 'GET',
			url : '/guest/guestList',
            params : params
		}).then(function(response) {
			return response;
		}, function errorCallback(response) {
			notifyError(response);
		});
		return promise;
	};

    loginService.getGuestList = _getGuestList;

	return loginService;
}]);