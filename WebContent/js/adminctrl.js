var admin = angular.module('admin', [ 'ngAnimate' ]);
admin.controller('adminController', [
		'$scope',
		'$http',
		'$filter',
		'fileUpload',
		'genUtils',
		function($scope, $http, $filter, fileUpload, genUtils) {

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

			$http.get("../admin/privileges.json").then(function(response) {
				$scope.privileges = response.data;
			});

			$http.get("../admin/employees.json").then(
					function(response) {
						console.log("entered into then function");

						$scope.employeesList = response.data;
						console.log(JSON.stringify(response.data));
						console.log($scope.employeesList);
						
						angular.forEach($scope.employeesList, function(
								employee, index) {
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

			$scope.downloadToExcel = function() {
				$http.get("../admin/1001/excel.json").then(function(response) {
				});
			};
			
			$scope.approve = function(id, employee) {
				employee.status = 1;
				var approveURL = "../admin/employee/" + id + ".json";
				$http.put(approveURL, employee).then(function(response) {
				});
			};

			$scope.personRemove = function(index) {
				$scope.employees.splice(index, 1);
			};

		} ]);

admin.controller('showCtrl', function($scope, $http, genUtils) {
	
	$scope.IsHidden = true;

	$scope.ShowHide = function() {
		//If DIV is hidden it will be visible and vice versa.
		$scope.IsHidden = $scope.IsHidden ? false : true;
	};
	$scope.uploadFile = function() {
		var file = $scope.myFile;
		console.dir(file);
		var uploadUrl = '../admin/1001/file.json';
		var fd = new FormData();
		fd.append('files', file);
		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined,
				enctype : 'multipart/form-data'
			}
		})
		.success(function(data, status, headers, config) {
			var location = headers('Location');
			$scope.filename = location;
		})
		.error(function() {
		});

	};
	$scope.compliancesSubmit = function(id, employee) {
		console.log("In complianceSubmit method");
		$scope.ShowHide();
		var approveURL = "../admin/employee/" + id + ".json";
		$scope.privileges = [];

		// helper method to get selected fruits
		/*$scope.selectedPrivileges = function selectedPrivileges() {
			return filterFilter($scope.privileges, {
				selected : true
			});
		};
		
		// watch fruits for changes
		$scope.$watch('privileges|filter:{selected:true}', function(nv,
				employee) {
			$scope.selection = nv.map(function(privilege) {
				return {
					"id" : privilege.id
				};
			});
			$scope.employee.privileges = $scope.selection;
			console.log("scope.selection:"+JSON.stringify($scope.selection));
		}, true);
		*/
		
		console.log(employee);
		$http.put(approveURL, employee).then(function(response) {
			console.log(response);
		});

	};
});


admin.controller('showPrivilegeCtrl', function($scope, $http, genUtils) {
	$scope.valueExistsinArray = function(array, key, value) {
		return genUtils.valueExistsinArray(array, key, value);
	};
	
	$scope.addValueInArray = function(array, key, value) {
		return genUtils.addValueInArray(array, key, value);
	};
	
	$scope.deleteValuefromArray = function(array, key, value) {
		return genUtils.deleteValuefromArray(array, key, value);
	};
	
	$scope.checked = {};
	$scope.updateArray = function(array, key, value, index) {
		console.log("update array:"+JSON.stringify(array)+",checked:"+JSON.stringify($scope.checked)+",key:"+key+",value:"+value+",index:"+index);
		if ($scope.checked[value]) {
			console.log("in checked true");
			genUtils.addValueInArray(array, key, value);
		} else {
			console.log("in checked false");
			genUtils.deleteValuefromArray(array, key, value);
		}		
	};
});

admin.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);

admin.service('fileUpload', [ '$http', function($http) {
	this.uploadFileToUrl = function(file, uploadUrl) {
		var fd = new FormData();
		fd.append('files', file);
		console.log('In fileUpload service');
		console.dir(file);
		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined,
				enctype : 'multipart/form-data'
			}
		})
		.success(function(data, status, headers, config) {
			var location = headers('Location');
		})
		.error(function() {
		});
	}
} ]);


admin.service('genUtils', ['$filter',function($filter) {
	this.valueExistsinArray = function(array, key, value) {
		//console.log('in valueExistsinArray,key:'+key+',value:'+value);
		//console.log('array:'+JSON.stringify(array));
		//console.log('filter:'+$filter);
		var obj = {};
	   	obj[key] = value;
		var found = $filter('filter')(array, obj, false);
		//console.log('found:'+JSON.stringify(found));
	     if (found.length) {
	         return true;
	     } else {
	         return false;
	     }
	}
	
	this.addValueInArray = function(array, key, value) {
		console.log('in addValueInArray,key:'+key+',value:'+value);
		console.log('array:'+JSON.stringify(array));
		var found = this.valueExistsinArray(array, key, value);
		console.log('found:'+JSON.stringify(found));
	     if (!found) {
	    	 var obj = {};
	    	 obj[key] = value;
	    	 array.push(obj);
	    	 console.log('array:'+JSON.stringify(array));
	    	 return true;
	     } 
	}
	
	this.deleteValuefromArray = function(array, key, value) {
		console.log('in deleteValuefromArray,key:'+key+',value:'+value);
		console.log('array:'+JSON.stringify(array));
		var found = this.valueExistsinArray(array, key, value);
		console.log('found:'+JSON.stringify(found));
	     if (found) {
	    	 var obj = {};
	    	 obj[key] = value;
	    	 var index = array.indexOf(obj);
	    	 console.log("index:"+index);
	    	 array.splice(index, 1);
	    	 console.log('array:'+JSON.stringify(array));
	    	 return true;
	     } 
	}
} ]);



