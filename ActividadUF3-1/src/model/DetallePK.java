package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detalle database table.
 * 
 */
@Embeddable
public class DetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int numero;

	@Column(insertable=false, updatable=false)
	private String codigo;

	public DetallePK() {
	}
	public int getNumero() {
		return this.numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetallePK)) {
			return false;
		}
		DetallePK castOther = (DetallePK)other;
		return 
			(this.numero == castOther.numero)
			&& this.codigo.equals(castOther.codigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numero;
		hash = hash * prime + this.codigo.hashCode();
		
		return hash;
	}
}