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
		
		<p>
			<td>${comment.content}</td>
		</p>
	
	</c:forEach>
	

</body>
</html>