package ficheros;

import java.io.File;
import java.io.IOException;
public class Principal04 {
	public static void main(String args[]) throws IOException {
		File carp = new File("resources\\cine");
		if (carp.exists()) {
			System.out.println("Existe la carpeta");
			System.out.println("¿Tiene permisos de escritura? " + carp.canWrite());
			String[] contenido = carp.list();
			System.out.println("Archivos o carpetas que contiene: " +
				contenido.length);
			for (String nombre: contenido) {
				System.out.println(nombre);
			}
		} else
			System.out.println("No existe la carpeta");
	}
}