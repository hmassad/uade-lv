<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<div id="encabezado">
	<div id="encabezado-izquierda">
		Usuario: <%= (String) session.getAttribute("Usuario") %>
	</div>
	<div id="encabezado-derecha">
		<a href="/webserver/password">Cambiar Contraseña</a>&nbsp;|&nbsp;
		<a href="/webserver/logout">Cerrar Sesión</a>
	</div>
	<div id="encabezado-fin">&nbsp;</div>
</div>