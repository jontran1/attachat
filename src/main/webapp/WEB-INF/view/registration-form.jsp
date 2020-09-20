<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html>
<html>
	<head>
	<title>Registration</title>
	</head>
	
		<!-- Reference Bootstrap files -->		 
	 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">
	
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
	            <span id="subreddit-name">Registration</span>
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
		                    
								<!-- Registration Form -->
								<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm" 
									  	   modelAttribute="crmUser"
									  	   class="form-horizontal">
			
								    <!-- Place for messages: error, alert etc ... -->
								    <div class="form-group">
								        <div class="col-xs-15">
								            <div>
											
												<!-- Check for registration error -->
												<c:if test="${registrationError != null}">
											
													<div class="alert alert-danger col-xs-offset-1 col-xs-10">
														${registrationError}
													</div>
					
												</c:if>
																						
								            </div>
								        </div>
								    </div>
			
									<!-- User name -->
									<div style="margin-bottom: 25px" class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
										<form:errors path="userName" class="failed" />
										<form:input path="userName" placeholder="username (*)" class="form-control" />
									</div>
			
									<!-- Password -->
									<div style="margin-bottom: 25px" class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
										<form:errors path="password" class="failed" />
										<form:password path="password" placeholder="password (*)" class="form-control" />
									</div>
									
									<!-- Confirm Password -->
									<div style="margin-bottom: 25px" class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
										<form:errors path="matchingPassword" class="failed" />
										<form:password path="matchingPassword" placeholder="confirm password (*)" class="form-control" />
									</div>
									
			
									<!-- Register Button -->
									<div style="margin-top: 10px" class="form-group">						
										<div class="col-sm-6 controls">
											<button type="submit" class="btn btn-primary">Register</button>
										</div>
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