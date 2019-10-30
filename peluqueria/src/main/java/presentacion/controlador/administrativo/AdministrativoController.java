package presentacion.controlador.administrativo;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
	private String fechaEnFiltro;
	private String horaEnFiltro;
	private boolean promocionesCheck;
	private boolean demoradosCheck;
	private boolean pagosPendientesCheck;
	
	public static AdministrativoController getInstance(Peluqueria peluqueria, UsuarioDTO usuario) {
		if(INSTANCE == null) INSTANCE = new AdministrativoController(peluqueria, usuario);
		return INSTANCE;
	}
	
	private AdministrativoController(Peluqueria peluqueria, UsuarioDTO usuario) {
		this.peluqueria = peluqueria;
		this.usuario = usuario;
		this.administrativoVista = AdministrativoVista.getInstance();
		
		escucharComponentes();
	}
	
	private void escucharComponentes() {
		escucharBotones();
		escucharFiltros();
	}

	private void escucharBotones() {
		escucharBotonCancelar();
		escucharBotonPagar();
		escucharBotonFiltro();
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
	
	private void escucharBotonPagar() {
		this.administrativoVista.getBotonPagarTurno().addActionListener(p -> pagarTurno(p));
	}

	private void pagarTurno(ActionEvent p) {
		if(this.administrativoVista.getTablaTurnos().getSelectedRow() != -1) {
			TurnoDTO turno = this.peluqueria.getTurnoDesdeID(this.administrativoVista.getTablaTurnos().getSelectedRow());
			//seguir con la logica para pagar un turno
		}else {
			JOptionPane.showMessageDialog(this.administrativoVista, "No se ha seleccionado ningún turno de la tabla");
		}
	}
	
	private void escucharBotonFiltro() {
		this.administrativoVista.getBotonFiltrarTurnos().addActionListener(f -> filtrarTurnos(f));
	}

	
	private void filtrarTurnos(ActionEvent f) {
		escucharFiltros();
	}

	private void escucharFiltros() {
		this.fechaEnFiltro = escucharFiltroFecha();
		this.horaEnFiltro = escucharFiltroHora();
		this.promocionesCheck = escucharFiltroPromocion();
		this.demoradosCheck = escucharFiltroDemorados();
		this.pagosPendientesCheck = escucharFiltroPagosPendientes();
		
		//pruebas
		System.out.println(fechaEnFiltro);
		System.out.println(horaEnFiltro);
		if(this.promocionesCheck) System.out.println("Turnos con promociones");
		if(this.demoradosCheck) System.out.println("Turnos demorados");
		if(this.pagosPendientesCheck) System.out.println("Turnos con pagos pendientes");
	}
	
	private boolean escucharFiltroPromocion() {
		return this.administrativoVista.getBoxPromocion().isSelected();
	}
	
	private boolean escucharFiltroDemorados() {
		return this.administrativoVista.getBoxDemorado().isSelected();
	}


	private boolean escucharFiltroPagosPendientes() {
		return this.administrativoVista.getBoxPagoPendiente().isSelected();
	}

	private String escucharFiltroFecha() {
		Date date = this.administrativoVista.getDateChooser().getDate();
		String fecha = "";
		if(date != null) {
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
			fecha = formateador.format(date);
		}
		return fecha;
	}

	private String escucharFiltroHora() {
		Object itemSeleccionado = this.administrativoVista.getComboBoxHoras().getSelectedItem();
		String hora = "";
		return (itemSeleccionado != null) ? hora = itemSeleccionado.toString() : hora;
	}

	private void escucharFiltrosCheckBox() {
		
	}

	public void mostrarAdministrativoVista() {
		refrescarTabla();
		setLblFechaActual();
		setLblUsuarioActual();
		setLblSucursalActual();
		this.administrativoVista.mostrar();
	}

	private void refrescarTabla() {
		List<TurnoDTO> turnos = this.peluqueria.obtenerTurnos();
		for(TurnoDTO t : turnos) {
			//if(t.getFecha())
		}
		this.administrativoVista.llenarTabla(turnos);
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
