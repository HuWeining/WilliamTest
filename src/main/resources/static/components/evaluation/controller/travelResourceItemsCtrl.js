app.controller('travelResourceItemsCtrl', [ '$scope', 'evaluationService','$stomp', '$uibModal','$state'
    ,function($scope, evaluationService, $stomp, $uibModal,$state) {

    $scope.travelResourceItem;

    getTravelResourceItems = function(){
        var param = {};
        evaluationService.getTravelResourceItems(param).then(function(res){
            if(res !== undefined){
                if (res.status === 200 && res.data.success){
                    $scope.travelResourceItem = res.data.data;
                    setShowTags($scope.travelResourceItem);
                }else {
                    notify("Get binding travel resource item unsuccessfully",'danger', true);
                }
            }
        });
	};

        setShowTags = function(travelPlans){
            for (var i = 0; i < travelPlans.length; i++){
                var travelPlan = travelPlans[i];
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
        }

    init = function() {
        notify("Get binding travel resource item successfully",'success', true);
        getTravelResourceItems();
    };

    init();
} ]);