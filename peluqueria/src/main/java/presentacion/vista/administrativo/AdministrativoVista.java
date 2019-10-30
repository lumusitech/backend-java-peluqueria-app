package presentacion.vista.administrativo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Time;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Cursor;
import dto.TurnoDTO;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class AdministrativoVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel subPanelInferior;
	private JPanel subPanelDerecho;
	private JCheckBox boxPromocion;
	private JCheckBox boxPagoPendiente;
	private JCheckBox boxDemorado;
	private JLabel lblFiltros;
	private JPanel subPanelCentral;
	private JPanel panelSuperior_tabla;
	private JPanel subPanelIzquierdo;
	private JLabel lblLogo;
	private JPanel subPanelSuperior;
	private JLabel lblFechaActual;
	private JLabel lblUsuario;
	private JLabel lblSucursal;
	private static AdministrativoVista INSTANCE;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panelInferior_tabla;
	private JPanel panelCentral_tabla;
	private JTable tablaTurnos;
	private DefaultTableModel modelInformacion;
	private String[] nombreColumnasTabla;
	private JScrollPane spTablaInformacion;
	private JTableHeader tableHeader;
	private JButton botonCrearTurno;
	private JButton botonEditarTurno;
	private JButton botonCancelarTurno;
	private JButton botonPerspectivaTurno;
	private JButton botonPagarTurno;
	private JDateChooser dateChooser;
	private JComboBox<Object> comboBoxHoras;
	private JButton botonFiltrarTurnos;
	private JPanel subPanelNorth_Panel_3;
	private JButton botonLimpiarFiltros;

	public static AdministrativoVista getInstance() {
		if (INSTANCE == null)
			INSTANCE = new AdministrativoVista();
		return INSTANCE;
	}

	private AdministrativoVista() {

		setPropiedades();

		setPanelPrincipal();
		setSubPanelSuperior();
		setSubPanelIzquierdo();
		setSubPanelCentral();
		setSubPanelDerecho();
		setSubPanelInferior();

		ocultar();
	}

	private void setPropiedades() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1073, 816);
		setMinimumSize(new Dimension(920, 650));
		getContentPane().setLayout(new BorderLayout(0, 0));
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Administrativo");
		controlDeCierre();
	}

	private void setSubPanelInferior() {
		subPanelInferior = new JPanel();
		subPanelInferior.setBackground(Color.DARK_GRAY);
		panelPrincipal.add(subPanelInferior, BorderLayout.SOUTH);
		subPanelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));

		setPropiedadesDelBotonPagarTurno();
		setPropiedadesDelBotonCrearTurno();
		setPropiedadesDelBotonEditarTurno();
		setPropiedadesDelBotonCancelarTurno();
	}

	private void setPropiedadesDelBotonPagarTurno() {

		// se crean los iconos que se van a usar
		ImageIcon iconoPagarTurno = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/pagarTurno.png"));
		ImageIcon iconoPagarTurno_rollover = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/pagarTurno_rollover.png"));
		ImageIcon iconoPagarTurno_pressed = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/pagarTurno_pressed.png"));

		botonPagarTurno = new JButton(iconoPagarTurno);
		botonPagarTurno.setFocusable(false);
		botonPagarTurno.setBorderPainted(false);
		botonPagarTurno.setRolloverIcon(iconoPagarTurno_rollover);
		botonPagarTurno.setPressedIcon(iconoPagarTurno_pressed);
		botonPagarTurno.setOpaque(false);// se hace transparente todo el boton default JButton
		botonPagarTurno.setContentAreaFilled(false);
		botonPagarTurno.setBounds(10, 20, 32, 32);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = botonPagarTurno.getWidth();
		int alto = botonPagarTurno.getHeight();
		iconoPagarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoPagarTurno_rollover.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoPagarTurno_pressed.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Abonar</font> un Turno</h3>"
				+ "<font size=3 color=red><p>=======================================================</p></font>"
				+ "<font size=3><p><b>Puedes pagar de forma parcial o completa el turno seleccionado</b></p></font>"
				+ "<font size=3 color=red><p>=======================================================</p></font>"
				+ "</body></html>";
		botonPagarTurno.setToolTipText(html);
		subPanelInferior.add(botonPagarTurno);
	}

	private void setPropiedadesDelBotonCrearTurno() {

		// se crean los iconos que se van a usar
		ImageIcon iconoCrearTurno = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/agregarTurno.png"));
		ImageIcon iconoCrearTurno_rollover = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/agregarTurno_rollover.png"));
		ImageIcon iiconoCrearTurno_pressed = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/agregarTurno_pressed.png"));

		botonCrearTurno = new JButton(iconoCrearTurno);
		botonCrearTurno.setFocusable(false);
		botonCrearTurno.setBorderPainted(false);
		botonCrearTurno.setRolloverIcon(iconoCrearTurno_rollover);
		botonCrearTurno.setPressedIcon(iiconoCrearTurno_pressed);
		botonCrearTurno.setOpaque(false);// se hace transparente todo el boton default JButton
		botonCrearTurno.setContentAreaFilled(false);
		botonCrearTurno.setBounds(10, 20, 32, 32);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = botonCrearTurno.getWidth();
		int alto = botonCrearTurno.getHeight();
		iconoCrearTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoCrearTurno_rollover.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iiconoCrearTurno_pressed.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Crear</font> un Turno</h3>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "<font size=3><p><b>Puedes crear un turno completando los datos solicitados</b></p></font>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "</body></html>";
		botonCrearTurno.setToolTipText(html);
		subPanelInferior.add(botonCrearTurno);
	}

	private void setPropiedadesDelBotonEditarTurno() {

		// se crean los iconos que se van a usar
		ImageIcon iconoEditarTurno = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/editarTurno.png"));
		ImageIcon iconoEditarTurno_rollover = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/editarTurno_rollover.png"));
		ImageIcon iiconoEditarTurno_pressed = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/editarTurno_pressed.png"));

		botonEditarTurno = new JButton(iconoEditarTurno);
		botonEditarTurno.setFocusable(false);
		botonEditarTurno.setRolloverIcon(iconoEditarTurno_rollover);
		botonEditarTurno.setPressedIcon(iiconoEditarTurno_pressed);
		botonEditarTurno.setOpaque(false);// se hace transparente todo el boton default JButton
		botonEditarTurno.setContentAreaFilled(false);// cuando se presiona no muestra el fondo default del JButton
		botonEditarTurno.setBorderPainted(false);// borra el borde default del JButton
		botonEditarTurno.setBounds(10, 20, 32, 32);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = botonEditarTurno.getWidth();
		int alto = botonEditarTurno.getHeight();
		iconoEditarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoEditarTurno_rollover.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iiconoEditarTurno_pressed.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Editar</font> un Turno</h3>"
				+ "<font size=3 color=red><p>====================================================</p></font>"
				+ "<font size=3><p><b>Puedes editar un seleccionado de la tabla</b></p></font>"
				+ "<font size=3 color=red><p>====================================================</p></font>"
				+ "</body></html>";
		botonEditarTurno.setToolTipText(html);
		subPanelInferior.add(botonEditarTurno);
	}

	private void setPropiedadesDelBotonCancelarTurno() {

		// se crean los iconos que se van a usar
		ImageIcon iconoCancelarTurno = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/cancelarTurno.png"));
		ImageIcon iconoCancelarTurno_rollover = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/cancelarTurno_rollover.png"));
		ImageIcon iiconoCancelarTurno_pressed = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/cancelarTurno_pressed.png"));

		botonCancelarTurno = new JButton(iconoCancelarTurno);
		botonCancelarTurno.setFocusable(false);
		botonCancelarTurno.setRolloverIcon(iconoCancelarTurno_rollover);
		botonCancelarTurno.setPressedIcon(iiconoCancelarTurno_pressed);
		botonCancelarTurno.setOpaque(false);// se hace transparente todo el boton default JButton
		botonCancelarTurno.setContentAreaFilled(false);// cuando se presiona no muestra el fondo default del JButton
		botonCancelarTurno.setBorderPainted(false);// borra el borde default del JButton
		botonCancelarTurno.setBounds(10, 20, 32, 32);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = botonCancelarTurno.getWidth();
		int alto = botonCancelarTurno.getHeight();
		iconoCancelarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoCancelarTurno_rollover.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iiconoCancelarTurno_pressed.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Cancelar</font> un Turno</h3>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "<font size=3><p><b>Puedes cancelar un turno seleccionado de la tabla</b></p></font>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "</body></html>";
		botonCancelarTurno.setToolTipText(html);
		subPanelInferior.add(botonCancelarTurno);
	}

	private void setSubPanelDerecho() {
		subPanelDerecho = new JPanel();
		subPanelDerecho.setPreferredSize(new Dimension(270, 10));
		subPanelDerecho.setBorder(null);
		subPanelDerecho.setBackground(Color.DARK_GRAY);
		panelPrincipal.add(subPanelDerecho, BorderLayout.EAST);
		subPanelDerecho.setLayout(new GridLayout(2, 0, 0, 0));

		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		subPanelDerecho.add(panel_1);
		panel_1.setLayout(null);

		lblFiltros = new JLabel("Filtrar por:");
		lblFiltros.setBounds(22, 13, 123, 23);
		panel_1.add(lblFiltros);
		lblFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltros.setForeground(Color.WHITE);
		lblFiltros.setFont(new Font("Verdana", Font.PLAIN, 20));

		boxPromocion = new JCheckBox("Turnos con promoción");
		boxPromocion.setBounds(27, 131, 223, 31);
		boxPromocion.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(boxPromocion);
		boxPromocion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		boxPromocion.setForeground(Color.WHITE);
		boxPromocion.setOpaque(false);

		boxPagoPendiente = new JCheckBox("Pagos pendientes");
		boxPagoPendiente.setBounds(27, 203, 223, 31);
		boxPagoPendiente.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(boxPagoPendiente);
		boxPagoPendiente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		boxPagoPendiente.setForeground(Color.WHITE);
		boxPagoPendiente.setOpaque(false);

		boxDemorado = new JCheckBox("Turnos demorados");
		boxDemorado.setBounds(27, 167, 223, 31);
		boxDemorado.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(boxDemorado);
		boxDemorado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		boxDemorado.setForeground(Color.WHITE);
		boxDemorado.setOpaque(false);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(86, 51, 137, 22);
		panel_1.add(dateChooser);
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateChooser.setPreferredSize(new Dimension(150, 22));

		comboBoxHoras = new JComboBox<Object>();
		comboBoxHoras.setMaximumRowCount(12);
		comboBoxHoras.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxHoras.setBounds(86, 86, 137, 24);
		comboBoxHoras.addItem("");
		comboBoxHoras.addItem("8:00");
		comboBoxHoras.addItem("8:30");
		comboBoxHoras.addItem("9:00");
		comboBoxHoras.addItem("9:30");
		comboBoxHoras.addItem("10:00");
		comboBoxHoras.addItem("10:30");
		comboBoxHoras.addItem("11:00");
		comboBoxHoras.addItem("11:30");
		comboBoxHoras.addItem("12:00");
		comboBoxHoras.addItem("12:30");
		comboBoxHoras.addItem("13:00");
		comboBoxHoras.addItem("13:30");
		comboBoxHoras.addItem("14:00");
		comboBoxHoras.addItem("14:30");
		comboBoxHoras.addItem("15:00");
		comboBoxHoras.addItem("15:30");
		comboBoxHoras.addItem("16:00");
		comboBoxHoras.addItem("16:30");
		comboBoxHoras.addItem("17:00");
		comboBoxHoras.addItem("17:30");
		comboBoxHoras.addItem("18:00");
		comboBoxHoras.addItem("18:30");
		comboBoxHoras.addItem("19:00");
		comboBoxHoras.addItem("19:30");
		comboBoxHoras.addItem("20:00");
		comboBoxHoras.addItem("20:30");
		panel_1.add(comboBoxHoras);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblFecha.setBounds(27, 49, 53, 23);
		panel_1.add(lblFecha);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(Color.WHITE);
		lblHora.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblHora.setBounds(27, 84, 47, 23);
		panel_1.add(lblHora);

		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		subPanelDerecho.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		lblLogo = new JLabel("");
		lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_3.add(lblLogo, BorderLayout.SOUTH);
		lblLogo.setAlignmentY(Component.TOP_ALIGNMENT);
		lblLogo.setIcon(new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/logo-peluqueria2_blanca_chica.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 18));

		subPanelNorth_Panel_3 = new JPanel();
		subPanelNorth_Panel_3.setOpaque(false);
		subPanelNorth_Panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_3.add(subPanelNorth_Panel_3, BorderLayout.NORTH);

		setPropiedadesDelBotonfiltrarTurnos();
		setPropiedadesDelBotonLimpiarfiltros();
	}

	private void setPropiedadesDelBotonfiltrarTurnos() {

		// se crean los iconos que se van a usar
		ImageIcon iconoFiltrarTurnos = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/filtrarTurnos.png"));
		ImageIcon iconoiconoFiltrarTurnos_rollover = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/filtrarTurnos_rollover.png"));
		ImageIcon iconoiconoFiltrarTurnos_pressed = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/filtrarTurnos_pressed.png"));

		botonFiltrarTurnos = new JButton(iconoFiltrarTurnos);
		botonFiltrarTurnos.setFocusable(false);
		botonFiltrarTurnos.setRolloverIcon(iconoiconoFiltrarTurnos_rollover);
		botonFiltrarTurnos.setPressedIcon(iconoiconoFiltrarTurnos_pressed);
		botonFiltrarTurnos.setOpaque(false);// se hace transparente todo el boton default JButton
		botonFiltrarTurnos.setContentAreaFilled(false);// cuando se presiona no muestra el fondo default del JButton
		botonFiltrarTurnos.setBorderPainted(false);// borra el borde default del JButton
		botonFiltrarTurnos.setBounds(91, 243, 71, 64);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = botonPerspectivaTurno.getWidth();
		int alto = botonPerspectivaTurno.getHeight();
		iconoFiltrarTurnos.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoiconoFiltrarTurnos_rollover.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoiconoFiltrarTurnos_pressed.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Aplicar los filtros</font> de Turnos</h3>"
				+ "<font size=3 color=red><p>========================================================</p></font>"
				+ "<font size=3><p><b>Puedes filtrar la información de la tabla estableciendo filtros</b></p></font>"
				+ "<font size=3 color=red><p>========================================================</p></font>"
				+ "</body></html>";

		botonFiltrarTurnos.setToolTipText(html);
		subPanelNorth_Panel_3.add(botonFiltrarTurnos);
	}

	private void setPropiedadesDelBotonLimpiarfiltros() {

		// se crean los iconos que se van a usar
		ImageIcon iconoLimpiarFiltros = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/limpiarFiltros.png"));
		ImageIcon iconoLimpiarFiltros_rollover = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/limpiarFiltros_rollover.png"));
		ImageIcon iconoLimpiarFiltros_pressed = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/limpiarFiltros_pressed.png"));

		botonLimpiarFiltros = new JButton(new ImageIcon(AdministrativoVista.class.getResource("/presentacion/vista/img/limpiarFiltros.png")));
		botonLimpiarFiltros.setFocusable(false);
		botonLimpiarFiltros.setRolloverIcon(iconoLimpiarFiltros_rollover);
		botonLimpiarFiltros.setPressedIcon(iconoLimpiarFiltros_pressed);
		botonLimpiarFiltros.setOpaque(false);// se hace transparente todo el boton default JButton
		botonLimpiarFiltros.setContentAreaFilled(false);// cuando se presiona no muestra el fondo default del JButton
		botonLimpiarFiltros.setBorderPainted(false);// borra el borde default del JButton
		botonLimpiarFiltros.setBounds(91, 243, 71, 64);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = botonPerspectivaTurno.getWidth();
		int alto = botonPerspectivaTurno.getHeight();
		iconoLimpiarFiltros.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoLimpiarFiltros_rollover.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoLimpiarFiltros_pressed.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Limpiar los filtros</font> de Turnos</h3>"
				+ "<font size=3 color=red><p>=======================================================</p></font>"
				+ "<font size=3><p><b>Puedes limpiar los filtros para ver todos los turnos del día</b></p></font>"
				+ "<font size=3 color=red><p>=======================================================</p></font>"
				+ "</body></html>";

		botonLimpiarFiltros.setToolTipText(html);
		subPanelNorth_Panel_3.add(botonLimpiarFiltros);
	}

	private void setSubPanelCentral() {
		subPanelCentral = new JPanel();
		subPanelCentral.setBackground(Color.WHITE);
		panelPrincipal.add(subPanelCentral, BorderLayout.CENTER);
		subPanelCentral.setLayout(new BorderLayout(0, 0));

		panelSuperior_tabla = new JPanel();
		panelSuperior_tabla.setPreferredSize(new Dimension(10, 50));
		panelSuperior_tabla.setAlignmentY(Component.TOP_ALIGNMENT);
		panelSuperior_tabla.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelSuperior_tabla.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelSuperior_tabla.setBackground(Color.GRAY);
		subPanelCentral.add(panelSuperior_tabla, BorderLayout.NORTH);
		panelSuperior_tabla.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panelInferior_tabla = new JPanel();
		panelInferior_tabla.setBackground(Color.GRAY);
		subPanelCentral.add(panelInferior_tabla, BorderLayout.SOUTH);

		panelCentral_tabla = new JPanel();
		panelCentral_tabla.setBackground(Color.WHITE);
		subPanelCentral.add(panelCentral_tabla, BorderLayout.CENTER);
		panelCentral_tabla.setLayout(new BoxLayout(panelCentral_tabla, BoxLayout.Y_AXIS));

		spTablaInformacion = new JScrollPane();
		spTablaInformacion.setBackground(Color.WHITE);
		spTablaInformacion.setPreferredSize(new Dimension(0, 0));
		spTablaInformacion.setBorder(null);
		spTablaInformacion.setViewportBorder(null);
		spTablaInformacion.getViewport().setBackground(new Color(64, 64, 64));
		spTablaInformacion.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		spTablaInformacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spTablaInformacion.setBounds(0, 0, 976, 547);
		panelCentral_tabla.add(spTablaInformacion);

		nombreColumnasTabla = new String[] { "Fecha", "Inicio", "fin", "Cliente", "Precio", "Puntaje", "Pagado",
				"Estado" };
		modelInformacion = new DefaultTableModel(null, nombreColumnasTabla);
		tablaTurnos = new JTable(modelInformacion);
		tableHeader = tablaTurnos.getTableHeader();
		tableHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		tableHeader.setBackground(new Color(255, 255, 255));
		tableHeader.setForeground(Color.black);

		tablaTurnos.setBackground(new Color(64, 64, 64));
		tablaTurnos.setForeground(Color.white);
		tablaTurnos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaTurnos.setRowHeight(30);
		tablaTurnos.setShowGrid(false);

		// tablaTurnos.setDefaultRenderer(Object.class, new RenderDeTablaCustom());

		spTablaInformacion.setViewportView(tablaTurnos);
	}

	private void setSubPanelIzquierdo() {
		subPanelIzquierdo = new JPanel();
		subPanelIzquierdo.setPreferredSize(new Dimension(100, 10));
		subPanelIzquierdo.setBackground(Color.DARK_GRAY);
		panelPrincipal.add(subPanelIzquierdo, BorderLayout.WEST);
		subPanelIzquierdo.setSize(new Dimension(300, 50));

		setPropiedadesDelBotonPerspectivaTurno();
	}

	private void setPropiedadesDelBotonPerspectivaTurno() {

		// se crean los iconos que se van a usar
		ImageIcon iconoPerspectivaTurno = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/cambiarPerspectivaTurnos.png"));
		ImageIcon iconoPerspectivaTurno_rollover = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/cambiarPerspectivaTurnos_rollover.png"));
		ImageIcon iconoPerspectivaTurno_pressed = new ImageIcon(
				AdministrativoVista.class.getResource("/presentacion/vista/img/cambiarPerspectivaTurnos_pressed.png"));

		botonPerspectivaTurno = new JButton(iconoPerspectivaTurno);
		botonPerspectivaTurno.setFocusable(false);
		botonPerspectivaTurno.setRolloverIcon(iconoPerspectivaTurno_rollover);
		botonPerspectivaTurno.setPressedIcon(iconoPerspectivaTurno_pressed);
		botonPerspectivaTurno.setOpaque(false);// se hace transparente todo el boton default JButton
		botonPerspectivaTurno.setContentAreaFilled(false);// cuando se presiona no muestra el fondo default del JButton
		botonPerspectivaTurno.setBorderPainted(false);// borra el borde default del JButton
		botonPerspectivaTurno.setBounds(10, 20, 32, 32);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = botonPerspectivaTurno.getWidth();
		int alto = botonPerspectivaTurno.getHeight();
		iconoPerspectivaTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoPerspectivaTurno_rollover.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoPerspectivaTurno_pressed.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Cambiar la vista</font> de Turnos</h3>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "<font size=3><p><b>Puedes cambiar la forma de ver los turnos en la tabla</b></p></font>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "</body></html>";
		subPanelIzquierdo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		botonPerspectivaTurno.setToolTipText(html);
		subPanelIzquierdo.add(botonPerspectivaTurno);
	}

	private void setSubPanelSuperior() {
		subPanelSuperior = new JPanel();
		subPanelSuperior.setBackground(Color.DARK_GRAY);
		panelPrincipal.add(subPanelSuperior, BorderLayout.NORTH);
		subPanelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));

		lblFechaActual = new JLabel("28/10/2019");
		lblFechaActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaActual.setForeground(Color.WHITE);
		lblFechaActual.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblFechaActual.setSize(new Dimension(300, 100));
		subPanelSuperior.add(lblFechaActual);

		lblUsuario = new JLabel("Usuario de prueba");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 18));
		subPanelSuperior.add(lblUsuario);

		lblSucursal = new JLabel("Sucursal");
		lblSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSucursal.setForeground(Color.WHITE);
		lblSucursal.setFont(new Font("Verdana", Font.PLAIN, 18));
		subPanelSuperior.add(lblSucursal);
	}

	private void setPanelPrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(null);
		panelPrincipal.setLayout(new BorderLayout());
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public JPanel getSubPanelInferior() {
		return subPanelInferior;
	}

	public void setSubPanelInferior(JPanel subPanelInferior) {
		this.subPanelInferior = subPanelInferior;
	}

	public JButton getBotonCrearTurno() {
		return botonCrearTurno;
	}

	public void setBotonCrearTurno(JButton botonCrearTurno) {
		this.botonCrearTurno = botonCrearTurno;
	}

	public JButton getBotonEditarTurno() {
		return botonEditarTurno;
	}

	public void setBotonEditarTurno(JButton botonEditarTurno) {
		this.botonEditarTurno = botonEditarTurno;
	}

	public JButton getBotonCancelarTurno() {
		return botonCancelarTurno;
	}

	public void setBotonCancelarTurno(JButton botonCancelarTurno) {
		this.botonCancelarTurno = botonCancelarTurno;
	}

	public JPanel getSubPanelDerecho() {
		return subPanelDerecho;
	}

	public void setSubPanelDerecho(JPanel subPanelDerecho) {
		this.subPanelDerecho = subPanelDerecho;
	}

	public JCheckBox getBoxPromocion() {
		return boxPromocion;
	}

	public void setBoxPromocion(JCheckBox boxPromocion) {
		this.boxPromocion = boxPromocion;
	}

	public JCheckBox getBoxPagoPendiente() {
		return boxPagoPendiente;
	}

	public void setBoxPagoPendiente(JCheckBox boxPagoPendiente) {
		this.boxPagoPendiente = boxPagoPendiente;
	}

	public JCheckBox getBoxTintura() {
		return boxDemorado;
	}

	public void setBoxTintura(JCheckBox boxTintura) {
		this.boxDemorado = boxTintura;
	}

	public JLabel getLblFiltros() {
		return lblFiltros;
	}

	public void setLblFiltros(JLabel lblFiltro) {
		this.lblFiltros = lblFiltro;
	}

	public JPanel getSubPanelCentral() {
		return subPanelCentral;
	}

	public void setSubPanelCentral(JPanel subPanelCentral) {
		this.subPanelCentral = subPanelCentral;
	}

	public JPanel getPanelSuperior_tabla() {
		return panelSuperior_tabla;
	}

	public void setPanelSuperior_tabla(JPanel panelSuperior_tabla) {
		this.panelSuperior_tabla = panelSuperior_tabla;
	}

	public JPanel getSubPanelIzquierdo() {
		return subPanelIzquierdo;
	}

	public void setSubPanelIzquierdo(JPanel subPanelIzquierdo) {
		this.subPanelIzquierdo = subPanelIzquierdo;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}

	public void setLblLogo(JLabel lblLogo) {
		this.lblLogo = lblLogo;
	}

	public JPanel getSubPanelSuperior() {
		return subPanelSuperior;
	}

	public void setSubPanelSuperior(JPanel subPanelSuperior) {
		this.subPanelSuperior = subPanelSuperior;
	}

	public JLabel getLblFechaActual() {
		return lblFechaActual;
	}

	public void setLblFechaActual(JLabel lblFechaActual) {
		this.lblFechaActual = lblFechaActual;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public void setLblSucursal(JLabel lblSucursal) {
		this.lblSucursal = lblSucursal;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JCheckBox getBoxDemorado() {
		return boxDemorado;
	}

	public void setBoxDemorado(JCheckBox boxDemorado) {
		this.boxDemorado = boxDemorado;
	}

	public JComboBox<Object> getComboBoxHoras() {
		return comboBoxHoras;
	}

	public void setComboBoxHoras(JComboBox<Object> comboBoxHoras) {
		this.comboBoxHoras = comboBoxHoras;
	}

	public JButton getBotonFiltrarTurnos() {
		return botonFiltrarTurnos;
	}

	public void setBotonFiltrarTurnos(JButton botonFiltrarTurnos) {
		this.botonFiltrarTurnos = botonFiltrarTurnos;
	}

	public JPanel getPanel_3() {
		return panel_3;
	}

	public void setPanel_3(JPanel panel_3) {
		this.panel_3 = panel_3;
	}

	public JButton getBotonPerspectivaTurno() {
		return botonPerspectivaTurno;
	}

	public void setBotonPerspectivaTurno(JButton botonPerspectivaTurno) {
		this.botonPerspectivaTurno = botonPerspectivaTurno;
	}

	public JButton getBotonPagarTurno() {
		return botonPagarTurno;
	}

	public void setBotonPagarTurno(JButton botonPagarTurno) {
		this.botonPagarTurno = botonPagarTurno;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	public JPanel getPanelInferior_tabla() {
		return panelInferior_tabla;
	}

	public void setPanelInferior_tabla(JPanel panelInferior_tabla) {
		this.panelInferior_tabla = panelInferior_tabla;
	}

	public JPanel getPanelCentral_tabla() {
		return panelCentral_tabla;
	}

	public void setPanelCentral_tabla(JPanel panelCentral_tabla) {
		this.panelCentral_tabla = panelCentral_tabla;
	}

	public JTable getTablaTurnos() {
		return tablaTurnos;
	}

	public void setTablaTurnos(JTable tablaTurnos) {
		this.tablaTurnos = tablaTurnos;
	}

	public DefaultTableModel getModelInformacion() {
		return modelInformacion;
	}

	public void setModelInformacion(DefaultTableModel modelInformacion) {
		this.modelInformacion = modelInformacion;
	}

	public String[] getNombreColumnasTabla() {
		return nombreColumnasTabla;
	}

	public void setNombreColumnasTabla(String[] nombreColumnasTabla) {
		this.nombreColumnasTabla = nombreColumnasTabla;
	}

	public JScrollPane getSpTablaInformacion() {
		return spTablaInformacion;
	}

	public void setSpTablaInformacion(JScrollPane spTablaInformacion) {
		this.spTablaInformacion = spTablaInformacion;
	}

	public JTableHeader getTableHeader() {
		return tableHeader;
	}

	public void setTableHeader(JTableHeader tableHeader) {
		this.tableHeader = tableHeader;
	}

	public void mostrar() {
		this.setVisible(true);
	}

	public void ocultar() {
		this.setVisible(false);
	}

	private void controlDeCierre() {
		// Manejo del cierre de ventana
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				// guardar();
				close();
			}
		});
	}

	private void close() {
		if (JOptionPane.showConfirmDialog(this, "Está seguro de cerrar la aplicación", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void llenarTabla(List<TurnoDTO> turnos) {
		this.getModelInformacion().setRowCount(0); // Para vaciar la tabla
		this.getModelInformacion().setColumnCount(0);
		this.getModelInformacion().setColumnIdentifiers(this.getNombreColumnasTabla());

		for (int i = 0; i < turnos.size(); i++) {
			// { "Fecha", "Inicio", "fin", "Cliente", "Precio", "Puntaje", "Pagado",
			// "Estado"};
			TurnoDTO t = turnos.get(i);
			String fecha = t.getFecha().toString();
			Time inicio = t.getDetalles().get(i).getHoraInicio();
			Time fin = t.getDetalles().get(i).getHoraFin();
			String cliente = t.getCliente().getNombre() + " " + t.getCliente().getApellido();
			Float precio = t.getPrecio();
			int puntaje = t.getPuntos();
			Float pagado = t.getMontoPagado();
			String estado = t.getEstado_turno().toString();

			Object[] fila = { fecha, inicio, fin, cliente, precio, puntaje, pagado, estado };
			this.getModelInformacion().addRow(fila);
		}
	}
}