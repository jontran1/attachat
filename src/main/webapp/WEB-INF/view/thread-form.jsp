<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thread Creation Form</title>
</head>
<body>
	<h3>Thread Creation Form</h3>
	
	<form:form action="saveThread" modelAttribute="thread" method="POST">

		
		<table>
			<tbody>
				<tr>
					<td><label>Thread title:</label></td>
					<td><form:input path="threadTitle"/></td>
				</tr>
				<tr>
					<td><label>Thread content:</label></td>
					<td><form:input path="threadContent"/></td>
				</tr>
				<tr>
					<td><form:input type="hidden" path="userName"/></td>
					<td><form:input type="hidden" path="subName"/></td>

				</tr>
				
				<tr>
					<td><lable></lable></td>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			
			</tbody>
		</table>
	
	</form:form>

</body>
</html>