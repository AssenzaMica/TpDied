package menu;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import died.Boleto;
import died.Camino;
import died.Estacion;
import died.EstadoEstacion;
import died.EstadoLinea;
import died.EstadoRuta;
import died.LineaDeTransporte;
import died.Ruta;
import died.TareaDeMantenimiento;

public class GestorJDBC {
	
	private static String url = "jdbc:mysql://localhost:3306/died";
	private static String user= "root";
	private static String password= "root";
	private static Connection coneccion;
	private static PreparedStatement pstm;
	
	
	
	private static void conectarYPrepararConsulta(String consulta) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		coneccion = DriverManager.getConnection(url,user,password);		
		pstm = coneccion.prepareStatement(consulta);
	}
	
	private static void cerrarConexion(ResultSet rs) {

		if(rs!=null) try { rs.close(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(coneccion!=null) try { coneccion.close(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		coneccion=null;
		pstm=null;
	}
	
	
	
	public static void cargarEstacion(String nombre, String apertura, String cierre, EstadoEstacion estado) {
		
		ResultSet resultado = null;
		
		String consulta = "Insert into estacion (nombre, horario_cierre, horario_apertura, estado) values (?,?,?,?)";
		
		try {
			conectarYPrepararConsulta(consulta);
			
			coneccion.setAutoCommit(false);
			
			pstm.setString(1, nombre);
			pstm.setString(2, cierre);
			pstm.setString(3, apertura);
			pstm.setString(4, estado.toString());
			pstm.executeUpdate();
			coneccion.commit();
			
			coneccion.setAutoCommit(true);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		
		
	}

	public static void cargarLinea(String nombre, String color, EstadoLinea estado) {
		
		ResultSet resultado = null;
		
		String consulta = "Insert into linea (nombre, color, estado) values (?,?,?)";
		
		try {
			conectarYPrepararConsulta(consulta);
			
			coneccion.setAutoCommit(false);
			
			pstm.setString(1, nombre);
			pstm.setString(2, color);
			pstm.setString(3, estado.toString());
			pstm.executeUpdate();
			coneccion.commit();
			
			coneccion.setAutoCommit(true);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
	}

	public static void cargarTrayecto(LineaDeTransporte linea, Estacion origen, Estacion destino, int distanciaKm,
			int duracionViajeMin, int capacidadMaximaPasajeros, EstadoRuta estado, double costo) {
		
		ResultSet resultado = null;
		
		String consulta = "Insert into ruta (id_linea, id_origen, id_destino, distancia, duracion, capacidad, estado, costo) values (?,?,?,?,?,?,?,?)";
		
		try {
			conectarYPrepararConsulta(consulta);
			
			coneccion.setAutoCommit(false);
			
			pstm.setInt(1, linea.getIdLinea());
			pstm.setInt(2, origen.getIdEstacion());
			pstm.setInt(3, destino.getIdEstacion());
			pstm.setInt(4, distanciaKm);
			pstm.setInt(5, duracionViajeMin);
			pstm.setInt(6, capacidadMaximaPasajeros);
			pstm.setString(7, estado.toString());
			pstm.setDouble(8, costo);
			pstm.executeUpdate();
			coneccion.commit();
			
			coneccion.setAutoCommit(true);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
	}

	public static Camino cargarCamino(List<Ruta> camino) {
		
		ResultSet resultado = null;
		Camino c = new Camino();
		String consulta = "Insert into camino (id_camino) values (?)";
		
		try {
			
			int ultimoId = GestorJDBC.getUltimoId("camino", "id_camino");
			
			conectarYPrepararConsulta(consulta);
			
			coneccion.setAutoCommit(false);
			
			pstm.setInt(1, ultimoId+1);
			pstm.executeUpdate();
			coneccion.commit();
			
			coneccion.setAutoCommit(true);
			c.setIdCamino(ultimoId+1);
			cerrarConexion(resultado);
			
			consulta = "Insert into camino_ruta (id_camino, id_ruta) values (?,?);";
			conectarYPrepararConsulta(consulta);

			coneccion.setAutoCommit(false);
			for (Ruta r: camino) {
				pstm.setInt(1, c.getIdCamino());
				pstm.setInt(2, r.getIdRuta());
				pstm.executeUpdate();
				coneccion.commit();

			}
			coneccion.setAutoCommit(true);
			c.setRecorrido(camino);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		return c;
		
	}
	
	public static void cargarBoleto(Boleto boleto) {
		
		ResultSet resultado = null;
		
		String consulta = "Insert into boleto (id_origen, id_destino, id_camino, nombre, email, fecha, costo) values (?,?,?,?,?,?,?)";
		
		try {
			conectarYPrepararConsulta(consulta);
			
			coneccion.setAutoCommit(false);
			
			pstm.setInt(1, boleto.getOrigen().getIdEstacion());
			pstm.setInt(2, boleto.getDestino().getIdEstacion());
			pstm.setInt(3, boleto.getRecorrido().getIdCamino());
			pstm.setString(4, boleto.getNombre());
			pstm.setString(5, boleto.getEmail());
			pstm.setString(6, boleto.getFechaVenta().toString());
			pstm.setDouble(7, boleto.getCosto());
			pstm.executeUpdate();
			coneccion.commit();
			
			coneccion.setAutoCommit(true);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		
	}
	
	public static void cargarMantenimiento(TareaDeMantenimiento tarea) {
		
		ResultSet resultado = null;
		
		String consulta = "Insert into mantenimiento (inicio, fin, observaciones, id_estacion) values (?,?,?,?)";
		
		try {
			conectarYPrepararConsulta(consulta);
			
			coneccion.setAutoCommit(false);
			
			pstm.setString(1, tarea.getFechaInicio().toString());
			pstm.setString(2, tarea.getFechaFin().toString());
			pstm.setString(3, tarea.getObservaciones());
			pstm.setInt(4, tarea.getEstacion().getIdEstacion());
			pstm.executeUpdate();
			coneccion.commit();
			
			coneccion.setAutoCommit(true);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
	}
	
	
	
	public static List<Estacion> buscarEstaciones() {
		
		ResultSet resultado = null;
		
		String consulta = "Select * from estacion";
		List<Estacion> estaciones = null;
		
		try {
			conectarYPrepararConsulta(consulta);
			
			resultado = pstm.executeQuery();
			
			estaciones = new ArrayList<Estacion>();
			
			while (resultado.next()) {
				Estacion e = new Estacion(resultado.getInt("id_estacion"), resultado.getString("nombre"), resultado.getString("horario_cierre"), resultado.getString("horario_apertura"), resultado.getString("estado"));
				estaciones.add(e);
			}			
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		return estaciones;
	}

	public static List<LineaDeTransporte> buscarLineas() {
		ResultSet result = null;
		
		String consulta = "Select * from linea";
		
		List<LineaDeTransporte> lineas = null;
		
		try {
			conectarYPrepararConsulta(consulta);
			
			result = pstm.executeQuery();
			
			lineas = new ArrayList<LineaDeTransporte>();
			
			while (result.next()) {
				LineaDeTransporte e = new LineaDeTransporte(result.getInt("id_linea"), result.getString("nombre"), result.getString("color"), result.getString("estado"));
				lineas.add(e);
			}			
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(result);
		}
		return lineas;
	}
	
	public static List<TareaDeMantenimiento> consultarMantenimiento(Integer id_estacion) {
		
		ResultSet result = null;
		
		String consulta = "Select * from mantenimiento where id_estacion=?";
		
		List<TareaDeMantenimiento> tareas = null;
		
		try {
			conectarYPrepararConsulta(consulta);
			
			pstm.setInt(1, id_estacion);
			result = pstm.executeQuery();
			
			tareas = new ArrayList<TareaDeMantenimiento>();
			
			while (result.next()) {
				TareaDeMantenimiento t = new TareaDeMantenimiento(result.getInt("id_mantenimiento"), result.getString("inicio"), result.getString("fin"), result.getString("observaciones"), GestorJDBC.buscarEstacion(id_estacion));
				tareas.add(t);
			}			
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(result);
		}
		return tareas;
	}
	
	public static List<Ruta> buscarRuta(Integer idEstacion, EstadoRuta activa) {
		
		ResultSet resultado = null;
		
		String consulta = "Select * from ruta where id_origen=? and estado=?";
		
		List<Ruta> rutas = new ArrayList<Ruta>();
		List<Integer> id_lineas = new ArrayList<Integer>();
		List<Integer> id_estaciones = new ArrayList<Integer>();
		
		try {
			conectarYPrepararConsulta(consulta);
			
			pstm.setInt(1, idEstacion);
			pstm.setString(2, activa.equals(EstadoRuta.ACTIVA)? EstadoRuta.ACTIVA.toString():EstadoRuta.INACTIVA.toString());
			resultado = pstm.executeQuery();
			
			while (resultado.next()) {
				id_lineas.add(resultado.getInt("id_linea"));
				id_estaciones.add(resultado.getInt("id_origen"));
				id_estaciones.add(resultado.getInt("id_destino"));
				
				//System.out.println(resultado.getInt("id_origen") + " - " + resultado.getInt("id_destino") );
				//System.out.println(id_estaciones.size());
				
				Ruta r = new Ruta(resultado.getInt("id_ruta"),null,null,null, resultado.getInt("distancia"),
						resultado.getInt("duracion"), resultado.getInt("capacidad"),
						resultado.getString("estado").equals(EstadoRuta.ACTIVA.toString())? EstadoRuta.ACTIVA:EstadoRuta.INACTIVA, resultado.getDouble("costo"));
				rutas.add(r);
			}			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		
		for(int i = 0;i<id_lineas.size();i++) {
			rutas.get(i).setLinea(GestorJDBC.buscarLinea(id_lineas.get(i)));
		}
		int j=0;
		for(int i = 0;i<id_estaciones.size()-1;i+=2) {
			rutas.get(j).setOrigen(GestorJDBC.buscarEstacion(id_estaciones.get(i)));
			rutas.get(j).setDestino(GestorJDBC.buscarEstacion(id_estaciones.get(i+1)));
			j++;
		}
		return rutas;
	}

	private static Estacion buscarEstacion(int idEstacion) {

		ResultSet resultado = null;
		
		String consulta = "Select * from estacion where id_estacion=?";
		
		Estacion est = null;
		
		try {
			conectarYPrepararConsulta(consulta);
			
			pstm.setInt(1, idEstacion);
			resultado = pstm.executeQuery();
			resultado.next();
			est = new Estacion(resultado.getInt("id_estacion"),resultado.getString("nombre"),resultado.getString("horario_apertura"),resultado.getString("horario_cierre"),
					resultado.getString("estado"));			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		
		return est;
	}
	
	private static LineaDeTransporte buscarLinea(int idLinea) {

		ResultSet resultado = null;
		
		String consulta = "Select * from linea where id_linea=?";
		
		LineaDeTransporte l=null;
		
		try {
			conectarYPrepararConsulta(consulta);
			
			pstm.setInt(1, idLinea);
			resultado = pstm.executeQuery();
			resultado.next();
			l = new LineaDeTransporte(resultado.getInt("id_linea"),resultado.getString("nombre"),resultado.getString("color"), resultado.getString("estado"));	
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		
		return l;
	}

	

	public static void editarEstacion(Estacion editable) {
		
		ResultSet resultado = null;
		
		String consulta = "Update estacion set nombre=?, horario_cierre=?, horario_apertura=?, estado=? where id_estacion=?;";
		
		try {
			conectarYPrepararConsulta(consulta);
			
			coneccion.setAutoCommit(false);
			
			pstm.setString(1, editable.getNombre());
			pstm.setString(2, editable.getHorarioCierre());
			pstm.setString(3, editable.getHorarioApertura());
			pstm.setString(4, editable.getEstado().toString());
			pstm.setInt(5, editable.getIdEstacion());
			pstm.executeUpdate();
			coneccion.commit();
			
			coneccion.setAutoCommit(true);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		
		
	}

	public static void editarLinea(LineaDeTransporte editable) {
		
		ResultSet resultado = null;
		
		String consulta = "Update linea set nombre=?, color=?, estado=? where id_linea=?;";
		
		try {
			conectarYPrepararConsulta(consulta);
			
			coneccion.setAutoCommit(false);
			
			pstm.setString(1, editable.getNombre());
			pstm.setString(2, editable.getColor());
			pstm.setString(3, editable.getEstado().toString());
			pstm.setInt(4, editable.getIdLinea());
			pstm.executeUpdate();
			coneccion.commit();
			
			coneccion.setAutoCommit(true);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
	}

	
	
	public static Integer getUltimoId(String tabla, String columna) {
		
		Integer ultimoId = null;
		
		ResultSet resultado = null;
		
		String consulta = "Select max("+columna+") from "+tabla;
		
		try {
			conectarYPrepararConsulta(consulta);
			
			resultado = pstm.executeQuery();
			resultado.next();
			
			ultimoId = resultado.getInt(1);
		
		}
	
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			cerrarConexion(resultado);
		}
		return ultimoId;
	}

	
	
	

	
	
	
}
