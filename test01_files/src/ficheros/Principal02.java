package ficheros;

import java.io.File;
import java.io.IOException;
public class Principal02 {
	public static void main(String args[]) throws IOException {
		File fich = new File("pelisdeterror.txt");
		boolean ok = fich.createNewFile();
		if (ok)
			System.out.println("El fichero se ha creado con éxito");
		else
			System.out.println("El fichero no ha podido crearse");
	}
}