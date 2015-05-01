<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thank You</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/questionbank.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<br>
<br>
	<center>
		<img width=500 height=80
			src="${pageContext.request.contextPath}/externalresources/logos/asu_math_header.jpg">
	</center>
	<h1>KiSS</h1>
	<center>
		<h4>Hi, ${name}</h4>
	</center>

	<nav class="navbar navbar-default navbar-static-top">
	<ul class="nav navbar-nav">
		<li><a href="home">Home</a></li>
		<li class="active"><a href="#">Solve Questions</a></li>
		<li><a href="javascript:formSubmit()">Logout</a></li>
	</ul>
	</nav>
	<br>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
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
	<center>
		<h3>${question.message}</h3>
		<h3>Thank you for taking the test.</h3>
		<c:choose>
			<c:when test="${question.typeId == 1}">
				<h3>Below is a video with explanation of the answer!</h3>
				<video width="400" controls> <source
					src="${question.videoLink}" type="video/mp4"> Your browser
				does not support HTML5 video. </video>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${question.correct}">
				<h3>Do you want to answer more questions?</h3>
			</c:when>
			<c:otherwise>
				<h3>Do you want to try again?</h3>
			</c:otherwise>
		</c:choose>
		<a class="btn btn-success" href="choice/true">YES</a> <a
			class="btn btn-danger" href="home">NO</a><br><br>
	</center>
</body>
</html>
