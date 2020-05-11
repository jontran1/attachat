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
    <c:url var="createReply" value="/comment/userAction/showFormCreateReply">
    	<c:param name="threadId" value="${comment.threadId }"/>
    	<c:param name="parentId" value="${comment.commentId }"/>
    </c:url>
    
    
    <c:set var="comment" value="${comment}" scope="request"/>
    <a href="${createReply }">Reply</a>
    
    <jsp:include page="node.jsp"/>
</c:forEach>



</body>
</html>