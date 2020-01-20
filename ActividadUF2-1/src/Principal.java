import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Principal {
	private static Scanner lector;

	public static void main(String[] args) {
		Connection con = abrirConexionBD();
		if (con == null)
			return;
		lector = new Scanner(System.in);
		int opc;
		do {
			mostrarMenu();
			opc = lector.nextInt();
			lector.nextLine(); // Recogemos el retorno de carro.
			System.out.println();
			/*
			 * Añade aquí una estructura switch que permita llamar a un método u otro según
			 * el valor de la variable opc.
			 */

			switch (opc) {
			case 1:
				listarProductos(con);
				break;
			case 2:
				nuevoProducto(con);
				break;
			case 3:
				actualizarStock(con);
				break;
			case 4:
				System.out.println("Programa finalizado");
				break;

			default:
				System.out.println("Opción incorrecta");
				break;
			}

		} while (opc != 4);
		cerrarConexion(con); // Pasamos como argumento la conexión a cerrar.
		lector.close();
	}

	private static Connection abrirConexionBD() {
		Connection conexion = null;

		// Carga del driver de conexion con el servidor MySQL
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("App: Driver de conexón cargado");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: Driver Mysql de conexion - No se encuentra.");
			e.printStackTrace();
		}

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
			conexion = DriverManager.getConnection(url, user, pass);
			System.out.println("App: Conexion establecida con la BD");

		} catch (SQLException e) {
			System.out.println("Error: No se ha podido realizar la conexión con la BD");
			e.printStackTrace();
		}
		return conexion;
	}

	private static void cerrarConexion(Connection con) {
		/*
		 * Añade el código necesario para cerrar la conexión que
		 * recibe esté método como argumento.
		 */
		try {
			con.close();
			System.out.println("App: conexion BD cerrada");
		} catch (SQLException e) {
			System.out.println("ERROR: al cerrar conexión con la BD");
			e.printStackTrace();
		}

	}

	public static void mostrarMenu() {
		System.out.println();
		System.out.println("GESTION DE ALMACEN");
		System.out.println("--------------------------------------");
		System.out.println("1. Listado de artículos");
		System.out.println("2. Añadir nuevo artículo");
		System.out.println("3. Actualizar stock");
		System.out.println("4. Terminar programa");
		System.out.println("--------------------------------------");
		System.out.println("¿Qué opción eliges?");

	}

	private static void listarProductos(Connection conexion) {
		try {
			// STUDY PrepareStatement -> Metodo seguro de realizar sentencias de SQL.
			// Se prepara la sentencia
			String sql = "SELECT * FROM PRODUCTO ORDER BY ?";
			String att = "DESCRIPCION";
			PreparedStatement s;
			s = (PreparedStatement) conexion.prepareStatement(sql);
			s.setString(1, att);

			// Ejecuta la sentencia
			ResultSet rs = s.executeQuery();

			// Recorre los resultados y se imprimen en pantalla.
			while (rs.next()) {
				int stock = Integer.parseInt(rs.getString("STOCK"));
				int stockMin = Integer.parseInt(rs.getString("MINIMO"));

				System.out.print(rs.getString("CODIGO"));
				System.err.print(" - ");
				System.out.print(rs.getString("DESCRIPCION"));
				System.err.print(" - ");
				System.out.print("Precio: " + rs.getString("PRECIO"));
				System.err.print(" - ");
				System.out.print("Stock: " + rs.getString("STOCK"));
				System.err.print(" - ");
				System.out.print("Stock minimo: " + rs.getString("MINIMO"));

				// Se comprueba el stock este por encima del minimo.
				if (stock < stockMin) {
					System.out.print(" - Hay que reponer " + (stockMin - stock) + " unidades.");
				}

				System.err.print("\n");
			}

		} catch (SQLException e) {
			System.out.println("Error: al listar los productos");
			e.printStackTrace();
		}
	}

	private static void nuevoProducto(Connection con) {
		/*
		 * Añade el código necesario para añadir un nuevo producto.
		 */

	}

	private static void actualizarStock(Connection con) {
		/*
		 * Añade el código necesario para actualizar el stock.
		 */

	}

}