package persistencia.dao.interfaz;

public interface DAOAbstractFactory {
	public ProfesionalDAO createProfesionalDAO();

	public ClienteDAO createClienteDAO();

	public TurnoDAO createTurnoDAO();

	public ServicioDAO createServicioDAO();

	public SucursalDAO createSucursalDAO();

	public DetalleTurnoDAO createDetalleTurnoDAO();

	public UsuarioDAO createUsuarioDAO();

	public PromocionDAO createPromocionDAO();
}
