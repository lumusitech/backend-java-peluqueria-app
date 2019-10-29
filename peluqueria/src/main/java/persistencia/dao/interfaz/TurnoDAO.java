package persistencia.dao.interfaz;

import java.time.LocalDate;
import java.util.List;

import dto.ClienteDTO;
import dto.ProfesionalDTO;
import dto.TurnoDTO;

public interface TurnoDAO {
	public boolean insert(TurnoDTO profesional);

	public boolean delete(TurnoDTO profesional_a_eliminar);

	public boolean update(TurnoDTO profesional_a_editar);

	public boolean used(TurnoDTO profesional_a_verificar);

	public List<TurnoDTO> readAll();

	List<TurnoDTO> readAll(int id_sucursal);

	public List<TurnoDTO> find(String fecha_inicio, String fecha_fin, String cadena, int id_sucursal);

	public List<TurnoDTO> find(String fecha_turno, int id_profesional, int id_sucursal);

	public ClienteDTO getClienteDesdeTurno(int id_cliente);

	public ProfesionalDTO getProfesionalDesdeTurno(int id_profesional);

	public TurnoDTO obtenerDesdeID(int id_turno);

	public boolean cancelarTurno(int id_turno);

	public List<TurnoDTO> getTurnosDelDia(int id_sucursal, LocalDate fecha);

}
