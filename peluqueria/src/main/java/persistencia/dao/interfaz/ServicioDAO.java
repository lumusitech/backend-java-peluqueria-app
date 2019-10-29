package persistencia.dao.interfaz;

import java.util.List;

import dto.ServicioDTO;

public interface ServicioDAO {
	public boolean insert(ServicioDTO servicio);

	public boolean delete(ServicioDTO servicio);

	public boolean update(ServicioDTO servicio);

	public boolean used(ServicioDTO servicio);

	public List<ServicioDTO> readAll();

	public ServicioDTO obtenerDesdeID(int id_servicio_seleccionado);

	public ServicioDTO find(String cadenaServicio);

	public List<ServicioDTO> obtenerDesdeProfesional(int id);

	public ServicioDTO find(String cadenaServicio, int id_profesional);
}