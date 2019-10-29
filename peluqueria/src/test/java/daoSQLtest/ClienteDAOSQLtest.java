package daoSQLtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dto.ClienteDTO;
import dto.EstadoCliente;
import persistencia.dao.mysql.ClienteDAOSQL;

public class ClienteDAOSQLtest {
	ClienteDAOSQL daosql;
	EstadoCliente estado;
	ClienteDTO prueba;

	public void variables() {
		daosql = new ClienteDAOSQL();
		estado = EstadoCliente.ACTIVO;
		prueba = new ClienteDTO(0, "Prueba", "Prueba", "Prueba@prueba.com", "11334455", "40648005", estado, null);
	}

	@Test
	public void readallTest() {
		variables();
		ClienteDTO cliente = new ClienteDTO(2, "test", "test", "test", "1111", "1111", EstadoCliente.ACTIVO, null);
		List<ClienteDTO> lista = daosql.readAll();
		ClienteDTO test = (ClienteDTO) lista.get(0);
		assertTrue(cliente.equals(test));
	}

	@Test
	public void insertTest() {
		variables();
		daosql.insert(prueba);
		List<ClienteDTO> lista = daosql.readAll();
		assertTrue(prueba.equals(lista.get(lista.size() - 1)));
	}

	@Test

	public void obtenerDesdeIdTest() {
		variables();
		ClienteDTO cliente = new ClienteDTO(2, "testob", "testob", "testob", "1111", "1111", EstadoCliente.ACTIVO,
				null);
		ClienteDTO test = daosql.obtenerDesdeID(2);
		assertTrue(cliente.equals(test));
	}

	@Test
	public void updateTest() {
		variables();
		List<ClienteDTO> lista = daosql.readAll();
		ClienteDTO cliente = lista.get(lista.size() - 1);
		daosql.update(cliente);
		cliente.setNombre("modificado");
		daosql.update(cliente);
		ClienteDTO test = daosql.obtenerDesdeID(cliente.getId());

		assertTrue(cliente.equals(test));
	}

	@Test
	public void deleteTest() {
		variables();
		List<ClienteDTO> lista = daosql.readAll();
		ClienteDTO cliente = lista.get(lista.size() - 1);
		daosql.delete(cliente);
		ClienteDTO test = daosql.obtenerDesdeID(cliente.getId());
		assertTrue(test == null);
	}

	@Test
	public void findTest() {
		variables();
		ClienteDTO cliente = new ClienteDTO(0, "xxx", "xxx", "xxx", "1111", "1111", EstadoCliente.ACTIVO, null);
		daosql.insert(cliente);
		ClienteDTO test = daosql.find("xxx");
		daosql.delete(test);
		assertTrue(cliente.equals(test));
	}
//	@Test
//	public void insertTrueTest() {
//		variables();
//		boolean insert = daosql.insert(prueba);
//		assertTrue(insert);
//	}
//	
//	@Test
//	public void instertFalseTest() {
//		variables();
//		prueba.setNombre(null);
//		boolean insert = daosql.insert(prueba);
//		assertFalse(insert);
//	}
//	
//	@Test
//	public void updateTrueTest() {
//		variables();
//		ClienteDTO clienteAeditar = new ClienteDTO(daosql.ultimoId(), "EDICION", "EDICION", "EDICION@pEDICION.com", "11334455", "40648005", estado);
//		boolean updateTest = daosql.update(clienteAeditar);
//		assertTrue(updateTest);
//	}
//	
//	@Test
//	public void updateFalseTest() {
//		variables();
//		ClienteDTO clienteAeditar = new ClienteDTO(daosql.ultimoId()+3, "EDICION", "EDICION", "EDICION@pEDICION.com", "11334455", "40648005", estado);
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
