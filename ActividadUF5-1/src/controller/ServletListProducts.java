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
@WebServlet("/ServletProducts")
public class ServletListProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Get
	// Muestra el listado de productos.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoService ps = new ProductoService();
		List<Producto> listarProductos = ps.listar();
		request.setAttribute("listarProductos", listarProductos);
		request.getRequestDispatcher("catalogo.jsp").forward(request, response);
	}

	// Post
	// Recibe los datos para el alta y gestiona segun el mensaje de error.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Capturo los datos del formulario.
		String codigo = request.getParameter("codigo");
		String descripcion = request.getParameter("descripcion");
		Float precio = (!request.getParameter("precio").isEmpty())?Float.parseFloat(request.getParameter("precio")):0;
		Float stock = (!request.getParameter("stock").isEmpty())?Float.parseFloat(request.getParameter("stock")):0;
		Float minimo = (!request.getParameter("minimo").isEmpty())?Float.parseFloat(request.getParameter("minimo")):0;

		// Paso los datos a la entidad Producto.
		Producto p = new Producto();
		p.setCodigo(codigo);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setStock(stock);
		p.setMinimo(minimo);

		//System.out.println("Codigo servlet: " + p.getCodigo());

		// Se crea una instancia del gestor de Productos.
		ProductoService ps = new ProductoService();

		// Recogo la respuesta del alta del producto.
		String respuesta = ps.alta(p);

		// Gestiono la respuesta.
		switch (respuesta) {
			case "er-01":
				request.setAttribute("msgType", "error");
				request.setAttribute("msg", "Codigo incorrecto, vuele a intentarlo");
				request.setAttribute("producto", p);
				request.getRequestDispatcher("alta.jsp").forward(request, response);
				break;
			case "er-02":
				request.setAttribute("msgType", "error");
				request.setAttribute("msg", "El codigo ya existe en la Bd, vuelve a intentarlo");
				request.setAttribute("producto", p);
				request.getRequestDispatcher("alta.jsp").forward(request, response);
				break;
			case "er-03":
				request.setAttribute("msgType", "error");
				request.setAttribute("msg", "El precio debe tener un valor mayor que 0");
				request.setAttribute("producto", p);
				request.getRequestDispatcher("alta.jsp").forward(request, response);
				break;
			case "er-04":
				request.setAttribute("msgType", "error");
				request.setAttribute("msg", "El campo descripcion no puede estar vacio");
				request.setAttribute("producto", p);
				request.getRequestDispatcher("alta.jsp").forward(request, response);
				break;
			default:
				List<Producto> listarProductos = ps.listar();
				request.setAttribute("msgType", "ok");
				request.setAttribute("msg", "Producto dado de alta con exito");
				request.setAttribute("listarProductos", listarProductos);
				request.setAttribute("pAdd", p.getCodigo());
				request.getRequestDispatcher("catalogo.jsp").forward(request, response);
				break;
		}
	}
}
