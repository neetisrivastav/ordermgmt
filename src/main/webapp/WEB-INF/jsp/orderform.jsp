<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="saveorderdetails" method="POST" modelAttribute="order">
		<form:input path="orderAmount"></form:input>
		<form:errors path="orderAmount"/>
		<form:input path="dueDate"></form:input>
		<form:input path="orderDiscriptions"></form:input>
		<form:input path="currency"></form:input>
		<form:errors path="currency" />
		
	<input type="submit" value="save order"/>
	</form:form>
</body>
</html>