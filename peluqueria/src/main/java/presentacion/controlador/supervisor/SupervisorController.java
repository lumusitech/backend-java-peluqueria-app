package presentacion.controlador.supervisor;

import presentacion.vista.supervisor.SupervisorVista;

public class SupervisorController {
	private static SupervisorController INSTANCE;
	private SupervisorVista supervisorVista;
	public static SupervisorController getInstance() {
		if(INSTANCE == null) INSTANCE = new SupervisorController();
		return INSTANCE;
	}
	
	private SupervisorController() {
		this.supervisorVista = SupervisorVista.getInstance();
	}
	
	public void mostrarSupervisorVista() {
		this.supervisorVista.mostrar();
	}
}
