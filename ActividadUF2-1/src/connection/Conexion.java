package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * Conexion
 */
public class Conexion {
	public Conexion() {
		// Carga del driver de conexion con el servidor MySQL
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("App: Driver de conexón cargado");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: Driver Mysql de conexion - No se encuentra.");
			e.printStackTrace();
		}
	}


	public static Connection abrirConexionBD() {
		Connection con = null;

		//STUDY Conexion BD.
		// protocolo:subprobogolo(bd):ip_conexion:puerto_conexion/nombre_bd

		String host = "localhost";
		String port = "3306";
		String db = "dam_ferreteria";
		String user = "userRW";
		String pass = "userRW";

		// url de conexion DB.
		// ?useSSL=false para no usar SSL en la conexion y no muestre el warming
		String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false", host, port, db);

		try {
			//STUDY DriverManager -> getConnection() se le pasa por parametors los valores de conexion.
			// Conexion con la BD a través de DriverManager
			con = (Connection) DriverManager.getConnection(url, user, pass);
			System.out.println("App: Conexion establecida con la BD");

		} catch (SQLException e) {
			System.out.println("Error: No se ha podido realizar la conexión con la BD");
			e.printStackTrace();
		}
		return con;
	}

	public static void cerrarConexion(Connection conexion) {
		try {
			conexion.close();
			System.out.println("App: conexion BD cerrada");
		} catch (SQLException e) {
			System.out.println("ERROR: al cerrar conexión con la BD");
			e.printStackTrace();
		}
	}


}