<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="setup.jsp" %>

<%@page language="java" import="controlador.*"%>
<%@page language="java" import="beans.*" %>
<%@page language="java" import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Redactar</title>

<link rel="stylesheet" type="text/css" href="estilo.css" />

</head>
<body>

<%@include file="encabezado.jsp" %>

<h2>Redactar</h2>
<div>
	Información de Casilla y Usuario, logout, etc
</div>
<form id="form" method="post" action="/webserver/redactar">
<div>
	<input type="submit" id="enviar" value="Enviar"></input>
	<input type="submit" id="guardar" value="Guardar"></input>
	<input type="submit" id="descartar" value="Descartar"></input>
</div>
<div>
	<p>
		<label for="idCasilla">Casilla</label>
		<select id="idCasilla">
			<% for(CasillaVO casilla : controladorMensajeria.listarCasillasPorUsuario(usuario)) { %>
			<option value="<%= casilla.getId() %>"><%= casilla.getDireccion() %></option>
			<% } %>
		</select>
	</p>
	<p>
		<label for="destinatarios">Destinatarios</label>
		<input type="text" id="destinatarios"></input>
	</p>
	<p>
		<label for="asunto">Asunto</label>
		<input type="text" id="asunto"></input>
	</p>
</div>
<div>
	<label for="cuerpo">Cuerpo</label><br/>
	<textarea id="cuerpo"></textarea>
</div>
</form>
</body>
</html>