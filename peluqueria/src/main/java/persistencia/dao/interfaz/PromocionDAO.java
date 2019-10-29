package persistencia.dao.interfaz;

import java.util.List;

import dto.PromocionDTO;

public interface PromocionDAO {

	public boolean insert(PromocionDTO promocion);

	public List<PromocionDTO> readall();

	public PromocionDTO getPromocion(int id);
}