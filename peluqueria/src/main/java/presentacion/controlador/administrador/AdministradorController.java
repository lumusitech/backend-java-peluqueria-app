package presentacion.controlador.administrador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.ProfesionalDTO;
import modelo.Peluqueria;
import presentacion.vista.administrador.AdministradorVista;

public class AdministradorController {
	private Peluqueria peluqueria;
	private static AdministradorController INSTANCE;
	private AdministradorVista administradorVista;
	private AddProfesionalController addProfController;
	private List<ProfesionalDTO> profesionalesEnTabla;

	public static AdministradorController getInstance(Peluqueria peluqueria) {
		if (INSTANCE == null)
			INSTANCE = new AdministradorController(peluqueria);
		return INSTANCE;
	}

	private AdministradorController(Peluqueria pel) {
		peluqueria = pel;
		administradorVista = AdministradorVista.getInstance();
		addProfController = AddProfesionalController.getInstance(peluqueria);
		actualizarTablaProfesionales();
		acciones();
	}

	private void acciones() {
		administradorVista.getBtnAddProfesional().addActionListener(e -> activateAddProfesionalVista(e));
	}

	private void activateAddProfesionalVista(ActionEvent e) {
		addProfController.mostrarAddProfesionalVista();
	}

	public void actualizarTablaProfesionales() {
		profesionalesEnTabla = peluqueria.obtenerProfesionales();
		administradorVista.llenarTabla(profesionalesEnTabla);
	}

	public void mostrarAdministradorVista() {
		this.administradorVista.mostrar();
	}
}
