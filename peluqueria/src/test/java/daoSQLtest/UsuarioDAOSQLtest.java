package daoSQLtest;

import org.junit.Test;

import dto.EstadoUsuario;
import dto.RolUsuario;
import dto.UsuarioDTO;
import persistencia.dao.mysql.UsuarioDAOSQL;

public class UsuarioDAOSQLtest {
	UsuarioDAOSQL daosql;
	UsuarioDTO user;

	public void variables() {
		daosql = new UsuarioDAOSQL();
		user = new UsuarioDTO(1, "test", "test", "test", "1", "test", "test", RolUsuario.ADMINISTRADOR, null,
				EstadoUsuario.ACTIVO);
	}

	@Test
	public void getUserTest() {
		variables();
		user = daosql.getUser("churrok", "julicapo");
		System.out.println(user.toString());
	}
}
