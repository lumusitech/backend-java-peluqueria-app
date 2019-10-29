package persistencia.dao.interfaz;

import java.util.List;

import dto.ClienteDTO;

public interface ClienteDAO {
	public boolean insert(ClienteDTO cliente);

	public boolean delete(ClienteDTO cliente);

	public boolean update(ClienteDTO cliente);

	public boolean used(ClienteDTO cliente);

	public List<ClienteDTO> readAll();

	public ClienteDTO obtenerDesdeID(int id_cliente_seleccionado);

	public ClienteDTO find(String cadenaCliente);

}