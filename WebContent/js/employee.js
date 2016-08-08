index
		.controller(
				'guestController',
				function($scope, $http, $filter, $window,employeeService) {
					console.log("Entered into the guestcontroller loop");

					/*
					 * $scope.update = function() { $scope.credentials.push({
					 * "username" : "def", "password" : "deghx" });
					 * console.log($scope.credentials);
					 * $http.put('../json/credentials.json' +
					 * $scope.credentials); };
					 */
					$scope.submitform = function(employee) {
						employeeService.AddEmployeeToDB(employee);
						$window
								.alert("You have successfully requested for employee to get into odc");

					};
					$scope.cancelForm = function() {
						$window.location.href = "#guest";
					};
					$scope.guestSearch = function() {
						console.log("u entered into guestSearch method");
						$http.get("../json/employee.json").then(
								function(response) {
									console.log("entered into then function");
									$scope.employees = response.data;
									console.log("response.data :"
											+ JSON.stringify(response.data));
									$scope.employees2 = $scope.employees;

									$scope.$watch('search', function(val) {
										$scope.employees = $filter('filter')(
												$scope.employees2, val);
									});

								});

					};

				});
index.factory("employeeService", [ '$http', function($http) {
	var fac = {};
	fac.AddEmployeeToDB = function(employee) {
		$http.post("../guest/employee/45",employee).success(function(response){
			alert(response.status);
		})

	}
	return fac;
} ]);
index.controller('showCtrl', function($scope) {
	$scope.IsHidden = true;
	$scope.ShowHide = function() {
		//If DIV is hidden it will be visible and vice versa.
		$scope.IsHidden = $scope.IsHidden ? false : true;
	}
});