<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file="setup.jsp" %>

<%
	// Cambiar password
	if (request.getMethod().equalsIgnoreCase("POST")) {
		String password = (String) request.getParameter("password");
		controladorMensajeria.cambiarPassword(usuario, password);
		response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("Contraseña cambiada.", "iso-8859-1"))));
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cambiar Contraseña</title>

<link rel="stylesheet" type="text/css" href="estilo.css" />

<script type="text/javascript">
	function validar(){
		var p1 = document.getElementById('password1').value;
		var p2 = document.getElementById('password2').value;
		var m = document.getElementById('mensaje');
		m.style.color = 'red';
		if(p1 != p2){
			m.innerHTML = 'Las contraseñas ingresadas deben coincidir.';
			return false;
		}
		if(!(p1.length > 0) || !(p2.length > 0)){
			m.innerHTML = 'La contraseña no puede ser vacía.';
			return false;
		}
		return true;
	}
</script>
</head>
<body>

<%@include file="/encabezado.jsp" %>

<h2>Cambiar Password</h2>
<form method="post" action="/webserver/password" onsubmit="return validar()">
	<span id="mensaje"></span>
	<p>
		<label for="password1">Nueva contraseña:</label>
		<input type="password" id="password1" name="password"></input>
	</p>
	<p>
		<label for="password2">Repita contraseña:</label>
		<input type="password" id="password2"></input>
	</p>
	<p>
		<input type="submit" id="submit" value="Cambiar Contraseña"></input>
	</p>
</form>
</body>
</html>