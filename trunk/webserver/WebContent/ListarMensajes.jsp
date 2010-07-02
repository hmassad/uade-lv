<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="controlador.ControladorMensajeria"%>
<%@page import="beans.MensajeVO"%>
<%@page import="java.util.Collection"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>Fecha</th>
		<th>Asunto</th>
		<th>Origen</th>
		<th>Estado</th>
		<th>Tipo</th>
	</tr>
	<%
		controlador.ControladorMensajeria controladorMensajeria = new ControladorMensajeria();
		Collection<MensajeVO> mensajes = controladorMensajeria.obtenerMensajesPorCasilla((String) session.getAttribute("direccion"));
		for (MensajeVO m : mensajes) {
	%>
	<tr>
		<td><%=m.getFecha()%></td>
		<td><%=m.getAsunto()%></td>
		<td><%=m.getOrigen()%></td>
		<td><%=m.getEstado()%></td>
		<td><%=m.getTipo()%></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>