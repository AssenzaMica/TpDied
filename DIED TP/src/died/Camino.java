package died;

import java.util.List;

public class Camino {

	Integer idCamino;
	List<Ruta> recorrido;
	
	public Camino() {}
	
	public Camino(List<Ruta> recorrido) {
		super();
		this.recorrido = recorrido;
	}


	public Camino(Integer idCamino, List<Ruta> recorrido) {
		super();
		this.idCamino = idCamino;
		this.recorrido = recorrido;
	}


	public Integer getIdCamino() {
		return idCamino;
	}


	public void setIdCamino(Integer idCamino) {
		this.idCamino = idCamino;
	}


	public List<Ruta> getRecorrido() {
		return recorrido;
	}


	public void setRecorrido(List<Ruta> recorrido) {
		this.recorrido = recorrido;
	}
	
	
	
}
