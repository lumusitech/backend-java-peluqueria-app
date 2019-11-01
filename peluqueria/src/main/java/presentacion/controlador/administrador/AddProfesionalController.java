package presentacion.controlador.administrador;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.EstadoProfesional;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;
import modelo.Peluqueria;
import presentacion.vista.administrador.AddProfesionalVista;

public class AddProfesionalController {
	private static AddProfesionalController INSTANCE;
	private Peluqueria peluqueria;
	private AddProfesionalVista addProfVista;
	private List<SucursalDTO> sucursales;

	public static AddProfesionalController getInstance(Peluqueria peluqueria) {
		if (INSTANCE == null)
			INSTANCE = new AddProfesionalController(peluqueria);
		return INSTANCE;
	}

	private AddProfesionalController(Peluqueria pel) {
		addProfVista = AddProfesionalVista.getInstance();
		peluqueria = pel;
//		actualizarComboSucursal();
		acciones();
	}

	private void acciones() {
		addProfVista.getBtnAgregarProfesional().addActionListener(e -> crearProfesional(e));
	}

	public void mostrarAddProfesionalVista() {
		addProfVista.mostrar();
	}

	public void actualizarComboSucursal() {
		sucursales = peluqueria.obtenerSucursales();
		addProfVista.actualizarComboSucursal(sucursales);
	}

	public void crearProfesional(ActionEvent e) {
		String nombre = addProfVista.getTxtNombre().getText();
		String apellido = addProfVista.getTxtApellido().getText();
		String email = addProfVista.getTxtEmail().getText();
		String telefono = addProfVista.getTxtTelefono().getText();
		String dni = addProfVista.getTxtDni().getText();
		EstadoProfesional estado = (EstadoProfesional) addProfVista.getComboEstado().getSelectedItem();
		SucursalDTO sucursal = new SucursalDTO(2, "nombre", "idioma", "calle", 0);
		ArrayList<ServicioDTO> habilidades = new ArrayList<ServicioDTO>();

		ProfesionalDTO nuevo = new ProfesionalDTO(nombre, apellido, email, telefono, dni, sucursal, habilidades,
				estado);
		int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
		if (resp == 0) {
			peluqueria.agregarProfesional(nuevo);
			addProfVista.ocultar();
		}
	}
}
