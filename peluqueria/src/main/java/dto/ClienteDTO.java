package dto;

import java.time.LocalDate;

public class ClienteDTO {

	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String dni;
	private EstadoCliente estado;
	private LocalDate ultimaVisita;

	public ClienteDTO(int id, String nombre, String apellido, String email, String telefono, String dni,
			EstadoCliente estado, LocalDate ultimaVisita) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.dni = dni;
		this.estado = estado;
		this.ultimaVisita = ultimaVisita;
	}

	public ClienteDTO(String nombre, String apellido, String email, String telefono, String dni, EstadoCliente estado) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.dni = dni;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public EstadoCliente getEstado() {
		return estado;
	}

	public void setEstado(EstadoCliente estado) {
		this.estado = estado;
	}

	public LocalDate getUltimaVisita() {
		return ultimaVisita;
	}

	public void setUltimaVisita(LocalDate ultimaVisita) {
		this.ultimaVisita = ultimaVisita;
	}
}
