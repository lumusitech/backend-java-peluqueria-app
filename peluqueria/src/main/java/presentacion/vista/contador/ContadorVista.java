package presentacion.vista.contador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ContadorVista extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private static ContadorVista INSTANCE;
	
	public static ContadorVista getInstance() {
		if(INSTANCE == null) INSTANCE = new ContadorVista();
		return INSTANCE;
	}
	
	private ContadorVista() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Contador");
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
		
		ocultar();
	}
	
	public void mostrar() {
		this.setVisible(true);
	}
	
	public void ocultar() {
		this.setVisible(false);
	}
}
