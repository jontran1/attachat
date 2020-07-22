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
    
    <c:url var="editComment" value="/Comment/userAction/showFormEditComment">
    	<c:param name="commentId" value="${comment.commentId }"/>
    </c:url>
    
	<div class="comment" style="margin-right: ${comment.indent}px;">
    	<ul>
    		<span>
    		${comment.userName }
    		${comment.localDateTime }
    		</span>  
    	</ul>
		
   		<div class="comment-body">
   			${comment.content}
   		</div>
		
		<ul>
		
		
		<span>
		
			<a href="${createReply }">Reply</a>
			<c:if test="${pageContext.request.userPrincipal.authenticated && comment.userName == userName}">	
				<a href="${editComment }">Edit</a>						
			</c:if>   
			<c:if test="${pageContext.request.userPrincipal.authenticated && comment.userName == userName && !comment.deleted }">
				<form action="${deleteComment }" method="POST">
				    <button class="btn btn-link" type="submit" >Delete</button>
			     	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>				    
				</form>
			</c:if>	
		
		</span>
		
		</ul>
	 
	<c:set var="comment" value="${comment}" scope="request"/>
	<jsp:include page="node.jsp"/>
	
  		</div>

</c:forEach>


