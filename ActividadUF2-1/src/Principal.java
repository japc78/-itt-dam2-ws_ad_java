import java.sql.Connection;
import java.util.Scanner;

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
			 * Añade aquí una estructura switch que permita llamar
			 * a un método u otro según el valor de la variable opc.
			 */

		} while (opc != 4);
		cerrarConexion(con); // Pasamos como argumento la conexión a cerrar.
		lector.close();
	}

	private static Connection abrirConexionBD() {
		Connection con = null;
		/*
		 * Añade el código necesario para abrir la conexión con la
		 * base de datos FERRETERIA.
		 */

		return con;
	}

	private static void cerrarConexion(Connection con) {
		/*
		 * Añade el código necesario para cerrar la conexión que
		 * recibe esté método como argumento.
		 */

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

	private static void listarProductos(Connection con) {
		/*
		 * Añade el código necesario para mostrar en pantalla
		 * el listado de artículos.
		 */

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