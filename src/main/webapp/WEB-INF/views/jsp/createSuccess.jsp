<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Paddle createSuccess</title>
</head>

<body>
	<H1>Creaci�n de pista correcto</H1>

	<h3>La pista (${court}) ha sido creado satisfactoriamente</h3>

	<p><a href="<c:url value='/court-list' />">Ir a Lista de pista</a></p>

	<p>UPM-MIW</p>

</body>
</html>