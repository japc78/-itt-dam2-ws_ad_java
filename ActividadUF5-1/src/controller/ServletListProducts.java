package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Producto;
import model.services.ProductoService;

/**
 * Servlet implementation class ServletListProducts
 */
@WebServlet("/ServletListProducts")
public class ServletListProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoService ps = new ProductoService();
		List<Producto> listarProductos = ps.listar();
		request.setAttribute("listarProductos", listarProductos);
		request.getRequestDispatcher("catalogo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ya que siempre quiero mostrar la lista de personas
		//se la pedimos al gestor y se la pasamos a la vista (jsp)

	}
}
