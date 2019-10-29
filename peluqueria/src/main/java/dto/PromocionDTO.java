package dto;

import java.math.BigDecimal;
import java.util.List;

public class PromocionDTO {
	private int id;
	private String nombre;
	private EstadoPromocion estado;
	private BigDecimal precio;
	private int multiplicacion;
	List<ServicioDTO> servicios;

	public PromocionDTO(int id, String nombre, EstadoPromocion estado, BigDecimal precio, int multiplicacion,
			List<ServicioDTO> servicios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.precio = precio;
		this.multiplicacion = multiplicacion;
		this.servicios = servicios;
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

	public EstadoPromocion getEstado() {
		return estado;
	}

	public void setEstado(EstadoPromocion estado) {
		this.estado = estado;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getMultiplicacion() {
		return multiplicacion;
	}

	public void setMultiplicacion(int multiplicacion) {
		this.multiplicacion = multiplicacion;
	}

	public List<ServicioDTO> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}

	@Override
	public String toString() {
		return "PromocionDTO [id=" + id + ", nombre=" + nombre + ", estado=" + estado + ", precio=" + precio
				+ ", multiplicacion=" + multiplicacion + ", servicios=" + servicios + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionDTO other = (PromocionDTO) obj;
		if (estado != other.estado)
			return false;
		if (id != other.id)
			return false;
		if (multiplicacion != other.multiplicacion)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio != other.precio)
			return false;
		if (servicios == null) {
			if (other.servicios != null)
				return false;
		} else if (!servicios.equals(other.servicios))
			return false;
		return true;
	}
}