<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>

	<head>
		<title>Atta Chat Login Page</title>
		
		<!-- Reference Bootstrap files -->		 
	 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">

	</head>

	<body>
	
	
		<!-- Top page nav bar -->
		<nav class="navbar navbar-dark bg-dark">
	
	    	<a class="navbar-brand" href="${pageContext.request.contextPath }/">
		        <img src="${pageContext.request.contextPath }/img/chat-icon.png" width="30" height="30" class="d-inline-block align-top" alt="">
		        AttaChat
		    </a>
		    
		</nav>
	
	
		<!-- Jumbotron -->
		<div class="jumbotron jumbotron-fluid">
	    <div class="container">
	        <h1 class="display-4 text-center">
	            <span id="subreddit-name">Login</span>
	        </h1>
	    </div>
		</div>
	
		<!-- Login form -->	
		<main class="login-form">
		    <div class="cotainer">
		        <div class="row justify-content-center">
		            <div class="col-md-8">
		                <div class="card">
		                    <div class="card-header">Login/Register</div>
		                    <div class="card-body">
		                    
		                        <form:form action="${pageContext.request.contextPath }/authenticateTheUser" method="POST">
		                            <div class="form-group row">
		                                <label for="user_name" class="col-md-4 col-form-label text-md-right">User Name</label>
		                                <div class="col-md-6">
		                                    <input type="text" id="user_name" class="form-control" name="username" required autofocus>
		                                </div>
		                            </div>
		
		                            <div class="form-group row">
		                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
		                                <div class="col-md-6">
		                                    <input type="password" class="form-control" name="password" required>
		                                                                    
											<c:if test="${param.error != null }">		
												<i class="failed" >Sorry! You entered invalid username/password.</i>
											</c:if>
									
		                                </div>
		                            </div>
		                            
		
		                            <div class="col-md-6 offset-md-4">
		                            	<input class="btn btn-primary" type="submit" value="Login" />
										 <a href="${pageContext.request.contextPath}/register/showRegistrationForm"
											 class="btn btn-primary"
											 role="button" aria-pressed="true">
											 Register
										 </a>
		                            </div>
		                         </form:form>
		                         
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</main>

			
	</body>
</html>