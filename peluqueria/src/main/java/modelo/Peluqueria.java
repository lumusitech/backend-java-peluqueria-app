package modelo;

import java.util.List;

import dto.ClienteDTO;
import dto.ProfesionalDTO;
import dto.PromocionDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;
import dto.TurnoDTO;
import dto.UsuarioDTO;
import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ProfesionalDAO;
import persistencia.dao.interfaz.PromocionDAO;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.SucursalDAO;
import persistencia.dao.interfaz.TurnoDAO;
import persistencia.dao.interfaz.UsuarioDAO;

public class Peluqueria {
	private ProfesionalDAO profesional;
	private ClienteDAO cliente;
	private TurnoDAO turno;
	private ServicioDAO servicio;
	private SucursalDAO sucursal;
	private UsuarioDAO usuario;
	private PromocionDAO promocion;

	public Peluqueria(DAOAbstractFactory metodo_persistencia) {
		this.turno = metodo_persistencia.createTurnoDAO();
		this.profesional = metodo_persistencia.createProfesionalDAO();
		this.cliente = metodo_persistencia.createClienteDAO();
		this.servicio = metodo_persistencia.createServicioDAO();
		this.sucursal = metodo_persistencia.createSucursalDAO();
		this.usuario = metodo_persistencia.createUsuarioDAO();
		this.promocion = metodo_persistencia.createPromocionDAO();

	}

	///////////////////////////////////////////////////////////////////

	public void agregarTurno(TurnoDTO nuevoTurno) {
		this.turno.insert(nuevoTurno);
	}

	public void borrarTurno(TurnoDTO turno_a_eliminar) {
		this.turno.delete(turno_a_eliminar);
	}

	public boolean actualizarTurno(TurnoDTO turno_a_actualizar) {
		return this.turno.update(turno_a_actualizar);
	}

	public List<TurnoDTO> obtenerTurnos() {
		List<TurnoDTO> lista = null;
		try {
			lista = this.turno.readAll();
		} catch (Exception e) {
			throw new RuntimeException("Error al buscar turnos en la clase Peluqueria");
		}
		return lista;
	}

	public List<TurnoDTO> obtenerTurnos(int id_sucursal) {
		List<TurnoDTO> lista = null;
		try {
			lista = this.turno.readAll(id_sucursal);
		} catch (Exception e) {
			throw new RuntimeException("Error al buscar turnos de la sucursal dada en la clase Peluqueria");
		}
		return lista;
	}

	public TurnoDTO getTurnoDesdeID(int id_turno) {
		return this.turno.obtenerDesdeID(id_turno);
	}

	public boolean cancelarTurno(int id_turno) {
		return this.turno.cancelarTurno(id_turno);
	}

	public List<TurnoDTO> buscarTurnos(String fecha_inicio, String fecha_fin, String cadena, int id_sucursal) {
		List<TurnoDTO> lista = null;
		try {
			lista = this.turno.find(fecha_inicio, fecha_fin, cadena, id_sucursal);
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los turnos en la clase Peluqueria");
		}
		return lista;
	}

	public List<TurnoDTO> buscarTurnos(String fecha_turno, int id_profesional, int id_sucursal) {
		List<TurnoDTO> lista = null;
		try {
			lista = this.turno.find(fecha_turno, id_profesional, id_sucursal);
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los turnos en la clase Peluqueria");
		}
		return lista;
	}

	///////////////////////////////////////////////////////////////////

	public void agregarProfesional(ProfesionalDTO nuevoProfesional) {
		this.profesional.insert(nuevoProfesional);
	}

	public void borrarProfesional(ProfesionalDTO profesional_a_eliminar) {
		this.profesional.delete(profesional_a_eliminar);
	}

	public void actualizarProfesional(ProfesionalDTO profesional_a_actualizar) {
		this.profesional.update(profesional_a_actualizar);
	}

	public List<ProfesionalDTO> obtenerProfesionales() {
		List<ProfesionalDTO> lista = null;
		try {
			lista = this.profesional.readAll();
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los profesionales en la clase Peluqueria");
		}
		return lista;
	}

	public ProfesionalDTO getProfesionalDesdeID(int id_profesional_seleccionado) {
		return this.profesional.obtenerDesdeID(id_profesional_seleccionado);
	}

	public ProfesionalDTO buscarProfesional(String cadenaProfesional) {
		return this.profesional.find(cadenaProfesional);
	}

	public ProfesionalDTO buscarProfesional(String cadenaProfesional, int id_sucursal) {
		return this.profesional.find(cadenaProfesional, id_sucursal);
	}

	public ProfesionalDTO buscarProfesional(String cadenaProfesional, int id_sucursal, int id_habilidad) {
		return this.profesional.find(cadenaProfesional, id_sucursal, id_habilidad);
	}

	///////////////////////////////////////////////////////////////////

	public void agregarCliente(ClienteDTO nuevoCliente) {
		this.cliente.insert(nuevoCliente);
	}

	public void borrarCliente(ClienteDTO clienteAborrar) {
		this.cliente.delete(clienteAborrar);
	}

	public void actualizarCliente(ClienteDTO clienteAactualizar) {
		this.cliente.update(clienteAactualizar);
	}

	public List<ClienteDTO> obtenerClientes() {
		List<ClienteDTO> lista = null;
		try {
			lista = this.cliente.readAll();
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los clientes de la clase Peluquería");
		}
		return lista;
	}

	public ClienteDTO getClienteDesdeID(int id_cliente_seleccionado) {
		return this.cliente.obtenerDesdeID(id_cliente_seleccionado);
	}

	////////////////////////////////////////////////////////////////////

	public void agregarServicio(ServicioDTO nuevoServicio) {
		this.servicio.insert(nuevoServicio);
	}

	public void borrarServicio(ServicioDTO servicioAeliminar) {
		this.servicio.delete(servicioAeliminar);
	}

	public void actualizarServicio(ServicioDTO servicioAeditar) {
		this.servicio.update(servicioAeditar);
	}

	public ClienteDTO buscarCliente(String cadenaCliente) {
		return this.cliente.find(cadenaCliente);
	}

	///////////////////////////////////////////////////////////////////

	public List<ServicioDTO> obtenerServicios() {
		List<ServicioDTO> lista = null;
		try {
			lista = this.servicio.readAll();
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los servicios de la clase Peluquería");
		}
		return lista;
	}

	public ServicioDTO getServicioDesdeID(int id_servicio_seleccionado) {
		return this.servicio.obtenerDesdeID(id_servicio_seleccionado);
	}

	public ServicioDTO buscarServicio(String cadenaServicio) {
		return this.servicio.find(cadenaServicio);
	}

	///////////////////////////////////////////////////////////////////

	public void agregarSucursal(SucursalDTO servicio) {
		this.sucursal.insert(servicio);
	}

	public void borrarSucursal(SucursalDTO servicio) {
		this.sucursal.delete(servicio);
	}

	public void actualizarSucursal(SucursalDTO servicio) {
		this.sucursal.update(servicio);
	}

	public List<SucursalDTO> obtenerSucursales() {
		List<SucursalDTO> lista = null;
		try {
			lista = this.sucursal.readAll();
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener los servicios de la clase Peluquería");
		}
		return lista;
	}

	public SucursalDTO getSucursalDesdeID(int id_sucursal_seleccionado) {
		return this.sucursal.obtenerDesdeID(id_sucursal_seleccionado);
	}

	public SucursalDTO buscarSucursal(String cadenaSucursal) {
		return this.sucursal.find(cadenaSucursal);
	}

	public ServicioDTO buscarServicio(String cadenaServicio, int id_profesional) {
		return this.servicio.find(cadenaServicio, id_profesional);
	}

	/////////////////////////////////////////////////////////////////////

	public UsuarioDTO getUsuario(String usuario, String pass) {
		return this.usuario.getUser(usuario, pass);

	}

	////////////////////////////////////////////////////////////////////////

	public List<PromocionDTO> getPromociones() {
		return promocion.readall();
	}
}
