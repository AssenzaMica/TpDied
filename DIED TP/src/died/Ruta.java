package died;

public class Ruta {
	
	Integer idRuta;
	LineaDeTransporte linea;
	Estacion origen,destino;
	Integer distanciaKm;
	Integer duracionViajeMin;
	Integer capacidadMaximaPasajeros;
	EstadoRuta estado;
	Double costo;
	
	public Ruta(Integer idRuta, LineaDeTransporte linea, Estacion origen, Estacion destino, Integer distanciaKm,
			Integer duracionViajeMin, Integer capacidadMaximaPasajeros, EstadoRuta estado, Double costo) {
		super();
		this.idRuta = idRuta;
		this.linea = linea;
		this.origen = origen;
		this.destino = destino;
		this.distanciaKm = distanciaKm;
		this.duracionViajeMin = duracionViajeMin;
		this.capacidadMaximaPasajeros = capacidadMaximaPasajeros;
		this.estado = estado;
		this.costo = costo;
	}

	public Integer getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}

	public LineaDeTransporte getLinea() {
		return linea;
	}

	public void setLinea(LineaDeTransporte linea) {
		this.linea = linea;
	}

	public Estacion getOrigen() {
		return origen;
	}

	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}

	public Estacion getDestino() {
		return destino;
	}

	public void setDestino(Estacion destino) {
		this.destino = destino;
	}

	public Integer getDistanciaKm() {
		return distanciaKm;
	}

	public void setDistanciaKm(Integer distanciaKm) {
		this.distanciaKm = distanciaKm;
	}

	public Integer getDuracionViajeMin() {
		return duracionViajeMin;
	}

	public void setDuracionViajeMin(Integer duracionViajeMin) {
		this.duracionViajeMin = duracionViajeMin;
	}

	public Integer getCapacidadMaximaPasajeros() {
		return capacidadMaximaPasajeros;
	}

	public void setCapacidadMaximaPasajeros(Integer capacidadMaximaPasajeros) {
		this.capacidadMaximaPasajeros = capacidadMaximaPasajeros;
	}

	public EstadoRuta getEstado() {
		return estado;
	}

	public void setEstado(EstadoRuta estado) {
		this.estado = estado;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	
	
	
	
}
