package presentacion.vista.administrador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.EstadoProfesional;
import dto.SucursalDTO;

import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;

public class AddProfesionalVista extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtEmail;
	private JButton btnAgregarProfesional;
	private static AddProfesionalVista INSTANCE;
	private JTextField txtTelefono;
	private JComboBox<EstadoProfesional> comboEstado;
	private JComboBox<SucursalDTO> comboSucursal;

	public static AddProfesionalVista getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AddProfesionalVista();
			return new AddProfesionalVista();
		} else
			return INSTANCE;
	}

	private AddProfesionalVista() {
		super();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, -12, 460, 486);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(22, 90, 113, 14);
		panel.add(lblApellido);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 62, 113, 14);
		panel.add(lblNombre);

		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(22, 174, 113, 14);
		panel.add(lblTelfono);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(22, 146, 113, 14);
		panel.add(lblEmail);

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 59, 226, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBounds(133, 87, 226, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(133, 115, 226, 20);
		panel.add(txtDni);
		txtDni.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(133, 143, 226, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		btnAgregarProfesional = new JButton("Agregar");
		btnAgregarProfesional.setBounds(317, 452, 89, 23);
		panel.add(btnAgregarProfesional);

		JLabel lblAgregarProfesional = new JLabel("Crear Profesional");
		lblAgregarProfesional.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAgregarProfesional.setBounds(133, 11, 226, 33);
		panel.add(lblAgregarProfesional);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(22, 118, 113, 14);
		panel.add(lblDni);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(133, 171, 226, 20);
		panel.add(txtTelefono);

		comboEstado = new JComboBox<EstadoProfesional>();
		comboEstado.setBounds(199, 217, 160, 20);
		comboEstado.addItem(EstadoProfesional.ACTIVO);
		comboEstado.addItem(EstadoProfesional.INACTIVO);

		panel.add(comboEstado);

		comboSucursal = new JComboBox<SucursalDTO>();
		comboSucursal.setBounds(22, 217, 160, 20);
//		comboSucursal.addItem("Sucursal");
		panel.add(comboSucursal);

		this.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtDni() {
		return txtDni;
	}

	public void setTxtDni(JTextField txtDni) {
		this.txtDni = txtDni;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JButton getBtnAgregarProfesional() {
		return btnAgregarProfesional;
	}

	public void setBtnAgregarProfesional(JButton btnAgregarProfesional) {
		this.btnAgregarProfesional = btnAgregarProfesional;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JComboBox<EstadoProfesional> getComboEstado() {
		return comboEstado;
	}

	public void setComboEstado(JComboBox<EstadoProfesional> comboEstado) {
		this.comboEstado = comboEstado;
	}

	public JComboBox<SucursalDTO> getComboSucursal() {
		return comboSucursal;
	}

	public void setComboSucursal(JComboBox<SucursalDTO> comboSucursal) {
		this.comboSucursal = comboSucursal;
	}

	public void actualizarComboSucursal(List<SucursalDTO> sucursales) {
		comboSucursal.removeAllItems();
		for (SucursalDTO s : sucursales) {
			comboSucursal.addItem(s);
		}
	}

	public void cerrar() {
		this.txtNombre.setText(null);
		this.txtApellido.setText(null);
		this.txtDni.setText(null);
		this.txtEmail.setText(null);
		this.dispose();
	}

	public void mostrar() {
		this.setVisible(true);
	}

	public void ocultar() {
		this.setVisible(false);
	}
}
