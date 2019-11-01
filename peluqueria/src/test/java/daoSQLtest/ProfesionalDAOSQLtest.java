package daoSQLtest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dto.EstadoProfesional;
import dto.ProfesionalDTO;
import persistencia.dao.mysql.ProfesionalDAOSQL;

public class ProfesionalDAOSQLtest {
	ProfesionalDAOSQL daosql;
	EstadoProfesional estado;
	ProfesionalDTO prueba;

	public void variables() {
		daosql = new ProfesionalDAOSQL();
		estado = EstadoProfesional.ACTIVO;

		prueba = new ProfesionalDTO(0, "pedro", "sanchez", "pedrito@gmail.com", "252525", "38888871", null, null,
				EstadoProfesional.ACTIVO);
	}

	@Test
	public void obtenerDesdeIDTest() {
		variables();
		ProfesionalDTO prof = daosql.obtenerDesdeID(1);
		System.out.println(prof.toString());
	}

	@Test
	public void readAllTest() {
		variables();
		List<ProfesionalDTO> lista = daosql.readAll(4);
		System.out.println(lista.size());

	}

}
