package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorMensajeria;

public class ListarDireccionesPosibles extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // proxy

		ControladorMensajeria controladorMensajeria = (ControladorMensajeria) request.getSession().getAttribute("ControladorMensajeria");
		if (controladorMensajeria == null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		String casilla = (String) request.getParameter("casilla");
		if (casilla == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String comienzo = (String) request.getParameter("comienzo");
		if (comienzo == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		response.getWriter().write("<ul>");
		for (String d : controladorMensajeria.listarDireccionesPosiblesQueComiencenCon(casilla, comienzo)) {
			response.getWriter().write(String.format("<li>%s</li>", d));
		}
		response.getWriter().write("</ul>");
	}
}
