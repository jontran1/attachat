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
	
	<c:url var="createThreadLink" value="/attaThread/userAction/showFormCreateAttaThread">
		<c:param name="subName" value="${subName }"/>
	</c:url>
	
	<a href="${createThreadLink}">Create A New Thread</a>
	
	<c:forEach var="thread" items="${threads}">
	
		<c:url var="threadLink" value="/attaThread/showAttaThread">
			<c:param name="threadId" value="${thread.threadId }"/>
		</c:url>
				
		<p>
			<td>Sub name: ${thread.subName} </td>
			
			<td>
				<a href="${threadLink }">${thread.threadTitle }</a>
			</td>

			
			<td>Thread content: ${thread.threadContent }</td>

		</p>
	</c:forEach>			
			

</html>