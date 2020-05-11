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
	
	<c:url var="createComment" value="/comment/userAction/showFormCreateComment">
		<c:param name="threadId" value="${thread.threadId }"/>
	</c:url>
	<p>
		<a href="${createComment }">Post a new comment</a>
	</p>

		<c:forEach var="comment" items="${comments}">
		
		    <c:url var="createReply" value="/comment/userAction/showFormCreateReply">
		    	<c:param name="threadId" value="${comment.threadId }"/>
		    	<c:param name="parentId" value="${comment.commentId }"/>
		    </c:url>
		    
		    <h4>Root comment: ${comment.content}</h4>
		    
		    <c:set var="comment" value="${comment}" scope="request"/>
		    <a href="${createReply }">Reply</a>
		    
			<jsp:include page="node.jsp"/>
		</c:forEach>
		

	
	</p>
	

</body>
</html>