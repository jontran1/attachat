<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
		<title>${thread.threadTitle }</title>
	</head>
	
	<!-- Reference Bootstrap files -->		 
 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
	
	<script>
		function showReplyFormFunction(id){
			console.log(id);
			let form = document.getElementById("hole");
			console.log(form);
  
			if (form.style.display === "none") {
				  form.style.display = "block";
			  } else {
				  form.style.display = "none";
			  }
		}
	</script>
	<body>
	
		<nav class="navbar navbar-dark bg-dark">
	
		    <a class="navbar-brand" href="${pageContext.request.contextPath }/">
		        <img src="${pageContext.request.contextPath }/img/chat-icon.png" width="30" height="30" class="d-inline-block align-top" alt="">
		        AttaChat
		    </a>
		    
		    <div id="user-info" style="display: inline; left;">
		        	        
		        	<c:if test="${!pageContext.request.userPrincipal.authenticated }">
						<a class="btn btn-success" href="${pageContext.request.contextPath }/showMyLoginPage">Login</a>
					</c:if> 
					<c:if test="${pageContext.request.userPrincipal.authenticated }">
		
					</c:if> 
					<c:if test="${pageContext.request.userPrincipal.authenticated }">
						<c:url var="userComments" value="/Comment/user/showComments">
							<c:param name="userName" value="${pageContext.request.userPrincipal.name }"/>
						</c:url>
						<a class="btn btn-success" href="${userComments }">Comment History</a>
					</c:if> 
		    </div>
		    
		</nav>
		
		<div class="jumbotron jumbotron-fluid">
		    <div class="container">
		        <h1 class="display-4 text-center">
			        <c:url var="subLink" value="/Sub/showSub">
						<c:param name="subName" value="${thread.subName }"/>
					</c:url>
		            <span id="sub-name"><a href="${subLink }">${thread.subName} </a></span>
		        </h1>
		    </div>
		</div>
		
		<div class="grid-container">
			<div class="main">
				<div class="comment-wrapper">
				
					<article class="post">
					
						<div class="info">
							<header>
								${thread.threadTitle }
							</header>
							
							<div>
								 submitted <time>${thread.localDateTime }</time>
								<c:url var="subLink" value="/Sub/showSub">
									<c:param name="subName" value="${thread.subName }"/>
								</c:url>
								 by <span class="post-user"><a href="#">${thread.userName }</a></span>
								 from <span><a href="${subLink }">${thread.subName }</a></span>
							</div>
							<p class="border border-primary post-body">${thread.threadContent }</p>
							<span class="post-options">
								<c:if test="${pageContext.request.userPrincipal.authenticated && pageContext.request.userPrincipal.name == thread.userName }">
									<c:url var="editThread" value="/Thread/userAction/editFormThread">
									<c:param name="threadId" value="${thread.threadId }"/>
									</c:url>
									<a href="${editThread }">Edit</a>
								</c:if>
							</span>
						</div>
						
					</article>
					
				</div>
				
				<div class="submit-wrapper">
				
		        	<c:if test="${!pageContext.request.userPrincipal.authenticated }">
						You must be <a href="${pageContext.request.contextPath }/showMyLoginPage">logged in</a> to leave a comment.
					</c:if> 
					<c:if test="${pageContext.request.userPrincipal.authenticated }">
						<!-- A form for posting comments. -->
						
						<!-- 
						<c:url var="createComment" value="/Comment/userAction/showFormCreateComment">
							<c:param name="threadId" value="${thread.threadId }"/>
						</c:url>
						-->
						
						<form:form action="${pageContext.request.contextPath }/Comment/userAction/saveComment" modelAttribute="comment" method="POST">
							<form:textarea class="form-control comment-text" placeholder="Comment here ..." maxlength="1000" path="content"/>
							<form:input path="userName" type="hidden"/>
							<form:input path="commentId" type="hidden"/>
							<form:input path="threadId" type="hidden"/>
							<form:input path="deleted" type="hidden"/>
							
							<input class="btn btn-primary submit" type="submit" value="Post a new comment"/>
						
						</form:form>
												
					</c:if> 
					
				</div>
				
				<div>
				
					<c:forEach var="comment" items="${comments}">
					
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
					    
			   		    <c:set var="comment" value="${comment}" scope="request"/>
					    
					    <article class="comment">
					    		<span>
					    		${comment.userName }
					    		${comment.localDateTime }
					    		</span>  
					   		
					   		<div class="comment-body">
					   		${comment.content}
					   		</div>
						    
						   <span>
				
     							<c:set var = "id" scope = "session" value = "id${comment.commentId }"/> 

							    <button type="button" class="btn btn-link" data-toggle="collapse" data-target="#${id }">Reply</button>
								<form:form id="${id }" class="collapse" action="${pageContext.request.contextPath }/Comment/userAction/saveComment" modelAttribute="comment" method="POST">
									<form:textarea class="form-control comment-text" placeholder="Comment here ..." maxlength="1000" path="content"/>
									<form:input path="userName" type="hidden"/>
									<form:input path="threadId" type="hidden"/>
									<form:input path="deleted" type="hidden"/>
									<form:input path="parentId" type="hidden" value="${comment.commentId }"/>									
									<input class="btn btn-primary submit" type="submit" value="Post a new comment"/>	
								</form:form>
								
							    <c:if test="${pageContext.request.userPrincipal.authenticated && 
							    			pageContext.request.userPrincipal.name == comment.userName &&
							    			!comment.deleted }">
								    <a href="${editComment }">Edit</a>	    
							    </c:if>
								
								<c:if test="${pageContext.request.userPrincipal.authenticated && comment.userName == userName}">								
									<c:if test="${!comment.deleted }">
										<form:form action="${deleteComment }" method="POST">
										    <button class="btn btn-link" type="submit" >Delete</button>
										</form:form>
									</c:if>
								</c:if>   
								
						   </span>
						   
						   <jsp:include page="node.jsp"/>
						   
					     </article>
						
					</c:forEach>
				
				</div>
				
			</div>
			
			<div class="sidebar">
				

				            
		      	<h4 class="text-center">Subbeddits (16)</h4>
				<table class="table table-striped">
					    <tbody>
					
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					        <tr>
					        <td><a href="/r/test">/r/test</a></td>
					        </tr>
					    
					    </tbody>
					</table>   
		        </div>

			</div>
		
		</div>
		
		
		</p>
		
	
	</body>
</html>