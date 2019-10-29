package persistencia.dao.mysql;

import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DetalleTurnoDAO;
import persistencia.dao.interfaz.ProfesionalDAO;
import persistencia.dao.interfaz.PromocionDAO;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.SucursalDAO;
import persistencia.dao.interfaz.TurnoDAO;
import persistencia.dao.interfaz.UsuarioDAO;

public class DAOSQLFactory implements DAOAbstractFactory {

	@Override
	public ClienteDAO createClienteDAO() {
		// TODO Auto-generated method stub
		return new ClienteDAOSQL();
	}

	@Override
	public ProfesionalDAO createProfesionalDAO() {
		// TODO Auto-generated method stub
		return new ProfesionalDAOSQL();
	}

	@Override
	public TurnoDAO createTurnoDAO() {
		// TODO Auto-generated method stub
		return new TurnoDAOSQL();
	}

	@Override
	public ServicioDAO createServicioDAO() {
		// TODO Auto-generated method stub
		return new ServicioDAOSQL();
	}

	@Override
	public SucursalDAO createSucursalDAO() {
		// TODO Auto-generated method stub
		return new SucursalDAOSQL();
	}

	@Override
	public DetalleTurnoDAO createDetalleTurnoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDAO createUsuarioDAO() {
		// TODO Auto-generated method stub
		return new UsuarioDAOSQL();
	}

	@Override
	public PromocionDAO createPromocionDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
