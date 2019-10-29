package daoSQLtest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.Test;
import dto.TurnoDTO;
import dto.ClienteDTO;
import dto.DetalleTurnoDTO;
import dto.EstadoCliente;
import dto.EstadoProfesional;
import dto.EstadoTurno;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import persistencia.dao.mysql.TurnoDAOSQL;

public class TurnoDAOSQLtest {
	TurnoDAOSQL daosql;
	EstadoTurno estado;
	TurnoDTO prueba;
	ClienteDTO cliente;
	ServicioDTO servicio;
	ProfesionalDTO profesional;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

	String date = "11/11/1111";

	// convert String to LocalDate
	LocalDate localDate = LocalDate.parse(date, formatter);

//	public void variables() {
//		daosql = new TurnoDAOSQL();	
//		estado = EstadoTurno.OCUPADO;
//		cliente = new ClienteDTO(1, "test", "test", "test", "1111", "1111", EstadoCliente.ACTIVO);
////		profesional = new ProfesionalDTO(1, "test", "test", "test", "1111", "1111", EstadoProfesional.ACTIVO);
//		servicio = new ServicioDTO(1, "test", 1);
////		prueba = new TurnoDTO(0,localDate, 1, cliente, profesional, servicio, estado);
//	}
	@Test
	public void obtenerDesdeIDTest() {
		daosql = new TurnoDAOSQL();
		List<TurnoDTO> turno = daosql.readAll();
		System.out.println(turno.get(0).getPromocion().toString());
		daosql.insert(turno.get(0));
	}
//	@Test
//	public void readAllTest() {
//		variables();
//		List<TurnoDTO> lista = daosql.readAll();
//		TurnoDTO test= lista.get(0);
//		System.out.println(test.getCliente().equals(cliente));
//		assertTrue(test.equals(prueba));
//	}
//	
//	@Test
//	public void insertTest() {
//		variables();
//		String fecha = "2016-08-16";
//		LocalDate localDate = LocalDate.parse(fecha);
//		prueba.setFecha(localDate);
//		daosql.insert(prueba);
//		List<TurnoDTO> lista = daosql.readAll();
//		TurnoDTO turno = lista.get(lista.size()-1);
//		
//		assertTrue(turno.equals(prueba));
//	}
//	@Test
//	public void deleteTest() {
//		variables();
//		List<TurnoDTO> lista = daosql.readAll();
//		int size = lista.size();
//		TurnoDTO turno = lista.get(size-1);
//		daosql.delete(turno);
//		int sizeTest = daosql.readAll().size();
//		assertTrue(size-sizeTest == 1);
//	}
//	@Test
//	public void updateTest() {
//		variables();
//		prueba.setId(2);
//		ClienteDTO cli = new ClienteDTO(2,"testob","testob","testob","1111","1111",EstadoCliente.ACTIVO);
//		prueba.setCliente(cli);
//		daosql.update(prueba);
//		List<TurnoDTO> lista = daosql.readAll();
//		TurnoDTO turno = lista.get(1);
//		assertTrue(prueba.equals(turno));
//	}
//	@Test
//	public void findTest() {
//		variables();
//		date = "1111-11-11";
////		List<TurnoDTO> lista = daosql.find(date, 1);
////		assertTrue(lista.size() == 3);
//	}

//	@Test
//	public void insertTrueTest() {
//		variables();
//		boolean insert = daosql.insert(prueba);
//		assertTrue(insert);
//	}

//	@Test
//	public void instertFalseTest() {
//		variables();
//		prueba.setCliente(null);
//		boolean insert = daosql.insert(prueba);
//		assertFalse(insert);
//	}

//	@Test
//	public void updateTrueTest() {
//		variables();
//		TurnoDTO clienteAeditar = new TurnoDTO(daosql.ultimoId(), localDate, 3, cliente, profesional, estado);
//		boolean updateTest = daosql.update(clienteAeditar);
//		assertTrue(updateTest);
//	}

//	@Test
//	public void updateFalseTest() {
//		variables();
//		TurnoDTO clienteAeditar = new TurnoDTO(daosql.ultimoId()+3, localDate, 3, cliente, profesional, servicio, estado);
//		boolean updateTest = daosql.update(clienteAeditar);
//		assertFalse(updateTest);
//	}
//	
//	@Test
//	public void deleteTrueTest() {
//		variables();
//		prueba.setId(daosql.ultimoId());
//		boolean delete = daosql.delete(prueba);
//		assertTrue(delete);
//	}
//	
//	@Test 
//	public void deleteFalseTest() {
//		variables();
//		prueba.setId(daosql.ultimoId()+3);
//		boolean delete = daosql.delete(prueba);
//		assertFalse(delete);
//	}
}
