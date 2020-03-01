package model.services;

import java.util.List;

import model.dao.impl.ProductoDaoImpl;
import model.entities.Producto;

/**
 * ProductoService
 */
public class ProductoService {

	public List<Producto> listar(){
		//aqui podriamos poner reglas de negocio
		ProductoDaoImpl dp = new ProductoDaoImpl();
		return dp.listar();
	}
}