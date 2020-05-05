<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>${thread.threadTitle }</h3>
	
	<p>
		${thread.threadContent }
	</p>
	<p>
		Thread creator: ${thread.userName }
	</p>
	

</body>
</html>