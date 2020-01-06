import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal02 {
	static Scanner lector;

	public static void main(String[] args) {
		lector = new Scanner(System.in);
		ArrayList<Coche> coches = new ArrayList<Coche>();
		int opc = 0;

		/*
		 * Comprueba si existe el fichero coches.dat usando la clase File Si existe lee
		 * secuencialmente todos los coches guardados y cópialos en el objeto ArrayList
		 * coches. Si no existe el fichero no tendrás nada que hacer con el fichero por
		 * el momento.
		 */

		// Se declara un objet de la clase File con el nombre del fichero.
		File bd = new File("coches02.dat");

		// Se comprueba que exite.
		if (bd.exists()) {
			// Try Catch con Autoclosaible.
			// STUDY Ficheros -> FileInputStreem abre el archivo para lectura.
			// STUDY Ficheros -> ObjectInputStream Permite la lectura de los objetos guardados en el fichero.
			try (FileInputStream fis = new FileInputStream(bd);
			ObjectInputStream ois = new ObjectInputStream(fis);){
				// Se lee el objeto que contiene el archivo.
				coches = (ArrayList<Coche>)ois.readObject();
				System.out.println("BD de coches leida correctamente.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		while (opc != 6) {
			mostrarMenu();
			try {
				opc = lector.nextInt();
				lector.nextLine();

				switch (opc) {
					case 1:
						nuevoCoche(coches);
						break;
					case 2:
						if(!coches.isEmpty()) {
							borrarCoche(coches);
						} else {
							System.out.println("No hay datos, introduzca algún vehículo");
						}
						break;
					case 3:
						if(!coches.isEmpty()) {
							consultarCoche(coches);
						} else {
							System.out.println("No hay datos, introduzca algún vehículo");
						}
						break;
					case 4:
						if(!coches.isEmpty()) {
							listadoCoches(coches);
						} else {
							System.out.println("No hay datos, introduzca algún vehículo");
						}
						break;
					case 5:
						if(!coches.isEmpty()) {
							exportarCoches(coches);
						} else {
							System.out.println("No hay datos, introduzca algún vehículo");
						}
						break;
					case 6:
						System.out.println("Programa finalizado");
						break;

					default:
						System.out.println("Opción incorrecta");
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Error, debe ser un número");
				lector.nextLine();
			}
		}

		// Try Catch con Autoclosaible.
		// STUDY Ficheros -> FileOutputStream abre el archivo para escritura.
		// STUDY Ficheros -> ObjectOutputStream Permite la escritura de objetos en el fichero.
		try (
			FileOutputStream fos = new FileOutputStream(bd);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			){
				// Se escribe el array de coches en el fichero.
				oos.writeObject(coches);
				System.out.println("Lista de coches Actualizada");
		} catch (IOException e) {
			System.out.println("No se ha podido guardar el fichero");
			System.out.println(e.getMessage());
		}
		lector.close();
	}

	public static void mostrarMenu() {
		System.out.println("------ COCHES MATRICULADOS ------------");
		System.out.println("---------------------------------------");
		System.out.println("1. Añadir nuevo coche");
		System.out.println("2. Borrar coche");
		System.out.println("3. Consultar coche");
		System.out.println("4. Listado de coches");
		System.out.println("5. Exportar coches a archivo de texto");
		System.out.println("6. Terminar programa");
		System.out.println("---------------------------------------");
		System.out.println("¿Qué opción eliges?");
	}

	/**
	* Añade un nuevo elemento a la colección coches.
	* El usuario tendrá que introducir por teclado
	* los datos del nuevo coche.
	*
	* @param coches
	*/
	public static void nuevoCoche(ArrayList<Coche> coches) {
		String matricula, marca, modelo, color;
		System.out.println("Añade un nuevo coche:");
		System.out.println("Introduce la matrícula:");
		matricula = lector.nextLine();
		System.out.println("Introduce la marca:");
		marca = lector.nextLine();
		System.out.println("Introduce el modelo:");
		modelo = lector.nextLine();
		System.out.println("Introduce el color:");
		color = lector.nextLine();
		coches.add(new Coche(matricula, marca, modelo, color));
		System.out.println("Coche añadido.");
	}

	/**
	 * Permite al usuario introducir por teclado la
	 * matricula de un coche para después eliminarlo de la colección.
	 *
	 * @param coches
	 */
	public static void borrarCoche(ArrayList<Coche> coches) {
		String tmp;
		System.out.println("Introduce la matrícula del vehículo");
		tmp = lector.nextLine();
		for (Coche coche : coches) {
			if (tmp.equalsIgnoreCase(coche.getMatricula())) {
				coches.remove(coche);
				System.out.println("Vehículo borrado con éxito");
				tmp = "";
				break;
			}
		}
		if(!tmp.isEmpty())System.out.println("La matrícula " + tmp + " intruducida no se encuentra en la BD");
	}

	/**
	 * Permite al usuario introducir por teclado la
	 * matricula de un coche y muestra en pantalla
	 * todos los datos del coche
	 *
	 * @param coches
	 */
	public static void consultarCoche(ArrayList<Coche> coches) {
		String tmp;
		System.out.println("Introduce la matrícula del vehículo");
		tmp = lector.nextLine();
		for (Coche coche : coches) {
			if (tmp.equalsIgnoreCase(coche.getMatricula())) {
				System.out.println("Vehículo en la BD: ");
				System.out.println(coche);
				tmp = "";
				break;
			}
		}

		if(!tmp.isEmpty())System.out.println("La matrícula " + tmp + " intruducida no se encuentra en la BD");
	}

	/**
	 * Recorre secuencialmente la colección de coches para mostrar
	 * un listado en pantalla.
	 *
	 * @param coches
	 */
	public static void listadoCoches(ArrayList<Coche> coches) {
		for (Coche coche : coches) {
			System.out.println(coche);
		}
	}

	/**
	 * Recorre secuencialmente la colección de coches y genera
	 * un fichero de texto llamado coches.txt
	 *
	 * @param coches
	 */
	public static void exportarCoches(ArrayList<Coche> coches) {
		// Try Catch con Autoclosaible.
		// STUDY Ficheros -> FileWriter abre el archivo para escribir caracteres.
		// STUDY Ficheros -> BufferedWriter permite la escritura en el fichero.
		try (FileWriter fw  = new FileWriter("coches02.txt");
			BufferedWriter  bw = new BufferedWriter(fw);) {
			for (Coche coche : coches) {
				// Se escribe el coche correspondiente en el fichero
				bw.write(coche.toString());

				// Se genera un salto de linea para el siguiente coche se escriba en al próxima linea, no continuo.
				bw.newLine();
			}
			System.out.println("Listado exportado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}