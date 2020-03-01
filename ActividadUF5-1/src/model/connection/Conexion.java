package model.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que establece conexion con la BBDD.
 */
public class Conexion {
	private EntityManager em;

	/**
	 * Metodo que establece conexion con la BBDD.
	 * @return Retorna true si la conexion es satisfactoria, false si ha habido algún problema de conexión.
	 */
	public boolean openConexion() {
		try {
			EntityManagerFactory factoria = Persistence.createEntityManagerFactory("ActividadUF5-1");
			em = factoria.createEntityManager();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método que cierra la conexion con la BBDD
	 * @return Retorna true si la conexion se ha cerrado satisfactoriamente, false si ha habido algún problema al cerrar la conexion.
	 */
	public boolean closeConexion() {
		try {
			em.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * EntityMananer de la conexion.
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
}