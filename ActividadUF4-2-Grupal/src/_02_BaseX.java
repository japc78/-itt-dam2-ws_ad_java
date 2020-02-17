import javax.xml.xquery.*;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

		try {
			xds.setProperty("serverName", "localhost");
			xds.setProperty("port", "1984");
			conexion = xds.getConnection("admin", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Conexion Ok con BaseX");

		querry = "for $detalles in fn:collection('recibos')//detalle order by $detalles/codigo return $detalles";

		try {
			expr = conexion.createExpression();
			result = expr.executeQuery(querry);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while (result.next()) {
				Node node = result.getNode();
				printRecibo(node);
			}
		} catch (XQException e) {
			System.out.println("Error al recorrer los elementos obtenidos");
			System.out.println(e.getMessage());
		}
	}

	public static void printRecibo (Node node) {
		String[] s = new String[4];
		s[0] = node.getChildNodes().item(1).getTextContent();
		s[1] = node.getChildNodes().item(3).getTextContent();
		s[2] = node.getChildNodes().item(5).getTextContent();
		s[3] = node.getChildNodes().item(7).getTextContent();
		System.out.println("  " + s[0] + "  " + s[1] + " " + s[2] + " unidades a "  + s[3]);
	}
}