package daoSQLtest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dto.ServicioDTO;
import persistencia.dao.mysql.ServicioDAOSQL;

public class ServicioDAOSQLtest {

	ServicioDAOSQL daosql;
	ServicioDTO servicio;

	public void variables() {
		daosql = new ServicioDAOSQL();
		servicio = new ServicioDTO(0, "test", 1, 1, 1);
	}

	@Test
	public void insertTest() {
		variables();
		daosql.insert(servicio);
	}

	@Test
	public void obtenerTest() {
		variables();
		ServicioDTO prueba = daosql.obtenerDesdeID(1);
		System.out.println(prueba.toString());
		assertTrue(prueba.equals(servicio));
	}
}
