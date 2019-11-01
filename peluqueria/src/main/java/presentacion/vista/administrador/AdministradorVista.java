package presentacion.vista.administrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Cursor;

import dto.EstadoProfesional;
import dto.ProfesionalDTO;

import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AdministradorVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel subPanelInferior;
	private JPanel subPanelDerecho;
	private JLabel lblFiltros;
	private JPanel subPanelCentral;
	private JPanel panelSuperior_tabla;
	private JPanel subPanelIzquierdo;
	private JLabel lblLogo;
	private JPanel subPanelSuperior;
	private JLabel lblFechaActual;
	private JLabel lblUsuario;
	private JLabel lblSucursal;
	private static AdministradorVista INSTANCE;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panelInferior_tabla;
	private JPanel panelCentral_tabla;
	private JTable tablaProfesionales;
	private DefaultTableModel modelInformacion;
	private String[] nombreColumnasTabla;
	private JScrollPane spTablaInformacion;
	private JTableHeader tableHeader;
	private JButton btnAddProfesional;
	private JButton btnEditProfesional;
	private JButton btnRemoveProfesional;
	private JLabel lblProfesionales;

	public static AdministradorVista getInstance() {
		if (INSTANCE == null)
			INSTANCE = new AdministradorVista();
		return INSTANCE;
	}

	private AdministradorVista() {
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
		setTitle("Administrador");
		controlDeCierre();
	}

	private void setSubPanelInferior() {
		subPanelInferior = new JPanel();
		subPanelInferior.setBackground(Color.DARK_GRAY);
		panelPrincipal.add(subPanelInferior, BorderLayout.SOUTH);
		subPanelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));

		setPropiedadesDelBotonCrearTurno();
		setPropiedadesDelBotonEditarTurno();
		setPropiedadesDelBotonCancelarTurno();
	}

	private void setPropiedadesDelBotonCrearTurno() {

		// se crean los iconos que se van a usar
		ImageIcon iconoCrearTurno = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/agregarTurno.png"));
		ImageIcon iconoCrearTurno_rollover = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/agregarTurno_rollover.png"));
		ImageIcon iiconoCrearTurno_pressed = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/agregarTurno_pressed.png"));

		btnAddProfesional = new JButton(iconoCrearTurno);
		btnAddProfesional.setFocusable(false);
		btnAddProfesional.setBorderPainted(false);
		btnAddProfesional.setRolloverIcon(iconoCrearTurno_rollover);
		btnAddProfesional.setPressedIcon(iiconoCrearTurno_pressed);
		btnAddProfesional.setOpaque(false);// se hace transparente todo el boton default JButton
		btnAddProfesional.setContentAreaFilled(false);
		btnAddProfesional.setBounds(10, 20, 32, 32);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = btnAddProfesional.getWidth();
		int alto = btnAddProfesional.getHeight();
		iconoCrearTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoCrearTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoCrearTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Crear</font> un Turno</h3>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "<font size=3><p><b>Puedes crear un turno completando los datos solicitados</b></p></font>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "</body></html>";
		btnAddProfesional.setToolTipText(html);
		subPanelInferior.add(btnAddProfesional);
	}

	private void setPropiedadesDelBotonEditarTurno() {

		// se crean los iconos que se van a usar
		ImageIcon iconoEditarTurno = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/editarTurno.png"));
		ImageIcon iconoEditarTurno_rollover = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/editarTurno_rollover.png"));
		ImageIcon iiconoEditarTurno_pressed = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/editarTurno_pressed.png"));

		btnEditProfesional = new JButton(iconoEditarTurno);
		btnEditProfesional.setFocusable(false);
		btnEditProfesional.setRolloverIcon(iconoEditarTurno_rollover);
		btnEditProfesional.setPressedIcon(iiconoEditarTurno_pressed);
		btnEditProfesional.setOpaque(false);// se hace transparente todo el boton default JButton
		btnEditProfesional.setContentAreaFilled(false);// cuando se presiona no muestra el fondo default del JButton
		btnEditProfesional.setBorderPainted(false);// borra el borde default del JButton
		btnEditProfesional.setBounds(10, 20, 32, 32);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = btnEditProfesional.getWidth();
		int alto = btnEditProfesional.getHeight();
		iconoEditarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoEditarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoEditarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Editar</font> un Turno</h3>"
				+ "<font size=3 color=red><p>====================================================</p></font>"
				+ "<font size=3><p><b>Puedes editar un seleccionado de la tabla</b></p></font>"
				+ "<font size=3 color=red><p>====================================================</p></font>"
				+ "</body></html>";
		btnEditProfesional.setToolTipText(html);
		subPanelInferior.add(btnEditProfesional);
	}

	private void setPropiedadesDelBotonCancelarTurno() {

		// se crean los iconos que se van a usar
		ImageIcon iconoCancelarTurno = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/cancelarTurno.png"));
		ImageIcon iconoCancelarTurno_rollover = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/cancelarTurno_rollover.png"));
		ImageIcon iiconoCancelarTurno_pressed = new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/cancelarTurno_pressed.png"));

		btnRemoveProfesional = new JButton(iconoCancelarTurno);
		btnRemoveProfesional.setFocusable(false);
		btnRemoveProfesional.setRolloverIcon(iconoCancelarTurno_rollover);
		btnRemoveProfesional.setPressedIcon(iiconoCancelarTurno_pressed);
		btnRemoveProfesional.setOpaque(false);// se hace transparente todo el boton default JButton
		btnRemoveProfesional.setContentAreaFilled(false);// cuando se presiona no muestra el fondo default del JButton
		btnRemoveProfesional.setBorderPainted(false);// borra el borde default del JButton
		btnRemoveProfesional.setBounds(10, 20, 32, 32);

		// setea los tamanios de imagenes a partir de lo que ponga en setbounds
		int ancho = btnRemoveProfesional.getWidth();
		int alto = btnRemoveProfesional.getHeight();
		iconoCancelarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoCancelarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		iconoCancelarTurno.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		String html = "<html>" + "<body>" + "<h3>Click para <font color=red>Cancelar</font> un Turno</h3>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "<font size=3><p><b>Puedes cancelar un turno seleccionado de la tabla</b></p></font>"
				+ "<font size=3 color=red><p>=================================================</p></font>"
				+ "</body></html>";
		btnRemoveProfesional.setToolTipText(html);
		subPanelInferior.add(btnRemoveProfesional);
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

		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		subPanelDerecho.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		lblLogo = new JLabel("");
		lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_3.add(lblLogo, BorderLayout.SOUTH);
		lblLogo.setAlignmentY(Component.TOP_ALIGNMENT);
		lblLogo.setIcon(new ImageIcon(
				AdministradorVista.class.getResource("/presentacion/vista/img/logo-peluqueria2_blanca_chica.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 18));

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

		lblProfesionales = new JLabel("Profesionales");
		lblProfesionales.setFont(new Font("Arial Black", Font.BOLD, 21));
		panelSuperior_tabla.add(lblProfesionales);

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

		nombreColumnasTabla = new String[] { "Nombre", "Apellido", "DNI", "Email", "Telefono", "Sucursal", "Estado" };
		modelInformacion = new DefaultTableModel(null, nombreColumnasTabla);
		tablaProfesionales = new JTable(modelInformacion);
		tableHeader = tablaProfesionales.getTableHeader();
		tableHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		tableHeader.setBackground(new Color(255, 255, 255));
		tableHeader.setForeground(Color.black);

		tablaProfesionales.setBackground(new Color(64, 64, 64));
		tablaProfesionales.setForeground(Color.white);
		tablaProfesionales.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaProfesionales.setRowHeight(30);
		tablaProfesionales.setShowGrid(false);

		// tablaTurnos.setDefaultRenderer(Object.class, new RenderDeTablaCustom());

		spTablaInformacion.setViewportView(tablaProfesionales);
	}

	private void setSubPanelIzquierdo() {
		subPanelIzquierdo = new JPanel();
		subPanelIzquierdo.setPreferredSize(new Dimension(100, 10));
		subPanelIzquierdo.setBackground(Color.DARK_GRAY);
		panelPrincipal.add(subPanelIzquierdo, BorderLayout.WEST);
		subPanelIzquierdo.setSize(new Dimension(300, 50));
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

	public JButton getBtnAddProfesional() {
		return btnAddProfesional;
	}

	public void setBtnAddProfesional(JButton btnAddProfesional) {
		this.btnAddProfesional = btnAddProfesional;
	}

	public JButton getBtnEditProfesional() {
		return btnEditProfesional;
	}

	public void setBtnEditProfesional(JButton btnEditProfesional) {
		this.btnEditProfesional = btnEditProfesional;
	}

	public JButton getBtnRemoveProfesional() {
		return btnRemoveProfesional;
	}

	public void setBtnRemoveProfesional(JButton btnRemoveProfesional) {
		this.btnRemoveProfesional = btnRemoveProfesional;
	}

	public JPanel getSubPanelDerecho() {
		return subPanelDerecho;
	}

	public void setSubPanelDerecho(JPanel subPanelDerecho) {
		this.subPanelDerecho = subPanelDerecho;
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

	public JPanel getPanel_3() {
		return panel_3;
	}

	public void setPanel_3(JPanel panel_3) {
		this.panel_3 = panel_3;
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
		return tablaProfesionales;
	}

	public void setTablaTurnos(JTable tablaTurnos) {
		this.tablaProfesionales = tablaTurnos;
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

	public void llenarTabla(List<ProfesionalDTO> profesionales) {
		this.getModelInformacion().setRowCount(0); // Para vaciar la tabla
		this.getModelInformacion().setColumnCount(0);
		this.getModelInformacion().setColumnIdentifiers(this.getNombreColumnasTabla());

		for (int i = 0; i < profesionales.size(); i++) {
			// { "Fecha", "Inicio", "fin", "Cliente", "Precio", "Puntaje", "Pagado",
			// "Estado"};
			ProfesionalDTO t = profesionales.get(i);
			String nombre = t.getNombre().toString();
			String apellido = t.getApellido();
			String dni = t.getDni();
			String email = t.getEmail();
			String telefono = t.getTelefono();
			String sucursal = t.getSucursal().getNombre();
			EstadoProfesional estado = t.getEstado();
			Object[] fila = { nombre, apellido, dni, email, telefono, sucursal, estado };
			this.getModelInformacion().addRow(fila);
		}
	}
}
