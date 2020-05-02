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
	
	<c:forEach var="sub" items="${subs}">
				
		<p>
			<td> Sub name: ${sub.subName} </td>
			<td> Creator: ${sub.creator.userName} </td>

		</p>
	</c:forEach>			
			

</html>