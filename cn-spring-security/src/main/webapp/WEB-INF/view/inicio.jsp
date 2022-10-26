<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="role" uri="http://www.springframework.org/security/tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Bienvenido</h1>
<p>
Usuario: <role:authentication property="principal.username"/>
<br><br>
Rol: <role:authentication property="principal.authorities"/>
</p>
<br>
<a href="${pageContext.request.contextPath}/admin">Administrador</a>
<hr>
<h3>Hemos llegado!!!</h3>
<form:form action="${pageContext.request.contextPath}/logout" method="post">
<input type="submit" value="Cerrar Sesión"/>
</form:form>
</body>
</html>