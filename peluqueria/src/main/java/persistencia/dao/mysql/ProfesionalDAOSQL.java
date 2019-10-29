package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EstadoProfesional;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProfesionalDAO;

public class ProfesionalDAOSQL implements ProfesionalDAO {
	private static final String insert = "insert into profesional (nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) "
			+ "values (?, ?, ?, ?, ?, ?, ?);";

	private static final String update = "UPDATE profesional SET nombre = ?, apellido = ?, email = ?, telefono = ?, dni = ?, estado_profesional = ? "
			+ "WHERE id_profesional = ?";
	private static final String delete = "DELETE FROM profesional WHERE id_profesional = ?";

	private static final String readall = "SELECT * FROM profesional";

	private static final String insertX = "insert into profesionalXservicio(id_servicio,id_profesional) values( ? , ? );";

	@Override
	public boolean insert(ProfesionalDTO profesional) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, profesional.getNombre());
			statement.setString(2, profesional.getApellido());
			statement.setString(3, profesional.getEmail());
			statement.setString(4, profesional.getTelefono());
			statement.setString(5, profesional.getDni());
			statement.setString(6, Integer.toString(profesional.getSucursal().getId()));
			statement.setString(7, profesional.getEstado().toString());
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
	public boolean delete(ProfesionalDTO profesional_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(profesional_a_eliminar.getId()));
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
	public boolean update(ProfesionalDTO profesional_a_editar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isupdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, profesional_a_editar.getNombre());
			statement.setString(2, profesional_a_editar.getApellido());
			statement.setString(3, profesional_a_editar.getEmail());
			statement.setString(4, profesional_a_editar.getTelefono());
			statement.setString(5, profesional_a_editar.getDni());
			statement.setString(6, profesional_a_editar.getEstado().toString());
			statement.setInt(7, profesional_a_editar.getId());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isupdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(update);
		return isupdateExitoso;
	}

	@Override
	public List<ProfesionalDTO> readAll() {

		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ProfesionalDTO> personas = new ArrayList<ProfesionalDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personas.add(getProfesionalDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar las personas de la BBDD");
		}
		return personas;
	}

	public boolean atarProfesionaServicio(ServicioDTO servicio, ProfesionalDTO profesional) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insertX);
			statement.setInt(1, servicio.getId());
			statement.setInt(2, profesional.getId());
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

	private ProfesionalDTO getProfesionalDTO(ResultSet resultSet) throws SQLException {
		ServicioDAOSQL daoServicio = new ServicioDAOSQL();
		SucursalDAOSQL daoSucursal = new SucursalDAOSQL();
		int id = resultSet.getInt("id_profesional");
		String apellido = resultSet.getString("apellido");
		String nombre = resultSet.getString("nombre");
		String email = resultSet.getString("email");
		String telefono = resultSet.getString("telefono");
		String dni = resultSet.getString("dni");
		SucursalDTO sucursal = daoSucursal.obtenerDesdeID(resultSet.getInt("id_sucursal"));
		ArrayList<ServicioDTO> habilidades = (ArrayList<ServicioDTO>) daoServicio.obtenerDesdeProfesional(id);
		EstadoProfesional estado = EstadoProfesional.valueOf(resultSet.getString("estado_profesional"));
		return new ProfesionalDTO(id, nombre, apellido, email, telefono, dni, sucursal, habilidades, estado);
	}

	@Override
	public boolean used(ProfesionalDTO profesional_a_verificar) {
		// TODO Auto-generated method stub
		return false;
	}

	public int ultimoId() {
		return readAll().get(readAll().size() - 1).getId();
	}

	@Override
	public ProfesionalDTO obtenerDesdeID(int id_profesional_seleccionado) {

		ProfesionalDTO profesional = null;
		Conexion conexion = Conexion.getConexion();
		String select_profesional = "SELECT * FROM profesional WHERE id_profesional = ?";
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(select_profesional);
			statement.setInt(1, id_profesional_seleccionado);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				profesional = getProfesionalDTO(resultSet);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profesional;
	}

	@Override
	public ProfesionalDTO find(String cadenaProfesional) {
		String find = "SELECT * FROM profesional WHERE (" + "id_profesional LIKE '%" + cadenaProfesional + "%'"
				+ " OR nombre LIKE '%" + cadenaProfesional + "%'" + " OR apellido LIKE '%" + cadenaProfesional + "%'"
				+ " OR email LIKE '%" + cadenaProfesional + "%'" + " OR telefono LIKE '%" + cadenaProfesional + "%'"
				+ " OR dni LIKE '%" + cadenaProfesional + "%'" + "OR id_sucursal '%" + cadenaProfesional + "%'"
				+ " OR estado_profesional LIKE '%" + cadenaProfesional + "%'" + ") LIMIT 1";

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ProfesionalDTO profesional = null;
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				profesional = getProfesionalDTO(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar al profesional de la BBDD");
		}
		return profesional;
	}

	@Override
	public ProfesionalDTO find(String cadenaProfesional, int id_sucursal) {
		String find = "SELECT * FROM profesional WHERE (" + "id_sucursal = " + id_sucursal + ""
				+ " AND (id_profesional LIKE '%" + cadenaProfesional + "%'" + " OR nombre LIKE '%" + cadenaProfesional
				+ "%'" + " OR apellido LIKE '%" + cadenaProfesional + "%'" + " OR email LIKE '%" + cadenaProfesional
				+ "%'" + " OR telefono LIKE '%" + cadenaProfesional + "%'" + " OR dni LIKE '%" + cadenaProfesional
				+ "%'" + " OR estado_profesional LIKE '%" + cadenaProfesional + "%')" + ") LIMIT 1";

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ProfesionalDTO profesional = null;
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				profesional = getProfesionalDTO(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar al profesional de la BBDD");
		}
		return profesional;
	}

	@Override
	public ProfesionalDTO find(String cadenaProfesional, int id_sucursal, int id_habilidad) {
		String find = "SELECT * FROM profesional WHERE (" + "id_sucursal = " + id_sucursal + ""
				+ " AND (id_profesional LIKE '%" + cadenaProfesional + "%'" + " OR nombre LIKE '%" + cadenaProfesional
				+ "%'" + " OR apellido LIKE '%" + cadenaProfesional + "%'" + " OR email LIKE '%" + cadenaProfesional
				+ "%'" + " OR telefono LIKE '%" + cadenaProfesional + "%'" + " OR dni LIKE '%" + cadenaProfesional
				+ "%'" + " OR estado_profesional LIKE '%" + cadenaProfesional + "%')" + ") LIMIT 1";

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ProfesionalDTO profesional = null;
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				profesional = getProfesionalDTO(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar al profesional de la BBDD");
		}

		if (profesional != null) {
			if (tieneHabilidad(profesional.getId(), id_habilidad)) {
				return profesional;
			}
		}

		return null;
	}

	private boolean tieneHabilidad(int id_profesional, int id_habilidad) {
		boolean habilidad = false;
		String find = "SELECT 1 FROM habilidad WHERE id_profesional = '" + id_profesional + "' AND id_habilidad = '"
				+ id_habilidad + "'";
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
}
