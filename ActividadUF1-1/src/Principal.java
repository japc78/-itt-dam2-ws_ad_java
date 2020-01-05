import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
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
		File bd = new File("coches.dat");

		if (bd.exists()) {
			try ( FileInputStream fis = new FileInputStream(bd);
			ObjectInputStream ois = new ObjectInputStream(fis);){
				coches = (ArrayList<Coche>)ois.readObject();
				System.out.println("BD de coches leida correctamente.");
			} catch (Exception e) {
				//TODO: handle exception
			}
		}

		while (opc != 6) {
			mostrarMenu();
			opc = lector.nextInt();
			lector.nextLine(); // Para recoger el retorno de carro.

			/*
			 * Añade aquí una estructura switch para lograr que en función de la opción que
			 * elija el usuario el control del programa pase a un método u otro de los que
			 * tendrás que implementar más abajo.
			 */
			switch (opc) {
			case 1:
				nuevoCoche(coches);
				break;
			case 2:
				borrarCoche(coches);
				break;
			case 3:
				consultarCoche(coches);
				break;
			case 4:
				listadoCoches(coches);
				break;

			case 5:
				exportarCoches(coches);
				break;
			case 6:
				System.out.println("Programa finalizado");
				break;

			default:
				System.out.println("Opción incorrecta");
				break;
			}
		}

		/*
		 * Abre el fichero coches.dat para escritura y guarda todos los objetos Coche
		 * que estén en la colección ArrayList coches. Si ya existe un fichero
		 * coches.dat deberá ser sobrescrito.
		 */

		try (
			FileOutputStream fos = new FileOutputStream(bd);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			){
				oos.writeObject(coches);
				System.out.println("Lista de coches Actualizada");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido abrir el fichero");
			System.out.println(e.getMessage());
			e.printStackTrace();
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

	public static void nuevoCoche(ArrayList<Coche> coches) {
		/*
		* Añade un nuevo elemento a la colección coches.
		* El usuario tendrá que introducir por teclado
		* los datos del nuevo coche.
		*/
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

	public static void borrarCoche(ArrayList<Coche> coches) {
		/*
		* Permite al usuario introducir por teclado la
		* matricula de un coche para después eliminarlo de la colección.
		*/
		String tmp;
		System.out.println("Introduce la matrícula del vehículo");
		tmp = lector.nextLine();
		for (Coche coche : coches) {
			if (tmp.equalsIgnoreCase(coche.getMatricula())) {
				coches.remove(coche);
				System.out.println("Vehículo borrado con éxito");
				break;
			} else {
				System.out.println("Matrícula intruducida no se encuentra en la BD");
			}
		}
	}

	public static void consultarCoche(ArrayList<Coche> coches) {
		/*
		* Permite al usuario introducir por teclado la
		* matricula de un coche y muestra en pantalla
		* todos los datos del coche.
		*/
		String tmp;
		System.out.println("Introduce la matrícula del vehículo");
		tmp = lector.nextLine();
		for (Coche coche : coches) {
			if (tmp.equalsIgnoreCase(coche.getMatricula())) {
				System.out.println(coche);
				break;
			} else {
				System.out.println("Matrícula intruducida no se encuentra en la BD");
			}
		}
	}

	public static void listadoCoches(ArrayList<Coche> coches) {
		/*
		* Recorre secuencialmente la colección de coches para mostrar
		* un listado en pantalla.
		*/
		for (Coche coche : coches) {
			System.out.println(coche);
		}
	}

	public static void exportarCoches(ArrayList<Coche> coches) {
		/*
		* Recorre secuencialmente la colección de coches y genera
		* un fichero de texto llamado coches.txt
		*/
	}

}