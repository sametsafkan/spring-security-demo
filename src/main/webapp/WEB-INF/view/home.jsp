<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h2>Welcome Home</h2>
	<hr>
	<p>
		User :
		<security:authentication property="principal.username" />
		<br> <br> Roles :
		<security:authentication property="principal.authorities" />
	</p>

	<p>
		<security:authorize access="hasRole('MANAGER')">
			<a href="${pageContext.request.contextPath}/management/">Management</a>
		</security:authorize>
		<br> <br>
		<security:authorize access="hasRole('ADMIN')">
				 <a
			href="${pageContext.request.contextPath}/system/">System
			Administration</a>
		</security:authorize>
	</p>

	<hr>
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>