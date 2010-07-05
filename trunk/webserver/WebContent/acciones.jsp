<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="enums.*"%>
<div id="acciones">
<ul>
	<li>
		<a href="/webserver/redactar">Redactar</a>
	</li>
</ul>
<ul>
	<li>
		<a href="/webserver/listar?estado=<%= MensajeEstado.NoLeido.toString() %>">No Leídos</a>
	</li>
	<li>
		<a href="/webserver/listar?estado=<%= MensajeEstado.Leido.toString() %>">Leídos</a>
	</li>
	<li>
		<a href="/webserver/listar?estado=<%= MensajeEstado.Enviado.toString() %>">Enviados</a>
	</li>
	<li>
		<a href="/webserver/listar?estado=<%= MensajeEstado.ParaBorrar.toString() %>">Borrados</a>
	</li>
	<li>
		<a href="/webserver/listar?estado=<%= MensajeEstado.SinEnviar.toString() %>">Sin Enviar</a>
	</li>
	<li>
		<a href="/webserver/listar">Todos</a>
	</li>
</ul>
</div>
