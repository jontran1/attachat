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
		
		<c:set var = "id" scope = "session" value = "id${comment.commentId }"/> 
		
		<form:form id="delete${id }" action="${deleteComment }" method="POST"></form:form>
		
		<ul class="list-inline">
			
			<li class="list-inline-item"><a href="#" data-toggle="collapse" data-target="#${id }">Reply</a></li>
			
			<c:if test="${pageContext.request.userPrincipal.authenticated && comment.userName == userName}">	
				
				<!-- 
				<li class="list-inline-item"><a href="${editComment }">Edit</a></li>
				 -->
				
				<c:if test="${!comment.deleted }">
				
					<li class="list-inline-item"><a href="#" data-toggle="collapse" data-target="#edit-${id }">Edit</a></li>
					<li class="list-inline-item"><a href="javascript:{}" onclick="document.getElementById('delete${id}').submit();">Delete</a></li>
					
					<form:form id="edit-${id }" class="collapse" action="${pageContext.request.contextPath }/Comment/userAction/saveComment" modelAttribute="comment" method="POST">
						
						<p><textarea id="content" name="content" maxlength="1000" class="form-control comment-text">${comment.content}</textarea></p>
																			
						<form:input path="userName" type="hidden" value="${comment.userName }"/>
						<form:input path="commentId" type="hidden" value="${comment.commentId }"/>
						<form:input path="threadId" type="hidden" value="${thread.threadId }"/>
						<form:input path="deleted" type="hidden" value="${comment.deleted }"/>
						<form:input path="parentId" type="hidden" value="${comment.parentId }"/>
						
						<input class="btn btn-primary submit" type="submit" value="Submit Comment"/>
		
					</form:form>	
					
				</c:if>

			</c:if>

			<form:form id="${id }" class="collapse" action="${pageContext.request.contextPath }/Comment/userAction/saveComment" modelAttribute="comment" method="POST">
				<p><textarea class="form-control comment-text" placeholder="Comment here ..." maxlength="1000" id="content" name="content"></textarea></p>
				<form:input path="userName" type="hidden"/>
				<form:input path="threadId" type="hidden"/>
				<form:input path="deleted" type="hidden"/>
				<form:input path="parentId" type="hidden" value="${comment.commentId }"/>									
				<input class="btn btn-primary submit" type="submit" value="Post a new comment"/>	
			</form:form>
			
		</ul>
	 
		<c:set var="comment" value="${comment}" scope="request"/>
		<jsp:include page="node.jsp"/>
		
	
	</article>

</c:forEach>


