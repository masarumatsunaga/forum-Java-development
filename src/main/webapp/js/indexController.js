(function () {
  let topApp = angular.module('top', ['ngResource', 'ngRoute']);

  // ルーティングの設定
  topApp.config(function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider
      .when('/search/:topicid', {
        templateUrl: 'search.html',
        controller: 'searchController',
      })
      .when('/', {
        templateUrl: 'list.html',
        controller: 'saveController',
      })
      .when('/', {
        templateUrl: 'list.html',
        controller: 'listController',
      })
      .otherwise({ redirectTo: '/' });
  });

  topApp
    .controller('saveController', [
      '$scope',
      '$resource',
      '$location',
      '$log',
      function ($scope, $resource, $location, $log) {
        // POST
        let forum = $resource('forum/topic');
        $scope.save = function () {
          if (window.confirm('新規追加してよろしいですか？')) {
            forum.save($scope.newObj, function () {
              console.log($scope);
              $scope.list = forum.query();
              $log.log($location.path());
              $location.path('/');
            });
          }
          $location.path('/');
        };
      },
    ])
    .controller('searchController', function ($scope, $resource, $location) {
      $scope.searchBtn = function () {
        let forum = $resource('forum/topic/:topicid', {
          topicid: $scope.topicid,
        });
        console.log('hello');
        $scope.target = forum.get();
        console.log($scope);
        $location.path('/search/$id');
      };
    })
    .controller('listController', function ($scope, $resource) {
      // GET
      let forum = $resource('forum/topic');
      $scope.list = forum.query();
    });
})();
