<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
	<title>TEST APP</title>
</head>
	
	<body>
		TEST APP!!! HELLO WORLD! THIS IS A TEST FOR GIT
	</body>
	
	<a href="${pageContext.request.contextPath }/test">TEST LINK</a>
	
	<a href="${pageContext.request.contextPath }/Sub/userAction/showFormCreateSub">Create a new Sub</a>
	
	<a href="${pageContext.request.contextPath }/showMyLoginPage">Login</a>
	
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
			

</html>