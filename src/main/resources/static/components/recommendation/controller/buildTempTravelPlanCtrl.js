app.controller('buildTempTravelPlanCtrl', [ '$scope', 'recommendationService','$stomp', '$uibModal',function($scope, recommendationService, $stomp, $uibModal) {

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
                    notify("Get binding travel resource item successfully",'success', true);
                }else {
                    notify("Get binding travel resource item unsuccessfully",'danger', true);
                }
            }
        });

        var uibModalInstance = $uibModal.open({
            templateUrl : 'components/recommendation/buildPlanDetail.html',
            controller : 'buildTempTravelPlanCtrl',
            scope : $scope,
            size : 'lg',
            backdrop : true,
            dialogFade : true,
            backdropFade : false,
            // resolve: {
            //     items: function () {
            //         $scope.row = row;
            //         return $scope.row;
            //     }
            // }
        });
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