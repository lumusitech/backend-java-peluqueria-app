package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ServicioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ServicioDAO;

public class ServicioDAOSQL implements ServicioDAO {
	private static final String insert = "insert into servicio(nombre, precio, duracion, puntos) "
			+ "values (?,?,?,?);";

	private static final String update = "UPDATE servicio SET (nombre = ?, precio = ?, duracion = ?, puntos = ?"
			+ "WHERE id_servicio = ?";

	private static final String delete = "DELETE FROM servicio WHERE id_servicio = ?";

	private static final String readall = "SELECT * FROM servicio";

	@Override
	public boolean insert(ServicioDTO servicio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, servicio.getNombre());
			statement.setString(2, String.valueOf(servicio.getPrecio()));
			statement.setString(3, String.valueOf(servicio.getDuracion()));
			statement.setString(4, String.valueOf(servicio.getPuntos()));
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
	public boolean delete(ServicioDTO servicioAeliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(servicioAeliminar.getId()));
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public boolean update(ServicioDTO servicoAeditar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isupdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, servicoAeditar.getNombre());
			statement.setString(2, String.valueOf(servicoAeditar.getPrecio()));
			statement.setInt(3, servicoAeditar.getId());
			statement.setString(4, String.valueOf(servicoAeditar.getDuracion()));
			statement.setString(5, String.valueOf(servicoAeditar.getPuntos()));

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isupdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isupdateExitoso;
	}

	@Override
	public List<ServicioDTO> readAll() {

		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ServicioDTO> servicios = new ArrayList<ServicioDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				servicios.add(getServicioDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los servicios de la BBDD");
		}
		return servicios;
	}

	private ServicioDTO getServicioDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id_servicio");
		String nombre = resultSet.getString("nombre");
		float precio = resultSet.getFloat("precio");
		int duracion = resultSet.getInt("duracion");
		int puntos = resultSet.getInt("puntos");
		return new ServicioDTO(id, nombre, precio, duracion, puntos);
	}

	@Override
	public boolean used(ServicioDTO profesional_a_verificar) {
		// TODO Auto-generated method stub
		return false;
	}

	public int ultimoId() {
		return readAll().get(readAll().size() - 1).getId();
	}

	@Override
	public ServicioDTO obtenerDesdeID(int id_servicio_seleccionado) {
		int id = id_servicio_seleccionado;

		ServicioDTO servicio = null;
		Conexion conexion = Conexion.getConexion();
		String select_servicio = "SELECT * FROM servicio WHERE id_servicio = ?";
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(select_servicio);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				servicio = getServicioDTO(resultSet);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return servicio;
	}

	@Override
	public ServicioDTO find(String cadenaServicio) {
		String find = "SELECT * FROM servicio WHERE (" + "id_servicio LIKE '%" + cadenaServicio + "%'"
				+ "OR nombre LIKE '%" + cadenaServicio + "%'" + "OR precio LIKE '%" + cadenaServicio + "%'"
				+ ") LIMIT 1";

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ServicioDTO servicio = null;
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				servicio = getServicioDTO(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar el servicio de la BBDD");
		}
		return servicio;
	}

	@Override
	public ServicioDTO find(String cadenaServicio, int id_profesional) {
		String find = "SELECT * FROM servicio WHERE (" + "id_servicio LIKE '%" + cadenaServicio + "%'"
				+ "OR nombre LIKE '%" + cadenaServicio + "%'" + "OR precio LIKE '%" + cadenaServicio + "%'"
				+ "OR duracion LIKE %" + cadenaServicio + "%'" + "OR puntos LIKE %" + cadenaServicio + "%'"
				+ ") LIMIT 1";

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ServicioDTO servicio = null;
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
//				servicio = new ServicioDTO(
//						resultSet.getInt("id_servicio"),
//						resultSet.getString("nombre"),
//						resultSet.getFloat("precio")
//					);
				servicio = getServicioDTO(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar el servicio de la BBDD");
		}

		if (servicio != null) {
			if (tieneHabilidad(servicio.getId(), id_profesional)) {
				return servicio;
			}
		}
		return null;
	}

	private boolean tieneHabilidad(int id_servicio, int id_profesional) {
		boolean habilidad = false;
		String find = "SELECT 1 FROM profesionalXservicio WHERE id_servicio = '" + id_servicio
				+ "' AND id_profesional = '" + id_profesional + "'";
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				habilidad = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar al profesional de la BBDD");
		}

		return habilidad;
	}

	@Override
	public List<ServicioDTO> obtenerDesdeProfesional(int id) {
		java.sql.PreparedStatement statement;
		String select = "SELECT * from profesionalXservicio where id_profesional = ?";
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ServicioDTO> servicios = new ArrayList<ServicioDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setString(1, Integer.toString(id));
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id_servicio = resultSet.getInt("id_servicio");
				servicios.add(obtenerDesdeID(id_servicio));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los servicios de la BBDD");
		}
		return servicios;
	}

}