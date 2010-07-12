<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page language="java" import="beans.*"%>
<%@page language="java" import="java.net.*"%>
<%@page language="java" import="java.util.*"%>
<%@page import="java.text.*"%>

<%@include file="setup.jsp"%>
<%
	Integer idMensaje;
	try {
		idMensaje = Integer.parseInt(request.getParameter("idMensaje"));
	}catch(Exception e){
		idMensaje = null;
	}

	String casilla = request.getParameter("casilla");
	if(idMensaje == null || casilla == null){
		response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("No se especificó mensaje.", "iso-8859-1"))));
		return;
	}

	MensajeVO mensaje = controladorMensajeria.obtenerMensaje(casilla, idMensaje);
	if(mensaje == null){
		response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("El mensaje no puede ser visualizado.", "iso-8859-1"))));
		return;
	}

	if(request.getParameter("borrar") != null){
		controladorMensajeria.cambiarMensajeEstado(casilla, idMensaje, MensajeEstado.ParaBorrar);
		response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("El mensaje ha sido borrado.", "iso-8859-1"))));
		return;
	}

	if(request.getParameter("bloquear") != null){
		controladorMensajeria.bloquearCasilla(usuario, mensaje.getOrigen());
		response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("La casilla ha sido bloqueada.", "iso-8859-1"))));
		return;
	}

	if(mensaje.getEstado().equals(MensajeEstado.NoLeido)){
		controladorMensajeria.cambiarMensajeEstado(casilla, idMensaje, MensajeEstado.Leido);
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mensaje</title>
<link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>
<%@include file="encabezado.jsp" %>
<%@include file="acciones.jsp" %>
<div id="main-content">
<form method="post" action="/webserver/mensaje?casilla=<%= casilla %>&idMensaje=<%= idMensaje %>&">
	<input type="submit" id="borrar" name="borrar" value="Borrar" />
	<input type="submit" id="bloquear" name="bloquear" value="Bloquear" />
</form>
<div id="mensaje-recuadro">
	<div><label>Fecha</label> <%= new SimpleDateFormat("d-MMM HH:mm").format(mensaje.getFecha()) %></div>
	<div><label>Desde</label> <%= mensaje.getOrigen() %></div>
	<div><label>Enviado a</label>
		<% for(String s : mensaje.getDestinos()) { %>
			<%= s + ";&nbsp;" %>
		<% } %>
	</div>
	<div><label>Tipo</label> <%= mensaje.getTipo() %></div>
	<div><label>Asunto</label> <%= mensaje.getAsunto() %></div>
	<div><label>Cuerpo</label><br/><%= mensaje.getCuerpo().replaceAll("\r\n", "<br/>") %></div>
	</div>
</div>
</body>
</html>
