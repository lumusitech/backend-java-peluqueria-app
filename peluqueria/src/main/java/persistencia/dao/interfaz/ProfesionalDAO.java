package persistencia.dao.interfaz;

import java.util.List;

import dto.ProfesionalDTO;
import dto.ServicioDTO;

public interface ProfesionalDAO {
	public boolean insert(ProfesionalDTO profesional);

	public boolean delete(ProfesionalDTO profesional_a_eliminar);

	public boolean update(ProfesionalDTO profesional_a_editar);

	public boolean used(ProfesionalDTO profesional_a_verificar);

	public List<ProfesionalDTO> readAll();

	public ProfesionalDTO obtenerDesdeID(int id_profesional_seleccionado);

	public ProfesionalDTO find(String cadenaProfesional);

	public ProfesionalDTO find(String cadenaProfesional, int id_sucursal);

	ProfesionalDTO find(String cadenaProfesional, int id_sucursal, int id_habilidad);

	public boolean atarProfesionaServicio(ServicioDTO servicio, ProfesionalDTO profesional);

}