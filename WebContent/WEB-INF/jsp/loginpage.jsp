<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
	
</head>
<body>

<h1>Login</h1>

<%-- <div id="login-error">${error}</div> --%>

	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
      <font color="red">
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
    </c:if>

<form action=" <c:url value='../../j_spring_security_check'/> " method="post" >

<p>
	
	<input id="j_username" name="j_username" placeholder="username" type="text" />
</p>

<p>
	<input id="j_password" name="j_password" placeholder="password" type="password" />
</p>

<input class="btn btn-lg btn-primary" type="submit" value="Login"/>

<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
</form>

</body>
</html>