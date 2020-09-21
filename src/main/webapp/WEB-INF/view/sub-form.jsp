<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
	<title>Sub Creation Form</title>
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
		        	        
	        	<c:if test="${empty pageContext.request.userPrincipal }">
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
		            <span id="subreddit-name">Sub Creation Form</span>
		        </h1>
		    </div>
		    
		</div>
		
		<div class="container">

			<form:form action="saveSub" modelAttribute="sub" method="POST">
			
				<label for="subName">Sub Name</label>
				<form:errors class="failed" path="subName"/>				
				<form:input class="form-control" path="subName"/>
				
				<label for="subInfo">Sub Information</label>
				<textarea class="form-control" id="subInfo" placeholder="Give the user an idea what this sub is about." rows="3"></textarea>
				
				<input type="submit" value="Save" class="save"/>
				
			</form:form>
	
		</div>
			
	</body>
</html>