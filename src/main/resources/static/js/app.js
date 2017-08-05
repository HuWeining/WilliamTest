
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
}]);


