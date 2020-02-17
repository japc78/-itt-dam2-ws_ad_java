import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XmlCreate
 */
public class XmlCreate {

	public static void main(String[] args) {
		//STUDY DocumentBuilderFactory -> Permite obtener el objeto DocumentBuilder a partir de su método newInstante()
		// Se crea la factoria.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		//STUDY  DocumentBuilder -> Analizador o parser permite construir el árbol DOM a partir del documento XML por medio de su método parse().
		DocumentBuilder analyzer;

		// Document -> Representa un modelo de objetos como replica de un documento XML. DocumentBuilder analiza el contenido del documento XML y devuelve el objeto Document con el árbol DOM.
		Document file;

		try {
			//
			analyzer = factory.newDocumentBuilder();

			// Se crea el documento del tipo DOM
			file = analyzer.newDocument();

			// Se añade el elemento raiz
			Element root = file.createElement("concierto");
			file.appendChild(root);

			// Para add el contenido al fichero se crea un metodo al cual se le pasa el elemento raiz (root) y el objeto del tipo Docoument(file)
			addContent(root, file);

			// Metodo para guarar en disco el fichero xml
			safeFile(file);
			System.out.println("Fichero xml creado con exito");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Se crearn los distintos nodos que contendrá el fichero XML.
	public static void addContent (Element root, Document file) {
		Element nodeFecha = file.createElement("fecha");
		Element nodeHora = file.createElement("hora");
		Element nodeParticipantes = file.createElement("participantes");
		Element nodeParticipante = file.createElement("participante");
		Element nodeEntrada = file.createElement("entrada");
		Element nodeGrupo = file.createElement("grupo");

		root.appendChild(nodeFecha);
		root.appendChild(nodeHora);
		root.appendChild(nodeParticipantes);
		nodeFecha.appendChild(file.createTextNode("20-oct-2018"));
		nodeHora.appendChild(file.createTextNode("21:30"));

		// Participante 01
		nodeParticipantes.appendChild(nodeParticipante);
		nodeParticipante.appendChild(nodeEntrada);
		nodeEntrada.appendChild(file.createTextNode("21:30"));
		nodeParticipante.appendChild(nodeGrupo);
		nodeGrupo.appendChild(file.createTextNode("Las Ardillas de Dakota"));

		//Participante 02
		nodeParticipante = file.createElement("participante");
		nodeParticipantes.appendChild(nodeParticipante);
		nodeEntrada = file.createElement("entrada");
		nodeParticipante.appendChild(nodeEntrada);
		nodeEntrada.appendChild(file.createTextNode("22:15"));
		nodeGrupo = file.createElement("grupo");
		nodeParticipante.appendChild(nodeGrupo);
		nodeGrupo.appendChild(file.createTextNode("Fito y los Fitipaldis"));

		//Participante 03
		nodeParticipante = file.createElement("participante");
		nodeParticipantes.appendChild(nodeParticipante);
		nodeEntrada = file.createElement("entrada");
		nodeParticipante.appendChild(nodeEntrada);
		nodeEntrada.appendChild(file.createTextNode("23:00"));
		nodeGrupo = file.createElement("grupo");
		nodeParticipante.appendChild(nodeGrupo);
		nodeGrupo.appendChild(file.createTextNode("Coldplay"));
	}

	// Metodo para guardar el fichero.
	//STUDY Transformer permite transformar un árbol DOM a un formato de documento XML.
	public static void safeFile (Document file) {
		try {
			// Se crea el objeto Transformer invocando el método newTransformer() de la clase TransformerFactory.
			TransformerFactory factoryConversor = TransformerFactory.newInstance();
			Transformer conversor = factoryConversor.newTransformer();

			// Necesario para el metodo Transform. Representa el origen del fichero, en este caso el objeto file que se pasa por argumento al metoto
			DOMSource fuente = new DOMSource(file);

			// Representa el flujo de datos de escritura hacia el fichero donde se desea escribir.
			StreamResult resultado = new StreamResult(new File("concierto.xml"));

			//STUDY -> transform() realiza la transformación al formato XML, genera el fichero.
			conversor.transform(fuente, resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}