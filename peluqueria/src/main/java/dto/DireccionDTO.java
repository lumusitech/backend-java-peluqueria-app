package dto;

public class DireccionDTO {
	private int id;
	private String altura;
	private String calle;
	private String localidad;
	private String provincia;
	private String pais;

	public DireccionDTO(int id, String altura, String calle, String localidad, String provincia, String pais) {
		this.id = id;
		this.altura = altura;
		this.calle = calle;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
	}

	public DireccionDTO(String altura, String calle, String localidad, String provincia, String pais) {
		this.altura = altura;
		this.calle = calle;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}