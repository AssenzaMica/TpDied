package died;

import java.time.LocalDate;

public class Estacion {
	
	private Integer idEstacion;
	private String nombre;
	private String horarioApertura, horarioCierre;
	private EstadoEstacion estado;
	

	public Estacion(Integer id, String nombre, String horario_cierre, String horario_apertura, String estado) {
		// TODO Auto-generated constructor stub
		this.idEstacion= id;
		this.nombre = nombre;
		this.horarioApertura = horario_apertura;
		this.horarioCierre = horario_cierre;
		this.estado = estado.equals(EstadoEstacion.OPERATIVA.toString())?EstadoEstacion.OPERATIVA:EstadoEstacion.ENMANTENIMIENTO;
	}

	public Integer getIdEstacion() {
		return idEstacion;
	}

	public void setIdEstacion(Integer idEstacion) {
		this.idEstacion = idEstacion;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHorarioApertura() {
		return horarioApertura;
	}
	public void setHorarioApertura(String horarioApertura) {
		this.horarioApertura = horarioApertura;
	}
	public String getHorarioCierre() {
		return horarioCierre;
	}
	public void setHorarioCierre(String horarioCierre) {
		this.horarioCierre = horarioCierre;
	}
	public EstadoEstacion getEstado() {
		return estado;
	}
	public void setEstado(EstadoEstacion estado) {
		this.estado = estado;
	}
	
	
	public void cambiarEstado() {
		
		if(estado == EstadoEstacion.ENMANTENIMIENTO) estado = EstadoEstacion.OPERATIVA;
		else estado = EstadoEstacion.ENMANTENIMIENTO;
		
		
	}
	
	
	public String toString() {
		return this.nombre;
	}

	public boolean equals(Object o) {
		return this.idEstacion.equals(((Estacion)o).getIdEstacion());
	}

}
