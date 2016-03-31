<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8" />
<title>Paddle Home</title>
</head>
<body>
	<H1>Página principal de JEE.ECP1 Paddle con JSP</H1>

	<h3>ViewResolver</h3>


	<h3>Ejercicios:</h3>
	<p><a href="<c:url value='/court-list'/>">- Lista de pistas</a></p>
	<p><a href="<c:url value='/create-court'/>">- Crear una pista</a></p>

	<p>UPM-MIW</p>
</body>
</html>