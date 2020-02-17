import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlRead {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder analyzer;
		Document file;
		Node root;

		try {
			analyzer = factory.newDocumentBuilder();
			// Se analiza y se pasea el fichero, se conierte a un objeto DOM
			file = analyzer.parse("concierto.xml");
			// Se le pasa el nodo raiz
			root = file.getDocumentElement();
			// Metodo para imprimir el resultado deseado.
			printFile(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Como el documento generado no esta indentado ni tiene espacios en blanco no se crean nodo de texto vacios que haya que controlar
	public static void printFile (Node node) {
		System.out.println("Fecha y hora del concierto: " + node.getChildNodes().item(0).getTextContent() + " " + node.getChildNodes().item(1).getTextContent());
		System.out.println("Participaran los siguientes grupos: ");
		NodeList conciertos = node.getChildNodes().item(2).getChildNodes();
		for (int i = 0; i < conciertos.getLength(); i++) {
			String hora = conciertos.item(i).getChildNodes().item(0).getTextContent();
			String grupo = conciertos.item(i).getChildNodes().item(1).getTextContent();
			System.out.println("\t" + hora + " " + grupo);
		}
	}
}