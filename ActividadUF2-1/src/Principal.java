import java.util.InputMismatchException;
import java.util.Scanner;


import dao.*;

public class Principal {
	// Validacion: Codigo producto [0], precio[1], stock[2]
	private static final String[] pattern = new String[]{"[a-zA-Z]{3}\\d", "^\\d+(\\.\\d+)?$", "^\\d+"};
	private static ArticulosDao crud = new ArticulosDao();
	private static Scanner lector;
	public static void main(String[] args) {

		//Connection conexion = abrirConexionBD();
		//if (conexion == null)
		//	return;

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
						crud.listarProductos();
						break;

					case 2:
						crud.nuevoProducto(datosArticuloNuevo());
						break;

					case 3:
						crud.actualizarStock(datosArticuloActualizar());
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
		//cerrarConexion(conexion); // Pasamos como argumento la conexión a cerrar.
		lector.close();
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

	public static String[] datosArticuloNuevo() {
		String[] temp = new String[5];
		// Se recogen los valores del nuevo producto.
		// Codigo articulo.
		System.out.println("Código del articulo. Del tipo XXX1(3 letras y un número)");

		do {
			temp[0] = lector.nextLine();
			if (!temp[0].matches(pattern[0]))
				System.out.println("Codigo incorrecto, vuele a intentarlo");

			else if (crud.exists(temp[0]))
				System.out.println("El codigo ya existe en la Bd, vuelve a intentarlo");

		} while ((!temp[0].matches(pattern[0]) || (crud.exists(temp[0]))));

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

		return temp;

	}

	public static String[] datosArticuloActualizar() {
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
		return temp;
	}
}