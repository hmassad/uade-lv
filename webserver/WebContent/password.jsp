<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="setup.jsp"%>

<%
	// Cambiar password
	if (request.getMethod().equalsIgnoreCase("POST")) {
		String password = (String) request.getParameter("password");
		controladorMensajeria.cambiarPassword(usuario, password);
		response.sendRedirect(response.encodeURL(String.format(
				"/webserver/listar?anuncio=%s", URLEncoder.encode(
						"Contraseña cambiada.", "iso-8859-1"))));
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cambiar Contraseña</title>

<link rel="stylesheet" type="text/css" href="estilo.css" />

<script type="text/javascript" src="validador.js"></script>
<script type="text/javascript">
	function mostrarError(mensaje){
		var m = document.getElementById('mensaje');
		m.style.color = 'red';
		m.innerHTML = mensaje;
	}

	function validar(){
		var p1 = document.getElementById('password1');
		var p2 = document.getElementById('password2');
		if(!sonIguales(p1, p2)){
			mostrarError('Las contraseñas ingresadas deben coincidir.');
			return false;
		}
		if(!esVacio(p1)){
			mostrarError('La contraseña no puede ser vacía.');
			return false;
		}
		return true;
	}
</script>
</head>
<body>

<%@include file="encabezado.jsp"%>
<%@include file="acciones.jsp" %>

<div id="main-content">
<h2>Cambiar Password</h2>
<form method="post" action="/webserver/password" onsubmit="return validar()">
<div id="mensaje" style="height: 3em;">&nbsp;</div>
<div style="height: 3em;">
	<label for="password1" style="width: 150px;">Nueva contraseña:</label>
	<input type="password" id="password1" name="password"></input>
</div>
<div style="height: 3em;">
	<label for="password2" style="width: 150px;">Repita contraseña:</label>
	<input type="password" id="password2"></input>
</div>
<div style="margin-top: 20px;">
	<input type="submit" id="submit" value="Cambiar Contraseña"></input>
</div>
</form>
</div>
</body>
</html>