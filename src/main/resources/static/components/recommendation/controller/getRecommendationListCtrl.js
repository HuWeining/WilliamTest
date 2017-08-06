app.controller('getRecommendationListCtrl', [ '$scope', 'recommendationService','$stomp', function($scope, recommendationService, $stomp) {

    getGuestList = function(){
        var params = {
        };
        guestService.getGuestList(params).then(function(res){
			if(res !== undefined){
				if (res.status === 200 && res.data.success){
					$scope.guestList = res.data.data;
                    notify("Login successfully",'success', true);
				}else {
                    notify("Login failure",'danger', true);
				}
			}
		});
	};

    init = function() {
        getGuestList();
    };

    init();

    $stomp.connect('http://localhost:8080/stomp', {}).then(function(frame) {
        $stomp.subscribe('/guest/update', function(payload, headers, res) {
            notify("Update successfully",'success', true);
            getGuestList();
        }, {});
    });
} ]);