<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

	<form:form name="employeeForm" method="post" action="saveEmployee.do" modelAttribute="employee" commandName="employee">
		<div align="center">
			<h1>Employee Detail</h1>
			<table border="1">

				<tr>
					<th>id</th>
					<td><form:input path="id" /></td>

				</tr>
				<tr>
					<th>firstName</th>
					<td><form:input path="name" /></td>

				</tr>
				<tr>
					<th>middleName</th>
					<td><form:input path="appArea" /></td>

				</tr>
				<tr>
					<th>lastName</th>
					<td><form:input path="dsId" /></td>

				</tr>
				<tr>
					<th>salutation</th>
					<td><form:input path="badgeEndDate" /></td>

				</tr>
				<tr>
					<th>loginName</th>
					<td><form:input path="email" /></td>

				</tr>
				<tr>
					<th>loginPassword</th>
					<td><form:password path="cubicleId" /></td>

				</tr>
				<tr>
					<th>phoneNumber</th>
					<td><form:input path="managerId" /></td>

				</tr>
				<tr>
					<th>adminFlag</th>
					<td><form:input path="requestType" /></td>

				</tr>				
				<tr>
					<th><input type="submit" value="Add New Item"></th>
					<th><input type="button" value="Update" onclick="update()" />
						<input type="button" value="List" onclick="list()" /></th>

				</tr>


			</table>
		</div>

	</form:form>

	<div align="center">
		<h1>Item List</h1>
		<table border="1">
			<tr>
				<th>id</th>
				<th>firstName</th>
				<th>middleName</th>
				<th>lastName</th>
				<th>salutation</th>
				<th>loginName</th>
				<th>loginPassword</th>
				<th>phoneNumber</th>
				<th>adminFlag</th>								
				<th>Action</th>
				<th>Action</th>


			</tr>
			<c:forEach var="employee" items="${employeeList}" varStatus="status">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.firstName}</td>
					<td>${employee.middleName}</td>
					<td>${employee.lastName}</td>
					<td>${employee.salutation}</td>
					<td>${employee.loginName}</td>
					<td>${employee.loginPassword}</td>
					<td>${employee.phoneNumber}</td>
					<td>${employee.adminFlag}</td>					
					<td><a href="editEmployee.do?id=${employee.id}">Edit</a></td>
					<td><a href="deleteEmployee.do?id=${employee.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>