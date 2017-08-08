app.controller('buildTempTravelPlanCtrl', [ '$scope', 'recommendationService','$stomp', '$uibModal','$state'
    ,function($scope, recommendationService, $stomp, $uibModal,$state) {

    getAllTravelSite = function(){
        var params = {
        };
        recommendationService.getAllTravelSite(params).then(function(res){
			if(res !== undefined){
				if (res.status === 200 && res.data.success){
					$scope.travelSiteList = res.data.data;
                    notify("Get all travelSite successfully",'success', true);
				}else {
                    notify("Login failure",'danger', true);
				}
			}
		});
	};

    $scope.openTravelPlanBuildBox = function(){

        $scope.selectData = [];
        $scope.travelSiteIds = [];
        angular.forEach($scope.travelSiteList, function(item) {
            if(item.checked){
                $scope.selectData[$scope.selectData.length] = item;
                $scope.travelSiteIds[$scope.travelSiteIds.length] = item.id;
            }
        });
        var param = {
            "travelSiteIdsJson": angular.toJson($scope.travelSiteIds)
        }

        recommendationService.findTravelResourceItemByTravelSiteId(param).then(function(res){
            if(res !== undefined){
                if (res.status === 200 && res.data.success){
                    $scope.travelResourceItem = res.data.data;
                    recommendationService.putTravelResourceItems($scope.travelResourceItem);
                    toBuildPlanDetail();
                }else {
                    notify("Get binding travel resource item unsuccessfully",'danger', true);
                }
            }
        });
    };

    toBuildPlanDetail = function () {
        $state.go('recommendation_buildPlanProcess');
    };

    init = function() {
        getAllTravelSite();
    };

    init();

    $stomp.connect('http://localhost:8080/stomp', {}).then(function(frame) {
        $stomp.subscribe('/guest/update', function(payload, headers, res) {
            notify("Update successfully",'success', true);
            getGuestList();
        }, {});
    });
} ]);