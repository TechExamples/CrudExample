<!DOCTYPE html>
<html ng-app="index">
<head>
<meta charset="ISO-8859-1">
<title>ODC Tool</title>
<link rel="stylesheet" href="../css/style.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js">
	
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-route.js">
	
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-animate.js">
	
</script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.4.min.js">
	
</script>
<script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0-rc.2/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0-rc.2/angular-route.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0-rc.2/angular-animate.min.js"></script> -->

</head>


<body ng-controller="indexcontroller">
	<div id="header" style="color: white">
		<!-- <img src="../images/infyicon.png" -->
		<img src=""
			style="width: 100px; height: 50px; padding-left: 40px;">
	</div>
	<div id="viewArea">
		<div>

			<div ng-view></div>
		</div>
	</div>
	<footer id="footer">
		<span style="color: white">infosys</span> <span id="searchNdHome" style="color: white">Copyright &copy;<a
			href="#copyright-popup" ng-click="copyright()">2016</a>Infosys
			Limited.
		</span>
	</footer>
	<script>
		var index = angular.module('index', [ 'ngRoute', 'ngAnimate' ]);
		index.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/main', {
				templateUrl : 'mainPage.jsp'
			}).when('/admin', {
				templateUrl : 'Login.html',
				controller : 'LoginCtrl'
			}).

			when('/guest', {
				templateUrl : 'Guest.html',
				controller : 'guestController'
			}).

			when('/adminDashboard', {
				templateUrl : 'AdminDashboard.html',
				controller : 'adminController'
			}).

			otherwise({
				redirectTo : '/main'
			});
		} ]);
		index
				.controller(
						'indexcontroller',
						function($scope, $window) {
							$scope.copyright = function() {
								$window
										.alert("bution of this Program, or any portion of it, may result in severe civil and criminal penalties, and will be prosecuted to the maximum extent possible under the law.");
							};

						});
	</script>
	<script type="text/javascript" src="../js/controllers.js"></script>
	<script type="text/javascript" src="../js/employee.js"></script>
	<script type="text/javascript" src="../js/adminctrl.js"></script>
	<script type="text/javascript" src="../js/mojillaDate.js"></script>

</body>
</html>