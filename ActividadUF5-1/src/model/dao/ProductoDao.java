package model.dao;

import java.util.List;

import model.entities.Producto;

/**
 * ArticuloDaoInterface
 */
public interface ProductoDao {
	/**
	 * Metodo que devuelve el listado de prooductos
	 * @return
	 */
	List<Producto> listar();

	/**
	 * Metodo para dar de alta un producto
	 * @param p Se le pasa por argumento un objeto de la clase Producto
	 * @return Devuelve el id del producto añadido.
	 */
	String alta(Producto p);


	/**
	 * Metetodo que comprueba si existe un productoo.
	 * @param p Se le pasa por argumento el producto.
	 * @return Devuelve el producto.
	 */
	Producto exists (Producto p);
}