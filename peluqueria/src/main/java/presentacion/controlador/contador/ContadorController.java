package presentacion.controlador.contador;

import presentacion.vista.contador.ContadorVista;

public class ContadorController {
	private static ContadorController INSTANCE;
	private ContadorVista contadorVista;
	
	public static ContadorController getInstance() {
		if(INSTANCE == null) INSTANCE = new ContadorController();
		return INSTANCE;
	}
	private ContadorController() {
		this.contadorVista = ContadorVista.getInstance();
	}
	
	public void mostrarContadorVista() {
		this.contadorVista.mostrar();
	}
}
