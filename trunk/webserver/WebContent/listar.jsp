<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file="setup.jsp"%>

<%@page language="java" import="controlador.*"%>
<%@page language="java" import="beans.*" %>
<%@page language="java" import="java.util.*"%>
<%@page language="java" import="java.text.*"%>
<%@page language="java" import="enums.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mensajes (x mensajes sin leer)</title>

<link rel="stylesheet" type="text/css" href="estilo.css" />

<script type="text/javascript">
	function refresh(){
		window.location = window.location;
	}
	//setTimeout('refresh()', 5000);
	criterioOrden = 0;
	function ordenar(columna){
		if (columna != undefined){
			criterioOrden = columna;
		}
		//alert('estoy ordenando por la columna' + criterioOrden + '!');
		/*
		var tabla = document.getElementById('mensajes');
		var i;
		for(i = 1; i < tabla.childNodes.length; i++){
			var j;
			for(j = 0; j < tabla.childNodes[i].childNodes.length; j++){
				for(j = 0; j < tabla.childNodes[j].childNodes[criterioOrden].length; j++){
				}
			}
		}
		*/
	}
	function loaded(){
		ordenar();
	}</script>
</head>
<body onload="loaded();">
<%@include file="encabezado.jsp" %>
<%@include file="acciones.jsp" %>

<div id="main-content">
<table id="tabla-mensajes" width="100%">
	<%
		Collection<CasillaVO> casillas = controladorMensajeria.listarCasillasPorUsuario(usuario);
		for(CasillaVO c : casillas){
	%>
			<tr>
				<th colspan="5">Casilla: <%= c.getDireccion() %></th>
			</tr>
			<tr>
				<th><a onclick="ordenar(0);" href="#">Fecha</a></th>
				<th><a onclick="ordenar(1);" href="#">Asunto</a></th>
				<th><a onclick="ordenar(2);" href="#">Origen</a></th>
				<th><a onclick="ordenar(3);" href="#">Estado</a></th>
				<th><a onclick="ordenar(4);" href="#">Tipo</a></th>
			</tr>
			<%
			Collection<MensajeVO> mensajes = null;
			try{
				mensajes = controladorMensajeria.listarMensajesPorCasillaPorEstado(c.getDireccion(), MensajeEstado.valueOf(request.getParameter("estado")));
			}catch(Exception e){
				mensajes =  controladorMensajeria.listarMensajesPorCasilla(c.getDireccion());
			}
			for (MensajeVO m : mensajes) {
			%>
			<tr>
				<td><%= new SimpleDateFormat("d-MMM HH:mm").format(m.getFecha()) %></td>
				<%
				if(m.getEstado().equals(MensajeEstado.SinEnviar)){
				%>
					<td><a href="/webserver/redactar?idMensaje=<%= m.getId() %>&casilla=<%= c.getDireccion() %>"><% if(m.getAsunto().length() == 0) { out.write("&nbsp;"); } else { out.write(m.getAsunto()); } %></a></td>
				<%
					
				}else{
				%>
					<td><a href="/webserver/mensaje?idMensaje=<%= m.getId() %>&casilla=<%= c.getDireccion() %>"><%= m.getAsunto() %></a></td>
				<%
				}
				%>
				<td><%= m.getOrigen() %></td>
				<td><%= m.getEstado().toString() %></td>
				<td><%= m.getTipo() %></td>
			</tr>
	<%
			}
		}
	%>
</table>
</div>

<%
	String anuncio = request.getParameter("anuncio"); 
	if(anuncio != null){
		%>
		<div id="anuncio"><div id="anuncio-content"><%= anuncio %></div></div>
		<%
	}
%>

</body>
</html>