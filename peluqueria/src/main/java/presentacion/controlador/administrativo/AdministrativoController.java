package presentacion.controlador.administrativo;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import dto.EstadoPromocion;
import dto.EstadoTurno;
import dto.SucursalDTO;
import dto.TurnoDTO;
import dto.UsuarioDTO;
import modelo.Peluqueria;
import presentacion.vista.administrativo.AdministrativoVista;

public class AdministrativoController {
	private static AdministrativoController INSTANCE;
	private AdministrativoVista administrativoVista;
	private Peluqueria peluqueria;
	private UsuarioDTO usuario;
	private SucursalDTO sucursal;
	private List<TurnoDTO> turnos;
	private String fechaEnFiltro;
	private String horaEnFiltro;
	private boolean promocionesCheck;
	private boolean demoradosCheck;
	private boolean pagosPendientesCheck;

	public static AdministrativoController getInstance(Peluqueria peluqueria, UsuarioDTO usuario) {
		if (INSTANCE == null)
			INSTANCE = new AdministrativoController(peluqueria, usuario);
		return INSTANCE;
	}

	private AdministrativoController(Peluqueria peluqueria, UsuarioDTO usuario) {
		this.peluqueria = peluqueria;
		this.usuario = usuario;
		this.sucursal = usuario.getSucursal();
		this.administrativoVista = AdministrativoVista.getInstance();
		this.turnos = this.peluqueria.obtenerTurnos();

		escucharComponentes();
	}

	private void escucharComponentes() {
		escucharBotones();
		escucharFiltros();
	}

	private void escucharBotones() {
		escucharBotonPerspectiva();
		escucharBotonPagar();
		escucharBotonCrear();
		escucharBotonEditar();
		escucharBotonCancelar();
		escucharBotonFiltro();
		escucharBotonLimpiarFiltro();
	}

	private void escucharBotonPerspectiva() {
		this.administrativoVista.getBotonPerspectivaTurno().addActionListener(p -> cambiarPerspectiva(p));
	}

	private void cambiarPerspectiva(ActionEvent p) {
		System.out.println("No implementado");
	}

	private void escucharBotonPagar() {
		this.administrativoVista.getBotonPagarTurno().addActionListener(p -> pagarTurno(p));
	}

	private void pagarTurno(ActionEvent p) {
		if (this.administrativoVista.getTablaTurnos().getSelectedRow() != -1) {
//			TurnoDTO turno = this.peluqueria
//					.getTurnoDesdeID(this.administrativoVista.getTablaTurnos().getSelectedRow() + 1);
			// seguir con la logica para pagar un turno
			System.out.println("No implementado");
		} else {
			JOptionPane.showMessageDialog(this.administrativoVista, "No se ha seleccionado ningún turno de la tabla");
		}
	}

	private void escucharBotonCrear() {
		this.administrativoVista.getBotonCrearTurno().addActionListener(cr -> crearTurno(cr));
	}

	private void crearTurno(ActionEvent cr) {
		System.out.println("No implementado");
	}

	private void escucharBotonEditar() {
		this.administrativoVista.getBotonEditarTurno().addActionListener(ed -> editarTurno(ed));
	}

	private void editarTurno(ActionEvent ed) {
		System.out.println("No implementado");
	}

	private void escucharBotonCancelar() {
		this.administrativoVista.getBotonCancelarTurno().addActionListener(c -> cancelarTurno(c));

	}

	private void cancelarTurno(ActionEvent c) {
		if (this.administrativoVista.getTablaTurnos().getSelectedRow() != -1) {
			int option = JOptionPane.showConfirmDialog(this.administrativoVista,
					"¿Está seguro que desea cancelar este turno?");
			if (option == 0)
				this.peluqueria.cancelarTurno(this.administrativoVista.getTablaTurnos().getSelectedRow() + 1);
		} else {
			JOptionPane.showMessageDialog(this.administrativoVista, "No se ha seleccionado ningún turno de la tabla");
		}
	}

	private void escucharBotonFiltro() {
		this.administrativoVista.getBotonFiltrarTurnos().addActionListener(f -> filtrarTurnos(f));
	}

	private void filtrarTurnos(ActionEvent f) {
		escucharFiltros();
	}

	private void escucharBotonLimpiarFiltro() {
		this.administrativoVista.getBotonLimpiarFiltros().addActionListener(l -> limpiarFiltros(l));
	}

	private void limpiarFiltros(ActionEvent l) {
		this.administrativoVista.getDateChooser().setDate(null);
		this.administrativoVista.getComboBoxHoras().setSelectedIndex(0);
		this.administrativoVista.getBoxPromocion().setSelected(false);
		this.administrativoVista.getBoxDemorado().setSelected(false);
		this.administrativoVista.getBoxPagoPendiente().setSelected(false);
		refrescarTabla(this.turnos);
	}

	private void escucharFiltros() {
		this.fechaEnFiltro = escucharFiltroFecha();
		this.horaEnFiltro = escucharFiltroHora();
		this.promocionesCheck = escucharFiltroPromocion();
		this.demoradosCheck = escucharFiltroDemorados();
		this.pagosPendientesCheck = escucharFiltroPagosPendientes();

		refrescarTabla(filtrar(this.turnos));
	}

	private List<TurnoDTO> filtrar(List<TurnoDTO> turnos) {
		List<TurnoDTO> turnosFiltrados = new ArrayList<TurnoDTO>();

		turnosFiltrados = filtrarPorFecha(this.turnos, getFechaActual("yyyy/MM/dd"));

		if (promocionesCheck && demoradosCheck && pagosPendientesCheck) {
			for (TurnoDTO t : turnosFiltrados) {
				if (!t.getPromocion().getNombre().equals("sin promocion")
						&& t.getPromocion().getEstado().equals(EstadoPromocion.ACTIVO)
						&& t.getEstado_turno().equals(EstadoTurno.DEMORADO) && t.getMontoPagado() < t.getPrecio()) {
					if (!this.fechaEnFiltro.equals("") && t.getFecha().toString().equals(fechaEnFiltro)) {
						if (!this.horaEnFiltro.equals("") && t.getHora_inicio().toString().equals(horaEnFiltro)) {
							turnosFiltrados.add(t);
						}
					}
				}
			}
		} else {
			turnosFiltrados = this.turnos;
		}

		if (!this.fechaEnFiltro.equals("")) {

		}

		return turnosFiltrados;
	}

	private List<TurnoDTO> filtrarPorFecha(List<TurnoDTO> lista, String fecha) {
		List<TurnoDTO> ret = new ArrayList<TurnoDTO>();
		if (lista != null) {
			for (TurnoDTO t : lista) {
				if (t.getFecha().toString().equals(fecha)) {
					ret.add(t);
				}
			}
		}

		return ret;
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
		if (date != null) {
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

	public void mostrarAdministrativoVista() {
		refrescarTabla(this.turnos);
		setLblFechaActual(getFechaActual("dd/MM/yyyy"));
		setLblUsuarioActual();
		setLblSucursalActual();
		this.administrativoVista.mostrar();
	}

	private void setLblFechaActual(String fechaActual) {
		this.administrativoVista.getLblFechaActual().setText("Fecha: " + fechaActual);
	}

	private String getFechaActual(String formato) {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
		String fecha = localDate.format(formatter);
		return fecha;
	}

	private void refrescarTabla(List<TurnoDTO> turnos) {
		this.administrativoVista.llenarTabla(turnos);
	}

	private void setLblUsuarioActual() {
		this.administrativoVista.getLblUsuario().setText("Usuario: " + usuario.getUsuario());
	}

	private void setLblSucursalActual() {
		this.administrativoVista.getLblSucursal().setText("Sucursal: " + usuario.getSucursal().getNombre());
	}
}
