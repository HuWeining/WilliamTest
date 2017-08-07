
var app = angular.module('springBootDemo', ['ui.router', 'ngRoute','ui.bootstrap', 'ngStomp']);

app.config(function($controllerProvider) {
	'use strict';

	app.registerController = function(name, constructor) {
		$controllerProvider.register(name, constructor);
		return this;
	};
});


app.config([ '$urlRouterProvider', '$stateProvider', function($urlRouterProvider, $stateProvider) {

    $urlRouterProvider.otherwise('/login');
    $stateProvider
		.state('homePage',{
			url: '/',
			template: 'components/login/login.html',
		})
		.state('login', {
			url: '/login',
			templateUrl: 'components/login/login.html',
		})
        .state('guest', {
            url: '/guest',
            templateUrl: 'components/guest/guestList.html',
        })
        .state('recommendation_buildTempTravelPlan', {
            url: '/recommendation/buildTempTravelPlan',
            templateUrl: 'components/recommendation/buildTempTravelPlan.html',
        })
        .state('recommendation_findTravelPlanByTags', {
            url: '/recommendation/findTravelPlanByTags',
            templateUrl: 'components/recommendation/findTravelPlanByTags.html',
        })
        .state('recommendation_findTravelResourceItemByTravelSiteId', {
            url: '/recommendation/findTravelResourceItemByTravelSiteId',
            templateUrl: 'components/recommendation/findTravelResourceItemByTravelSiteId.html',
        })
        .state('recommendation_getRecommendationList', {
            url: '/recommendation/getRecommendationList',
            templateUrl: 'components/recommendation/getRecommendationList.html',
        })
        .state('recommendation_buildPlanDetail', {
            url: '/recommendation/buildPlanDetail',
            templateUrl: 'components/recommendation/buildPlanDetail.html',
        })
}]);


