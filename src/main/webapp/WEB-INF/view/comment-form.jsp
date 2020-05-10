<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Comment Creation Form</h3>
	
	<form:form action="saveComment" modelAttribute="comment" method="POST">
		
		<table>
			<tr>
				<td><label>Comment content:</label></td>
				<td><form:input path="content"/></td>
			</tr>
			<tr>
				<td><label></label></td>
				<td><input type="submit" value="Save" class="save"/></td>
			</tr>
				<td><form:input path="threadId" type="hidden"/></td>
				<td><form:input path="userName" type="hidden"/></td>
		
		</table>
	
		<p>
			<a href="${pageContext.request.contextPath }/">Home</a>
		</p>
	</form:form>
	
</body>
</html>