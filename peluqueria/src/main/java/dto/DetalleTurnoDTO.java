package dto;

import java.sql.Time;

public class DetalleTurnoDTO {
	private int id;
	private Time horaInicio;
	private Time horaFin;
	private ProfesionalDTO profesional;
	private ServicioDTO servicio;

	public DetalleTurnoDTO(int id, Time horaInicio, Time horaFin, ProfesionalDTO profesional, ServicioDTO servicio) {
		super();
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.profesional = profesional;
		this.servicio = servicio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public ProfesionalDTO getProfesional() {
		return profesional;
	}

	public void setProfesional(ProfesionalDTO profesional) {
		this.profesional = profesional;
	}

	public ServicioDTO getServicio() {
		return servicio;
	}

	public void setServicio(ServicioDTO servicio) {
		this.servicio = servicio;
	}

	@Override
	public String toString() {
		return "DetalleTurnoDTO [id=" + id + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", profesional="
				+ profesional + ", servicio=" + servicio + "]";
	}
}