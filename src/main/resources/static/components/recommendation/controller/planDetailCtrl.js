app.controller('planDetailCtrl', [ '$scope', 'recommendationService','$stomp', '$uibModal','$state'
    ,function($scope, recommendationService, $stomp, $uibModal,$state) {

    getTempTravelPlan = function(){
        $scope.tempTravelPlan = angular.fromJson(recommendationService.getTempTravelPlan());
    };
    init = function() {
        for (var i = 0; i < $scope.chosenPlan.travelSiteAndTravelResourceItemLists.length; i++){
            var oneSiteAndItems = $scope.chosenPlan.travelSiteAndTravelResourceItemLists[i];
            setShowTags(oneSiteAndItems.travelResourceItemVOList);
        }
    };

        setShowTags = function(travelResourceItems){
            for (var i = 0; i < travelResourceItems.length; i++){
                var travelResourceItem = travelResourceItems[i];
                var area = "";
                var scene = "";
                var season = "";
                var suitAge = "";
                var category = "";
                for (var j = 0; j < travelResourceItem.area.length; j++){
                    area = area+travelResourceItem.area[j] + ",";
                }
                for (var j = 0; j < travelResourceItem.scene.length; j++){
                    scene = scene+travelResourceItem.scene[j] + ",";
                }
                for (var j = 0; j < travelResourceItem.season.length; j++){
                    season = season+travelResourceItem.season[j] + ",";
                }
                for (var j = 0; j < travelResourceItem.suitAge.length; j++){
                    suitAge = suitAge+travelResourceItem.suitAge[j] + ",";
                }
                for (var j = 0; j < travelResourceItem.category.length; j++){
                    category = category+travelResourceItem.category[j] + ",";
                }
                travelResourceItem.areaString = area.substring(0, area.length-1);
                travelResourceItem.sceneString = scene.substring(0, scene.length-1);
                travelResourceItem.seasonString = season.substring(0, season.length-1);
                travelResourceItem.suitAgeString = suitAge.substring(0, suitAge.length-1);
                travelResourceItem.categoryString = category.substring(0, category.length-1);
            }
        }

    init();
} ]);