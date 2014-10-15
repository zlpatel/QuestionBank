<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions</title>
</head>
<body>
<form:form method="POST" modelattribute="command">
	<table>
		<tr>
			<td>
				<label>${command.statement}</label>
				  
			</td>
		</tr>
		<tr>
			<td>
				<ol type="a">  
            		<form:radiobuttons element="li" path="selectedOption" items="${optionList}" />  
        		</ol>
			</td>
		</tr>
		<tr>
        	<td><input type="submit" name="submit" value="Submit"></td>
        </tr>
		
	</table>		
</form:form>
</body>
</html>