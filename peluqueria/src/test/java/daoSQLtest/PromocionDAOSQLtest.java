package daoSQLtest;

import org.junit.Test;
import dto.PromocionDTO;
import persistencia.dao.mysql.PromocionDAOSQL;

public class PromocionDAOSQLtest {

	@Test
	public void readallTest() {
		PromocionDAOSQL dao = new PromocionDAOSQL();
		java.util.List<PromocionDTO> promos = dao.readall();
		for (PromocionDTO promocionDTO : promos) {
			System.out.println(promocionDTO.toString());
		}
	}
}
