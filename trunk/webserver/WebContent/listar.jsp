<%@page import="com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter"%>
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
<title>Mensajes<%
	int mensajesNoLeidos = controladorMensajeria.obtenerCantidadMensajesNoLeidosDeUsuario(usuario);
	if(mensajesNoLeidos>0){
		out.write(" (" + mensajesNoLeidos + " mensajes sin leer)");
	}
%></title>

<link rel="stylesheet" type="text/css" href="estilo.css" />

<script type="text/javascript">
	//setTimeout('window.location.reload(true);', 10000);
</script>
</head>
<body>
<%@include file="encabezado.jsp" %>
<%@include file="acciones.jsp" %>

<div id="main-content">
<table id="tabla-mensajes" width="100%">
<%
	MensajeEstado estado;
	try{
	 	estado = MensajeEstado.valueOf(request.getParameter("estado"));
	} catch(Exception e){
		estado = null;
	}
	
	class MensajeConCasilla extends MensajeVO {
		private String casilla;

		public String getCasilla(){
			return casilla;
		}
		
		public void setCasilla(String casilla) {
			this.casilla = casilla;
		}
		
		public boolean equals(Object otro){
			if(otro instanceof MensajeConCasilla){
				return this.getId() == ((MensajeConCasilla) otro).getId();
			}
			return false;
		}
	}
	
	List<MensajeConCasilla> mensajes = new ArrayList<MensajeConCasilla>();
	
	for(CasillaVO c : controladorMensajeria.listarCasillasPorUsuario(usuario)){
		Collection<MensajeVO> mensajesEnCasilla;
		if(estado != null){
			mensajesEnCasilla = controladorMensajeria.listarMensajesPorCasillaPorEstado(c.getDireccion(), estado);
		}else{
			mensajesEnCasilla =  controladorMensajeria.listarMensajesPorCasilla(c.getDireccion());
		}
		for(MensajeVO mensaje : mensajesEnCasilla){
			MensajeConCasilla mc = new MensajeConCasilla();
			mc.setId(mensaje.getId());
			mc.setFecha(mensaje.getFecha());
			mc.setAsunto(mensaje.getAsunto());
			mc.setOrigen(mensaje.getOrigen());
			mc.setDestinos(mensaje.getDestinos());
			mc.setEstado(mensaje.getEstado());
			mc.setTipo(mensaje.getTipo());
			mc.setCasilla(c.getDireccion());
			mensajes.add(mc);
		}
	}

	// Aplicar criterio de orden
	CriterioOrden criterioOrden;
	try{
		criterioOrden = CriterioOrden.valueOf(request.getParameter("criterioOrden"));
	} catch(Exception e) {
		criterioOrden = CriterioOrden.Default;
	}

	System.out.printf("antes: %s\n", mensajes);
	List<MensajeConCasilla> mensajesOrdenados = new ArrayList<MensajeConCasilla>();
	MensajeConCasilla minimo;
	while (mensajes.size() > 0) {
		minimo = mensajes.get(0);
		for(int i = 0; i < mensajes.size(); i++) {
			MensajeConCasilla actual = mensajes.get(i);
	    	switch(criterioOrden){
		    	case Fecha:
		    		if(minimo.getFecha().compareTo(actual.getFecha()) > 0){
		    			minimo = actual;
		    		}
		    		break;
		    	case Asunto:
		    		if(minimo.getAsunto().compareToIgnoreCase(actual.getAsunto()) > 0) {
	    				minimo = actual;
    				}
		    		break;
		    	case Casilla:
		    		if(minimo.getCasilla().compareTo(actual.getCasilla()) > 0) {
	    				minimo = actual;
					}
		    		break;
		    	case Estado:
		    		if(minimo.getEstado().compareTo(actual.getEstado()) > 0) {
	    				minimo = actual;
					}
		    		break;
				case Origen:
					if(actual.getOrigen() == null){
						minimo = actual;
					}
		    		if(minimo.getOrigen() != null && minimo.getOrigen().compareTo(actual.getOrigen()) > 0) {
		    			minimo = actual;
		    		}
		    		break;
		    	case Tipo:
		    		if(minimo.getTipo().compareTo(actual.getTipo()) > 0) {
		    			minimo = actual;
		    		}
		    		break;
		    	default:
		    		if(minimo.getId() > actual.getId()){
	    				minimo = actual;
	    			}
		    		break;
	    	}
		}
		mensajes.remove(minimo);
		mensajesOrdenados.add(minimo);
	}
	mensajes = mensajesOrdenados;
	System.out.printf("despues: %s\n", mensajes);
	
	// Mostrar
	String estadoParameter = estado == null ? "" : "estado=" + estado + "&";
%>
	<tr>
		<th><a href="/webserver/listar?criterioOrden=Fecha&<%= estadoParameter %>">Fecha</a></th>
		<th><a href="/webserver/listar?criterioOrden=Asunto&<%= estadoParameter %>">Asunto</a></th>
		<th><a href="/webserver/listar?criterioOrden=Origen&<%= estadoParameter %>">Origen</a></th>
		<th><a href="/webserver/listar?criterioOrden=Destinos&<%= estadoParameter %>">Destinos</a></th>
		<th><a href="/webserver/listar?criterioOrden=Estado&<%= estadoParameter %>">Estado</a></th>
		<th><a href="/webserver/listar?criterioOrden=Tipo&<%= estadoParameter %>">Tipo</a></th>
		<th><a href="/webserver/listar?criterioOrden=Casilla&<%= estadoParameter %>">Casilla</a></th>
	</tr>
<%
	for(int i = 0; i < mensajes.size(); i++){
    	MensajeConCasilla m = mensajes.get(i);
%>
	<tr>
		<td><%= new SimpleDateFormat("d-MMM HH:mm").format(m.getFecha()) %></td>
		<% if(m.getEstado().equals(MensajeEstado.SinEnviar)){ %>
			<td><a href="/webserver/redactar?idMensaje=<%= m.getId() %>&casilla=<%= m.getCasilla() %>"><%= m.getAsunto().length() == 0 ? "<em>Sin Asunto</em>" : m.getAsunto() %></a></td>
		<% } else { %>
			<td><a href="/webserver/mensaje?idMensaje=<%= m.getId() %>&casilla=<%= m.getCasilla() %>"><%= m.getAsunto() %></a></td>
		<% } %>
		<td><%= m.getOrigen() == null ? "" : m.getOrigen() %></td>
		<td><%= m.getDestinos() %></td>
		<td><%= m.getEstado().toString() %></td>
		<td><%= m.getTipo() %></td>
		<td><%= m.getCasilla() %></td>
	</tr>
<%	} %>
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