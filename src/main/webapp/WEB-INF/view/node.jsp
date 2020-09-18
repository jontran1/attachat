<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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
    
	<article class="comment" style="margin-right: ${comment.indent}px;">
	
    	<ul>
    		<span>
    		${comment.userName }
    		${comment.localDateTime }
    		</span>  
    	</ul>
		
   		<div class="comment-body">
   			${comment.content}
   		</div>
		
		
		<span>
			
			<c:if test="${pageContext.request.userPrincipal.authenticated && comment.userName == userName}">	
				<a href="${editComment }">Edit</a>						
			</c:if>   
			<c:if test="${pageContext.request.userPrincipal.authenticated && comment.userName == userName && !comment.deleted }">
				<form action="${deleteComment }" method="POST">
				    <button class="btn btn-link" type="submit" >Delete</button>
			     	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>				    
				</form>
			</c:if>	
			
			
			<c:set var = "id" scope = "session" value = "id${comment.commentId }"/> 
	
			<button type="button" class="btn btn-link" data-toggle="collapse" data-target="#${id }" aria-expanded="true">Reply</button>			
			

			<form:form id="${id }" class="collapse" action="${pageContext.request.contextPath }/Comment/userAction/saveComment" modelAttribute="comment" method="POST">
				<textarea class="form-control comment-text" placeholder="Comment here ..." maxlength="1000" id="content" name="content""></textarea>
				<form:input path="userName" type="hidden"/>
				<form:input path="threadId" type="hidden"/>
				<form:input path="deleted" type="hidden"/>
				<form:input path="parentId" type="hidden" value="${comment.commentId }"/>									
				<input class="btn btn-primary submit" type="submit" value="Post a new comment"/>	
			</form:form>
			
		</span>
		
	 
		<c:set var="comment" value="${comment}" scope="request"/>
		<jsp:include page="node.jsp"/>
		
	
	</article>

</c:forEach>


