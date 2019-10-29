package persistencia.dao.mysql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EstadoPromocion;
import dto.PromocionDTO;
import dto.ServicioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PromocionDAO;

public class PromocionDAOSQL implements PromocionDAO {
	private static final String insert = "insert into promocion(nombre,estado,precio,multiplicacion)"
			+ " values( ? , ? , ? , ? );";
	private static final String insertX = "insert into servicioxpromocion(id_servicio,id_promocion)"
			+ " values( ? , ? );";
	private static final String readall = "select * from promocion";

	private static final String selectId = "select * from promocion where id_promocion = ?;";

	private static final String selectX = "select * from servicioxpromocion WHERE id_promocion = ? ";

	private static final String ultimoID = "SELECT MAX(id_promocion) FROM promocion;";

	@Override
	public boolean insert(PromocionDTO promocion) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, promocion.getNombre());
			statement.setString(2, String.valueOf(promocion.getEstado()));
			statement.setBigDecimal(3, promocion.getPrecio());
			statement.setInt(4, promocion.getMultiplicacion());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
				int id_promo = ultimoID();
				for (ServicioDTO elem : promocion.getServicios()) {
					insertX(elem, id_promo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isInsertExitoso;
	}

	public boolean insertX(ServicioDTO servicio, int id_promocion) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insertX);
			statement.setInt(1, servicio.getId());
			statement.setInt(2, id_promocion);
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isInsertExitoso;
	}

	@Override
	public List<PromocionDTO> readall() {
		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PromocionDTO> promociones = new ArrayList<PromocionDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				promociones.add(getPromocionDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los servicios de la BBDD");
		}
		return promociones;
	}

	@Override
	public PromocionDTO getPromocion(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		PromocionDTO promo = null;
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(selectId);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				promo = getPromocionDTO(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los promocion de la BBDD");
		}

		return promo;

	}

	public PromocionDTO getPromocionDTO(ResultSet resul) throws SQLException {
		int id = resul.getInt("id_promocion");
		String nombre = resul.getString("nombre");
		EstadoPromocion estado = EstadoPromocion.valueOf(resul.getString("estado"));
		BigDecimal precio = resul.getBigDecimal("precio");
		int multiplicacion = resul.getInt("multiplicacion");
		List<ServicioDTO> servicios = getServicios(id);
		PromocionDTO promo = new PromocionDTO(id, nombre, estado, precio, multiplicacion, servicios);
		return promo;
	}

	private List<ServicioDTO> getServicios(int id) {
		ArrayList<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
		PreparedStatement statement;
		ResultSet resultSet;
		ServicioDAOSQL serviciodao = new ServicioDAOSQL();
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(selectX);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				servicios.add(serviciodao.obtenerDesdeID(resultSet.getInt("id_servicio")));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los servicios de la BBDD");
		}
		return servicios;
	}

	public int ultimoID() {
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int id = 0;
		try {
			statement = conexion.prepareStatement(ultimoID);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return id;
	}

}
