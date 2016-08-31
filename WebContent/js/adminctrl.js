var admin = angular.module('admin', [ 'ngAnimate' ]);
admin.controller('adminController', [
		'$scope',
		'$http',
		'$filter','fileUpload',
		function($scope, $http, $filter, fileUpload) {

			$scope.tabs = [ {
				title : 'Pending requests',
				url : 'one.tpl.html',
				count : 'countOne'
			}, {
				title : 'incomplete compliances',
				url : 'two.tpl.html',
				count : 'countTwo'
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
			$scope.duedate = $scope.FromDate
					.setDate($scope.FromDate.getDate() + 14);
			$scope.countOne = 0;
			$scope.countTwo = 0;
			
			$http.get("../admin/privileges.json").then(
				function(response) {
					$scope.privileges= response.data;
				}
			);
			
			$http.get("../admin/employees.json").then(
					function(response) {
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
						console.log("response.data :"
								+ JSON.stringify(response.data));
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
								$scope.employees = $filter('filter')(
										$scope.employees2, val);
							});
						});

			};
			$scope.approve = function(id,employee) {
				employee.status = 1;
				var approveURL = "../admin/employee/"+id+".json";
				$http.put(approveURL, employee).then(
						function(response) {
							});

			};
			
		/*	$scope.upload = function(id) {
				var file = $scope.files;
				console.log('file is ' );
				console.dir(file);
				var uploadUrl = '/admin/1001/file';
				fileUpload.uploadFileToUrl(file, uploadUrl);
				};
				var fd = new FormData();
				angular.forEach($scope.files, function(file) {
					fd.append('files', file)
					alert("data inserting");
				})
				//fd.append('files', file);
				//fd.append('filenames',filename);
				var uploadUrl = '../admin/'+id+'/file.json';
				$http.post(uploadUrl, fd, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type': undefined,
						enctype:'multipart/form-data'
					}
				}).success(function() {
				}).error(function() {
				});
			}*/
			$scope.personRemove = function(index) {
				$scope.employees.splice(index, 1);
			};

		} ]);
admin.controller('showCtrl', function($scope,fileUpload) {
	$scope.IsHidden = true;
	$scope.ShowHide = function() {
		//If DIV is hidden it will be visible and vice versa.
		$scope.IsHidden = $scope.IsHidden ? false : true;
	};
	 $scope.uploadFile = function(){
	       var file = $scope.myFile;
	       console.log('test is '+$scope.test1);
	       console.log('file is ' +file);
	       console.dir(file);
	       var uploadUrl = '../admin/1001/file.json';
	       fileUpload.uploadFileToUrl(file, uploadUrl);
	    };
	
});

/*admin.directive('fileInput', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {

			element.bind('change', function() {
				scope.$apply(function() {
					$parse(attrs.fileInput).assign(scope, element[0].files)
					scope.$apply()
				});
			});
		}
	};
} ]);*/

/*admin.service('fileUpload', [ '$http', function($http) {
	this.uploadFileToUrl = function(file, uploadUrl) {
		var fd = new FormData();
		angular.forEach($scope.files, function(file) {
			fd.append('files', file)
		})
		//fd.append('files', file);
		//fd.append('filenames',filename);
		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(function() {
		}).error(function() {
		});
	}
} ]);*/
admin.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);

admin.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
       var fd = new FormData();
       fd.append('files', file);
       console.log('In fileUpload service');
       console.dir(file);
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined,
				enctype:'multipart/form-data'}
       })
    
       .success(function(){
       })
    
       .error(function(){
       });
    }
 }]);

	
