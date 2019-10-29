package presentacion.controlador.administrador;

import presentacion.vista.administrador.AdministradorVista;

public class AdministradorController {
	private static AdministradorController INSTANCE;
	private AdministradorVista administradorVista;
	
	public static AdministradorController getInstance() {
		if(INSTANCE == null) INSTANCE = new AdministradorController();
		return INSTANCE;
	}
	
	private AdministradorController() {
		this.administradorVista = AdministradorVista.getInstance();
	}
	
	public void mostrarAdministradorVista() {
		this.administradorVista.mostrar();
	}
}
