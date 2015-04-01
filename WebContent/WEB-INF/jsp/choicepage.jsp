<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question Bank</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<<<<<<< HEAD

<center><h1> QUESTION BANK </h1> </center>
<center><h4>Hi! ${USERNAME}</h4></center>

<ul class="nav nav-tabs">
<li><a href="home">Home</a></li>
<li class="active"><a href="#">Solve Questions</a></li>
<li><a href="javascript:formSubmit()">Logout</a> </li>
</ul>
<br>
	<h2>${message}</h2>
=======
	<h2>${question.message}</h2>
>>>>>>> refs/heads/chaitanyaBranch
	<h2>Thank you for taking the test.</h2>
<<<<<<< HEAD
	<h2>Below is a video with explanation of the answer!</h2>
	<video width="400" controls>
  		<source src="${pageContext.request.contextPath}/externalresources/videos/sample.mp4" type="video/mp4">
  		Your browser does not support HTML5 video.
	</video>
	<h2> Do you want to answer more questions?</h2>
	<a href="choice/true">YES</a> <a href="home">NO</a>
	
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
 
	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
	
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
=======
	<c:choose>
      <c:when test="${question.correct}">
      		<h2>Below is a video with explanation of the answer!</h2>
			<video width="400" controls>
	  			<source src="${pageContext.request.contextPath}/externalresources/videos/sample.mp4" type="video/mp4">
  				Your browser does not support HTML5 video.
			</video>
			<h2> Do you want to answer more questions?</h2>
	  </c:when>
      <c:otherwise>
   			<h2> Do you want to try again?</h2>
   	  </c:otherwise>
    </c:choose>
    <a href="choice/true">YES</a> <a href="home">NO</a>
>>>>>>> refs/heads/chaitanyaBranch
</body>
</html>
