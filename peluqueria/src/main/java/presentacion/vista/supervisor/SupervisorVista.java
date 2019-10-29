package presentacion.vista.supervisor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SupervisorVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private static SupervisorVista INSTANCE;
	
	public static SupervisorVista getInstance() {
		if(INSTANCE == null) INSTANCE = new SupervisorVista();
		return INSTANCE;
	}
	
	private SupervisorVista() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Supervisor");
		
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
