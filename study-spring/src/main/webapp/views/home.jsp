<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lottery Page</title>
</head>
<body>
	<form action="command" method="post">
		<label for="column">Column:</label> <input type="text" id="column"
			name="column" value="${param.column}">
		<button name="command" value="draw">Draw</button>
		<button name="command" value="reset">Reset</button>
	</form>
	<table>
		<thead>
			<tr>
				<th>Column #1</th>
				<th>Column #2</th>
				<th>Column #3</th>
				<th>Column #4</th>
				<th>Column #5</th>
				<th>Column #6</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="numbers" items="${lottery.numbers}">
				<tr>
					<c:forEach var="number" items="${numbers}">
					   <td>${number}</td>
				    </c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>