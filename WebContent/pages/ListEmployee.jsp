<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<div align="center">
		<h1>Item List</h1>
		<table border="1">
			<tr>
				<th>S.No.</th>
				<th>Item Code</th>
				<th>Item Name</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>

			<c:forEach var="item" items="${itemList}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${item.item_code}</td>
					<td>${item.item_name}</td>
					<td>${item.price}</td>
					<td>${item.qty}</td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>