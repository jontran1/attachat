<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${userName } comment history</title>
</head>
<body>
	<h2>${userName } comment history</h2>
	

	<c:forEach var="comment" items="${comments }">

		<c:url var="threadLink" value="/Thread/showThread">
			<c:param name="threadId" value="${comment.threadId }"/>
		</c:url>
		
		<p>
			<td>${comment.content}</td>
			<td>
				<a href="${threadLink }">Thread</a>
			</td>
		</p>
	
	</c:forEach>
	

</body>
</html>