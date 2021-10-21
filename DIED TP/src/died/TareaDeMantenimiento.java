package died;


public class TareaDeMantenimiento {
	
	
	Integer idMantenimiento;
	String fechaInicio, fechaFin;
	String observaciones;
	Estacion estacion;
	
	public TareaDeMantenimiento(int idMantenimiento, String fechaInicio, String fechaFin, String observaciones, Estacion estacion) {
		this.idMantenimiento = idMantenimiento;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.observaciones = observaciones;
		this.estacion = estacion;
	}
	
	public TareaDeMantenimiento(String fechaInicio, String fechaFin, String observaciones, Estacion estacion) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.observaciones = observaciones;
		this.estacion = estacion;
	}

	public Integer getIdMantenimiento() {
		return idMantenimiento;
	}

	public void setIdMantenimiento(Integer idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	
}
