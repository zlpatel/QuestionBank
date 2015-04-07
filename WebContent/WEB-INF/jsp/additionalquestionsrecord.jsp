<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
...
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Record</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/externalresources/questionbank.css">
	
<!-- Latest compiled and minified JavaScript -->
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/externalresources/fMath/fonts/fmathFormulaFonts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/externalresources/fMath/js/fmathFormulaC.js"></script>
<script>
		
		FMATH.ApplicationConfiguration.setFolderUrlForFonts("fonts");
		FMATH.ApplicationConfiguration.setFolderUrlForGlyphs("glyphs");

		function convertFromLatexToMathML(inputid,outputid)
		{
			var latexInput = document.getElementById(inputid);
			var latexCanvas=document.getElementById(outputid);
			var formula = new FMATH.MathMLFormula();
			var mathml = formula.convertLatexToMathML(latexInput.value);
			formula.drawImage(latexCanvas, mathml);
		}
	</script>

</head>
<body>
<center><h1 class="bg-primary"> QUESTION BANK </h1> </center>
<center><h4>Hi! ${USERNAME}</h4></center>

<nav class="navbar navbar-default navbar-static-top">
<ul class="nav navbar-nav">
		<li><a href="../home">Home</a></li>
		<li class="active"><a href="#">Students Record</a></li>
		<li><a href="javascript:formSubmit()">Logout</a></li>
	</ul>
	</nav>
	<br>
	<form:form method="GET">
	Category-wise statistics for student: ${studentName}
		<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead>
				<tr class="success">
					<th class="text-center">Question Name</th>
					<th class="text-center">Marked Answer</th>
					<th class="text-center">Date-Time</th>
					<th class="text-center">Result</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${additionalQuestionsRecordList}" var="additionalQuestionsRecord" varStatus="loop">
					<tr>
						<td onmouseover="convertFromLatexToMathML('latex${loop.index +1}','latexCanvas${loop.index +1}')"><input type="hidden" id="latex${loop.index +1}"
					value="${additionalQuestionsRecord.questionName}" style="width: 500px; height: 20px"></input>
					<canvas id="latexCanvas${loop.index +1}" width="100px" height="20px"
						style="border:0px solid #000000;"></canvas></td>
						<%-- <td class="text-center">${additionalQuestionsRecord.questionName}</td> --%>
						<td onmouseover="convertFromLatexToMathML('latex_answer${loop.index +1}','latexCanvas_answer${loop.index +1}')"><input type="hidden" id="latex_answer${loop.index +1}"
					value="${additionalQuestionsRecord.markedAnswer}" style="width: 500px; height: 20px;"></input>
					<canvas id="latexCanvas_answer${loop.index +1}" width="100px" height="20px" size="10px"
						style="border:0px solid #000000;"></canvas></td>
						<%-- <td class="text-center">${additionalQuestionsRecord.markedAnswer}</td> --%>
						<td class="text-center"><fmt:formatDate value="${additionalQuestionsRecord.dateTime}" pattern="MM/dd/yyyy HH:mm:ss" /></td>
						<td class="text-center">${additionalQuestionsRecord.result}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</form:form>
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