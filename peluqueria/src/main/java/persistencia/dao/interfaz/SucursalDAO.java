package persistencia.dao.interfaz;

import java.util.List;

import dto.SucursalDTO;

public interface SucursalDAO {
	public boolean insert(SucursalDTO sucursal);

	public boolean delete(SucursalDTO sucursal);

	public boolean update(SucursalDTO sucursal);

	public boolean used(SucursalDTO sucursal);

	public List<SucursalDTO> readAll();

	public SucursalDTO obtenerDesdeID(int id_sucursal);

	public SucursalDTO find(String cadenaSucursal);

}