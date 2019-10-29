package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dto.ClienteDTO;
import dto.EstadoCliente;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ClienteDAO;

public class ClienteDAOSQL implements ClienteDAO {
	private static final String insert = "insert into cliente(nombre,apellido,dni,email,telefono,estado_cliente,ultima_visita)"
			+ " values( ?, ?, ?, ?, ?, ?, ?";

	private static final String delete = "DELETE FROM cliente WHERE id_cliente = ?";

	private static final String update = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ?, dni = ?, estado_cliente = ?, ultima_visita = ? "
			+ "WHERE id_cliente = ?";

	private static final String readall = "SELECT * FROM cliente";

	@Override
	public boolean insert(ClienteDTO cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getApellido());
			statement.setString(3, cliente.getDni());
			statement.setString(4, cliente.getEmail());
			statement.setString(5, cliente.getTelefono());
			statement.setString(6, cliente.getEstado().toString());
			statement.setString(7, cliente.getUltimaVisita().toString());

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
	public boolean delete(ClienteDTO cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(cliente.getId()));
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
	public boolean update(ClienteDTO cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isupdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getApellido());
			statement.setString(3, cliente.getEmail());
			statement.setString(4, cliente.getTelefono());
			statement.setString(5, cliente.getDni());
			statement.setString(6, cliente.getEstado().toString());
			statement.setString(7, cliente.getUltimaVisita().toString());
			statement.setString(8, Integer.toString(cliente.getId()));

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
	public List<ClienteDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clientes.add(getClienteDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	private ClienteDTO getClienteDTO(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("id_cliente");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		String email = resultSet.getString("email");
		String telefono = resultSet.getString("telefono");
		String dni = resultSet.getString("dni");
		LocalDate ultimaVisita = LocalDate.parse(resultSet.getString("ultima_visita"));
		EstadoCliente estado = EstadoCliente.valueOf(resultSet.getString("estado_cliente"));

		return new ClienteDTO(id, nombre, apellido, email, telefono, dni, estado, ultimaVisita);
	}

	@Override
	public boolean used(ClienteDTO cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	public int ultimoId() {
		return readAll().get(readAll().size() - 1).getId();
	}

	@Override
	public ClienteDTO obtenerDesdeID(int id_cliente_seleccionado) {

		ClienteDTO cliente = null;
		Conexion conexion = Conexion.getConexion();
		String select_cliente = "SELECT * FROM cliente WHERE id_cliente = ?";
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(select_cliente);
			statement.setInt(1, id_cliente_seleccionado);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				cliente = getClienteDTO(resultSet);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cliente;

	}

	@Override
	public ClienteDTO find(String cadenaCliente) {
		String find = "SELECT * FROM cliente WHERE (" + "id_cliente LIKE '%" + cadenaCliente + "%'"
				+ "OR nombre LIKE '%" + cadenaCliente + "%'" + "OR apellido LIKE '%" + cadenaCliente + "%'"
				+ "OR email LIKE '%" + cadenaCliente + "%'" + "OR telefono LIKE '%" + cadenaCliente + "%'"
				+ "OR dni LIKE '%" + cadenaCliente + "%'" + "OR estado_cliente LIKE '%" + cadenaCliente + "%'"
				+ ") LIMIT 1";

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ClienteDTO cliente = null;
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cliente = getClienteDTO(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar al cliente de la BBDD");
		}
		return cliente;
	}
}
