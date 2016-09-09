

<!DOCTYPE html>
<html ng-app="admin">
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


</head>
<body ng-controller="adminController" id="adminController">
	<div id="header" style="color: white">
		<!-- <img src="../images/infyicon.png" -->
		<img src="" style="width: 100px; height: 50px; padding-left: 40px;">
	</div>
	<div id="viewArea">
		<div >
			<!-- <div >
		<h1>Admin Dashboard</h1>
		<input class="barAdmin" type="search" ng-model="search"
			placeholder="search" ng-click=adminSearch() >


	</div> -->
			<div id="searchNdHome">
				<input type="search" ng-model="search" placeholder="search"
					ng-click=adminSearch()> <span>|</span>
				<button ng-click="downloadToExcel()">Export to Excel</button>
				<span>|</span> <a href="#main" style="color: white">Home</a>
			</div>
			<div id="tabs" class="dashboard-container" ng-hide="search">

				<div id="test">
					<ul id="tabview">
						<li ng-repeat="tab in tabs"
							ng-class="{active:isActiveTab(tab.url)}"
							ng-click="onClickTab(tab)"><span
							ng-if="tab.title=='Pending requests'"><b
								style="color: red">{{countOne}}</b>{{tab.title}}</span><span
							ng-if="tab.title=='incomplete compliances'"><b
								style="color: red">{{countTwo}}</b>{{tab.title}}</span> <span
							ng-if="tab.title=='Actions'">{{tab.title}}</span></li>
					</ul>
				</div>
				<div id="mainView">
					<div ng-include="currentTab"></div>
				</div>
			</div>

			<div ng-show="search" class="dashboard-container">
				<div ng-include="'searchAdmin.html'"></div>


			</div>
		</div>
		<footer id="footer">
			<span style="color: white">infosys</span> <span id="searchNdHome"
				style="color: white">Copyright &copy;<a
				href="#copyright-popup" ng-click="copyright()">2016</a>Infosys
				Limited.
			</span>
		</footer>
		<script type="text/ng-template" id="one.tpl.html">
		<div id="viewOne" >
<ul>
            <li  >
			<div ng-include="'PendingRequestTemplate.html'"></div>
                 
             </li>
        </ul>
		</div>
		</script>

		<script type="text/ng-template" id="two.tpl.html">
		<div id="viewTwo" >
		
			<div ng-include="'incompleteCompliancesTemplate.html'" ></div>
                 
        
</div>
		</script>

		<script type="text/ng-template" id="three.tpl.html">

		<div id="viewThree">
		<h1>Not Yet Developed!</h1>
		</div>
		</script>

		<script src="../js/adminctrl.js">
			
		</script>
	</div>
</body>
</html>