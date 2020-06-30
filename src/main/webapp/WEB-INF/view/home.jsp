<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
	<title>Atta Chat</title>
</head>

	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<body>
	
	<a href="${pageContext.request.contextPath }/Sub/userAction/showFormCreateSub">Create a new Sub</a>
	
	<c:if test="${!pageContext.request.userPrincipal.authenticated }">
		<a href="${pageContext.request.contextPath }/showMyLoginPage">Login</a>
	</c:if> 
	<c:if test="${pageContext.request.userPrincipal.authenticated }">
		<a href="${pageContext.request.contextPath }/logout">Logout</a>
	</c:if> 
	
	<c:if test="${pageContext.request.userPrincipal.authenticated }">
		<c:url var="userComments" value="/Comment/user/showComments">
			<c:param name="userName" value="${pageContext.request.userPrincipal.name }"/>
		</c:url>
		<a href="${userComments }">Comment History</a>
	</c:if> 
	
	
	
	<c:forEach var="sub" items="${subs}">
	
		<c:url var="subLink" value="/Sub/showSub">
			<c:param name="subName" value="${sub.subName }"/>
		</c:url>
				
		<p>
			<td> Sub name: ${sub.subName} </td>
			<td>
				<a href="${subLink }">${sub.subName} </a>
			</td>
			<td> Creator: ${sub.creator} </td>
			<td> Population count:  ${sub.numberOfFollowers } </td>

		</p>
	</c:forEach>			
			

	<c:if test="${pageContext.request.userPrincipal.authenticated }">
		<p>My subs</p>
			<c:forEach var="sub" items="${userSubs}">
				<c:url var="subLink" value="/Sub/showSub">
				<c:param name="subName" value="${sub.subName }"/>
				</c:url>
						
				<p>
					<td> Sub name: ${sub.subName} </td>
				<td>
					<a href="${subLink }">${sub.subName} </a>
				</td>
				<td> Creator: ${sub.creator} </td>
				<td> Population count:  ${sub.numberOfFollowers } </td>
				
				</p>
			</c:forEach>
		<c:if test="${empty userSubs}">
		<p>You are not currently following any subs.</p>
		</c:if>
		
	
	</c:if> 	
	
	</body>
	
</html>