package menu;

import java.util.ArrayList;
import java.util.List;

import died.Boleto;
import died.Estacion;
import died.EstadoRuta;
import died.Ruta;
import died.TareaDeMantenimiento;

public class GestorAlgoritmos {

	public static Double calcularCostoBoleto(List<Ruta> camino) {
		
		double costoBoleto = 0.0;
		
		for(Ruta r: camino) {
			costoBoleto = costoBoleto + r.getCosto();
		}
		
		return costoBoleto;
	}

	public static int calcularDistanciaBoleto(List<Ruta> camino) {
		
		int distancia = 0;
		
		for(Ruta r: camino) {
			distancia = distancia + r.getDistanciaKm();
		}
		
		return distancia;
	}
	
	public static int calcularTiempoBoleto(List<Ruta> camino) {
		
		int tiempo = 0;
		
		for(Ruta r: camino) {
			tiempo = tiempo + r.getDuracionViajeMin();
		}
		
		return tiempo;
	}

	
	
	public static List<Ruta> caminoMasRapido(Estacion origen, Estacion destino) {
		
		List<List<Ruta>> todos = GestorAlgoritmos.todosLosCaminos(origen, destino);
		
		List<Ruta> masRapido = GestorAlgoritmos.getMasRapido(todos);
		
		//System.out.println(todos.size());
		//System.out.println(masRapido.size());
	
		return masRapido;
	}

	private static List<Ruta> getMasRapido(List<List<Ruta>> todos) {		
		// menor duracion entre todos los de origen/destino
		
		int menorDuracion=1000000;
		List<Ruta> caminoMasCorto = null;
		
		for(List<Ruta> camino : todos) {
			
			int duracion=0;
			
			for(Ruta r : camino) {
				duracion = duracion + r.getDuracionViajeMin();
			}
			
			if(duracion<menorDuracion) {
				menorDuracion=duracion;
				caminoMasCorto = camino;
			}
		}
		
		
		return caminoMasCorto;
	}

	public static List<Ruta> caminoMenorDistancia(Estacion origen, Estacion destino) {
		
		List<List<Ruta>> todos = GestorAlgoritmos.todosLosCaminos(origen, destino);
		
		List<Ruta> menorDistancia = GestorAlgoritmos.getMenorDistancia(todos);
		
		//System.out.println(todos.size());
		//System.out.println(masRapido.size());
	
		return menorDistancia;	}

	private static List<Ruta> getMenorDistancia(List<List<Ruta>> todos) {
		
		// menor distancia entre todos los de origen/destino
		
		int menorDistancia=1000000;
		List<Ruta> caminoMenorDistancia = null;
		
		for(List<Ruta> camino : todos) {
			
			int distancia=0;
			
			for(Ruta r : camino) {
				distancia = distancia + r.getDistanciaKm();
			}
			
			if(distancia<menorDistancia) {
				menorDistancia=distancia;
				caminoMenorDistancia = camino;
			}
		}
		
		
		return caminoMenorDistancia;
	}

	public static List<Ruta> caminoMasBarato(Estacion origen, Estacion destino) {
		
		List<List<Ruta>> todos = GestorAlgoritmos.todosLosCaminos(origen, destino);
		
		List<Ruta> masBarato = GestorAlgoritmos.getMasBarato(todos);
		
		//System.out.println(todos.size());
		//System.out.println(masRapido.size());
	
		return masBarato;
	}
	
	private static List<Ruta> getMasBarato(List<List<Ruta>> todos) {
		
		// menor costo entre todos los de origen/destino
		
		double menorCosto=1000000;
		List<Ruta> caminoMasBarato = null;
		
		for(List<Ruta> camino : todos) {
			
			double costo=0;
			
			for(Ruta r : camino) {
				costo = costo + r.getCosto();
			}
			
			if(costo<menorCosto) {
				menorCosto=costo;
				caminoMasBarato = camino;
			}
		}
		
		
		return caminoMasBarato;
	}

	
	
	
	private static List<List<Ruta>> todosLosCaminos(Estacion origen, Estacion destino) {
		
		List<List<Ruta>> caminos = new ArrayList<List<Ruta>>();
		todosLosCaminosAux(origen, destino, caminos, new ArrayList<Ruta>(), new ArrayList<Estacion>());
		
		return caminos;
	}

	private static void todosLosCaminosAux(Estacion origen, Estacion destino, List<List<Ruta>> caminos, List<Ruta> camino, List<Estacion> usadas) {
		
		if(origen.equals(destino)) {
			caminos.add(camino);
			return;
		}
		
		List<Ruta> Rutas = GestorJDBC.buscarRuta(origen.getIdEstacion(), EstadoRuta.ACTIVA);
	
		for(Ruta t : Rutas) {
			List<Ruta> copiaCamino = new ArrayList<Ruta>(camino);
			List<Estacion> copiaUsadas = new ArrayList<Estacion>(usadas);
			if(!copiaCamino.contains(t) && !copiaUsadas.contains(t.getDestino())) {
				copiaCamino.add(t);
				copiaUsadas.add(t.getDestino());
				todosLosCaminosAux(t.getDestino(),destino,caminos,copiaCamino,copiaUsadas);
			}
		}
	}

	
	public static int maxFlow(Estacion origen, Estacion destino) {
		List<List<Ruta>> recorridos = todosLosCaminos(origen, destino);
		int flujoMaximo = 0;
		for(List<Ruta> camino : recorridos) {
			int capacidadDelCamino = 100000;
			
			for(Ruta ruta : camino) {
				if(ruta.getCapacidadMaximaPasajeros() <= 0) {
					capacidadDelCamino = 0;
					break;
				}
				if(ruta.getCapacidadMaximaPasajeros() < capacidadDelCamino) capacidadDelCamino = ruta.getCapacidadMaximaPasajeros();
			}

			for(Ruta rutaDelCamino : camino) {
				rutaDelCamino.setCapacidadMaximaPasajeros(rutaDelCamino.getCapacidadMaximaPasajeros() - capacidadDelCamino);
				for(List<Ruta> revisionCamino : recorridos) {
					for(Ruta rutaRevision : revisionCamino) {
						if(rutaRevision.getOrigen().getNombre().equals(rutaDelCamino.getOrigen().getNombre())) {
							if(rutaRevision.getDestino().getNombre().equals(rutaDelCamino.getDestino().getNombre())) {
								rutaRevision.setCapacidadMaximaPasajeros(rutaDelCamino.getCapacidadMaximaPasajeros());
							}
						}
					}
				}
				
			}
						
			flujoMaximo += capacidadDelCamino;
			
		}
		
		return flujoMaximo;
		
		/*
		recorridosDFS = new HashMap<>();
		grafoDFS = copyGrafo(estacionesIngreso);
		int retornoDFS = dfs(origen, destino, estaciones, 100000);
		int flujoMaximo = 0;
		while(retornoDFS != -1) {
			recorridosDFS.clear();
			recorridosDFS = new HashMap<>();
			flujoMaximo += flujoEncontradoEnDFS;
			flujoEncontradoEnDFS = 1000000;
			retornoDFS = dfs(origen, destino, estaciones, 100000);
		}
		return flujoMaximo;
		*/
	}
	
	
	
	public static String imprimirRecorrido(Boleto boleto) {

		List<Ruta> recorrido = boleto.getRecorrido().getRecorrido();
		
		String recorridoDelBoleto = " - Itinerario: " + boleto.getOrigen().toString();
		
		for(Ruta e: recorrido) {
			recorridoDelBoleto = recorridoDelBoleto + " > " + e.getDestino().toString();
		}
		
		
		return recorridoDelBoleto;
	}

	public static Object[][] crearMatriz(List<TareaDeMantenimiento> tareas) {
		
		int cantfilas = tareas.size();
		Object[][] matriz= new Object[cantfilas][5];
		Object[] fila = {"id","inicio","fin","obs","estacion"};
		
		int index= 0;
		
		for(TareaDeMantenimiento tarea : tareas) {
			
			System.out.println(tarea.getIdMantenimiento());
			System.out.println(tarea.getFechaInicio());
			System.out.println(tarea.getFechaFin());
			System.out.println(tarea.getObservaciones());
			System.out.println(tarea.getEstacion().getIdEstacion());
			
			
			matriz[index][0] = tarea.getIdMantenimiento();
			matriz[index][1] = tarea.getFechaInicio();
			matriz[index][2] = tarea.getFechaFin();
			matriz[index][3] = tarea.getObservaciones();
			matriz[index][4] = tarea.getEstacion().getIdEstacion();
			
			
			index++;
		}		
		
		return matriz;
		
	}
	

	
}
