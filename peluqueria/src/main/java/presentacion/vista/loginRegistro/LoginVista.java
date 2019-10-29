package presentacion.vista.loginRegistro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class LoginVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private static LoginVista INSTANCE;
	private JPanel panelPrincipal;
	private JTextField jtxt_usuario;
	private JPasswordField passwordField;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JButton btnLogin;
	
	public static LoginVista getInstance() {
		if(INSTANCE == null) INSTANCE = new LoginVista();
		return INSTANCE;
	}

	private LoginVista() {
		setPropiedades();
		setPanelPrincipal();
		setCampoUsuario();
		setCampoPass();
		setBotonLogin();
		ocultar();
	}

	private void setPropiedades() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 299);
		setTitle("Login");
		setResizable(false);
	}
	
	private void setPanelPrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(null);
		setContentPane(panelPrincipal);
	}
	
	private void setCampoUsuario() {
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(52, 44, 64, 16);
		panelPrincipal.add(lblUsuario);
		
		jtxt_usuario = new JTextField();
		jtxt_usuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxt_usuario.setBounds(167, 36, 243, 37);
		panelPrincipal.add(jtxt_usuario);
	}
	
	private void setCampoPass() {
		lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContrasea.setBounds(52, 128, 88, 16);
		panelPrincipal.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 118, 243, 37);
		panelPrincipal.add(passwordField);
	}
	
	private void setBotonLogin() {
		btnLogin = new JButton("Login");
		btnLogin.setBounds(313, 196, 97, 25);
		panelPrincipal.add(btnLogin);
	}
	
	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public JTextField getJtxt_usuario() {
		return jtxt_usuario;
	}

	public void setJtxt_usuario(JTextField jtxt_usuario) {
		this.jtxt_usuario = jtxt_usuario;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JLabel getLblContrasea() {
		return lblContrasea;
	}

	public void setLblContrasea(JLabel lblContrasea) {
		this.lblContrasea = lblContrasea;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public void mostrar() {
		this.setVisible(true);
	}
	
	public void ocultar() {
		this.setVisible(false);
	}
}
