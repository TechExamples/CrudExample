index.controller('LoginCtrl', function($scope, $http,$window) {
		console.log("Entered into the controller loop");

		/*
		 * $scope.update = function() { $scope.credentials.push({ "username" :
		 * "def", "password" : "deghx" }); console.log($scope.credentials);
		 * $http.put('../json/credentials.json' + $scope.credentials); };
		 */
		$scope.validate = function(username, password) {
			console.log("u entered into validate method");
			console.log("test user:" + username);
			console.log("test password:" + password);
			$http.get("../json/credentials.json").then(
					function(response) {
						console.log("entered into then function");
						$scope.credentials = response.data;
						console.log("response.data :"
								+ JSON.stringify(response.data));
						if (username == "" || password == "") {
							$scope.message = "please enter credentials";
						} else {
							angular.forEach($scope.credentials, function(
									credential, index) {
								console.log(credential.username);
								console.log(credential.password);
								if (credential.username == username
										&& credential.password == password) {
									console.log("valid user");
									$window.location.href = "#adminDashboard";
								} else {
									console.log("invalid user");
									$scope.message = "Error : invalid user";
								}

							});
						}
					});

		};

	});