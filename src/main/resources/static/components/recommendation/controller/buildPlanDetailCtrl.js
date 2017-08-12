app.controller('buildPlanDetailCtrl', [ '$scope', 'recommendationService','$stomp', '$uibModal','$state'
    ,function($scope, recommendationService, $stomp, $uibModal,$state) {

    getTempTravelPlan = function(){
        $scope.tempTravelPlan = angular.fromJson(recommendationService.getTempTravelPlan());
    };

    init = function() {
        getTempTravelPlan();
        setShowTags($scope.tempTravelPlan);
        // notify($scope.tempTravelPlan, 'success', false);
    };

        setShowTags = function(travelPlan){
            var area = "";
            var scene = "";
            var season = "";
            var suitAge = "";
            var category = "";
            for (var j = 0; j < travelPlan.area.length; j++){
                area = area+travelPlan.area[j] + ",";
            }
            for (var j = 0; j < travelPlan.scene.length; j++){
                scene = scene+travelPlan.scene[j] + ",";
            }
            for (var j = 0; j < travelPlan.season.length; j++){
                season = season+travelPlan.season[j] + ",";
            }
            for (var j = 0; j < travelPlan.suitAge.length; j++){
                suitAge = suitAge+travelPlan.suitAge[j] + ",";
            }
            for (var j = 0; j < travelPlan.category.length; j++){
                category = category+travelPlan.category[j] + ",";
            }
            travelPlan.areaString = area.substring(0, area.length-1);
            travelPlan.sceneString = scene.substring(0, scene.length-1);
            travelPlan.seasonString = season.substring(0, season.length-1);
            travelPlan.suitAgeString = suitAge.substring(0, suitAge.length-1);
            travelPlan.categoryString = category.substring(0, category.length-1);
        }

    init();
} ]);