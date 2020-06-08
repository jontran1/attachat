<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${thread.threadTitle }</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/">Home</a>
	
	<h3>${thread.threadTitle }</h3>
	
	<p>
		${thread.threadContent }
	</p>
	<c:url var="editThread" value="/Thread/userAction/editFormThread">
		<c:param name="threadId" value="${thread.threadId }"/>
	</c:url>
	<a href="${editThread }">Edit</a>
	
	<p>
		Thread creator: ${thread.userName }
	</p>
	
	<c:url var="createComment" value="/Comment/userAction/showFormCreateComment">
		<c:param name="threadId" value="${thread.threadId }"/>
	</c:url>
	<p>
		<a href="${createComment }">Post a new comment</a>
	</p>

		<c:forEach var="comment" items="${comments}">
		
		    <c:url var="createReply" value="/Comment/userAction/showFormCreateReply">
		    	<c:param name="threadId" value="${comment.threadId }"/>
		    	<c:param name="parentId" value="${comment.commentId }"/>
		    </c:url>
		    
   		    <c:url var="deleteComment" value="/Comment/userAction/deleteComment">
		    	<c:param name="commentId" value="${comment.commentId }"/>
		    </c:url>
		    
   		    <c:set var="comment" value="${comment}" scope="request"/>
		    
		    <h4>Root comment: ${comment.content}
		    User name: ${comment.userName }
		    <a href="${createReply }">Reply</a>
			
			<c:if test="${pageContext.request.userPrincipal.authenticated && comment.userName == userName}">								
				<c:if test="${!comment.deleted }">
					<form action="${deleteComment }" method="POST">
					    <button type="submit" >Delete comment</button>
					</form>
				</c:if>
			</c:if>   

		    </h4>
		    
			<jsp:include page="node.jsp"/>
		</c:forEach>
	
	</p>
	

</body>
</html>