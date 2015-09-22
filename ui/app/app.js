'use strict';

// Declare app level module which depends on views, and components
angular.module('meetupApp', [
  'ngRoute',
  'meetupApp.users',
  'users-api'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/users'});
}]);
