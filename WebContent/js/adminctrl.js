var admin = angular.module('admin', [ 'ngAnimate' ]);
admin.controller('adminController',['$scope', '$http', '$filter', function($scope, $http, $filter) {

 	$scope.tabs = [ {
 		title : 'Pending requests',
 		url : 'one.tpl.html',
 		count:'countOne'
 	}, {
 		title : 'incomplete compliances',
 		url : 'two.tpl.html',
 			count:'countTwo'
 	}, {
 		title : 'Actions',
 		url : 'three.tpl.html'
 	} ];

 	$scope.currentTab = 'one.tpl.html';

 	$scope.onClickTab = function(tab) {
 		$scope.currentTab = tab.url;
 	};

 	$scope.isActiveTab = function(tabUrl) {
 		return tabUrl == $scope.currentTab;
 	};
 	$scope.FromDate = new Date();
 	$scope.duedate = $scope.FromDate.setDate($scope.FromDate.getDate() + 14); 
 	$scope.countOne=0;
 	$scope.countTwo=0;
 	$http.get("../admin/employees.json").then(function(response) {
 		console.log("entered into then function");
 		
 		$scope.employeesList = response.data;
 		console.log(JSON.stringify(response.data));
 		console.log($scope.employeesList);
 		//$scope.count = Object.keys($scope.employeesList).length;
 		angular.forEach($scope.employeesList, function(
				employee, index) {
 			
 	 		//$scope.count = Object.keys($scope
// 			console.log(employee.ReqStatus);
// 		if(employee.ReqStatus == 0){
// 			$scope.countOne=$scope.countOne+1;
// 			
// 		}else if(employee.ReqStatus == 1){
// 			$scope.countTwo=$scope.countTwo+1;
// 		}
//
 		});
 		console.log("response.data :" + JSON.stringify(response.data));
 	});
 	$scope.adminSearch = function() {
 		console.log("u entered into adminsearch method");
 		$http.get("../admin/employees.json").then(
 				function(response) {
 					console.log("entered into then function");
 					$scope.employees = response.data;
 					console.log("response.dataadmincontroller :"
 							+ JSON.stringify(response.data));
 					$scope.employees2 = $scope.employees;

 					$scope.$watch('search', function(val) {
 						$scope.employees = $filter('filter')($scope.employees2,
 								val);
 					});
 				});

 	};
 	 $scope.personRemove = function(index) {
         $scope.employees.splice(index, 1);
     };
 }]);
admin.controller('showCtrl', function($scope) {
 	$scope.IsHidden = true;
 	$scope.ShowHide = function() {
 		//If DIV is hidden it will be visible and vice versa.
 		$scope.IsHidden = $scope.IsHidden ? false : true;
 	}
 });