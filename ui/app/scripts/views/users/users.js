'use strict';

angular.module('meetupApp.users', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/users', {
    templateUrl: 'scripts/views/users/users.html',
    controller: 'UsersCtrl'
  });
}])

.controller('UsersCtrl', ['$scope', 'RegisteredUserApi', function($scope, registeredUserApi) {

  registeredUserApi.query().success(function(response){
    $scope.users = response.content;
    $scope.page = response.page;
  });

}]);
