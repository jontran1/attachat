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

<c:forEach var="comment" items="${comment.children}">
    <p style="text-indent: 20px;;">${comment.content}</p>
    <c:set var="comment" value="${comment}" scope="request"/>
    <jsp:include page="node.jsp"/>
</c:forEach>



</body>
</html>