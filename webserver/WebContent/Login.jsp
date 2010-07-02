<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="controlador.ControladorMensajeria"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="default.css" />
</head>
<body>
<%
	if (session.getAttribute("ControladorMensajeria") == null) {
		session.setAttribute("ControladorMensajeria", new ControladorMensajeria());
	}
	ControladorMensajeria controladorMensajeria = (ControladorMensajeria) session.getAttribute("ControladorMensajeria");
	if (controladorMensajeria != null) {
		System.out.println("ControladorMensajeria conectado.");
	}
	String direccion = (String) request.getParameter("direccion");
	System.out.printf("direccion: %s\n", direccion);
	String password = (String) request.getParameter("password");
	System.out.printf("password: %s\n", password);
	if (controladorMensajeria.validarLogin(direccion, password)) {
		response.getWriter().write("credenciales válidas");
	} else {
		response.getWriter().write("credenciales no válidas");
	}
%>
<h1>Login</h1>
<form id="form1" method="post" action="Login.jsp">
<p><label for="direccion">Casilla</label> <input type="text"
	id="direccion" name="direccion" /></p>
<p><label for="password">Contraseña</label> <input type="password"
	id="password" name="password" /></p>
<p><input type="submit" id="submit" name="submit"
	value="Iniciar Sesión"></input></p>
</form>
</body>
</html>