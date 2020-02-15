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
		DocumentBuilder analyzer;
		Document file;

		try {
			analyzer = factory.newDocumentBuilder();
			file = analyzer.newDocument();

			Element root = file.createElement("concierto");
			file.appendChild(root);

			addContent(root, file);
			safeFile(file);

		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}

	}

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

	public static void safeFile (Document file) {
		try {
			TransformerFactory factoryConversor = TransformerFactory.newInstance();
			Transformer conversor = factoryConversor.newTransformer();
			DOMSource fuente = new DOMSource(file);
			StreamResult resultado = new StreamResult(new File("concierto.xml"));
			conversor.transform(fuente, resultado);
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}
	}
}