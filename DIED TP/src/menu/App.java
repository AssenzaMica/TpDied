package menu;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import died.Boleto;
import died.Camino;
import died.Estacion;
import died.EstadoEstacion;
import died.EstadoLinea;
import died.EstadoRuta;
import died.LineaDeTransporte;
import died.Ruta;
import died.TareaDeMantenimiento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class App {

	public static void main(String[] args) {

		JFrame ventana = new JFrame();
		ventana.setAutoRequestFocus(false);
		ventana.setTitle("TP Assenza Micaela");
		ventana.setExtendedState(JFrame.NORMAL);
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JPanel panelEstacion = new JPanel();
		JPanel panelLineas = new JPanel();
		JPanel panelTrayectos = new JPanel();
		JPanel panelMantenimiento = new JPanel();
		JPanel panelVentas = new JPanel();
		JPanel panelInformes = new JPanel();
		
		JPanel panelAltaEstacion = new JPanel();
		JPanel panelBajaEstacion = new JPanel();
		JPanel panelEdicionEstacion = new JPanel();
		
		JPanel panelAltaLinea = new JPanel();
		JPanel panelBajaLinea = new JPanel();
		JPanel panelEdicionLinea = new JPanel();	
		
		JPanel panelTabla = new JPanel();
		
		JPanel panelMaxFlow = new JPanel();
		
		JComboBox<Estacion> comboBox2 = new JComboBox<Estacion>(); // Estacion en buscar
		JComboBox<Estacion> comboBox3 = new JComboBox<Estacion>(); // Estacion en mantenimiento
		JComboBox<LineaDeTransporte> comboBox4 = new JComboBox<LineaDeTransporte>(); // Linea en Baja
		JComboBox<LineaDeTransporte> comboBox5 = new JComboBox<LineaDeTransporte>(); // linea en Ruta
		JComboBox<LineaDeTransporte> comboBox6 = new JComboBox<LineaDeTransporte>(); // Linea en Buscar
		JComboBox<Estacion> comboBoxOrigen = new JComboBox<Estacion>(); // Estacion origen en ruta
		JComboBox<Estacion> comboBoxDestino = new JComboBox<Estacion>();// Estacion destino en ruta
		JComboBox<Estacion> comboBox7 = new JComboBox<Estacion>(); // Estacion origen en venta
		JComboBox<Estacion> comboBox8 = new JComboBox<Estacion>(); // Estacion destino en venta
		JComboBox<Estacion> comboBox1 = new JComboBox<Estacion>(); // Baja estacion
		JComboBox<Estacion> comboBox9 = new JComboBox<Estacion>(); // Max Flow Origen
		JComboBox<Estacion> comboBox10 = new JComboBox<Estacion>(); // Max Flow Destino


		
		// MENU PRINCIPAL // ---------------------------------------------------------------
		
		JButton boton1 = new JButton("Estaciones");
		panel.add(boton1);
		boton1.addActionListener(e -> {
			ventana.setContentPane(panelEstacion);
			//ventana.setBounds(400, 300, 400, 190);
			ventana.pack();	
		});
		
		JButton boton2 = new JButton("Mantenimiento");
		panel.add(boton2);
		boton2.addActionListener(e -> {
			ventana.setContentPane(panelMantenimiento);
			llenarEstaciones(comboBox3);
			//ventana.setBounds(400, 300, 400, 190);
			ventana.pack();	
		});
		
		JButton boton3 = new JButton("Lineas");
		panel.add(boton3);
		boton3.addActionListener(e -> {
			ventana.setContentPane(panelLineas);
			//ventana.setBounds(400, 300, 400, 190);
			//ventana.setLocation(400, 300);
			ventana.pack();	
		});
		
		JButton boton6 = new JButton("Trayectos");
		panel.add(boton6);
		boton6.addActionListener(e -> {
			ventana.setContentPane(panelTrayectos);
			llenarLineas(comboBox5);
			llenarEstaciones(comboBoxOrigen);
			llenarEstaciones(comboBoxDestino);
			ventana.setBounds(400, 300, 400, 190);
			//ventana.pack();	
		});
		
		JButton boton4 = new JButton("Ventas");
		panel.add(boton4);
		boton4.addActionListener(e -> {
			ventana.setContentPane(panelVentas);
			llenarEstaciones(comboBox7);
			llenarEstaciones(comboBox8);
			ventana.setBounds(400, 300, 400, 190);
			//ventana.pack();	
		});
		
		JButton boton5 = new JButton("Informes");
		panel.add(boton5);
		boton5.addActionListener(e -> {
			ventana.setContentPane(panelInformes);
			//ventana.setBounds(400, 300, 400, 190);
			ventana.pack();	
		});
		
		
		
		// MENU ESTACIONES // ----------------------------------------------------------------
		
		// PanelEstacion
		
		// Alta Estacion
		
		JButton btnNewButton = new JButton("Alta Estaci\u00F3n");
		panelEstacion.add(btnNewButton);
		btnNewButton.addActionListener(e -> {
			ventana.setContentPane(panelAltaEstacion);
			//ventana.setBounds(400, 300, 450, 190);
			ventana.pack();	
			});
		
				
			JLabel lblNombre = new JLabel ("Nombre:");
			panelAltaEstacion.add(lblNombre);
			JTextField txtNombre = new JTextField(20);
			panelAltaEstacion.add(txtNombre);
		
			JLabel lblApertura = new JLabel ("Hora Apertura:");
			panelAltaEstacion.add(lblApertura);
			JTextField txtApertura = new JTextField(5);
			panelAltaEstacion.add(txtApertura);
			
			JLabel lblCierre = new JLabel ("Hora Cierre:");
			panelAltaEstacion.add(lblCierre);
			JTextField txtCierre = new JTextField(5);
			panelAltaEstacion.add(txtCierre);
			
			JLabel lblEstado = new JLabel ("Estado:");
			panelAltaEstacion.add(lblEstado);
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Operativa");
			rdbtnNewRadioButton.setSelected(true);
			panelAltaEstacion.add(rdbtnNewRadioButton);
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("en Mantenimiento");
			panelAltaEstacion.add(rdbtnNewRadioButton_1);
			ButtonGroup bg=new ButtonGroup();    
			bg.add(rdbtnNewRadioButton);bg.add(rdbtnNewRadioButton_1); 
			
			JButton btnAgregar = new JButton("Agregar");
			panelAltaEstacion.add(btnAgregar);
			
			// AGREGA ESTACION EN LA BASE DE DATOS
			btnAgregar.addActionListener(new ActionListener() {

				@SuppressWarnings("unlikely-arg-type")
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					GestorJDBC.cargarEstacion(txtNombre.getText(), txtApertura.getText(), txtCierre.getText(), (rdbtnNewRadioButton.getSelectedObjects() != null)?EstadoEstacion.OPERATIVA:EstadoEstacion.ENMANTENIMIENTO);
					JOptionPane.showConfirmDialog(null, "La Estacion ha sido cargada exitosamente", "Exito", JOptionPane.DEFAULT_OPTION);
				}	});
			
			JButton btnCancelar = new JButton("Cancelar");
			panelAltaEstacion.add(btnCancelar);
			btnCancelar.addActionListener(e -> {
				ventana.setContentPane(panelEstacion);
				//ventana.setBounds(400, 300, 450, 190);
				ventana.pack();	
			});
		
			
		// Baja Estacion	
			
		
		JButton btnDarDeBaja = new JButton("Dar de Baja Estaci\u00F3n");
		panelEstacion.add(btnDarDeBaja);
		btnDarDeBaja.addActionListener(e -> {
			ventana.setContentPane(panelBajaEstacion);
			llenarEstaciones(comboBox1);
			//ventana.setBounds(400, 300, 450, 190);
			ventana.pack();	
			});
			
			JLabel lblEstacionBaja = new JLabel ("Estacion:");
			panelBajaEstacion.add(lblEstacionBaja);
			panelBajaEstacion.add(comboBox1);
			
			JButton btnEliminar = new JButton("Eliminar");
			panelBajaEstacion.add(btnEliminar);
			
			btnEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showConfirmDialog(null, "No posee permisos suficientes para eliminar Estaciones", "Error", JOptionPane.DEFAULT_OPTION);
				}	});
			
			JButton btnCancelarB = new JButton("Cancelar");
			panelBajaEstacion.add(btnCancelarB);
			btnCancelarB.addActionListener(e -> {
				ventana.setContentPane(panelEstacion);
				//ventana.setBounds(400, 300, 450, 190);
				ventana.pack();	
			});
		
			
		// Editar Estacion
			
		JButton btnEditarEstacin = new JButton("Buscar Estaci\u00F3n");
		panelEstacion.add(btnEditarEstacin);
		btnEditarEstacin.addActionListener(e -> {
			ventana.setContentPane(panelEdicionEstacion);
			//ventana.setBounds(400, 300, 450, 190);
			llenarEstaciones(comboBox2);
			ventana.pack();
			});
		
		JLabel lblEstacionEditar = new JLabel ("Estacion:");
		JLabel lblNombreE = new JLabel ("Nombre:");
		JTextField txtNombreE = new JTextField(10);
		JLabel lblAperturaE = new JLabel ("Hora Apertura:");
		JTextField txtAperturaE = new JTextField(5);
		JLabel lblCierreE = new JLabel ("Hora Cierre:");
		JTextField txtCierreE = new JTextField(5);
		
		JLabel lblEstadoE = new JLabel ("Estado:");
		JRadioButton rdbtnNewRadioButtonE = new JRadioButton("Operativa");
		JRadioButton rdbtnNewRadioButton_1E = new JRadioButton("en Mantenimiento");
		rdbtnNewRadioButtonE.setSelected(true);
		ButtonGroup bgE=new ButtonGroup();    
		bgE.add(rdbtnNewRadioButtonE);bgE.add(rdbtnNewRadioButton_1E); 
		
		
		// TRAE DATOS EXISTENTES AL ELEGIR UNA ESTACION
		comboBox2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(comboBox2.getSelectedItem()==null) {
					return;
				}
				Estacion aux = (Estacion) comboBox2.getSelectedItem();
				txtNombreE.setText(aux.getNombre());
				txtAperturaE.setText(aux.getHorarioApertura());
				txtCierreE.setText(aux.getHorarioCierre());
				rdbtnNewRadioButtonE.setSelected(aux.getEstado().equals(EstadoEstacion.OPERATIVA));
				rdbtnNewRadioButton_1E.setSelected(aux.getEstado().equals(EstadoEstacion.ENMANTENIMIENTO));
			}
			
		});
		
		panelEdicionEstacion.add(lblEstacionEditar);
		panelEdicionEstacion.add(comboBox2);
		panelEdicionEstacion.add(lblNombreE);
		panelEdicionEstacion.add(txtNombreE);
		panelEdicionEstacion.add(lblAperturaE);
		panelEdicionEstacion.add(txtAperturaE);
		panelEdicionEstacion.add(lblCierreE);
		panelEdicionEstacion.add(txtCierreE);
		panelEdicionEstacion.add(lblEstadoE);
		panelEdicionEstacion.add(rdbtnNewRadioButtonE);
		panelEdicionEstacion.add(rdbtnNewRadioButton_1E);
		
		JButton btnEditar = new JButton("Editar");
		panelEdicionEstacion.add(btnEditar);
		JButton btnCancelarE = new JButton("Cancelar");
		panelEdicionEstacion.add(btnCancelarE);
					
		// GUARDA CAMBIOS EN ESTACION
		btnEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Estacion est = (Estacion) comboBox2.getSelectedItem();
				est.setNombre(txtNombreE.getText());
				est.setHorarioApertura(txtAperturaE.getText());
				est.setHorarioCierre(txtCierreE.getText());
				est.setEstado((rdbtnNewRadioButtonE.getSelectedObjects() != null)?EstadoEstacion.OPERATIVA:EstadoEstacion.ENMANTENIMIENTO);
				GestorJDBC.editarEstacion(est);
				JOptionPane.showConfirmDialog(null, "La Estacion ha sido editada exitosamente", "Exito", JOptionPane.DEFAULT_OPTION);
			}	});
			
			
			btnCancelarE.addActionListener(e -> {
				ventana.setContentPane(panelEstacion);
				//ventana.setBounds(400, 300, 450, 190);
				ventana.pack();	
			});

			
		JButton btnAtrs = new JButton("Atr\u00E1s");
		panelEstacion.add(btnAtrs);
		btnAtrs.addActionListener(e -> {
			ventana.setContentPane(panel);
			//ventana.setBounds(400, 300, 450, 190);
			ventana.pack();	
		});
		
		
		
		// MENU LINEAS // ----------------------------------------------------------------
		
		JButton btnNewButtonL = new JButton("Alta Linea");
		panelLineas.add(btnNewButtonL);
		btnNewButtonL.addActionListener(e -> {
			ventana.setContentPane(panelAltaLinea);
			ventana.pack();	});
		
			JLabel lblNombreL = new JLabel ("Nombre:");
			panelAltaLinea.add(lblNombreL);
			JTextField txtNombreL = new JTextField(20);
			panelAltaLinea.add(txtNombreL);
			
			JLabel lblColor = new JLabel ("Color:");
			panelAltaLinea.add(lblColor);
			JTextField txtColor = new JTextField(10);
			panelAltaLinea.add(txtColor);
			
			JLabel lblEstadoL = new JLabel ("Estado:");
			panelAltaLinea.add(lblEstadoL);
			JRadioButton rdbtnNewRadioButtonL = new JRadioButton("Activa");
			rdbtnNewRadioButtonL.setSelected(true);
			panelAltaLinea.add(rdbtnNewRadioButtonL);
			JRadioButton rdbtnNewRadioButton_1L = new JRadioButton("Inactiva");
			panelAltaLinea.add(rdbtnNewRadioButton_1L);
			ButtonGroup bgL=new ButtonGroup();    
			bgL.add(rdbtnNewRadioButtonL);bgL.add(rdbtnNewRadioButton_1L); 
			
			JButton btnAgregarL = new JButton("Agregar");
			panelAltaLinea.add(btnAgregarL);
			
			// AGREGAR LINEA EN LA BASE DE DATOS
			btnAgregarL.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					GestorJDBC.cargarLinea(txtNombreL.getText(), txtColor.getText(), (rdbtnNewRadioButtonL.getSelectedObjects() != null)?EstadoLinea.ACTIVA:EstadoLinea.NOACTIVA);
					JOptionPane.showConfirmDialog(null, "La Linea ha sido cargada exitosamente", "Exito", JOptionPane.DEFAULT_OPTION);
				}	});
			
			
			JButton btnCancelarL = new JButton("Cancelar");
			panelAltaLinea.add(btnCancelarL);
			btnCancelarL.addActionListener(e -> {
				ventana.setContentPane(panelLineas);
				ventana.pack();	});
		
		JButton btnDarDeBajaL = new JButton("Dar de Baja Linea");
		panelLineas.add(btnDarDeBajaL);
		btnDarDeBajaL.addActionListener(e -> {
			ventana.setContentPane(panelBajaLinea);
			llenarLineas(comboBox4);
			ventana.pack();	});
		
			JLabel lblLineaBaja = new JLabel ("Linea:");
			panelBajaLinea.add(lblLineaBaja);
			panelBajaLinea.add(comboBox4);
			
			JButton btnEliminarL = new JButton("Eliminar");
			panelBajaLinea.add(btnEliminarL);
			
			btnEliminarL.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showConfirmDialog(null, "No posee permisos suficientes para eliminar Lineas", "Error", JOptionPane.DEFAULT_OPTION);
				}	});
			
			JButton btnCancelarBL = new JButton("Cancelar");
			panelBajaLinea.add(btnCancelarBL);
			btnCancelarBL.addActionListener(e -> {
				ventana.setContentPane(panelLineas);
				ventana.pack();	});
		
			
		JButton btnEditarLinea = new JButton("Buscar Linea");
		panelLineas.add(btnEditarLinea);
		btnEditarLinea.addActionListener(e -> {
			ventana.setContentPane(panelEdicionLinea);
			llenarLineas(comboBox6);
			ventana.pack();	});
		
			JLabel lblLineaB = new JLabel ("Linea:");
			JLabel lblNombreEL = new JLabel ("Nombre:");
			JTextField txtNombreEL = new JTextField(20);
			JLabel lblColorE = new JLabel ("Color:");
			JTextField txtColorE = new JTextField(10);
			JLabel lblEstadoEL = new JLabel ("Estado:");
			JRadioButton rdbtnNewRadioButtonEL = new JRadioButton("Activa");
			JRadioButton rdbtnNewRadioButton_1EL = new JRadioButton("Inactiva");
			rdbtnNewRadioButtonEL.setSelected(true);
			ButtonGroup bgEL=new ButtonGroup();    
			bgEL.add(rdbtnNewRadioButtonEL);bgEL.add(rdbtnNewRadioButton_1EL); 
		
			
			// LLENA CAMPOS AL SELECCIONAR LINEA 
			comboBox6.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(comboBox6.getSelectedItem()==null) {
						return;
					}
					LineaDeTransporte aux = (LineaDeTransporte) comboBox6.getSelectedItem();
					txtNombreEL.setText(aux.getNombre());
					txtColorE.setText(aux.getColor());
					rdbtnNewRadioButtonEL.setSelected(aux.getEstado().equals(EstadoLinea.ACTIVA));
					rdbtnNewRadioButton_1EL.setSelected(aux.getEstado().equals(EstadoLinea.NOACTIVA));
				}
				
			});
		
			panelEdicionLinea.add(lblLineaB);
			panelEdicionLinea.add(comboBox6);
			panelEdicionLinea.add(lblNombreEL);
			panelEdicionLinea.add(txtNombreEL);
			panelEdicionLinea.add(lblColorE);
			panelEdicionLinea.add(txtColorE);
			panelEdicionLinea.add(lblEstadoEL);
			panelEdicionLinea.add(rdbtnNewRadioButtonEL);
			panelEdicionLinea.add(rdbtnNewRadioButton_1EL);
			
			
			JButton btnEditarL = new JButton("Editar");
			panelEdicionLinea.add(btnEditarL);
			
			//GUARDA CAMBIOS EN LINEA
			btnEditarL.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					LineaDeTransporte l = (LineaDeTransporte)comboBox6.getSelectedItem();
					l.setNombre(txtNombreEL.getText());
					l.setColor(txtColorE.getText());
					l.setEstado((rdbtnNewRadioButtonEL.getSelectedObjects() != null)?EstadoLinea.ACTIVA:EstadoLinea.NOACTIVA);
					GestorJDBC.editarLinea(l);
					JOptionPane.showConfirmDialog(null, "La Estacion ha sido editada exitosamente", "Exito", JOptionPane.DEFAULT_OPTION);
				}	});
			
			JButton btnCancelarEL = new JButton("Cancelar");
			panelEdicionLinea.add(btnCancelarEL);
			btnCancelarEL.addActionListener(e -> {
				ventana.setContentPane(panelLineas);
				ventana.pack();	});
		
		
		JButton btnAtrsL = new JButton("Atr\u00E1s");
		panelLineas.add(btnAtrsL);
		btnAtrsL.addActionListener(e -> {
			ventana.setContentPane(panel);
			ventana.pack();	});
		
		
		// MENU TRAYECTOS // ----------------------------------------------------------------

		JLabel lblLineaT = new JLabel ("Linea:");
		panelTrayectos.add(lblLineaT);
		panelTrayectos.add(comboBox5);
		
		JLabel lblEstacionO = new JLabel ("Origen:");
		panelTrayectos.add(lblEstacionO);
		panelTrayectos.add(comboBoxOrigen);
		
		JLabel lblEstacionD = new JLabel ("Destino:");
		panelTrayectos.add(lblEstacionD);
		panelTrayectos.add(comboBoxDestino);
		
		JLabel lblDistancia = new JLabel ("Distancia en Km:");
		panelTrayectos.add(lblDistancia);
		JTextField txtDistancia = new JTextField(6);
		panelTrayectos.add(txtDistancia);
		
		JLabel lblDuracion = new JLabel ("Tiempo en minutos:");
		panelTrayectos.add(lblDuracion);
		JTextField txtDuracion = new JTextField(6);
		panelTrayectos.add(txtDuracion);
		
		JLabel lblCantMax = new JLabel ("Cant Max Pasajeros:");
		panelTrayectos.add(lblCantMax);
		JTextField txtCantMax = new JTextField(6);
		panelTrayectos.add(txtCantMax);
		
		JLabel lblEstadoRuta = new JLabel ("Estado:");
		panelTrayectos.add(lblEstadoRuta);
		JRadioButton rdbtnNewRadioButtonRuta = new JRadioButton("Activa");
		rdbtnNewRadioButtonRuta.setSelected(true);
		panelTrayectos.add(rdbtnNewRadioButtonRuta);
		JRadioButton rdbtnNewRadioButton_1Ruta = new JRadioButton("Inactiva");
		panelTrayectos.add(rdbtnNewRadioButton_1Ruta);
		ButtonGroup bgRuta=new ButtonGroup();    
		bgRuta.add(rdbtnNewRadioButtonRuta);bgRuta.add(rdbtnNewRadioButton_1Ruta); 
		
		JLabel lblCosto = new JLabel ("Costo:");
		panelTrayectos.add(lblCosto);
		JTextField txtCosto = new JTextField(6);
		panelTrayectos.add(txtCosto);
		
		JButton btnAgregarRuta = new JButton("Agregar");
		panelTrayectos.add(btnAgregarRuta);
		btnAgregarRuta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GestorJDBC.cargarTrayecto((LineaDeTransporte) comboBox5.getSelectedItem(), (Estacion) comboBoxOrigen.getSelectedItem(), (Estacion) comboBoxDestino.getSelectedItem(), 
						Integer.valueOf(txtDistancia.getText()), Integer.valueOf(txtDuracion.getText()), Integer.valueOf(txtCantMax.getText()),
						((rdbtnNewRadioButtonRuta.getSelectedObjects() != null)?EstadoRuta.ACTIVA:EstadoRuta.INACTIVA), Integer.valueOf(txtCosto.getText())); 
				JOptionPane.showConfirmDialog(null, "La Ruta ha sido cargada exitosamente", "Exito", JOptionPane.DEFAULT_OPTION);
			}	});
		
		JButton btnCancelarRuta = new JButton("Cancelar");
		panelTrayectos.add(btnCancelarRuta);
		btnCancelarRuta.addActionListener(e -> {
			ventana.setContentPane(panel);
			ventana.pack();	});
		
		
		// MENU MANTENIMIENTO // ------------------------------------------------------------
		
		JLabel lblMant = new JLabel ("Estacion:");
		panelMantenimiento.add(lblMant);
		panelMantenimiento.add(comboBox3);
		
		JLabel lblinicio = new JLabel ("Inicio:");
		panelMantenimiento.add(lblinicio);
		JTextField txtInicio = new JTextField(8);
		panelMantenimiento.add(txtInicio);

		
		JLabel lblFin = new JLabel ("Fin:");
		panelMantenimiento.add(lblFin);
		JTextField txtFin = new JTextField(8);
		panelMantenimiento.add(txtFin);

		
		JLabel lblobs = new JLabel ("Observaciones:");
		panelMantenimiento.add(lblobs);
		JTextField txtobs = new JTextField(30);
		panelMantenimiento.add(txtobs);

		
		JButton btnCargar = new JButton("Cargar");
		panelMantenimiento.add(btnCargar);
		btnCargar.addActionListener(e -> {
			TareaDeMantenimiento tarea = new TareaDeMantenimiento(txtInicio.getText(), txtFin.getText(), txtobs.getText(), (Estacion) comboBox3.getSelectedItem());
			GestorJDBC.cargarMantenimiento(tarea);
			JOptionPane.showConfirmDialog(null, "El Mantenimiento ha sido cargado exitosamente", "Exito", JOptionPane.DEFAULT_OPTION);
		});
		
		JButton btnConsultar = new JButton("Consultar");
		panelMantenimiento.add(btnConsultar);
		btnConsultar.addActionListener(e -> {
			Estacion estAux = (Estacion)comboBox3.getSelectedItem();
			String[] nombreColumnas = {"Fecha Inicio", "Fecha Fin", "Observaciones", "Id Estacion"};
			Object[][] data = GestorAlgoritmos.crearMatriz((GestorJDBC.consultarMantenimiento(estAux.getIdEstacion())));
			
			final JTable tabla = new JTable(data, nombreColumnas);
			panelTabla.removeAll();
			panelTabla.add(tabla);
			ventana.setContentPane(panelTabla);
			ventana.setVisible(true);
			ventana.pack();
			
			JButton btnAtrsMant = new JButton("Atr\u00E1s");
			panelTabla.add(btnAtrsMant);
			btnAtrsMant.addActionListener(f -> {
				ventana.setContentPane(panelMantenimiento);
				ventana.pack();
			});
			
			
			//JOptionPane.showConfirmDialog(null, "Consulta Finalizada", "Exito", JOptionPane.DEFAULT_OPTION);
		});
			
		
		JButton btnAtrsM = new JButton("Atr\u00E1s");
		panelMantenimiento.add(btnAtrsM);
		btnAtrsM.addActionListener(e -> {
			ventana.setContentPane(panel);
			ventana.pack();
		});
		
		
		
		
		// MENU VENTAS // --------------------------------------------------------------------
		
		JLabel lblNombreComprador = new JLabel ("Nombre:");
		panelVentas.add(lblNombreComprador);
		JTextField txtNombreComprador = new JTextField(27);
		panelVentas.add(txtNombreComprador);
		
		JLabel lblemail = new JLabel ("Email:");
		panelVentas.add(lblemail);
		JTextField txtemail = new JTextField(27);
		panelVentas.add(txtemail);
		
		JLabel lblOrigen = new JLabel ("Estacion Origen:");
		panelVentas.add(lblOrigen);
		panelVentas.add(comboBox7);
		
		JLabel lblDestino = new JLabel ("Estacion Destino:");
		panelVentas.add(lblDestino);
		panelVentas.add(comboBox8);
		
		JButton btnRapido = new JButton("Más Rápido");
		panelVentas.add(btnRapido);
		btnRapido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Ruta> camino = GestorAlgoritmos.caminoMasRapido((Estacion) comboBox7.getSelectedItem(), (Estacion) comboBox8.getSelectedItem());
				
				if(camino==null) {
					JOptionPane.showConfirmDialog(null, "No se ha encontrado camino", "Fallo", JOptionPane.DEFAULT_OPTION);
					return;
				}
								
				Camino camino1 = GestorJDBC.cargarCamino(camino);
								
				Double costoBoleto = GestorAlgoritmos.calcularCostoBoleto(camino);
				int distanciaBoleto = GestorAlgoritmos.calcularDistanciaBoleto(camino);
				int tiempoBoleto = GestorAlgoritmos.calcularTiempoBoleto(camino);
								
				Boleto boleto = new Boleto(txtNombreComprador.getText(), txtemail.getText(), LocalDate.now(), (Estacion) comboBox7.getSelectedItem(),
						(Estacion) comboBox8.getSelectedItem(), camino1, costoBoleto);
				
				GestorJDBC.cargarBoleto(boleto);
								
				String texto = "Numero: " + GestorJDBC.getUltimoId("boleto", "id_boleto") + " - Sr/a: " + boleto.getNombre() + 
				" - Fecha: " + boleto.getFechaVenta().toString() + " - Email: " + boleto.getEmail() + 
				" - Origen: " + boleto.getOrigen().toString() + " - Destino: " + boleto.getDestino().toString() + 
				GestorAlgoritmos.imprimirRecorrido(boleto) + " - Precio: $" + boleto.getCosto() + 
				" - Distancia: " + distanciaBoleto + "Kms" + " - Tiempo en minutos: " + tiempoBoleto + "'";
				
				JOptionPane.showConfirmDialog(null, texto, "Boleto Emitido. Opcion Elegida: Menor Tiempo", JOptionPane.DEFAULT_OPTION);

				
			}

		});
			
		
		JButton btnMenorDist = new JButton("Menor Distancia");
		panelVentas.add(btnMenorDist);
		btnMenorDist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Ruta> camino = GestorAlgoritmos.caminoMenorDistancia((Estacion) comboBox7.getSelectedItem(), (Estacion) comboBox8.getSelectedItem());
				
				Camino camino1 = GestorJDBC.cargarCamino(camino);
				
				Double costoBoleto = GestorAlgoritmos.calcularCostoBoleto(camino);
				int distanciaBoleto = GestorAlgoritmos.calcularDistanciaBoleto(camino);
				int tiempoBoleto = GestorAlgoritmos.calcularTiempoBoleto(camino);
				
				Boleto boleto = new Boleto(txtNombreComprador.getText(), txtemail.getText(), LocalDate.now(), (Estacion) comboBox7.getSelectedItem(),
						(Estacion) comboBox8.getSelectedItem(), camino1, costoBoleto);
				
				GestorJDBC.cargarBoleto(boleto);
				
				String texto = "Numero: " + GestorJDBC.getUltimoId("boleto", "id_boleto") + " - Sr/a: " + boleto.getNombre() + 
						" - Fecha: " + boleto.getFechaVenta().toString() + " - Email: " + boleto.getEmail() + 
						" - Origen: " + boleto.getOrigen().toString() + " - Destino: " + boleto.getDestino().toString() + 
						GestorAlgoritmos.imprimirRecorrido(boleto) + " - Precio: $" + boleto.getCosto() + 
						" - Distancia: " + distanciaBoleto + "Kms" + " - Tiempo en minutos: " + tiempoBoleto + "'";
						
				JOptionPane.showConfirmDialog(null, texto, "Boleto Emitido. Opcion Elegida: Menor Distancia", JOptionPane.DEFAULT_OPTION);
			}
		});
		
		
		JButton btnMasBarato = new JButton("Mas Barato");
		panelVentas.add(btnMasBarato);
		btnMasBarato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Ruta> camino = GestorAlgoritmos.caminoMasBarato((Estacion) comboBox7.getSelectedItem(), (Estacion) comboBox8.getSelectedItem());
				
				Camino camino1 = GestorJDBC.cargarCamino(camino);
				
				Double costoBoleto = GestorAlgoritmos.calcularCostoBoleto(camino);
				int distanciaBoleto = GestorAlgoritmos.calcularDistanciaBoleto(camino);
				int tiempoBoleto = GestorAlgoritmos.calcularTiempoBoleto(camino);
				
				Boleto boleto = new Boleto(txtNombreComprador.getText(), txtemail.getText(), LocalDate.now(), (Estacion) comboBox7.getSelectedItem(),
						(Estacion) comboBox8.getSelectedItem(), camino1, costoBoleto);
				
				GestorJDBC.cargarBoleto(boleto);
				
				String texto = "Numero: " + GestorJDBC.getUltimoId("boleto", "id_boleto") + " - Sr/a: " + boleto.getNombre() + 
						" - Fecha: " + boleto.getFechaVenta().toString() + " - Email: " + boleto.getEmail() + 
						" - Origen: " + boleto.getOrigen().toString() + " - Destino: " + boleto.getDestino().toString() + 
						GestorAlgoritmos.imprimirRecorrido(boleto) + " - Precio: $" + boleto.getCosto() + 
						" - Distancia: " + distanciaBoleto + "Kms" + " - Tiempo en minutos: " + tiempoBoleto + "'";
						
				JOptionPane.showConfirmDialog(null, texto, "Boleto Emitido. Opcion Elegida: Menor Costo", JOptionPane.DEFAULT_OPTION);
			}
		});
		
		JButton btnAtrsV = new JButton("Atr\u00E1s");
		panelVentas.add(btnAtrsV);
		btnAtrsV.addActionListener(e -> {
			ventana.setContentPane(panel);
			ventana.pack();
		});
		
		
		// MENU INFORMES // ------------------------------------------------------------------
		
		// Max Flow ..........................................................................
		
		JButton btnFlujoMax = new JButton("Flujo Máximo");
		panelInformes.add(btnFlujoMax);
		btnFlujoMax.addActionListener(e -> {
			ventana.setContentPane(panelMaxFlow);
			llenarEstaciones(comboBox9);
			llenarEstaciones(comboBox10);
			ventana.pack();	});
		
		JLabel lblEstacionMF = new JLabel ("Origen:");
		panelMaxFlow.add(lblEstacionMF);
		panelMaxFlow.add(comboBox9);
		
		JLabel lblEstacionMFD = new JLabel ("Destino:");
		panelMaxFlow.add(lblEstacionMFD);
		panelMaxFlow.add(comboBox10);
		
		JButton btnCalcularMF = new JButton("Calcular");
		panelMaxFlow.add(btnCalcularMF);
		
		btnCalcularMF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int max = GestorAlgoritmos.maxFlow((Estacion) comboBox9.getSelectedItem(), (Estacion) comboBox10.getSelectedItem());
				Estacion origen=(Estacion) comboBox9.getSelectedItem();
				Estacion destino=(Estacion) comboBox10.getSelectedItem();
				JOptionPane.showConfirmDialog(null, "Max Flow desde estacion: "+ origen.getNombre() + " a estacion: "+destino.getNombre() + " = " + max , "Exito", JOptionPane.DEFAULT_OPTION);
			}	});
		
		JButton btnCancelarMF = new JButton("Cancelar");
		panelMaxFlow.add(btnCancelarMF);
		btnCancelarMF.addActionListener(e -> {
			ventana.setContentPane(panel);
			ventana.pack();	});
		

		
		JButton btnPageRank = new JButton("Page Rank");
		panelInformes.add(btnPageRank);
		btnPageRank.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "En construcción...", "Lo siento", JOptionPane.DEFAULT_OPTION);
			}	});
		
		JButton btnProxMant = new JButton("Proximo Mantenimiento");
		panelInformes.add(btnProxMant);
		btnProxMant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "En construcción...", "Lo siento", JOptionPane.DEFAULT_OPTION);
			}	});
		
		JButton btnAtrsI = new JButton("Atr\u00E1s");
		panelInformes.add(btnAtrsI);
		btnAtrsI.addActionListener(e -> {
			ventana.setContentPane(panel);
			ventana.pack();	});
		
		
		ventana.setContentPane(panel);
		ventana.setVisible(true);
		ventana.pack();
	}




	private static void llenarEstaciones(JComboBox<Estacion> combo) {

		List<Estacion> estaciones = GestorJDBC.buscarEstaciones();
		
		combo.removeAllItems();
		
		for(Estacion e: estaciones) {
			combo.addItem(e);
		}
		
	}
	
	private static void llenarLineas(JComboBox<LineaDeTransporte> combo) {
		// TODO Auto-generated method stub
		List<LineaDeTransporte> lineas = GestorJDBC.buscarLineas();
		
		combo.removeAllItems();
		
		for(LineaDeTransporte e: lineas) {
			combo.addItem(e);
		}
	}

}
