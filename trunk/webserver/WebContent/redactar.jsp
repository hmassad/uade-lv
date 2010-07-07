<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file="setup.jsp"%>

<%@page language="java" import="controlador.*"%>
<%@page language="java" import="beans.*"%>
<%@page language="java" import="java.util.*"%>

<%
	String error = "";

	MensajeVO mensaje = null;
	if(request.getParameter("idMensaje") != null){
		Integer idMensaje;
		try {
			idMensaje = Integer.parseInt(request.getParameter("idMensaje"));
		}catch(Exception e){
			idMensaje = null;
		}
		if(idMensaje != null){
			String casilla = request.getParameter("casilla");
			if(casilla == null){
				for(CasillaVO c : controladorMensajeria.listarCasillasPorUsuario(usuario)){ 
					casilla = c.getDireccion();
					break;
				}
			}
			mensaje = controladorMensajeria.obtenerMensaje(casilla, idMensaje);
		} else {
			mensaje = null;
		}
	}

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
			if(request.getParameter("destinatarios") != null){
				for(String s : request.getParameter("destinatarios").split(";")){
					destinos.add(s);
				}
			}
			String asunto = request.getParameter("asunto");
			String cuerpo = request.getParameter("cuerpo");
			MensajeTipo tipo = MensajeTipo.Normal;
			if(request.getParameter("tipo") != null){
				tipo = MensajeTipo.Urgente;
			}
			if (controladorMensajeria.enviarMensaje(id, origen, destinos, asunto, cuerpo, tipo)){
				if(mensaje != null){
					controladorMensajeria.cambiarMensajeEstado(origen, id, MensajeEstado.Enviado);
				}
				response.sendRedirect(response.encodeURL(String.format("/webserver/listar?anuncio=%s", URLEncoder.encode("El mensaje ha sido enviado.", "iso-8859-1"))));
				return;
			} else {
				error = "No se pudo enviar el mensaje. Verifique las direcciones destino.";
			}
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
			if(request.getParameter("tipo") != null){
				tipo = MensajeTipo.Urgente;
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
<script type="text/javascript" src="validador.js"></script>
<script type="text/javascript">
function mostrarError(mensaje){
	var m = document.getElementById('mensaje');
	m.style.color = 'red';
	m.innerHTML = mensaje;
}

function validar(){
	if(!esVacio(document.getElementById('asunto'))){
		mostrarError('El mensaje no tiene asunto.');
		return false;
	}
	return true;
}

var xmlhttp=false;
/*@cc_on @*/
/*@if (@_jscript_version >= 5)
// JScript gives us Conditional compilation, we can cope with old IE versions.
// and security blocked creation of the objects.
 try {
  xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
 } catch (e) {
  try {
   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  } catch (E) {
   xmlhttp = false;
  }
 }
@end @*/
if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
	try {
		xmlhttp = new XMLHttpRequest();
	} catch (e) {
		xmlhttp=false;
	}
}
if (!xmlhttp && window.createRequest) {
	try {
		xmlhttp = window.createRequest();
	} catch (e) {
		xmlhttp=false;
	}
}

function mostrarDireccionesPosibles(){
	var div = document.getElementById('direccionesPosibles');
	div.style.height = 'auto';
}

function ocultarDireccionesPosibles(){
	var div = document.getElementById('direccionesPosibles');
	div.style.height = '1px';
	div.innerHTML = '';
}

function actualizarDireccionesPosibles(){
	var div = document.getElementById('direccionesPosibles');
	var casilla = document.getElementById('casilla').value;
	var comienzo = document.getElementById('destinatarios').value;

	var i = comienzo.indexOf(';');
	while(i > -1){
		comienzo = comienzo.substring(i + 1);
		i = comienzo.indexOf(';');
	}
	comienzo = comienzo.replace(/^\s*|\s*$/g,"");

    div.innerHTML = '<em>Cargando ...</em>';
    var request = '/webserver/listardireccionesposibles?casilla=' + casilla + '&comienzo=' + comienzo + '&';
    xmlhttp.open("GET", request, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
           	div.innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.send("");
}

</script>

</head>
<body>

<%@include file="encabezado.jsp"%>

<%@include file="acciones.jsp" %>

<div id="main-content">
<h2>Redactar</h2>
<form id="form" method="post" action="/webserver/redactar">
	<span id="mensaje" style="color: red;"><%= error %></span>
	<input type="hidden" id="idMensaje" name="idMensaje" value="<%
			if( mensaje != null) {
				out.write(((Integer)mensaje.getId()).toString());
			}
		%>"
	/>
	<div>
		<input type="submit" id="enviar" name="enviar" value="Enviar" onclick="return validar()"></input>
		<input type="submit" id="guardar" name="guardar" value="Guardar"></input>
		<input type="submit" id="descartar" name="descartar" value="Descartar"></input>
	</div>
<div>
<div>
	<label for="casilla">Casilla</label>
	<% if (mensaje != null) { %>
		<span id="casilla"><%= mensaje.getOrigen() %></span><input type="hidden" id="casilla" name="casilla" value="<%= mensaje.getOrigen() %>" />
	<% } else { %>
		<select id="casilla" name="casilla">
			<% for (CasillaVO casilla : controladorMensajeria.listarCasillasPorUsuario(usuario)) { %>
			<option value="<%=casilla.getDireccion()%>"><%=casilla.getDireccion()%></option>
			<% } %>
		</select>
	<% } %>
</div>
<div>
	<label for="destinatarios">Destinatarios</label>
	<input type="text" id="destinatarios" name="destinatarios"
	<%
		String destinatarios = (String) request.getParameter("destinatarios");
		if (destinatarios != null){
			 out.write("value=\"" + destinatarios + "\"");
		}
	%> onfocus="mostrarDireccionesPosibles()" onblur="ocultarDireccionesPosibles()" onkeyup="actualizarDireccionesPosibles()"
	/>
	<div id="direccionesPosibles" style="margin-left: 101px; height: 0;">&nbsp;</div>
</div>
<div>
	<label for="tipo">Urgente</label>
	<input type="checkbox" id="tipo" name="tipo" value="Urgente"
	<%
		if(mensaje != null && mensaje.getTipo() == MensajeTipo.Urgente) { %>
			<%= "checked=\"checked\"" %>
		<% } %>
	/>
</div>
<div>
	<label for="asunto">Asunto</label>
	<input type="text" id="asunto" name="asunto" 
	<%
		String asunto = (String) request.getParameter("asunto");
		if (asunto != null){
			 out.write("value=\"" + asunto + "\"");
		} else if (mensaje != null) {
			 out.write("value=\"" + mensaje.getAsunto() + "\"");
		}
	%>
	/>
</div>
</div>
<div>
	<label for="cuerpo">Cuerpo</label><br />
	<textarea id="cuerpo" name="cuerpo" style="width: 100%; height: 300px;"><%
		String cuerpo = (String) request.getParameter("cuerpo");
		if (cuerpo != null){
			 out.write(cuerpo);
		} else if (mensaje != null) {
			 out.write(mensaje.getCuerpo());
		}
	%></textarea>
</div>
</form>
</div>
</body>
</html>