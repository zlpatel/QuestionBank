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
	<h2>${question.message}</h2>
	<h2>Thank you for taking the test.</h2>
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
</body>
</html>