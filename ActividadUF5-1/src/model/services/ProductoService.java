package model.services;

import java.util.List;

import model.dao.impl.ProductoDaoImpl;
import model.entities.Producto;

/**
 * Gestor de Producto.
 */
public class ProductoService {
	// Se crea un objeto de la clase ProductoDaoImpl para realizar las operaciones necesarias en base a los metodos de la clase.
	private ProductoDaoImpl dp = new ProductoDaoImpl();

	public List<Producto> listar(){
		//aqui podriamos poner reglas de negocio
		return dp.listar();
	}

	/**
	 * Metodo que gestiona el alta del producto
	 * @param p Se le pasa por argumento el producto a dar de alta.
	 * @return Retorna er-01 si el codigo introudcido no es correcto, er-02 si el hay un producto con el mismo código, er-03 si el precio es igual a 0, er-04 si el campo descripción está vacio, y ok-01 si el producto se ha dado de alta correctamente.
	 */
	public String alta(Producto p) {
		System.out.println("Codigo: " + p.getCodigo());
		if (!p.getCodigo().matches("[a-zA-Z]{3}\\d")) {
			return "er-01";
		} else if (exists(p)) {
			return "er-02";
		} else if (p.getPrecio() == 0){
			return "er-03";
		} else if (p.getDescripcion().isEmpty()){
			return "er-04";
		} else {
			dp.alta(p);
			return "ok-01";
		}
	}

	/**
	 * Metodo que comprueba si el producuto a introducir ya se encuentra en la BBDD.
	 * @param p Se le pasa por argumento el producto.
	 * @return Devuelve true si el producto existe y false si no existe.
	 */
	private boolean exists(Producto p) {
		if(dp.exists(p) != null) {
			return true;
		} else {
			return false;
		}
	}
}