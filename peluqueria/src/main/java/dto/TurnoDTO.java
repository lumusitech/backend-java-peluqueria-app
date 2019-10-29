package dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class TurnoDTO {
	private int id;
	private LocalDate fecha;
	private Time hora_inicio;
	private float precio;
	private float montoPagado;
	private int puntos;
	private ClienteDTO cliente;
	private List<DetalleTurnoDTO> detalles;
	private SucursalDTO sucursal;
	private EstadoTurno estado_turno;
	private PromocionDTO promocion;

	public TurnoDTO(int id, LocalDate fecha, Time hora_inicio, float precio, float montoPagado, int puntos,
			ClienteDTO cliente, List<DetalleTurnoDTO> detalles, SucursalDTO sucursal, EstadoTurno estado_turno,
			PromocionDTO promocion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora_inicio = hora_inicio;
		this.precio = precio;
		this.montoPagado = montoPagado;
		this.puntos = puntos;
		this.cliente = cliente;
		this.detalles = detalles;
		this.sucursal = sucursal;
		this.estado_turno = estado_turno;
		this.promocion = promocion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}

	public EstadoTurno getEstado_turno() {
		return estado_turno;
	}

	public void setEstado_turno(EstadoTurno estado_turno) {
		this.estado_turno = estado_turno;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(float montoPagado) {
		this.montoPagado = montoPagado;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public List<DetalleTurnoDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleTurnoDTO> detalles) {
		this.detalles = detalles;
	}

	public PromocionDTO getPromocion() {
		return promocion;
	}

	public void setPromocion(PromocionDTO promocion) {
		this.promocion = promocion;
	}

	@Override
	public String toString() {
		return "TurnoDTO [id=" + id + ", fecha=" + fecha + ", hora_inicio=" + hora_inicio + ", precio=" + precio
				+ ", montoPagado=" + montoPagado + ", puntos=" + puntos + ", cliente=" + cliente + ", detalles="
				+ detalles + ", sucursal=" + sucursal + ", estado_turno=" + estado_turno + ", promocion=" + promocion
				+ "]";
	}
}