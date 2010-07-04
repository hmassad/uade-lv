package servlets;

import java.io.IOException;
import java.rmi.NotBoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorMensajeria;

public class ListarDireccionesPosibles extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String direccion = (String) request.getParameter("direccion");
		if (direccion == null) {
			return;
		}

		ControladorMensajeria controladorMensajeria = (ControladorMensajeria) request.getSession().getAttribute("ControladorMensajeria");
		if (controladorMensajeria == null) {
			//return;
			try {
				controladorMensajeria = new ControladorMensajeria();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}

		String s = "";
		for (String d : controladorMensajeria.listarDireccionesPosibles(direccion)) {
			if (s.length() > 0) {
				s += ";";
			}
			s += d;
		}
		response.getWriter().append(s);
	}
}
