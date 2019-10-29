package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.EstadoUsuario;
import dto.RolUsuario;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.UsuarioDAO;

public class UsuarioDAOSQL implements UsuarioDAO {
	private static final String insert = "insert into usuario(nombre_user,nombre,apellido,dni,email,pass,rol,id_sucursal,estado_usuario)"
			+ " values( ?, ? , ? , ? , ? , ? , ? , ? , ?);";
	private static final String select = "select * from usuario where nombre_user = ? AND pass = ?;";

	@Override
	public boolean insert(UsuarioDTO usuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getNombre());
			statement.setString(3, usuario.getApellido());
			statement.setString(4, usuario.getDni());
			statement.setString(5, usuario.getEmail());
			statement.setString(6, usuario.getPassword());
			statement.setString(7, usuario.getRol().toString());
			statement.setString(8, Integer.toString(usuario.getSucursal().getId()));
			statement.setString(9, usuario.getEstado().toString());
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
	public UsuarioDTO getUser(String user, String pass) {
		UsuarioDTO usuario = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setString(1, user);
			statement.setString(2, pass);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				usuario = getUsuarioDTO(resultSet);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;
	}

	private UsuarioDTO getUsuarioDTO(ResultSet resultSet) throws SQLException {
		SucursalDAOSQL daoSucursal = new SucursalDAOSQL();
		int id = resultSet.getInt("id_usuario");
		String usuario = resultSet.getString("nombre_user");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		String dni = resultSet.getString("dni");
		String email = resultSet.getString("email");
		String password = resultSet.getString("pass");
		RolUsuario rol = RolUsuario.valueOf(resultSet.getString("rol"));
		SucursalDTO sucursal = daoSucursal.obtenerDesdeID(resultSet.getInt("id_sucursal"));
		EstadoUsuario estado = EstadoUsuario.valueOf(resultSet.getString("estado_usuario"));

		UsuarioDTO ret = new UsuarioDTO(id, usuario, nombre, apellido, dni, email, password, rol, sucursal, estado);
		return ret;
	}

}
