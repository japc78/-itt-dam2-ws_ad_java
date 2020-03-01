package model.dao;

import java.util.List;

import model.entities.Producto;

/**
 * ArticuloDaoInterface
 */
public interface ProductoDao {
	List<Producto> listar();
}