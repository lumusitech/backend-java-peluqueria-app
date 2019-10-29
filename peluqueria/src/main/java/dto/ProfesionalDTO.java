package dto;

import java.util.ArrayList;

public class ProfesionalDTO {
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String dni;
	private SucursalDTO sucursal;
	private ArrayList<ServicioDTO> habilidades;
	private EstadoProfesional estado;

	public ProfesionalDTO(int id, String nombre, String apellido, String email, String telefono, String dni,
			SucursalDTO sucursal, ArrayList<ServicioDTO> habilidades, EstadoProfesional estado) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.dni = dni;
		this.sucursal = sucursal;
		this.habilidades = habilidades;
		this.estado = estado;
	}

	public ProfesionalDTO(String nombre, String apellido, String email, String telefono, String dni,
			SucursalDTO sucursal, ArrayList<ServicioDTO> habilidades, EstadoProfesional estado) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.dni = dni;
		this.sucursal = sucursal;
		this.habilidades = habilidades;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}

	public ArrayList<ServicioDTO> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<ServicioDTO> habilidades) {
		this.habilidades = habilidades;
	}

	public EstadoProfesional getEstado() {
		return estado;
	}

	public void setEstado(EstadoProfesional estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ProfesionalDTO [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", telefono=" + telefono + ", dni=" + dni + ", sucursal=" + sucursal + ", habilidades=" + habilidades
				+ ", estado=" + estado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((habilidades == null) ? 0 : habilidades.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfesionalDTO other = (ProfesionalDTO) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estado != other.estado)
			return false;
		if (habilidades == null) {
			if (other.habilidades != null)
				return false;
		} else if (!habilidades.equals(other.habilidades))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
}