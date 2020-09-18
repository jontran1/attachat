<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>${subName }</title>
	</head>
	
		<!-- Reference Bootstrap files -->		 
 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">
	
	<body>

	<nav class="navbar navbar-dark bg-dark">

    <a class="navbar-brand" href="${pageContext.request.contextPath }/">
        <img src="${pageContext.request.contextPath }/img/chat-icon.png" width="30" height="30" class="d-inline-block align-top" alt="">
        AttaChat
    </a>
    
	    <div id="user-info">        
        	<c:if test="${!pageContext.request.userPrincipal.authenticated }">
				<a class="btn btn-success" href="${pageContext.request.contextPath }/showMyLoginPage">Login</a>
			</c:if> 
			<c:if test="${pageContext.request.userPrincipal.authenticated }">
				<form:form action="${pageContext.request.contextPath }/logout" method="POST">
					<input class="btn btn-success" type="submit" value="Logout" />
				</form:form>
			<!-- Show currently logged in user's comment.
				<c:url var="userComments" value="/Comment/user/showComments">
					<c:param name="userName" value="${pageContext.request.userPrincipal.name }"/>
				</c:url>
				<a class="btn btn-success" href="${userComments }">Comment History</a>
			-->
			</c:if> 
	    </div>
		    
	</nav>
	
	
	<div class="jumbotron jumbotron-fluid">
	    <div class="container">
	        <h1 class="display-4 text-center">
        		<c:url var="subLink" value="/Sub/showSub">
					<c:param name="subName" value="${subName }"/>
				</c:url>
	            <span id="sub-name"><a href="${subLink }">${subName} </a></span>
	        </h1>
	    </div>
	</div>
	
	<c:url var="createThreadLink" value="/Thread/userAction/showFormCreateThread">
		<c:param name="subName" value="${subName }"/>
	</c:url>
	
	<c:url var="followSub" value="/Sub/userAction/followSub">
		<c:param name="subName" value="${subName }"/>
	</c:url>
	<c:url var="unfollowSub" value="/Sub/userAction/unfollowSub">
		<c:param name="subName" value="${subName }"/>
	</c:url>
	
		<div class="grid-container">
		
			<div class="main">
			
				<c:forEach var="thread" items="${threads}">
					<c:url var="threadLink" value="/Thread/showThread">
						<c:param name="threadId" value="${thread.threadId }"/>
					</c:url>
							
					<article>
						<div>
							<td><a href="${threadLink }">${thread.threadTitle }</a> </td>
						</div>
					</article>
				</c:forEach>
	
			</div>
				
			<div class="sidebar">
			     <form id="form-search" action="/search/" method="post">
				    <div class="input-group mb-3">
				        <input type="text" class="form-control" name="query" placeholder="Find a post" required="">
				        <div class="input-group-append">
				            <button class="btn btn-primary submit" type="submit">Search</button>
				        </div>
				    </div>
				</form>                  
				
				
				<div class="btn-group" style="width: 100%">

				
				<c:if test="${pageContext.request.userPrincipal.authenticated && !isFollower}">
					<form:form action="${followSub }" method="post">
					    <button class="btn btn-block btn-light" type="submit" >Follow</button>
					</form:form>
				</c:if> 
				<c:if test="${pageContext.request.userPrincipal.authenticated && isFollower}">
					<form:form action="${unfollowSub }" method="post">
					    <button class="btn btn-block btn-light" type="submit" >Unfollow</button>
					</form:form>
				</c:if> 
				</div>
				<br><br>
				
				<a class="btn btn-block btn-light" href="${createThreadLink}">Create A New Thread</a>
				
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
	
	</body>

</html>