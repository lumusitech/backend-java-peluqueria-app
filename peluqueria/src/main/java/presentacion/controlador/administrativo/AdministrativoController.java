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
		this.turnos = this.peluqueria.obtenerTurnos();
		this.administrativoVista = AdministrativoVista.getInstance();
	}

	public void mostrarAdministrativoVista() {
		setLblFechaActual(getFechaActual("dd/MM/yyyy"));
		setLblUsuarioActual();
		setLblSucursalActual();
		refrescarTabla(filtrarPorFecha(this.turnos, getFechaActual("yyyy-MM-dd")));
		escucharComponentes();
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

	private LocalDate formatearFecha(String fechaRecibida) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return LocalDate.parse(fechaRecibida, formateador);
		
	}

	private void setLblUsuarioActual() {
		this.administrativoVista.getLblUsuario().setText("Usuario: " + usuario.getUsuario());
	}

	private void setLblSucursalActual() {
		this.administrativoVista.getLblSucursal().setText("Sucursal: " + sucursal.getNombre());
	}

	private void refrescarTabla(List<TurnoDTO> turnos) {
		this.administrativoVista.llenarTabla(turnos);
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
		escucharBotonCancelar();// listo
		escucharBotonFiltro();// en proceso
		escucharBotonLimpiarFiltro();// listo
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

	private void escucharFiltros() {
		this.fechaEnFiltro = escucharFiltroFecha();
		this.horaEnFiltro = escucharFiltroHora();
		this.promocionesCheck = escucharFiltroPromocion();
		this.demoradosCheck = escucharFiltroDemorados();
		this.pagosPendientesCheck = escucharFiltroPagosPendientes();

		if (this.fechaEnFiltro.equals("") && this.horaEnFiltro.equals("")
				&& !(promocionesCheck && demoradosCheck && pagosPendientesCheck))
			refrescarTabla(filtrarPorFecha(this.turnos, getFechaActual("yyyy-MM-dd")));// con los filtros vacios muestra
																						// todos los turnos del dia
		else
			refrescarTabla(filtrar(this.turnos));// Si uno a varios filtros estan activos, se manda a filtrar

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

	private boolean escucharFiltroPromocion() {
		return this.administrativoVista.getBoxPromocion().isSelected();
	}

	private boolean escucharFiltroDemorados() {
		return this.administrativoVista.getBoxDemorado().isSelected();
	}

	private boolean escucharFiltroPagosPendientes() {
		return this.administrativoVista.getBoxPagoPendiente().isSelected();
	}

	private List<TurnoDTO> filtrar(List<TurnoDTO> turnosCompletos) {
		List<TurnoDTO> turnosFiltrados = new ArrayList<TurnoDTO>();

		for (TurnoDTO t : turnosCompletos) {
			if (!this.fechaEnFiltro.equals("")) {
				if (t.getFecha().equals(formatearFecha(fechaEnFiltro))) {
					turnosFiltrados.add(t);
				}
			}else {
				turnosFiltrados.add(t);
			}
			if (!this.horaEnFiltro.equals("") && !t.getHora_inicio().toString().substring(0, 5).equals(horaEnFiltro)) {
				
				turnosFiltrados.remove(t);
			}
		}

		return turnosFiltrados;
	}

	private List<TurnoDTO> filtrarPorFecha(List<TurnoDTO> lista, String fecha) {
		List<TurnoDTO> ret = new ArrayList<TurnoDTO>();
		if (lista != null && !fecha.equals("")) {
			for (TurnoDTO t : lista) {
				if (t.getFecha().toString().equals(fecha)) {
					ret.add(t);
				}
			}
		}

		return ret;
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
		refrescarTabla(filtrarPorFecha(this.turnos, getFechaActual("yyyy-MM-dd")));
	}
}
