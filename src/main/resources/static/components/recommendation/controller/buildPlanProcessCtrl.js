app.controller('buildPlanProcessCtrl', [ '$scope', 'recommendationService','$stomp', '$uibModal','$state'
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
                    $scope.tempTravelPlan = res.data.data;
                    recommendationService.putTempTravelPlan($scope.tempTravelPlan);
                    openTravelProductetailModel();
                }else {
                    notify("Get binding travel resource item unsuccessfully",'danger', true);
                }
            }
        });
    };

    openTravelProductetailModel = function() {
        var uibModalInstance = $uibModal.open({
            templateUrl : 'components/recommendation/buildPlanDetail.html',
            controller : 'buildPlanDetailCtrl',
            scope : $scope,
            size : 'lg',
            backdrop : true,
            dialogFade : true,
            backdropFade : false,
            // resolve: {
            //     items: function () {
            //         $scope.tempTravelPlan = tempTravelPlan;
            //         return $scope.tempTravelPlan;
            //     }
            // }
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