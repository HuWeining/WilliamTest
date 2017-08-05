/*global app, workflowUrl $*/
app.controller('loginCtrl', [ '$scope', 'loginService', function($scope, loginService) {

    $scope.login = function(){
		var params = {
		    'username': $scope.username,
		    'password': $scope.password
		};
        loginService.login(params).then(function(res){
			if(res !== undefined){
				if (res.status === 200 && res.data.success) {
					// var result = res.data.data;
					$scope.loginSuccess = 'true';
                    notify("Login successfully",'success', true);
				}else {
                    $scope.loginSuccess = 'false';
                    notify("Login failure",'danger', true);
				}
			}
		});
	};

    init = function() {

    };
} ]);