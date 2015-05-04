<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<noscript>
	<meta HTTP-EQUIV="Refresh" CONTENT="0;URL=/ExamModule/secure/jserror">
</noscript>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/questionbank.css">

<link
	href="${pageContext.request.contextPath}/externalresources/font-awesome-4.3.0/css/font-awesome.min.css"
	rel="stylesheet">

<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
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
		<li><a href="studentsRecord">Students Record</a></li>
		<li class="active"><a href="#">Add User</a></li>
		<li><a href="javascript:formSubmit()">Logout</a></li>
	</ul>
	</nav>
	<br>
	<center>
		<h4>
			Please enter the user information below.<br> <br>
			<c:if test="${not empty message}">
				<center>
					<font color="green"> <c:out value="${message}" />
					</font>
				</center>
			</c:if>
			<c:if test="${not empty error}">
				<center>
					<font color="red"> <c:out value="${error}" />
					</font>
				</center>
			</c:if>
		</h4>
	</center>
	<form id="adduserform" action="addUser" method="post"
		modelattribute="command">
		<div class="container">
			<div class="row">
				<div class="col-md-offset-5 col-md-3">
					<div class="form-login">
						<h4>Add User Account</h4>
						<input type="text" id="userName" name="userName"
							placeholder="ASURITE ID" value="${command.userName}"
							class="form-control input-sm chat-input" required="true"
							autofocus /> <br> <input type="password" id="passWord"
							name="passWord" placeholder="Password"
							value="${command.passWord}"
							class="form-control input-sm chat-input" required="true" /> <br>
						<input type="text" id="fullName" name="fullName"
							placeholder="Full Name" value="${command.fullName}"
							class="form-control input-sm chat-input" required="true" /> <br>
						<input type="radio" checked name="radios" value="2" /> Student<br>
						<input type="radio" name="radios" value="1" /> Admin<br>
						<div class="wrapper">
							<span class="group-btn">
								<center>
									<input type="submit" class="btn btn-primary btn-md"
										value="Add User" name="Add User">
								</center>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
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
</body>
</html>