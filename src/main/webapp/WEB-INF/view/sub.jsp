<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
	<title></title>
</head>
	
	<body>
		TEST APP!!! HELLO WORLD! THIS IS A TEST FOR GIT
	</body>
	
	<a href="${pageContext.request.contextPath }/test">TEST LINK</a>
	
	<c:forEach var="thread" items="${threads}">
				
		<p>
			<p><td> Sub name: ${thread.subName} </td></p>
			
			<td>Thread title: ${thread.threadTitle }</td>
			
			<td>Thread title: ${thread.threadContent }</td>

		</p>
	</c:forEach>			
			

</html>