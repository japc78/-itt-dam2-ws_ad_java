import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class _01_JDOM {
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder analyzer;
		Document file;
		Node root;

		// Se parse al fichero al objeto file de tipo DOM
		try {
			analyzer = factory.newDocumentBuilder();
			file = analyzer.parse("xml//recibos.xml");

			// Se obotiene la raiz del documento.
			root = file.getDocumentElement();

			// Se imprime el resultado mediante la implementacion de un metodo.
			printFile(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printFile (Node node) {
		NodeList recibos = node.getChildNodes();
		for (int i = 0; i < recibos.getLength(); i++) {
			Node recibo = recibos.item(i);
			if (recibo.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Recibo: " + recibo.getChildNodes().item(1).getTextContent());
				System.out.println("Fecha: " + recibo.getChildNodes().item(3).getTextContent());

				// Imprime cada linea de detalle
				printDetails(recibo);
			}
		}
	}


	public static void printDetails (Node node) {
		NodeList detalles = node.getChildNodes();
		for (int i = 0; i < detalles.getLength(); i++) {
			Node detalle = detalles.item(i);
			String[] s = new String[4];
			if (detalle.getNodeName().equalsIgnoreCase("detalle")) {
				s[0] = detalle.getChildNodes().item(1).getTextContent();
				s[1] = detalle.getChildNodes().item(3).getTextContent();
				s[2] = detalle.getChildNodes().item(5).getTextContent();
				s[3] = detalle.getChildNodes().item(7).getTextContent();
				System.out.println("  " + s[0] + "  " + s[1] + " " + s[2] + " unidades a "  + s[3]);
			}
		}
		System.out.println();
	}
}