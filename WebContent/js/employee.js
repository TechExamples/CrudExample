 index.controller('guestController', function($scope, $http, $filter,$window) {
		console.log("Entered into the guestcontroller loop");

		/*
		 * $scope.update = function() { $scope.credentials.push({ "username" :
		 * "def", "password" : "deghx" }); console.log($scope.credentials);
		 * $http.put('../json/credentials.json' + $scope.credentials); };
		 */
		$scope.addEmployee = function() {
			console.log("entered into addemployee button click");
			$http.get("../json/employee.json").then(function(response) {
				$scope.employees = response.data;
				console.log("response.data :" + JSON.stringify(response.data));
				$scope.employees.push({
					"Name": "roopa",
					"EmpId": "1596",
					"Managerid": "54564",
					"mobilenumber": "9874654964",
					"status": "done",
					"comment": "submitted"
				});
				$scope.employees.push({
					"Name": "roopa",
					"EmpId": "1596",
					"Managerid": "54564",
					"mobilenumber": "9874654964",
					"status": "done",
					"comment": "submitted"
				});
				console.log($scope.employees);
				//$http.put('file:///E:/StudyPractices/ploadedfiles/employee.json' + $scope.employees);
				
				        $window.alert("You have successfully requested for employee to get into odc");
				      

			});

		};
		$scope.cancelForm=function(){
			$window.location.href = "#guest";
		};
		$scope.guestSearch = function() {
			console.log("u entered into guestSearch method");
			$http.get("../json/employee.json").then(function(response) {
				console.log("entered into then function");
				$scope.employees = response.data;
				console.log("response.data :" + JSON.stringify(response.data));
				$scope.employees2 = $scope.employees;

				$scope.$watch('search', function(val) {
					$scope.employees = $filter('filter')($scope.employees2, val);
				});

			});

		};

	});

 index.controller('showCtrl', function($scope) {
 	$scope.IsHidden = true;
 	$scope.ShowHide = function() {
 		//If DIV is hidden it will be visible and vice versa.
 		$scope.IsHidden = $scope.IsHidden ? false : true;
 	}
 });