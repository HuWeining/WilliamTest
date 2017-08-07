app.controller('buildPlanDetailCtrl', [ '$scope', 'recommendationService','$stomp', '$uibModal','$state'
    ,function($scope, recommendationService, $stomp, $uibModal,$state) {


    $scope.buildPlan  = function(){
        $scope.selectData = [];
        $scope.travelResourceItemIds = [];
        $scope.travelSiteIds = [];
        angular.forEach($scope.travelResourceItem, function(item) {
            if(item.checked){
                $scope.selectData[$scope.selectData.length] = item;
                $scope.travelResourceItemIds[$scope.travelResourceItemIds.length] = item.id;
                $scope.travelSiteIds[$scope.travelSiteIds.length] = item.travelSiteId;
            }
        });
        var param = {
            "travelResourceItemIdsJson": angular.toJson($scope.travelResourceItemIds),
            "travelSiteIdsJson": angular.toJson($scope.travelSiteIds)
        }

        recommendationService.buildTempTravelPlan(param).then(function(res){
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

    getTravelResourceItems = function(){
        $scope.travelResourceItem = recommendationService.getTravelResourceItems();
	};

    init = function() {
        notify("Get binding travel resource item successfully",'success', true);
        getTravelResourceItems();
    };

    init();
} ]);