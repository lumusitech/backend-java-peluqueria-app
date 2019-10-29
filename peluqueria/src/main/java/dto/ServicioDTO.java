package dto;

public class ServicioDTO {
	private int id;
	private String nombre;
	private float precio;
	private int duracion;
	private int puntos;

	public ServicioDTO(int id, String nombre, float precio, int duracion, int puntos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.duracion = duracion;
		this.puntos = puntos;
	}

	public ServicioDTO(int id, String nombre, float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "ServicioDTO [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", duracion=" + duracion
				+ ", puntos=" + puntos + "]";
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duracion;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + Float.floatToIntBits(precio);
		result = prime * result + puntos;
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
		ServicioDTO other = (ServicioDTO) obj;
		if (duracion != other.duracion)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Float.floatToIntBits(precio) != Float.floatToIntBits(other.precio))
			return false;
		if (puntos != other.puntos)
			return false;
		return true;
	}
}