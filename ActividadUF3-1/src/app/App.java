package app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;

import model.*;

public class App {
	private static Scanner lector;
	private static EntityManagerFactory factoria;
	private static EntityManager em;
	private static final String pattern = "\\d{1,4}";


	public static void main(String[] args) {
		//STUDY JPA EntityManager se crea la factoria para crear objetos EntityManager.	
		//Una factoria es un objeto que te crea objetos. Es un pratron de diseño.
		factoria = Persistence.createEntityManagerFactory("ActividadUF3-1");

		//STUDY JPA Se crea el objeto EntityManager, que es el encargado de realizar las conexion e interacciones a la BBDD.
		em = factoria.createEntityManager();
		
		lector = new Scanner(System.in);
		int opc;
		do {
			mostrarMenu();

			try {
				opc = lector.nextInt();
				lector.nextLine();
				System.out.println();

				switch (opc) {
				case 1:
					listarClientes();
					break;
				case 2:
					consultarFactura();
					break;
				case 3:
					sumaImportesVentas2();
					break;

				case 4:
					listadoArticulos();
					break;

				case 5:
					listadoArticulosVendidos();
					break;
					
					
				case 6:
					System.out.println("Programa finalizado");
					break;

				default:
					System.out.println("Opción incorrecta");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Debe de ser un valor del menu del 1 al 6");
				lector.nextLine();
				opc = 0;
			}

		} while (opc != 6);
		
		// Cerramos la conexion con la BD y el Lector.
		em.close();
		lector.close();
	}

	public static void mostrarMenu() {
		System.out.println();
		System.out.println("CONSULTAS FERRETERIA");
		System.out.println("--------------------------------------");
		System.out.println("1. Listado de clientes");
		System.out.println("2. Consultar factura");
		System.out.println("3. Suma de los importes de todas las ventas");
		System.out.println("4. Listado de artículos y total de unidades vendidas");
		System.out.println("5. Listado de artículos vendidos y sus unidades");
		System.out.println("6. Terminar programa");
		System.out.println("--------------------------------------");
		System.out.println("¿Qué opción eliges?");
	}

	private static void listarClientes() {
		//STUDY JPA -> JPQL para consultas complejas. JPQL es una combinaciono de JPA Y SQL.
		List<Cliente> list = em.createQuery("from Cliente c", Cliente.class).getResultList();
		for (Cliente c : list) {
			System.out.println(c);
		}
	}

	private static void consultarFactura() {
		String n;
		double total = 0;

		System.out.println("Numero de factura:");
		n = lector.nextLine();
		
		if (n.matches(pattern)) {		
			Factura f = em.find(Factura.class, Integer.parseInt(n));	
			
			if (f != null) {		
				System.out.println(n);
				
				System.out.println("\nFecha: " + f.getFecha().toString());
				System.out.println("Pagado: " + f.getPagado());
				System.out.println("Cliente " + f.getCliente().getNif() + " - " + f.getCliente().getNombre() + "\n");
				
				for (Detalle d : f.getDetalles()) {
					System.out.println("\t" + d.getProducto().getDescripcion() +", " + d.getUnidades() + " unidades a " + d.getPrecio() + "€" );
					total += d.getUnidades() * d.getPrecio();
				}
				
				System.out.println("\n\t" + "IMPORTE TOTAL: " + String.format("%.2f", total) + "€");
			} else {
				System.out.println("No existe, vuelve a intentarlo");
			}
		} else {
			System.out.println("Formato introducido incorrecto, vuelve a intentarlo");
		}
	}
	
	// He elaborado dos metodos con dos formas diferentes del mismo resultado
	// Una con una Query Generica 
	private static void sumaImportesVentas() {
		double total = 0;
		List<Detalle> list = em.createQuery("from Detalle d", Detalle.class).getResultList();
		for (Detalle d : list) {
			total += d.getUnidades() * d.getPrecio();
		}
		System.out.println("Importe total de las ventas: " + String.format("%.2f", total) + "€");
	}
	
	//El segundo con TypedQuery pasado el resultado diretamente con la consulta.
	private static void sumaImportesVentas2() {
		String sql = "SELECT SUM(d.unidades * d.precio) FROM Detalle d";
		TypedQuery<Double> q = em.createQuery(sql, Double.class);
		System.out.println("Importe total de las ventas: " + String.format("%.2f", q.getSingleResult()) + "€");
	}

	// He añadido una opcion más al menu, no tenia claro si eran todos los articulos con sus ventas, incluso los que 
	// tienen venta a 0, o solo los que tienen venta.
	
	// Muestra todos los articulos y sus ventas. Muestra tambien los que tienen venta a 0.
	// He utilizado una consulta con parametros.
	private static void listadoArticulos() {
		List<Producto> list = em.createQuery("from Producto d", Producto.class).getResultList();
		
		for (Producto p : list) {
			TypedQuery<Long> q = em.createQuery("SELECT SUM(d.unidades) FROM Detalle d WHERE d.producto = ?1", Long.class);
			q.setParameter(1, p);
			
			System.out.println(p.getDescripcion() + " - " 
					+ ((q.getSingleResult() == null ? 0 : q.getSingleResult())) + " unidades vendidas");
		}
	}
	
	// Muestra solo los articulos con ventas y su numero de unidades vendidas.
	// He probado a realizar la consulta tanto con where como con  INNER JOIN.
	private static void listadoArticulosVendidos() {
		//String sql = "SELECT p.descripcion, sum(d.unidades) FROM Producto p INNER JOIN Detalle d ON d.producto = p GROUP BY p";

		String sql = "SELECT p.descripcion, sum(d.unidades) FROM Producto p, Detalle d WHERE d.producto = p GROUP BY p";
		Query q = em.createQuery(sql); 
		List<Object[]> list = q.getResultList();
		for (Object[] obj : list) {
			System.out.println(obj[0] + " - " + obj[1] + " unidades vendidas");
		}		
	}	
}