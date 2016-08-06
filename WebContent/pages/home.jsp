<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page session="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Employee</title>

<script type="text/javascript">

   function update()
	{
	document.employeeForm.action="updateEmployee.do";
	document.employeeForm.submit();
	
	}

   function list()
	{
	document.employeeForm.action="listEmployee.do";
	document.employeeForm.submit();
	
	}

</script>
</head>

<body>

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
	  <h2>
		Welcome : ${pageContext.request.userPrincipal.name} | <a
			href="javascript:formSubmit()"> Logout</a>
	  </h2>
	</c:if>

	<sec:authorize access="isRememberMe()">
		<h2># This user is login by "Remember Me Cookies".</h2>
	</sec:authorize>

	<sec:authorize access="isFullyAuthenticated()">
		<h2># This user is login by username / password.</h2>
	</sec:authorize>
	

	<div align="center">
		<h1>Item List</h1>
		<table border="1">
			<tr>
				<th>id</th>
				<th>name</th>
				<th>appArea</th>
				<th>dsId</th>
				<th>badgeEndDate</th>
				<th>email</th>
				<th>cubicleId</th>
				<th>managerId</th>
				<th>requestType</th>								
				<th>Action</th>
				<th>Action</th>


			</tr>
			<c:forEach var="employee" items="${employees}" varStatus="status">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.name}</td>
					<td>${employee.appArea}</td>
					<td>${employee.dsId}</td>
					<td>${employee.badgeEndDate}</td>
					<td>${employee.email}</td>
					<td>${employee.cubicleId}</td>
					<td>${employee.managerId}</td>
					<td>${employee.requestType}</td>					
					<td><a href="editEmployee.do?id=${employee.id}">Edit</a></td>
					<td><a href="deleteEmployee.do?id=${employee.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<form method="POST" action="uploadMultipleFile.do" enctype="multipart/form-data">
		File1 to upload: <input type="file" name="file">
 
		Name1: <input type="text" name="name">
 
 
		File2 to upload: <input type="file" name="file">
 
		Name2: <input type="text" name="name">
 

		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
	
</body>
</html>