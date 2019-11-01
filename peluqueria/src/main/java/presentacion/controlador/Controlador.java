package presentacion.controlador;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import dto.RolUsuario;
import modelo.Peluqueria;
import presentacion.controlador.administrador.AdministradorController;
import presentacion.controlador.administrativo.AdministrativoController;
import presentacion.controlador.contador.ContadorController;
import presentacion.controlador.supervisor.SupervisorController;
import presentacion.vista.loginRegistro.LoginVista;

public class Controlador {

	private LoginVista loginVista;
	private Peluqueria peluqueria;
	private AdministradorController administradorController;
	private AdministrativoController administrativoController;
	private ContadorController contadorController;
	private SupervisorController supervisorController;
	private dto.UsuarioDTO usuario;

	public Controlador(LoginVista vista, Peluqueria peluqueria) {
		this.peluqueria = peluqueria;
		this.loginVista = vista;
		this.usuario = null;
		this.administradorController = AdministradorController.getInstance(peluqueria);
		this.contadorController = ContadorController.getInstance();
		this.supervisorController = SupervisorController.getInstance();

		this.loginVista.getBtnLogin().addActionListener(l -> login(l));

	}

	private void login(ActionEvent l) {

		// Se recuperan los datos ingresados en el login
		String campoUsuario = this.loginVista.getJtxt_usuario().getText();
		char[] campoPass = this.loginVista.getPasswordField().getPassword();
		String pass = new String(campoPass);

		// Se intenta obtener un usuario con los datos ingresados
		usuario = this.peluqueria.getUsuario(campoUsuario, pass);
		
		if(usuario != null) {
			if( usuario.getUsuario().equals(campoUsuario) && usuario.getPassword().equals(pass) ) {
				
				this.loginVista.ocultar();
				
				//Se inicializan las vistas con el modelo y el usuario
				this.administrativoController = AdministrativoController.getInstance(peluqueria, usuario);
				
				String tipo = usuario.getRol().toString();
				switch(tipo) {
				case "ADMINISTRADOR": this.administradorController.mostrarAdministradorVista(); break;
				case "ADMINISTRATIVO" : this.administrativoController.mostrarAdministrativoVista(); break;
				case "CONTADOR" : this.contadorController.mostrarContadorVista(); break;
				case "SUPERVISOR" : this.supervisorController.mostrarSupervisorVista(); break;
				}
			}else {
				JOptionPane.showMessageDialog(loginVista, "Usuario o password incorrecto", "ERROR", 0, null);
			}
		}else {
			JOptionPane.showMessageDialog(loginVista, "Usuario o password incorrecto", "ERROR", 0, null);
		}
	}

	public void inicializar() {
		this.loginVista.mostrar();
	}

	public Peluqueria getPeluqueria() {
		return peluqueria;
	}
}
