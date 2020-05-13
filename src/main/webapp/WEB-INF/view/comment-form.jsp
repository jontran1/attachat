<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
				
			<c:if test="${isReply}">
				<td><form:input path="parentId" type="hidden"/></td>				
			</c:if>
						
		</table>
	
		<p>
			<a href="${pageContext.request.contextPath }/">Home</a>
		</p>
	</form:form>
	
</body>
</html>