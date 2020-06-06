<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:forEach var="comment" items="${comment.children}">
     <c:url var="createReply" value="/Comment/userAction/showFormCreateReply">
    	<c:param name="threadId" value="${comment.threadId }"/>
    	<c:param name="parentId" value="${comment.commentId }"/>
    </c:url>
    
    <c:url var="deleteComment" value="/Comment/userAction/deleteComment">
    	<c:param name="commentId" value="${comment.commentId }"/>
    </c:url>
    
		<p style="text-indent: ${comment.indent}px;">
		
		${comment.content}
		User name: ${comment.userName }
		<a href="${createReply }">Reply</a>
		<c:if test="${pageContext.request.userPrincipal.authenticated && comment.userName == userName}">	
			<c:if test="${!comment.deleted }">
				<form action="${deleteComment }" method="POST" style="text-indent: ${comment.indent}px;">
				    <button type="submit" >Delete comment</button>
				</form>
			</c:if>							
		</c:if>   
		 
		<c:set var="comment" value="${comment}" scope="request"/>
		<jsp:include page="node.jsp"/>
		
   		</p>

</c:forEach>


