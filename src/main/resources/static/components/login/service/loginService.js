app.factory('loginService', ['$http', function($http){
	var loginService = {};

	var _login = function(params){
		var promise = $http({
			method : 'POST',
			url : '/login/loginAdmin',
			params : params
		}).then(function(response) {
			return response;
		}, function errorCallback(response) {
			notifyError(response);
		});
		return promise;
	};

    loginService.login = _login;

	return loginService;
}]);