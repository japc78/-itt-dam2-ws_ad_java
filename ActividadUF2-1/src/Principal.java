import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
	// Validacion: Codigo producto [0], precio[1], stock[2]
	private static final String[] pattern = new String[]{"[a-zA-Z]{3}\\d", "^\\d+(\\.\\d+)?$", "^\\d+"};
	private static Scanner lector;

	public static void main(String[] args) {

		Connection conexion = abrirConexionBD();
		if (conexion == null)
			return;
		lector = new Scanner(System.in);
		int opc;
		do {
			mostrarMenu();

			try {
				opc = lector.nextInt();
				lector.nextLine(); // Recogemos el retorno de carro.
				System.out.println();

				switch (opc) {
					case 1:
						listarProductos(conexion);
						break;
					case 2:
						nuevoProducto(conexion);
						break;
					case 3:
						actualizarStock(conexion);
						break;
					case 4:
						System.out.println("Programa finalizado");
						break;

					default:
						System.out.println("Opción incorrecta");
						break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Debe de ser un valor del menu del 1 al 4");
				lector.nextLine();
				opc = 0;
			}

		} while (opc != 4);
		cerrarConexion(conexion); // Pasamos como argumento la conexión a cerrar.
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

	private static void cerrarConexion(Connection conexion) {
		try {
			conexion.close();
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
			s = conexion.prepareStatement(sql);
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

	private static void nuevoProducto(Connection conexion) {
		String[] temp = new String[5];

		// Se recogen los valores del nuevo producto.

		// Codigo articulo.
		System.out.println("Código del articulo. Del tipo XXX1(3 letras y un número)");
		do {
			temp[0] = lector.nextLine();
			if (!temp[0].matches(pattern[0]))
				System.out.println("Codigo incorrecto, vuele a intentarlo");

			else if (exists(conexion, temp[0]))
				System.out.println("El codigo ya existe en la Bd, vuelve a intentarlo");

		} while ((!temp[0].matches(pattern[0]) || (exists(conexion, temp[0]))));

		// Descripcion articulo
		System.out.println("Descripcion del articulo:");
		temp[1] = lector.nextLine();

		// Precio
		System.out.println("Introduce un precio");
		do {
			temp[2] = lector.nextLine();
			if (!temp[2].matches(pattern[1]))
				System.out.println("Precio incorrecto, vuele a intentarlo");
		} while (!temp[2].matches(pattern[1]));

		// Stock
		System.out.println("Introduce el stock");
		do {
			temp[3] = lector.nextLine();
			if (!temp[3].matches(pattern[2]))
				System.out.println("Stock incorrecto, vuele a intentarlo");
		} while (!temp[3].matches(pattern[2]));

		// Stock minimo
		System.out.println("Introduce el stock minimo");
		do {
			temp[4] = lector.nextLine();
			if (!temp[4].matches(pattern[2]))
				System.out.println("Stock minimo incorrecto, vuele a intentarlo");
		} while (!temp[4].matches(pattern[2]));

		// SQL add producto.
		String sql = "INSERT INTO PRODUCTO (CODIGO, DESCRIPCION, PRECIO, STOCK, MINIMO) " +
				"VALUES (?, ?, ?, ?, ?)";

		//STUDY PrepareStatement -> Manera segura de preparar la sentencia SQL.
		PreparedStatement s;
		try {
			// Se envia la SQL sin parametros
			s = conexion.prepareStatement(sql);

			// Se envian los parametros
			for (int i = 0; i < temp.length; i++) {
				s.setString(i+1, temp[i]);
			}

			// Se ejecuta la sentencia y se recoge el numero de registros afectados.
			int updates = s.executeUpdate();
			System.out.println("Sentenecia SQL ejecutada con exito");
			System.out.println("Registros afectados: " + updates);

		} catch (SQLException e) {
			System.out.println("Error: al añadir el articulo en la BD.");
			e.printStackTrace();
		}
	}

	private static void actualizarStock(Connection conexion) {

		String[] temp = new String[2];

		// Se recoge codigo de producto y stock a reponer.
		// Codigo
		System.out.println("Código del articulo. Del tipo XXX1(3 letras y un número)");
		do {
			temp[0] = lector.nextLine();
			if (!temp[0].matches(pattern[0]))
				System.out.println("Codigo incorrecto, vuele a intentarlo");
		} while (!temp[0].matches(pattern[0]));

		// Stock
		System.out.println("¿Cuantas unidades quieres reponer?");
		do {
			temp[1] = lector.nextLine();
			if (!temp[1].matches(pattern[2]))
				System.out.println("Stock incorrecto, vuele a intentarlo");
		} while (!temp[1].matches(pattern[2]));

		// Se comprueba si existe el codigo de producto en la BD para realizar la actualizacion del stock.
		if (exists(conexion, temp[0])) {
			try {
				// Se crea la sentencia
				String sql = "UPDATE PRODUCTO SET STOCK = STOCK + ? WHERE CODIGO = ?";

				// Se pasa la sentencia SQL
				PreparedStatement s = conexion.prepareStatement(sql);

				// Se pasan los parametros
				s.setString(1, temp[1]); // Stock
				s.setString(2, temp[0]); // Codigo Producto

				// Se ejecuta la actualizacion.
				s.executeUpdate();

				System.out.println("Stock actualizado correctamente");

			} catch (SQLException e) {
				System.out.println("Error: al actualizar el stock del producto");
				e.printStackTrace();
			}
		} else {
			System.out.println("El producto introducido no existe");
			System.out.println("No es posible actualizar el stock");
		}
	}

	/**
	 * Comprueba si el producto exite o no.
	 * @param conexion Se le pasa la conexion a la BD.
	 * @param codigo Codigo del producto
	 * @return Retorna True si el producto existe, False si el producto no existe y Null si ha habido algun error de conexion.
	 */
	private static Boolean exists (Connection conexion, String codigo) {
		Boolean b = null;
		try {
			String sql = "SELECT * FROM PRODUCTO WHERE CODIGO = ?";
			PreparedStatement s;
			s = conexion.prepareStatement(sql);
			s.setString(1, codigo);

			// Ejecuta la sentencia
			ResultSet rs = s.executeQuery();
			b = (rs.first())?true : false;

		} catch (SQLException e) {
			System.out.println("Error: no ha sido posible comprobar si el producto exite");
			e.printStackTrace();
		}
		return b;
	}
}