<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page language="java" import="controlador.*"%>
<%@page language="java" import="java.net.*"%>
<%
	ControladorMensajeria controladorMensajeria = (ControladorMensajeria) session.getAttribute("ControladorMensajeria");
	if (controladorMensajeria == null) {
		try {
			controladorMensajeria = new ControladorMensajeria();
			session.setAttribute("ControladorMensajeria", controladorMensajeria);
		} catch (Exception e) {
			session.invalidate();
			response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Server no está disponible.");
			return;
		}
	}
	// Validar usuario e insertar los atributos a la sesión
	String usuario = (String) request.getParameter("usuario");
	
	// TODO: Agregar método de encriptación
	String password = (String) request.getParameter("password");
	String urlDestino =  (String) request.getParameter("urlDestino");
	if (urlDestino == null) {
		urlDestino = response.encodeURL("/webserver/listar.jsp");
	} else {
		urlDestino = URLDecoder.decode(urlDestino, "iso-8859-1");
	}
	String mensajeError = null;
	if (usuario != null && password != null) {
		if (controladorMensajeria.validarLogin(usuario, password)) {
			session.setAttribute("Usuario", usuario);
			response.sendRedirect(response.encodeURL(urlDestino));
			return;
		} else {
			mensajeError = "Usuario o contraseña incorrectos.";
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<link rel="stylesheet" type="text/css" href="/webserver/estilo.css" />

</head>
<body>

<div id="login">
<h2>Login</h2>
<form id="form1" method="post" action="/webserver/login?urlDestino=<%= urlDestino %>">
<% if(mensajeError != null) { %>
	<div id="mensaje"><%= mensajeError %>&nbsp;</div>
<% } %>
<div>
	<label for="usuario">Usuario</label>
	<input type="text" id="usuario" name="usuario" class="input" />
</div>
<div>
	<label for="password">Contraseña</label>
	<input type="password" id="password" name="password" class="input" />
</div>
<div id="botones">
	<input type="submit" id="submit" name="submit" value="Iniciar Sesión"></input>
	<input type="reset" id="reset" name="reset" value="Borrar"></input>
</div>
</form>
</div>
</body>
</html>