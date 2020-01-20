package ficheros;

import java.io.File;
public class Principal03 {
	public static void main(String args[]) {
		File fich = new File("pelisdeterror.txt");
		boolean ok = fich.delete();
		if (ok)
			System.out.println("El fichero se ha borrado con éxito");
		else
			System.out.println("El fichero no ha podido borrarse");
	}
}