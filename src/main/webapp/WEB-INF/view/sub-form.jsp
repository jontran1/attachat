<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

   
<!DOCTYPE html>
<html>
<head>
<title>Sub Creation Form</title>
</head>
<body>

	<h3>Sub Creation Form</h3>
	
	<form:form action= "saveSub" modelAttribute="sub" method="POST">
	
		<table>
			<tbody>
				<tr>
					<td><label>Sub name:</label></td>
					<td><form:input path="subName"/></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>
			</tbody>
		
		</table>
		
		<p>
			<a href="${pageContext.request.contextPath }/">Home</a>
		</p>
	
	</form:form>

</body>
</html>