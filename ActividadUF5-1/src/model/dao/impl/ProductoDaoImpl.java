package model.dao.impl;

import java.util.List;

import javax.persistence.EntityTransaction;
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

		//Se usa JPQL para la consulta.
		Query query = con.getEm().createQuery("from Producto p", Producto.class);
		List<Producto> listadoProductos = query.getResultList();
		return listadoProductos;
	}

	@Override
	public String alta(Producto p) {
		if(!con.openConexion()) {
			return null;
		}

		EntityTransaction et = con.getEm().getTransaction();
		et.begin();
		con.getEm().persist(p);
		et.commit();
		con.closeConexion();

		//Una vez persistido se me actualiza el objeto con su id, y podemos devolverlo
		return p.getCodigo();
	}

	@Override
	public Producto exists(Producto p) {
		if(!con.openConexion()) {
			return null;
		}

		// Se busca el product con el metodo Find.
		Producto producto = con.getEm().find(Producto.class, p.getCodigo());
		return producto;
	}
}