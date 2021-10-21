package died;

import java.util.ArrayList;

public class LineaDeTransporte {
	
	private Integer idLinea;
	private String nombre;
	private String color;
	private EstadoLinea estado;
	private ArrayList <Ruta> rutas;
	
	public LineaDeTransporte(int id, String nombre, String color, String estado) {
		// TODO Auto-generated constructor stub
		this.idLinea= id;
		this.nombre = nombre;
		this.color = color;
		this.estado = estado.equals(EstadoLinea.ACTIVA.toString())?EstadoLinea.ACTIVA:EstadoLinea.NOACTIVA;
	}
		
	
	public Integer getIdLinea() {
		return idLinea;
	}


	public void setIdLinea(Integer idLinea) {
		this.idLinea = idLinea;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public EstadoLinea getEstado() {
		return estado;
	}


	public void setEstado(EstadoLinea estado) {
		this.estado = estado;
	}


	public ArrayList<Ruta> getRutas() {
		return rutas;
	}






	public void setRutas(ArrayList<Ruta> rutas) {
		this.rutas = rutas;
	}






	public String toString() {
		return this.nombre;
	}
		
	
}
