package persistencia.dao.interfaz;

import dto.UsuarioDTO;

public interface UsuarioDAO {

	public boolean insert(UsuarioDTO usuario);

	public UsuarioDTO getUser(String user, String pass);

}