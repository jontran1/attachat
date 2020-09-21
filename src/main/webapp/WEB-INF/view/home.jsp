<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Atta Chat</title>
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
	        	        
	        	<c:if test="${empty pageContext.request.userPrincipal}">
					<a class="btn btn-success" href="${pageContext.request.contextPath }/showMyLoginPage">Login</a>
					<a class="btn btn-success" href="${pageContext.request.contextPath }/register/showRegistrationForm">New User</a>
				</c:if> 
				<c:if test="${not empty pageContext.request.userPrincipal }">
					
					<!-- Show currently logged in user's comment history.
					<c:url var="userComments" value="/Comment/user/showComments">
						<c:param name="userName" value="${pageContext.request.userPrincipal.name }"/>
					</c:url>
					<a class="btn btn-success" href="${userComments }">Comment History</a>
					-->
					
					<form:form action="${pageContext.request.contextPath }/logout" method="POST">
						<input class="btn btn-success" type="submit" value="Logout" />
					</form:form>

				</c:if> 

	    </div>
	    
	</nav>
	
	<div class="jumbotron jumbotron-fluid">
	    <div class="container">
	        <h1 class="display-4 text-center">
	            <span id="subreddit-name">Home</span>
	        </h1>
	    </div>
	</div>
	
	<div class="grid-container">
	
		<div class="main">
			<c:forEach var="sub" items="${subs}">
				<c:url var="subLink" value="/Sub/showSub">
					<c:param name="subName" value="${sub.subName }"/>
				</c:url>
				
				<article class="thread-wrapper">
					
					<h2>
						<a href="${subLink }">${sub.subName} </a>
					</h2>
					<span>
						Created by ${sub.creator} Followers:  ${sub.numberOfFollowers }
					</span>
				
				</article>
			
			</c:forEach>	
		</div>
			
		<div class="sidebar">
		     <form id="form-search" action="/search/" method="post">
			    <div class="input-group mb-3">
			        <input type="text" class="form-control" name="query" placeholder="Find a sub" required="">
			        <div class="input-group-append">
			            <button class="btn btn-primary submit" type="submit">Search</button>
			        </div>
			    </div>
			</form>                  
			
			
			<div class="btn-group" style="width: 100%">
			<a class="btn btn-success auth-req" href="/submit/link" id="submit-link" style="width: 100%">New link</a>
			<a class="btn btn-primary auth-req" href="/submit/post" id="submit-post" style="width: 100%">New post</a>
			</div>
			<br><br>
			
			<a class="btn btn-block btn-light auth-req" href="${pageContext.request.contextPath }/Sub/userAction/showFormCreateSub">Create sub</a><br>
			            
			
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

	<c:if test="${not empty pageContext.request.userPrincipal }">
	
		<p>My subs</p>
			<c:forEach var="sub" items="${userSubs}">
				<c:url var="subLink" value="/Sub/showSub">
				<c:param name="subName" value="${sub.subName }"/>
				</c:url>
						
				<p>
					Sub name: ${sub.subName}
				
					<a href="${subLink }">${sub.subName} </a>
			
					Creator: ${sub.creator}
					Population count:  ${sub.numberOfFollowers }
				
				</p>
			</c:forEach>
		<c:if test="${empty userSubs}">
		<p>You are not currently following any subs.</p>
		</c:if>

	</c:if> 	
	
	</body>
	
</html>