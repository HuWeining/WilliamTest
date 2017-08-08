app.controller('buildPlanDetailCtrl', [ '$scope', 'recommendationService','$stomp', '$uibModal','$state'
    ,function($scope, recommendationService, $stomp, $uibModal,$state) {

    getTempTravelPlan = function(){
        $scope.tempTravelPlan = angular.fromJson(recommendationService.getTempTravelPlan());
    };

    init = function() {
        getTempTravelPlan();
        notify($scope.tempTravelPlan, 'success', false);
    };

    init();
} ]);