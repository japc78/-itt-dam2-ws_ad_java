package model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product
 */
@Entity
@Table(name = "producto")
public class Producto {
	@Id
	private String codigo;
	private String descripcion;
	private float precio;
	private float stock;
	private float minimo;

	public Producto() {
		super();
	}

	public Producto (String codigo, String descripcion, float precio, float stock, float minimo) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.minimo = minimo;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * @return the stock
	 */
	public float getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(float stock) {
		this.stock = stock;
	}

	/**
	 * @return the minimo
	 */
	public float getMinimo() {
		return minimo;
	}

	/**
	 * @param minimo the minimo to set
	 */
	public void setMinimo(float minimo) {
		this.minimo = minimo;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", descripcion=" + descripcion + ", minimo=" + minimo + ", precio="
				+ precio + ", stock=" + stock + "]";
	}
}