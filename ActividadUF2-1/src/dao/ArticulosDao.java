package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import connection.*;

/**
 * Conexion
 */
public class ArticulosDao {


	public void listarProductos() {
		Connection con = null;

		try {
			con = Conexion.abrirConexionBD();
			// STUDY PrepareStatement -> Metodo seguro de realizar sentencias de SQL.
			// Se prepara la sentencia
			String sql = "SELECT * FROM PRODUCTO ORDER BY ?";
			String att = "DESCRIPCION";
			PreparedStatement s;
			s = con.prepareStatement(sql);
			s.setString(1, att);

			// Ejecuta la sentencia
			ResultSet rs = s.executeQuery();

			// Recorre los resultados y se imprimen en pantalla.
			while (rs.next()) {
				int stock = Integer.parseInt(rs.getString("STOCK"));
				int stockMin = Integer.parseInt(rs.getString("MINIMO"));

				System.out.print(rs.getString("CODIGO"));
				System.err.print(" - ");
				System.out.print(rs.getString("DESCRIPCION"));
				System.err.print(" - ");
				System.out.print("Precio: " + rs.getString("PRECIO"));
				System.err.print(" - ");
				System.out.print("Stock: " + rs.getString("STOCK"));
				System.err.print(" - ");
				System.out.print("Stock minimo: " + rs.getString("MINIMO"));

				// Se comprueba el stock este por encima del minimo.
				if (stock < stockMin) {
					System.out.print(" - Hay que reponer " + (stockMin - stock) + " unidades.");
				}

				System.err.print("\n");
			}

			Conexion.cerrarConexion(con);

		} catch (SQLException e) {
			System.out.println("Error: al listar los productos");
			e.printStackTrace();
		}
	}

	public void nuevoProducto(String[] datos) {
		Connection con = null;

		// SQL add producto.
		String sql = "INSERT INTO PRODUCTO (CODIGO, DESCRIPCION, PRECIO, STOCK, MINIMO) " +
				"VALUES (?, ?, ?, ?, ?)";


		//STUDY PrepareStatement -> Manera segura de preparar la sentencia SQL.
		PreparedStatement s;
		try {
			con = Conexion.abrirConexionBD();

			// Se envia la SQL sin parametros
			s = con.prepareStatement(sql);

			// Se envian los parametros
			for (int i = 0; i < datos.length; i++) {
				s.setString(i+1, datos[i]);
			}

			// Se ejecuta la sentencia y se recoge el numero de registros afectados.
			int updates = s.executeUpdate();
			System.out.println("Sentenecia SQL ejecutada con exito");
			System.out.println("Registros afectados: " + updates);

			Conexion.cerrarConexion(con);

		} catch (SQLException e) {
			System.out.println("Error: al añadir el articulo en la BD.");
			e.printStackTrace();
		}
	}

	public void actualizarStock(String[] datos) {

		// Se comprueba si existe el codigo de producto en la BD para realizar la actualizacion del stock.
		if (exists(datos[0])) {
			try {
				Connection con = Conexion.abrirConexionBD();
				// Se crea la sentencia
				String sql = "UPDATE PRODUCTO SET STOCK = STOCK + ? WHERE CODIGO = ?";

				// Se pasa la sentencia SQL
				PreparedStatement s = con.prepareStatement(sql);

				// Se pasan los parametros
				s.setString(1, datos[1]); // Stock
				s.setString(2, datos[0]); // Codigo Producto

				// Se ejecuta la actualizacion.
				s.executeUpdate();

				System.out.println("Stock actualizado correctamente");

				Conexion.cerrarConexion(con);
			} catch (SQLException e) {
				System.out.println("Error: al actualizar el stock del producto");
				e.printStackTrace();
			}
		} else {
			System.out.println("El producto introducido no existe");
			System.out.println("No es posible actualizar el stock");
		}
	}

	/**
	 * Comprueba si el producto exite o no.
	 * @param conexion Se le pasa la conexion a la BD.
	 * @param codigo Codigo del producto
	 * @return Retorna True si el producto existe, False si el producto no existe y Null si ha habido algun error de conexion.
	 */
	public Boolean exists (String codigo) {
		Boolean b = null;
		try {
			Connection con = Conexion.abrirConexionBD();
			String sql = "SELECT * FROM PRODUCTO WHERE CODIGO = ?";
			PreparedStatement s;
			s = con.prepareStatement(sql);
			s.setString(1, codigo);

			// Ejecuta la sentencia
			ResultSet rs = s.executeQuery();
			b = (rs.first())?true : false;
			Conexion.cerrarConexion(con);
		} catch (SQLException e) {
			System.out.println("Error: no ha sido posible comprobar si el producto exite");
			e.printStackTrace();
		}
		return b;
	}

}