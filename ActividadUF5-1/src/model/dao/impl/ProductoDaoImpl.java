package model.dao.impl;

import java.util.List;

import javax.persistence.Query;

import model.connection.Conexion;
import model.dao.ProductoDao;
import model.entities.Producto;

/**
 * ProductoDaoMySql
 */
public class ProductoDaoImpl implements ProductoDao {
	Conexion con = new Conexion();

	@Override
	@SuppressWarnings("unchecked")
	public List<Producto> listar() {
		if(!con.openConexion()) {
			return null;
		}

		//para hacer la consulta debemos de usar JPQL
		Query query = con.getEm().createQuery("select p from Producto p");
		List<Producto> listadoProductos = query.getResultList();
		return listadoProductos;
	}
}