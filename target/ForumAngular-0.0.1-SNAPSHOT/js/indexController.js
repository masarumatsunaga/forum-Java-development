(function() {
	let topApp = angular.module('top', ['ngResource', 'ngRoute']);
	// routing
	topApp.config(function($routeProvider, $locationProvider) {
		$locationProvider.hashPrefix('');
		$routeProvider
			.when('/comment/:id', {
				templateUrl: 'comment.html',
				controller: 'GetlistController'
			})
			.when('/', {
				templateUrl: 'list.html',
				controller: 'listController',
			})
			.otherwise({ redirectTo: '/' });
	});

	topApp
		.controller('GetlistController',
			function($scope, $resource, $routeParams, $window) {
				let com = $resource('forum/comment/' + $routeParams.id);
				// GET
				$scope.commentlist = com.query();
				$scope.newComment = {
					topic_id : $routeParams.id
				}

				// DELETE
				$scope.remove = function(comment_id) {
					if ($window.confirm(comment_id + "番を削除していいですか？")) {
						com.remove({ comment_id });
						$window.location.reload();
					}
				}

				// POST
				$scope.save = function() {
					let comment = $resource('forum/comment');
					$scope.newComment.topic_id = $routeParams.id;
					$scope.newComment.date = new Date($scope.newComment.date).getTime();
					if ($window.confirm("コメントを投稿しますか？")) {
						comment.save($scope.newComment, function() {
							$window.location.reload();
						})
					}
				}
			})
		.controller('listController', function($scope, $resource, $window) {
			// GET
			let forum = $resource('forum/topic/:topicid');
			$scope.list = forum.query({});
			// POST
			$scope.save = function() {
				if ($window.confirm('新規追加してよろしいですか？')) {
					forum.save($scope.newObj, function() {
						$window.location.reload();
					});
				}
			};
			// search
			$scope.searchBtn = function() {
				let forum = $resource('forum/topic/:topicid',
					{ topicid: $scope.topicid }
				);
				// 表示制御
				$scope.show = true;
				$scope.listshow = true;
				$scope.target = forum.get();
			}
		});
})();

