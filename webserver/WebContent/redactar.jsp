<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file="setup.jsp"%>

<%@page language="java" import="controlador.*"%>
<%@page language="java" import="beans.*"%>
<%@page language="java" import="java.util.*"%>

<%
	if (request.getMethod().equalsIgnoreCase("POST")) {
		if (request.getParameter("enviar") != null) {
			Integer id = null;
			try{
				id = Integer.parseInt(request.getParameter("idMensaje"));
			}catch(Exception e){
				id = null;
			}
			String origen = request.getParameter("casilla");
			Collection<String> destinos = new ArrayList<String>();
			if(request.getParameter("destinos") != null){
				for(String s : request.getParameter("destinos").split(";,\n")){
					destinos.add(s);
				}
			}
			String asunto = request.getParameter("asunto");
			String cuerpo = request.getParameter("cuerpo");
			MensajeTipo tipo = MensajeTipo.Normal;
			try{
				tipo = MensajeTipo.valueOf(request.getParameter("tipo"));
			}catch(Exception e){
				tipo = MensajeTipo.Normal;
			}
			controladorMensajeria.enviarMensaje(id, origen, destinos, asunto, cuerpo, tipo);
			response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("El mensaje ha sido enviado.", "iso-8859-1"))));
			return;
		} else if (request.getParameter("guardar") != null) {
			Integer id = null;
			try{
				id = Integer.parseInt(request.getParameter("idMensaje"));
			}catch(Exception e){
				id = null;
			}
			String direccion = request.getParameter("casilla");
			String asunto = request.getParameter("asunto");
			String cuerpo = request.getParameter("cuerpo");
			MensajeTipo tipo = MensajeTipo.Normal;
			try{
				tipo = MensajeTipo.valueOf(request.getParameter("tipo"));
			}catch(Exception e){
				tipo = MensajeTipo.Normal;
			}
			controladorMensajeria.guardarMensaje(id, direccion, asunto, cuerpo, tipo);
			response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("El mensaje ha sido guardado.", "iso-8859-1"))));
			return;
		} else if (request.getParameter("descartar") != null) {
			response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("El mensaje ha sido descartado.", "iso-8859-1"))));
			return;
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="enums.MensajeTipo"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Redactar</title>

<link rel="stylesheet" type="text/css" href="estilo.css" />

</head>
<body>

<%@include file="encabezado.jsp"%>

<h2>Redactar</h2>
<form id="form" method="post" action="/webserver/redactar">
	<input type="hidden" id="idMensaje" name="idMensaje" value="<%
			String idMensaje = request.getParameter("idMensaje");
			if( idMensaje != null) {
				out.write(idMensaje);
			}
		%>"
	/>
	<div>
		<input type="submit" id="enviar" name="enviar" value="Enviar"></input>
		<input type="submit" id="guardar" name="guardar" value="Guardar"></input>
		<input type="submit" id="descartar" name="descartar" value="Descartar"></input>
	</div>
<div>
<p><label for="casilla">Casilla</label> <select id="casilla" name="casilla">
	<%
		for (CasillaVO casilla : controladorMensajeria.listarCasillasPorUsuario(usuario)) {
	%>
	<option value="<%=casilla.getDireccion()%>" ><%=casilla.getDireccion()%></option>
	<%
		}
	%>
</select></p>
<p><label for="destinatarios">Destinatarios</label> <input
	type="text" id="destinatarios"></input></p>
<p><label for="asunto">Asunto</label> <input type="text" id="asunto"></input>
</p>
</div>
<div><label for="cuerpo">Cuerpo</label><br />
<textarea id="cuerpo"></textarea></div>
</form>
</body>
</html>