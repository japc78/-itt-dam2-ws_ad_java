import javax.xml.xquery.*;

import org.w3c.dom.Node;

import net.xqj.basex.BaseXXQDataSource;

/**
 * _02_BaseX
 */
public class _02_BaseX {
	public static void main(String[] args) {
		XQDataSource xds = new BaseXXQDataSource();
		XQConnection conexion = null;
		XQExpression expr;
		XQResultSequence result = null;
		String querry;

		// Se realiza la conexion.
		try {
			xds.setProperty("serverName", "localhost");
			xds.setProperty("port", "1984");
			conexion = xds.getConnection("admin", "admin");
			System.out.println("Conexion Ok con BaseX");
		} catch (Exception e) {
			System.out.println("Error al conectar con XBASE server");
			e.printStackTrace();
		}

		// Se crea la consulta y se ejecuta
		try {
			querry = "for $detalles in fn:collection('recibos')//detalle order by $detalles/codigo return $detalles";
			expr = conexion.createExpression();
			result = expr.executeQuery(querry);
		} catch (Exception e) {
			System.out.println("Error al realizar la consulta");
			e.printStackTrace();
		}

		// Se imprimen los resultados
		try {
			while (result.next()) {
				Node node = result.getNode();
				printRecibo(node);
			}
		} catch (XQException e) {
			System.out.println("Error al recorrer los elementos obtenidos");
			e.printStackTrace();
		}
	}

	// Metodo para imprir los resultados
	public static void printRecibo (Node node) {
		String[] s = new String[4];
		int j = 0;
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			if (i % 2 != 0)	{
				s[j] = node.getChildNodes().item(i).getTextContent();
				j++;
			}
		}
		System.out.println("  " + s[0] + "  " + s[1] + " " + s[2] + " unidades a "  + s[3]);
	}
}