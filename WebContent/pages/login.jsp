<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" href="../css/style.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js">
	
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-route.js">
	
</script>
</head>

<body>
	<div id="header" style="color: white">
		<!-- <img src="../images/infyicon.png" -->
		<img src="" style="width: 100px; height: 50px; padding-left: 40px;">
	</div>
	<div ng_controller="LoginCtrl" class="lg-container">
		<c:url value="/admin/logout.do" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h6>
				Welcome : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h6>
		</c:if>

		<sec:authorize access="isRememberMe()">
			<h6># This user is login by "Remember Me Cookies".</h6>
		</sec:authorize>

		<sec:authorize access="isFullyAuthenticated()">
			<h6># This user is login by username / password.</h6>
		</sec:authorize>

		<div class="login-form">
			<c:url var="loginUrl" value="/login.do" />
			<form action="${loginUrl}" method="post" id="lg-form">
				<c:if test="${error != null}">
					<div >
						<p>Invalid username and password.</p>
					</div>
				</c:if>
				<c:if test="${logout != null}">
					<div >
						<p>You have been logged out successfully.</p>
					</div>
				</c:if>
				<div>
					<label for="username">Username</label> <input type="text"
						id="username" name="ssoId" placeholder="Enter Username" required>
				</div>
				<div>
					<label for="password">Password</label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="Enter Password" required>
				</div>
				<div>
					<div class="checkbox">
						<label><input type="checkbox" id="rememberme"
							name="remember-me"> Remember Me</label>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div class="form-actions">
					<input type="submit" class="btn btn-block btn-primary btn-default"
						value="Log in">
				</div>
				<p>
					username: ${user} <br />

				</p>
			</form>
		</div>
	</div>
	<footer id="footer">
		<span style="color: white">infosys</span> <span id="searchNdHome"
			style="color: white">Copyright &copy;<a
			href="#copyright-popup" ng-click="copyright()">2016</a>Infosys
			Limited.
		</span>
	</footer>
</body>
</html>