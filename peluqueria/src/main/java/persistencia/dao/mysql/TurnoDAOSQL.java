package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import dto.DetalleTurnoDTO;
import dto.EstadoTurno;
import dto.ProfesionalDTO;
import dto.PromocionDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;
import dto.TurnoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TurnoDAO;

public class TurnoDAOSQL implements TurnoDAO {
	private static final String insert = "insert into turno(fecha,hora_inicio,estado_turno,precio,monto_pagado,puntaje,id_promocion,id_sucursal,id_cliente)"
			+ " values( ? , ? , ? , ? , ? , ? , ? , ? , ? );";

	private static final String update = "UPDATE turno SET fecha = ?, hora_inicio = ?, id_cliente = ?, id_profesional = ?, id_servicio = ?, "
			+ "id_sucursal = ?, estado_turno = ? WHERE id_turno = ?";
	private static final String cancel = "UPDATE turno SET estado_turno = ? WHERE id_turno = ?";
	private static final String delete = "DELETE FROM turno WHERE id_turno = ?";
	private static final String readall = "SELECT * FROM turno";
	private static final String readallFromSucursal = "SELECT * FROM turno WHERE id_sucursal = ?";
	private static final String readTurnoDesdeTurno = "SELECT * FROM turno WHERE id_turno = ?";
	private static final String selectDetalle = "select * from detalle_turno where id_turno = ? ;";
	private static final String ultimoId = "SELECT MAX(id_turno) FROM turno;";
	private static final String insertX = "insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno)"
			+ " values( ? , ? , ? , ? , ? );";
	private static final String readAllFromSucursalFecha = "select * from turno where id_sucursal = ? and fecha = ?;";

	@Override
	public boolean insert(TurnoDTO turno) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, turno.getFecha().toString());
			statement.setTime(2, turno.getHora_inicio());
			statement.setString(3, turno.getEstado_turno().toString());
			statement.setFloat(4, turno.getPrecio());
			statement.setFloat(5, turno.getMontoPagado());
			statement.setInt(6, turno.getPuntos());
			statement.setInt(7, turno.getPromocion().getId());
			statement.setInt(8, turno.getSucursal().getId());
			statement.setInt(9, turno.getCliente().getId());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = insertarDetalles(turno.getDetalles(), ultimoId());
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

	private boolean insertarDetalles(List<DetalleTurnoDTO> detalles, int id) {
		boolean ret = true;
		for (DetalleTurnoDTO detalleTurnoDTO : detalles) {
			ret = ret && insertarDetalle(detalleTurnoDTO, id);
		}
		return ret;
	}

	private boolean insertarDetalle(DetalleTurnoDTO detalle, int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insertX);
			statement.setTime(1, detalle.getHoraInicio());
			statement.setTime(2, detalle.getHoraFin());
			statement.setInt(3, detalle.getProfesional().getId());
			statement.setInt(4, detalle.getServicio().getId());
			statement.setInt(5, id);
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
	public boolean delete(TurnoDTO turnoAeliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(turnoAeliminar.getId()));
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
	public boolean update(TurnoDTO turnoAModificar) {
		System.out.println(turnoAModificar.getId());
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isupdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

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
	public boolean used(TurnoDTO profesional_a_verificar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TurnoDTO> getTurnosDelDia(int id_sucursal, LocalDate fecha) {

		// "select * from turno where id_sucursal = ? and fecha = ?;";

		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TurnoDTO> turnos = new ArrayList<TurnoDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(readAllFromSucursalFecha);
			statement.setInt(1, id_sucursal);
			statement.setString(2, fecha.toString());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				turnos.add(getTurnoDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los turnos de la BBDD");
		}
		return turnos;
	}

	@Override
	public List<TurnoDTO> readAll() {
		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TurnoDTO> turnos = new ArrayList<TurnoDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				turnos.add(getTurnoDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los turnos de la BBDD");
		}
		return turnos;
	}

	@Override
	public List<TurnoDTO> readAll(int id_sucursal) {
		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TurnoDTO> turnos = new ArrayList<TurnoDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(readallFromSucursal);
			statement.setInt(1, id_sucursal);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				turnos.add(getTurnoDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los turnos de la BBDD");
		}
		return turnos;
	}

	private TurnoDTO getTurnoDTO(ResultSet resultSet) throws SQLException {
		ClienteDAOSQL clientedao = new ClienteDAOSQL();
		SucursalDAOSQL sucursaldao = new SucursalDAOSQL();
		PromocionDAOSQL promodao = new PromocionDAOSQL();

		int id = resultSet.getInt("id_turno");
		LocalDate fecha = LocalDate.parse(resultSet.getString("fecha"));
		Time hora_inicio = resultSet.getTime("hora_inicio");
		float precio = resultSet.getFloat("precio");
		float montoPagado = resultSet.getFloat("monto_pagado");
		int puntos = resultSet.getInt("puntaje");
		ClienteDTO cliente = clientedao.obtenerDesdeID(resultSet.getInt("id_cliente"));
		SucursalDTO sucursal = sucursaldao.obtenerDesdeID(resultSet.getInt("id_sucursal"));
		EstadoTurno estado_turno = EstadoTurno.valueOf(resultSet.getString("estado_turno"));
		List<DetalleTurnoDTO> detalles = obtenerDetalles(id);
		PromocionDTO promocion = promodao.getPromocion(resultSet.getInt("id_promocion"));

		return new TurnoDTO(id, fecha, hora_inicio, precio, montoPagado, puntos, cliente, detalles, sucursal,
				estado_turno, promocion);
	}

	public List<DetalleTurnoDTO> obtenerDetalles(int id_turno) {
		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		List<DetalleTurnoDTO> detalles = new ArrayList<DetalleTurnoDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(selectDetalle);
			statement.setInt(1, id_turno);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				detalles.add(getDetalleTurnoDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los detalles de la BBDD");
		}
		return detalles;
	}

	private DetalleTurnoDTO getDetalleTurnoDTO(ResultSet resultSet) throws SQLException {
		ProfesionalDAOSQL profesionaldao = new ProfesionalDAOSQL();
		ServicioDAOSQL serviciodao = new ServicioDAOSQL();

		int id = resultSet.getInt("id_detalle_turno");
		Time horaInicio = resultSet.getTime("hora_inicio");
		Time horaFin = resultSet.getTime("hora_fin");
		ProfesionalDTO profesional = profesionaldao.obtenerDesdeID(resultSet.getInt("id_profesional"));
		ServicioDTO servicio = serviciodao.obtenerDesdeID(resultSet.getInt("id_servicio"));

		return new DetalleTurnoDTO(id, horaInicio, horaFin, profesional, servicio);
	}

	public int ultimoId() {
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int id = 0;
		try {
			statement = conexion.prepareStatement(ultimoId);
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

	@Override
	public List<TurnoDTO> find(String fecha_inicio, String fecha_fin, String cadena, int id_sucursal) {
		String find = "";
		if (cadena.equals("") && fecha_fin.equals("")) {
			find = "SELECT * FROM turno WHERE fecha >= CAST('" + fecha_inicio + "' AS DATE) AND id_sucursal = "
					+ id_sucursal + ";";
		} else if (cadena.equals("") && fecha_inicio.equals("")) {
			find = "SELECT * FROM turno WHERE fecha <= CAST('" + fecha_fin + "' AS DATE) AND id_sucursal = "
					+ id_sucursal + ";";
		} else if (!fecha_inicio.equals("") && !fecha_fin.equals("") && cadena.equals("")) {
			find = "SELECT * FROM turno WHERE ((fecha BETWEEN CAST('" + fecha_inicio + "' AS DATE) " + "AND CAST('"
					+ fecha_fin + "' AS DATE)) " + "AND id_sucursal = " + id_sucursal + ");";
		} else if (fecha_inicio.equals("") && fecha_fin.equals("")) {
			find = "SELECT * FROM turno WHERE(" + "(hora_inicio LIKE '%" + cadena + "%'" + " OR id_cliente LIKE '%"
					+ cadena + "%'" + " OR id_profesional LIKE '%" + cadena + "%'" + " OR estado_turno LIKE '%" + cadena
					+ "%') AND id_sucursal = " + id_sucursal + ")";
		} else if (!fecha_inicio.equals("") && fecha_fin.equals("")) {
			find = "SELECT * FROM turno WHERE(" + "fecha >= '" + fecha_inicio + "'" + " AND (hora_inicio LIKE '%"
					+ cadena + "%'" + " OR id_cliente LIKE '%" + cadena + "%'" + " OR id_profesional LIKE '%" + cadena
					+ "%'" + " OR estado_turno LIKE '%" + cadena + "%') AND id_sucursal = " + id_sucursal + ")";
		} else if (fecha_inicio.equals("") && !fecha_fin.equals("")) {
			find = "SELECT * FROM turno WHERE(" + "fecha <= '" + fecha_fin + "'" + " AND (hora_inicio LIKE '%" + cadena
					+ "%'" + " OR id_cliente LIKE '%" + cadena + "%'" + " OR id_profesional LIKE '%" + cadena + "%'"
					+ " OR estado_turno LIKE '%" + cadena + "%') AND id_sucursal = " + id_sucursal + ")";
		} else {
			find = "SELECT * FROM turno WHERE(" + "(fecha BETWEEN '" + fecha_inicio + "'" + " AND '" + fecha_fin + "')"
					+ " AND (hora_inicio LIKE '%" + cadena + "%'" + " OR id_cliente LIKE '%" + cadena + "%'"
					+ " OR id_profesional LIKE '%" + cadena + "%'" + " OR estado_turno LIKE '%" + cadena
					+ "%') AND id_sucursal = " + id_sucursal + ")";
		}

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TurnoDTO> turnos = new ArrayList<TurnoDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				turnos.add(getTurnoDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los turnos de la BBDD");
		}
		return turnos;
	}

	@Override
	public List<TurnoDTO> find(String fecha_turno, int id_profesional, int id_sucursal) {
		String find = "SELECT * FROM turno WHERE fecha = ? AND id_profesional = ? "
				+ "AND (estado_turno = ? OR estado_turno = ?) " + "AND id_sucursal = '" + id_sucursal + "'";

		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TurnoDTO> turnos = new ArrayList<TurnoDTO>();

		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setString(1, fecha_turno);
			statement.setInt(2, id_profesional);
			statement.setString(3, "OCUPADO");
			statement.setString(4, "DEMORADO");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				turnos.add(getTurnoDTO(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al recuperar los turnos filtrados por fecha y profesional de la BBDD");
		}
		return turnos;
	}

	@Override
	public TurnoDTO obtenerDesdeID(int id_turno) {

		java.sql.PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		TurnoDTO turno = null;
		try {
			Conexion conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(readTurnoDesdeTurno);
			statement.setInt(1, id_turno);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				turno = getTurnoDTO(resultSet);
			}

		} catch (Exception e) {
			throw new RuntimeException("Error al recuperar el turno" + id_turno + " de la BBDD");
		}

		return turno;
	}

	@Override
	public boolean cancelarTurno(int id_turno) {
		String estadoCancelado = EstadoTurno.CANCELADO.toString();
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isCancelExitoso = false;
		try {
			statement = conexion.prepareStatement(cancel);
			statement.setString(1, estadoCancelado);
			statement.setInt(2, id_turno);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isCancelExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isCancelExitoso;
	}

	@Override
	public ClienteDTO getClienteDesdeTurno(int id_cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfesionalDTO getProfesionalDesdeTurno(int id_profesional) {
		// TODO Auto-generated method stub
		return null;
	}

}
