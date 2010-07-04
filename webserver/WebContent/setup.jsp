<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page language="java" import="controlador.*"%>
<%@page language="java" import="java.net.*"%>

<%
	response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setDateHeader("Expires", 0); // proxy

	String usuario = (String) session.getAttribute("Usuario");
	if (session.isNew() || usuario == null) {
		if (session.getAttribute("Usuario") != null) {
			session.removeAttribute("Usuario");
		}
		session.invalidate();
		response.sendRedirect(response.encodeRedirectURL(String.format("login.jsp?urlDestino=%s", URLEncoder.encode(request.getRequestURL().toString(), "iso-8859-1"))));
		return;
	}

	ControladorMensajeria controladorMensajeria = (ControladorMensajeria) session.getAttribute("ControladorMensajeria");
	if (controladorMensajeria == null) {
		try {
			controladorMensajeria = new ControladorMensajeria();
			session.setAttribute("ControladorMensajeria", controladorMensajeria);
		} catch (Exception e) {
			session.invalidate();
			response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Server no está disponible.");
			return;
		}
	}
%>