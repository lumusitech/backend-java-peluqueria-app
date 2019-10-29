package persistencia.dao.interfaz;

import dto.DetalleTurnoDTO;

public interface DetalleTurnoDAO {

	public boolean insert(DetalleTurnoDAO detalleTurno, int id_turno);

	public DetalleTurnoDTO obtenerDesdeTurno(int idTurno);

}
