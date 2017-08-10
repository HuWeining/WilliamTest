app.controller('findTravelPlanByTagsCtrl', [ '$scope', 'recommendationService','$stomp', function($scope, recommendationService, $stomp) {

    $scope.areas = [
        {id : 1, name : 'Japen'},
        {id : 2, name : 'SE Asia'},
        {id : 4, name : 'China'},
        {id : 8, name : 'Europe'},
        {id : 16, name : 'America'},
        {id : 32, name : 'Australia'},
        {id : 64, name : 'Africa'}
    ] ;
    $scope.scenes = [
        {id : 1, name : 'island'},
        {id : 2, name : 'mountain'},
        {id : 4, name : 'prairie'},
        {id : 8, name : 'sea/Ocean'},
        {id : 16, name : 'desert'},
        {id : 32, name : 'urban'},
        {id : 64, name : 'remote area'}
    ] ;
    $scope.seasons = [
        {id : 1, name : 'spring'},
        {id : 2, name : 'summer'},
        {id : 4, name : 'autumn'},
        {id : 8, name : 'winter'}
    ] ;
    $scope.suitAges = [
        {id : 1, name : 'child'},
        {id : 2, name : 'young'},
        {id : 4, name : 'middle-aged'},
        {id : 8, name : 'elderly'}
    ] ;
    $scope.categorys = [
        {id : 1, name : 'historical'},
        {id : 2, name : 'museum'},
        {id : 4, name : 'religion'},
        {id : 8, name : 'festival'},
        {id : 16, name : 'nature'},
        {id : 32, name : 'animal'},
        {id : 64, name : 'building'}
    ] ;

    calSum = function(list){
        var sum = 0;
        for (var i = 0; i < list.length; i++){
            sum += list[i];
        }
        return sum;
    }

    $scope.searchTravelPlan = function(){

        var param = {
            "area": calSum($scope.selectedArea),
            "scene": calSum($scope.selectedScene),
            "season": calSum($scope.selectedSeason),
            "suitAge": calSum($scope.selectedSuitAge),
            "category": calSum($scope.selectedCategory)
        }

        recommendationService.findTravelPlanByTags(param).then(function(res){
            if(res !== undefined){
                if (res.status === 200 && res.data.success){
                    $scope.travelPlans = res.data.data;
                }else {
                    notify("Get binding travel resource item unsuccessfully",'danger', true);
                }
            }
        });
    };

    $scope.selectedArea = [] ;
    $scope.selectedScene = [] ;
    $scope.selectedSeason = [] ;
    $scope.selectedSuitAge = [] ;
    $scope.selectedCategory = [] ;

    $scope.isCheckedArea = function(id){
        return $scope.selectedArea.indexOf(id) >= 0 ;
    } ;

    $scope.isCheckedScene = function(id){
        return $scope.selectedScene.indexOf(id) >= 0 ;
    } ;
    $scope.isCheckedSeason = function(id){
        return $scope.selectedSeason.indexOf(id) >= 0 ;
    } ;
    $scope.isCheckedSuitAge = function(id){
        return $scope.selectedSuitAge.indexOf(id) >= 0 ;
    } ;
    $scope.isCheckedCategory = function(id){
        return $scope.selectedCategory.indexOf(id) >= 0 ;
    } ;

    $scope.updateSelectionArea = function($event,id){
        var checkbox = $event.target ;
        var checked = checkbox.checked ;
        if(checked){
            $scope.selectedArea.push(id) ;
        }else{
            var idx = $scope.selectedArea.indexOf(id) ;
            $scope.selectedArea.splice(idx,1) ;
        }
    } ;

    $scope.updateSelectionScene = function($event,id){
        var checkbox = $event.target ;
        var checked = checkbox.checked ;
        if(checked){
            $scope.selectedScene.push(id) ;
        }else{
            var idx = $scope.selectedScene.indexOf(id) ;
            $scope.selectedScene.splice(idx,1) ;
        }
    } ;

    $scope.updateSelectionSeason = function($event,id){
        var checkbox = $event.target ;
        var checked = checkbox.checked ;
        if(checked){
            $scope.selectedSeason.push(id) ;
        }else{
            var idx = $scope.selectedSeason.indexOf(id) ;
            $scope.selectedSeason.splice(idx,1) ;
        }
    } ;

    $scope.updateSelectionSuitAge = function($event,id){
        var checkbox = $event.target ;
        var checked = checkbox.checked ;
        if(checked){
            $scope.selectedSuitAge.push(id) ;
        }else{
            var idx = $scope.selectedSuitAge.indexOf(id) ;
            $scope.selectedSuitAge.splice(idx,1) ;
        }
    } ;

    $scope.updateSelectionCategory = function($event,id){
        var checkbox = $event.target ;
        var checked = checkbox.checked ;
        if(checked){
            $scope.selectedCategory.push(id) ;
        }else{
            var idx = $scope.selectedCategory.indexOf(id) ;
            $scope.selectedCategory.splice(idx,1) ;
        }
    } ;

    init = function() {
    };

    init();


} ]);