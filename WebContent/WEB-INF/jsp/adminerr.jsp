<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<!-- Latest compiled and minified CSS -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/questionbank.css">

<!-- Latest compiled and minified JavaScript -->
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
<!-- Latex to image -->

</head>
<body>
	<center>
		<img width=500 height=80
			src="${pageContext.request.contextPath}/externalresources/logos/asu_math_header.jpg">
	</center>
	<h1 class=" bg-primary">KiSS</h1>
	<center>
		<h4>Hi, ${name}</h4>
	</center>

	<nav class="navbar navbar-default navbar-static-top">
	<ul class="nav navbar-nav">
		<li><a href="home">Home</a></li>
		<li class="active"><a href="#">Students Record</a></li>
		<li><a href="javascript:formSubmit()">Logout</a></li>
	</ul>
	</nav>
	<br> ${message}
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<a class="btn btn-info" href="../studentsRecord">Back</a>
	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</body>
</html>
