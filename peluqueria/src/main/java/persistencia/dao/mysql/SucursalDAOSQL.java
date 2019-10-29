package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.SucursalDTO;
import persistencia.conexion.Conexion;

public class SucursalDAOSQL implements persistencia.dao.interfaz.SucursalDAO {

	@Override
	public boolean insert(SucursalDTO sucursal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(SucursalDTO sucursal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(SucursalDTO sucursal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean used(SucursalDTO sucursal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SucursalDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SucursalDTO obtenerDesdeID(int id_sucursal) {
		SucursalDTO sucursal = null;
		Conexion conexion = Conexion.getConexion();
		String select_sucursal = "SELECT * FROM sucursal WHERE id_sucursal = ?";
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(select_sucursal);
			statement.setInt(1, id_sucursal);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				sucursal = getSucursalDTO(resultSet);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sucursal;
	}

	public SucursalDTO getSucursalDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id_sucursal");
		String nombre = resultSet.getString("nombre");
		String idioma = resultSet.getString("idioma");
		String calle = resultSet.getString("calle");
		int altura = resultSet.getInt("altura");
		return new SucursalDTO(id, nombre, idioma, calle, altura);
	}

	@Override
	public SucursalDTO find(String cadenaSucursal) {
		// TODO Auto-generated method stub
		return null;
	}

}
