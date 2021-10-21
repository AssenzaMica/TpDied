package died;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Boleto {
	
	private Integer idBoleto;
	private String nombre;
	private String email;
	private LocalDate fechaVenta;
	private Estacion origen, destino;
	private Camino recorrido;
	private Double costo;
	
	public Boleto(String nombre, String email, LocalDate fechaVenta, Estacion origen,
			Estacion destino, Camino recorrido, Double costo) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.fechaVenta = fechaVenta;
		this.origen = origen;
		this.destino = destino;
		this.recorrido = recorrido;
		this.costo = costo;
	}

	public Integer getIdBoleto() {
		return idBoleto;
	}

	public void setIdBoleto(Integer idBoleto) {
		this.idBoleto = idBoleto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
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

	public Camino getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(Camino recorrido) {
		this.recorrido = recorrido;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	
	
	
	
	

}
