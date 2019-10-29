package presentacion.controlador.administrativo;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import dto.TurnoDTO;
import dto.UsuarioDTO;
import modelo.Peluqueria;
import presentacion.vista.administrativo.AdministrativoVista;

public class AdministrativoController {
	private static AdministrativoController INSTANCE;
	private AdministrativoVista administrativoVista;
	private Peluqueria peluqueria;
	private UsuarioDTO usuario;
	
	public static AdministrativoController getInstance(Peluqueria peluqueria, UsuarioDTO usuario) {
		if(INSTANCE == null) INSTANCE = new AdministrativoController(peluqueria, usuario);
		return INSTANCE;
	}
	
	private AdministrativoController(Peluqueria peluqueria, UsuarioDTO usuario) {
		this.peluqueria = peluqueria;
		this.usuario = usuario;
		this.administrativoVista = AdministrativoVista.getInstance();
		
		escucharBotones();
	}
	
	private void escucharBotones() {
		escucharBotonCancelar();
	}

	private void escucharBotonCancelar() {
		this.administrativoVista.getBotonCancelarTurno().addActionListener(c -> cancelarTurno(c));
		
	}

	private void cancelarTurno(ActionEvent c) {
		if(this.administrativoVista.getTablaTurnos().getSelectedRow() != -1) {
			int option = JOptionPane.showConfirmDialog(this.administrativoVista, "¿Está seguro que desea cancelar este turno?");
			if (option == 0) this.peluqueria.cancelarTurno(this.administrativoVista.getTablaTurnos().getSelectedRow()+1);
		}else {
			JOptionPane.showMessageDialog(this.administrativoVista, "No se ha seleccionado ningún turno de la tabla");
		}
		
	}

	public void mostrarAdministrativoVista() {
		List<TurnoDTO> turnos = this.peluqueria.obtenerTurnos();
		setLblFechaActual();
		setLblUsuarioActual();
		setLblSucursalActual();
		this.administrativoVista.llenarTabla(turnos);
		this.administrativoVista.mostrar();
	}

	private void setLblFechaActual() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fecha = localDate.format(formatter);
		this.administrativoVista.getLblFechaActual().setText("Fecha: "+fecha);
	}
	
	private void setLblUsuarioActual() {
		this.administrativoVista.getLblUsuario().setText("Usuario: "+usuario.getUsuario());
	}
	
	private void setLblSucursalActual() {
		this.administrativoVista.getLblSucursal().setText("Sucursal: "+usuario.getSucursal().getNombre());
	}
}
