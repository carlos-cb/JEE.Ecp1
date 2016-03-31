<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="MIW. Spring Paddle">
<meta name="author" content="Bei Chu">
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/jumbotron.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/blog.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/signin.css' />" rel="stylesheet">
<title>Spring Paddle CreateCourt</title>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value='/home'/>">Spring Paddle</a>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<form:form action="create-court" modelAttribute="court"
					class="form-horizontal form-signin">
					<h2 class="form-signin-heading">Create Court</h2>
					<label for="courtId" class="control-label">CourtId</label>
					<input name="courtId" type="number" placeholder="CourtId"
						class="form-control" required autofocus value="${court.courtId}" />
					<form:errors path="courtId" class="control-label" />
					<label for="active" class="control-label">Active</label>
					<input name="active" type="text" maxlength="30" class="form-control"
						placeholder="Active" required value="${court.active}" />
					<form:errors path="active" class="control-label" />
					<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
						in</button>
				</form:form>
			</div>
			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="sidebar-module sidebar-module-inset">
					<h4>Acerca de los formularios</h4>
					<p>Bootstrap 3 define estilos adecuados para todos y cada uno
						de los campos de formulario existentes. Los campos de tipo
						&lt;input&gt; son los más numerosos, ya que con HTML5 la lista se
						ha ampliado a text, password, datetime, datetime-local, date,
						month, time, week, number, email, url, search, tel, y color.</p>
				</div>
			</div>
		</div>
		<hr>
		<footer>
			<p>&nbsp;&nbsp;&copy; UPM-MIW</p>
		</footer>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="<c:url value='/static/js/bootstrap.js' />"></script>
</body>
</html>