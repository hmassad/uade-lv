<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file="setup.jsp"%>

<%@page language="java" import="controlador.*"%>
<%@page language="java" import="beans.*" %>
<%@page language="java" import="java.util.*"%>

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
<%
	String anuncio = request.getParameter("anuncio"); 
	if(anuncio != null){
		%>
		<div id="anuncio"><div id="anuncio-content"><%= anuncio %></div></div>
		<%
	}
%>
<div id="acciones">
<ul>
<li>
	<a href="redactar.jsp">Redactar</a>
</li>
<li>
	<a href="listar.jsp?e=noleidos&criterioOrden=0">No Leídos</a>
</li>
<li>
	<a href="listar.jsp?e=leidos&criterioOrden=0">Leídos</a>
</li>
<li>
	<a href="listar.jsp">Todos</a>
</li>
</ul>
</div>
<div id="main-content">
<table id="tabla-mensajes">
	<%
		Collection<CasillaVO> casillas = controladorMensajeria.listarCasillasPorUsuario(usuario);
		for(CasillaVO c:casillas){
	%>
			<tr>
				<th colspan="5">Casilla: <%= c.getDireccion() %></th>
			</tr>
			<tr>
				<th onclick="ordenar(0);">Fecha</th>
				<th onclick="ordenar(1);">Asunto</th>
				<th onclick="ordenar(2);">Origen</th>
				<th onclick="ordenar(3);">Estado</th>
				<th onclick="ordenar(4)">Tipo</th>
			</tr>
	<%
			Collection<MensajeVO> mensajes = controladorMensajeria.listarMensajesPorCasilla(c.getDireccion());
			for (MensajeVO m : mensajes) {
	%>
			<tr>
				<td><%= m.getFecha() %></td>
				<td><%= m.getAsunto() %></td>
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
</body>
</html>