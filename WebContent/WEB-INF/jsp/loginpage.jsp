<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
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
	src="${pageContext.request.contextPath}/externalresources/bootstrap/js/jquery-1.9.1.js"></script>
<script
	src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script> -->
<script
	src="${pageContext.request.contextPath}/externalresources/jumble/jumble.js"></script>
</head>
<body>
	<br>
	<br>
	<center>
		<img width=500 height=80
			src="${pageContext.request.contextPath}/externalresources/logos/asu_math_header.jpg">
	</center>
	<h1>
		<span class="two">Welcome to KiSS</span> <span class="four">(Keeping
			in Summer Shape)</span>
	</h1>
	<c:if test="${not empty error}">
		<center>
			<font color="red"> <c:out value="${error}" />
			</font>
		</center>
	</c:if>

	<form id="loginform"
		action=" <c:url value='../../j_spring_security_check'/> "
		method="post">

		<div class="container">
			<div class="row">
				<div class="col-md-offset-5 col-md-3">
					<div class="form-login">
						<h4>Sign-In</h4>
						<input type="text" id="j_username" name="j_username"
							class="form-control input-sm chat-input"
							placeholder="ASURITE user ID" required autofocus /> <br> <input
							type="password" id="j_password" name="j_password"
							class="form-control input-sm chat-input" placeholder="password"
							required /> <br>
						<div class="wrapper">
							<span class="group-btn">
								<center>
									<input type="submit" class="btn btn-primary btn-md"
										value="Login" name="Login">
									<!-- <a href="javascript:formSubmit()"
										class="btn btn-primary btn-md">login <i
										class="fa fa-sign-in"></i></a> -->
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

	<script>
		function formSubmit() {
			document.getElementById("loginform").submit();
		}
	</script>
	<script>
		(function($) {

			$('.one').jumble([ 110, 220, 180 ], false, false, true);
			$('.three, .two').jumble([ 255, 220, 100 ], [ 255, 255, 255 ],
					false, false, 100);
			$('.four').jumble([ 120, 190, 240 ], true, true, false, 100);
			$('.too').jumble([ 190, 180, 110 ], [ 250, 20, 170 ], true, false,
					100);

		})(jQuery);
	</script>
</body>
</html>