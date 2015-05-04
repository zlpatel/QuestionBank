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
<script
	src="${pageContext.request.contextPath}/externalresources/paper.js"></script>

<script canvas="myCanvas" type="text/paperscript">
	
	// Create a centered text item at the center of the view:
		var text = new PointText({
			point : view.center,
			justification : 'center',
			fontSize : 30,
			fillColor : 'red'
		});

		// Define a random point in the view, which we will be moving
		// the text item towards.
			var destination = Point.random() * view.size;
		
		function onFrame(event) {
			// Each frame, move the path 1/30th of the difference in position
			// between it and the destination.

			// The vector is the difference between the position of
			// the text item and the destination point:
			var vector = destination - text.position;

			// We add 1/30th of the vector to the position property
			// of the text item, to move it in the direction of the
			// destination point:
			text.position += vector / 30;

			// Set the content of the text item to be the length of the vector.
			// I.e. the distance it has to travel still:
			text.content = Math.round(vector.length);

			// If the distance between the path and the destination is less
			// than 5, we define a new random point in the view to move the
			// path to:
			if (vector.length < 5) {
				destination = Point.random() * view.size;
			}
		}
	</script> 
<!--<script canvas="myCanvas1" type="text/paperscript">
	// The amount of circles we want to make:
	var count = 150;

	// Create a symbol, which we will use to place instances of later:
	var path = new Path.Circle({
		center : [ 0, 0 ],
		radius : 10,
		fillColor : 'white',
		strokeColor : 'black'
	});

	var symbol = new Symbol(path);

	// Place the instances of the symbol:
	for (var i = 0; i < count; i++) {
		// The center position is a random point in the view:
		var center = Point.random() * view.size;
		var placedSymbol = symbol.place(center);
		placedSymbol.scale(i / count);
	}

	// The onFrame function is called up to 60 times a second:
	function onFrame(event) {
		// Run through the active layer's children list and change
		// the position of the placed symbols:
		for (var i = 0; i < count; i++) {
			var item = project.activeLayer.children[i];

			// Move the item 1/20th of its width to the right. This way
			// larger circles move faster than smaller circles:
			item.position.x += item.bounds.width / 20;

			// If the item has left the view on the right, move it back
			// to the left:
			if (item.bounds.left > view.size.width) {
				item.position.x = -item.bounds.width;
			}
		}
	}
</script>-->

</head>
<body>
	<br>
	<br>
	<center>
		<img width=500 height=80
			src="${pageContext.request.contextPath}/externalresources/logos/asu_math_header.jpg">
	</center>
	<h1>
		<span class="four">Welcome to KiSS</span> <span class="four">(Keeping
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

	<div class="canvas">
		<canvas id="myCanvas"
			style="background: none repeat scroll 0% 0% transperant; -moz-user-select: none; width: 100%; height: 20%;"
			width="100%" height="20%" data-paper-scope="1"
			data-paper-resize="true" />
	</div>


	<script>
		function formSubmit() {
			document.getElementById("loginform").submit();
		}
	</script>
	<script>
		(function($) {

			$('.one').jumble([ 110, 220, 180 ], false, false, true);
			$('.three, .two').jumble([ 255, 220, 100 ], [ 255, 255, 255 ],
					false, false, 300);
			$('.four').jumble([ 120, 190, 240 ], true, true, false);
			$('.too').jumble([ 190, 180, 110 ], [ 250, 20, 170 ], true, false,
					100);

		})(jQuery);
	</script>
</body>
</html>