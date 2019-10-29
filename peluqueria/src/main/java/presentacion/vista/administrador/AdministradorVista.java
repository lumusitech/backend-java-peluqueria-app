package presentacion.vista.administrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdministradorVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AdministradorVista INSTANCE;
	
	public static AdministradorVista getInstance() {
		if(INSTANCE == null) INSTANCE = new AdministradorVista();
		return INSTANCE;
	}
	private AdministradorVista() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Administrador");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ocultar();
	}
	
	public void mostrar() {
		this.setVisible(true);
	}
	
	public void ocultar() {
		this.setVisible(false);
	}
}
