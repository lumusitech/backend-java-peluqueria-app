package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
	private String direccion;
	private String puerto;
	private String dbname;
	private String user;
	private String pass;

	private Conexion() throws Exception {
		try {
			this.direccion = "localhost";
			this.puerto = "3306";
			this.dbname = "peluqueria";
			this.user = "root";
			this.pass = "root";

			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			this.connection = DriverManager.getConnection("jdbc:mysql://" + direccion + ":" + puerto + "/" + dbname,
					user, pass);
			this.connection.setAutoCommit(false);
			log.info("Conexión exitosa");
		} catch (Exception e) {
			log.error("Conexión fallida", e);
		}
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			try {
				instancia = new Conexion();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}

	public void cerrarConexion() {
		try {
			this.connection.close();
			log.info("Conexion cerrada");
		} catch (SQLException e) {
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
	}
}
