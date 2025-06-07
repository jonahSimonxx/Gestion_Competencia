package visuals;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Atleta;
import model.Ciudad;
import model.Competencia;
import model.Disciplina;
import model.Entrenador;
import model.Inscripcion;
import model.Pais;
import model.Registro;
import model.Sede;
import model.Usuario;
import reports.ReportGenerator;
import reports.ReporteAtletasIncompletos;
import reports.ReporteAtletasPorPais;
import reports.ReporteCompetencias;
import reports.ReporteCompetenciasPorCiudadYSede;
import reports.ReporteCompetenciasPorPais;
import reports.ReporteEntrenadores;
import reports.ReporteEstadoCompetencias;
import reports.ReporteInscripciones;
import reports.ReporteResultadosAnuales;
import services.AtletaServices;
import services.CiudadServices;
import services.CompetenciaServices;
import services.DisciplinaServices;
import services.EntrenadorServices;
import services.InscripcionServices;
import services.PaisServices;
import services.RegistroServices;
import services.SedeServices;
import tables.TableModelAtletas;
import tables.TableModelCiudad;
import tables.TableModelCompetencia;
import tables.TableModelDisciplina;
import tables.TableModelEntrenador;
import tables.TableModelInscripcion;
import tables.TableModelPais;
import tables.TableModelRegistro;
import tables.TableModelReporte;
import tables.TableModelSede;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.CardLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Canvas;
import javax.swing.SwingConstants;


public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ButtonGroup sexo = new ButtonGroup();
	private JPanel contentPane;
	private JPanel atras1;
	protected JPanel atras2;
	private JTable table_mostrar;
	private JTextField textField_nombreAtleta;
	private JTextField textField_paisAtleta;
	private JTextField textField_catDeportAtleta;
	private JTextField textField_id_Entrenador_Atleta;
	private boolean modificando = false;
	private Atleta viejoAtleta;
	private Ciudad viejaCiudad;
	private Pais viejoPais;
	private Disciplina viejaDisciplina;
	private Entrenador viejoEntrenador;
	private Sede viejaSede;
	private ReportGenerator generadorReportes = new ReportGenerator();
	private AtletaServices servicioAtleta = new AtletaServices();
	private CiudadServices servicioCiudad = new CiudadServices();
	private PaisServices servicioPais = new PaisServices();
	private DisciplinaServices servicioDisciplina = new DisciplinaServices();
	private EntrenadorServices servicioEntrenador = new EntrenadorServices();
	private SedeServices servicioSede = new SedeServices();
	private RegistroServices servicioRegistro = new RegistroServices();
	private TableModelAtletas atletasTableModel;
	private TableModelPais paisTableModel;
	private TableModelDisciplina disciplinaTableModel;;
	private TableModelCiudad ciudadTableModel;
	private TableModelEntrenador entrenadorTableModel;
	private TableModelSede sedeTableModel;
	private JTextField textField_nom_pais_2;
	private JTextField textField_nom_ciudad_1;
	private TableModelReporte reporteTableModel;
	private int entidad;
	private JTextField textField_nom_pais_1;
	private JTextField textField_disciplina_1;
	private JTextField textField_nombre_entrenador;
	private JTextField textField_id_entrenador;
	private JTextField textField_especialidad_entrenador;
	private JTextField textField_direccion_entrenador;
	private JTextField textField_sede_1;
	private JTextField textField_nom_ciudad_2;
	private JTextField textField_nom_competencia;
	private JTextField textField_sede_2;
	private JTextField textField_fecha_ini_competencia;
	private JTextField textField_fecha_fin_competencia;
	private JTextField textField_estado_competencia;
	private CompetenciaServices servicioCompetencia  = new CompetenciaServices();
	private Competencia viejaCompetencia;
	private TableModelCompetencia competenciaTableModel;
	private JTextField textField_nom_competencia_2;
	private JTextField textField_id_atleta_2;
	private JTextField textField_id_entrenador_2;
	private JTextField textField_fecha_fin_inscripcion;
	private JTextField textField_fecha_ini_inscripcion;
	private InscripcionServices servicioInscripcion = new InscripcionServices();
	private Inscripcion viejaInscripcion;
	private TableModelInscripcion inscripcionTableModel;
	private JTextField textField_nom_competencia_3;
	private JTextField textField_id_atleta_3;
	private JTextField textField_marcas;
	private JTextField textField_nom_disciplina_2;
	private Registro viejoRegistro;
	private TableModelRegistro registroTableModel;
	private JButton btn_rep_atletas_incompletos;
	private boolean esAdmin;
	private JMenu mnNewMenu;
	private JButton btnEliminarAtleta;
	private JButton btn_modificar_atleta;
	private JToolBar toolBar;
	private Usuario usuarioActual;
	
	
	
	
	@SuppressWarnings("removal")
	public Principal(boolean esAdmin, Usuario usuario) {
		this.esAdmin = esAdmin;
		this.usuarioActual = usuario;
		setTitle("Sistema de gestión de competencias " + (esAdmin ? "(Administrador)" : "(Usuario)"));
		setTitle("Sistema de gestión de competencias ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 30, 1084, 631);
		contentPane.add(panelPrincipal);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(new CardLayout(0, 0));
		
		JPanel panel_bienvenida = new JPanel();
		panelPrincipal.add(panel_bienvenida, "name_2575985823000300");
		panel_bienvenida.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 71));
		lblNewLabel.setBounds(248, 0, 584, 534);
		panel_bienvenida.add(lblNewLabel);
		
		final JPanel panel_añadir = new JPanel();
		panelPrincipal.add(panel_añadir, "name_2462234680338100");
		panel_añadir.setLayout(new CardLayout(0, 0));
		final JPanel panel_añadir_atleta = new JPanel();
		panel_añadir.add(panel_añadir_atleta, "name_2462254774735800");
		panel_añadir_atleta.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(430, 51, 56, 14);
		panel_añadir_atleta.add(lblNombre);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(430, 95, 35, 14);
		panel_añadir_atleta.add(lblId);
		
		JLabel lblEdad = new JLabel("Edad :");
		lblEdad.setBounds(430, 294, 41, 14);
		panel_añadir_atleta.add(lblEdad);
		
		JLabel lblPais = new JLabel("Pa\u00EDs :");
		lblPais.setBounds(430, 137, 41, 14);
		panel_añadir_atleta.add(lblPais);
		
		JLabel lblCategoriaDeportiva = new JLabel("Categor\u00EDa deportiva :");
		lblCategoriaDeportiva.setBounds(430, 182, 109, 14);
		panel_añadir_atleta.add(lblCategoriaDeportiva);
		
		JLabel lblIdEntrenador = new JLabel("ID Entrenador :");
		lblIdEntrenador.setBounds(430, 233, 89, 14);
		panel_añadir_atleta.add(lblIdEntrenador);
		
		textField_id_Entrenador_Atleta = new JTextField();
		textField_id_Entrenador_Atleta.setColumns(10);
		textField_id_Entrenador_Atleta.setBounds(529, 230, 124, 20);
		panel_añadir_atleta.add(textField_id_Entrenador_Atleta);
		
		JLabel lblSexo = new JLabel("Sexo :");
		lblSexo.setBounds(430, 351, 41, 14);
		panel_añadir_atleta.add(lblSexo);
		
		final JRadioButton rdbtn_masculinoAtleta = new JRadioButton("Masculino");
		rdbtn_masculinoAtleta.setBounds(490, 347, 89, 23);
		panel_añadir_atleta.add(rdbtn_masculinoAtleta);
		
		final JRadioButton rdbtn_femeninoAtleta = new JRadioButton("Femenino");
		rdbtn_femeninoAtleta.setBounds(581, 347, 89, 23);
		panel_añadir_atleta.add(rdbtn_femeninoAtleta);
		sexo.add(rdbtn_femeninoAtleta);
		sexo.add(rdbtn_masculinoAtleta);
		
		
		textField_nombreAtleta = new JTextField();
		textField_nombreAtleta.setBounds(496, 48, 163, 20);
		panel_añadir_atleta.add(textField_nombreAtleta);
		textField_nombreAtleta.setColumns(10);
		
		final JTextField textField_idAtleta = new JTextField();
		textField_idAtleta.setColumns(10);
		textField_idAtleta.setBounds(475, 95, 86, 20);
		panel_añadir_atleta.add(textField_idAtleta);
		
		textField_paisAtleta = new JTextField();
		textField_paisAtleta.setColumns(10);
		textField_paisAtleta.setBounds(481, 134, 86, 20);
		panel_añadir_atleta.add(textField_paisAtleta);
		
		final JSpinner spinner_edadAtleta = new JSpinner();
		spinner_edadAtleta.setModel(new SpinnerNumberModel(new Integer(18), new Integer(18), null, new Integer(1)));
		spinner_edadAtleta.setBounds(475, 291, 41, 20);
		panel_añadir_atleta.add(spinner_edadAtleta);
		
		textField_catDeportAtleta = new JTextField();
		textField_catDeportAtleta.setColumns(10);
		textField_catDeportAtleta.setBounds(549, 179, 124, 20);
		panel_añadir_atleta.add(textField_catDeportAtleta);
		
		final JButton btnAñadir_atleta = new JButton("A\u00F1adir");
		btnAñadir_atleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char sexo = rdbtn_masculinoAtleta.isSelected()? 'M' : 'F';
				boolean error = false;
				Atleta nuevoAtleta = new Atleta(textField_idAtleta.getText(),textField_nombreAtleta.getText(),sexo , textField_catDeportAtleta.getText(),textField_id_Entrenador_Atleta.getText(),(Integer)spinner_edadAtleta.getValue(), textField_paisAtleta.getText());
				try {
				    if(modificando) {
				    	servicioAtleta.modificarAtleta(viejoAtleta.getIdAtleta(), nuevoAtleta);
				    }else
					servicioAtleta.insertarAtleta(nuevoAtleta);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
							atletasTableModel = new TableModelAtletas(servicioAtleta.obtenerTodosAtletas());
							table_mostrar.setModel(atletasTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				String accion = modificando? "modificado":"insertado" ;
			    JOptionPane.showMessageDialog(panel_añadir_atleta, "Se ha " + accion + " un atleta");
			    btnAñadir_atleta.setText("Añadir");
			    textField_idAtleta.setText(null);
			    textField_catDeportAtleta.setText(null);
			    textField_id_Entrenador_Atleta.setText(null);
			    textField_nombreAtleta.setText(null);
			    textField_paisAtleta.setText(null);
			    spinner_edadAtleta.setValue(18);
			    rdbtn_masculinoAtleta.setSelected(true);
		
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					panelPrincipal.repaint();
					panelPrincipal.revalidate();
					 modificando = false;
					 atras1 = null;
                     viejoAtleta = null;
				}else
				panelPrincipal.removeAll();
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}else
				JOptionPane.showMessageDialog(panel_añadir_atleta, "No se ha insertado el atleta, revise sus entradas");
			}
			
		});
		
		btnAñadir_atleta.setBounds(490, 466, 100, 40);
		panel_añadir_atleta.add(btnAñadir_atleta);
		
		final JPanel panel_añadir_ciudad = new JPanel();
		panel_añadir.add(panel_añadir_ciudad, "name_2462328150571300");
		panel_añadir_ciudad.setLayout(null);
		
		JLabel lblPais_1 = new JLabel("Pais :");
		lblPais_1.setBounds(433, 159, 46, 14);
		panel_añadir_ciudad.add(lblPais_1);
		
		textField_nom_pais_2 = new JTextField();
		textField_nom_pais_2.setBounds(489, 156, 103, 20);
		panel_añadir_ciudad.add(textField_nom_pais_2);
		textField_nom_pais_2.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad :");
		lblCiudad.setBounds(433, 194, 46, 14);
		panel_añadir_ciudad.add(lblCiudad);
		
		textField_nom_ciudad_1 = new JTextField();
		textField_nom_ciudad_1.setColumns(10);
		textField_nom_ciudad_1.setBounds(489, 191, 103, 20);
		panel_añadir_ciudad.add(textField_nom_ciudad_1);
		
		final JButton btnAñadir_ciudad = new JButton("Añadir");
		btnAñadir_ciudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String accion = modificando? "modificado":"insertado" ;
                Ciudad nuevaCiudad = new Ciudad(textField_nom_pais_2.getText(), textField_nom_ciudad_1.getText());
				try {
				    if(modificando) {
                    servicioCiudad.modificarCiudad(viejaCiudad.getNomCiudad(), nuevaCiudad);
				    }else
                    servicioCiudad.insertarCiudad(nuevaCiudad);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
                            ciudadTableModel = new TableModelCiudad(servicioCiudad.obtenerCiudades());
							table_mostrar.setModel(ciudadTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
			    JOptionPane.showMessageDialog(panel_añadir_ciudad, "Se ha " + accion + " una ciudad");
			    btnAñadir_ciudad.setText("Añadir");
			    textField_nom_ciudad_1.setText(null);
			    textField_nom_pais_2.setText(null);
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					 panelPrincipal.repaint();
                     panelPrincipal.revalidate();
                     
				}else{
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}
				atras1 = null;
 				viejaCiudad = null;
 				 modificando = false;
				}else
				JOptionPane.showMessageDialog(panel_añadir_ciudad, "No se ha " + accion + " la ciudad, revise sus entradas");
			
			}
		});
		btnAñadir_ciudad.setBounds(489, 307, 100, 40);
		panel_añadir_ciudad.add(btnAñadir_ciudad);
		
		final JPanel panel_añadir_pais = new JPanel();
		panel_añadir.add(panel_añadir_pais, "name_2471143836942300");
		panel_añadir_pais.setLayout(null);
		
		JLabel label = new JLabel("Pais :");
		label.setBounds(438, 178, 46, 14);
		panel_añadir_pais.add(label);
		
		textField_nom_pais_1 = new JTextField();
		textField_nom_pais_1.setColumns(10);
		textField_nom_pais_1.setBounds(494, 175, 103, 20);
		panel_añadir_pais.add(textField_nom_pais_1);
		
		final JButton btnAñadir_pais = new JButton("Añadir");
		btnAñadir_pais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String accion = modificando? "modificado":"insertado" ;
                Pais nuevoPais = new Pais(textField_nom_pais_1.getText());
				try {
				    if(modificando) {
                    servicioPais.modificarPais(viejoPais.getNomPais(), nuevoPais);
				    }else
                    servicioPais.insertarPais(nuevoPais);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
                            paisTableModel = new TableModelPais(servicioPais.obtenerPaises());
							table_mostrar.setModel(paisTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
			    JOptionPane.showMessageDialog(panel_añadir_pais, "Se ha " + accion + " un pais");
			    btnAñadir_pais.setText("Añadir");
			    textField_nom_pais_1.setText(null);
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					 panelPrincipal.repaint();
                     panelPrincipal.revalidate();
                     
				}else{
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}
				atras1 = null;
 				viejoPais= null;
 				modificando = false;
				}else
				JOptionPane.showMessageDialog(panel_añadir_pais, "No se ha " + accion + " el pais, revise sus entradas");
			
			
			}
		});
		btnAñadir_pais.setBounds(480, 288, 100, 40);
		panel_añadir_pais.add(btnAñadir_pais);
		
		final JPanel panel_añadir_disciplina = new JPanel();
		panel_añadir.add(panel_añadir_disciplina, "name_2473488136249400");
		panel_añadir_disciplina.setLayout(null);
		
		JLabel lblDisciplina = new JLabel("Disciplina :");
		lblDisciplina.setBounds(398, 181, 71, 14);
		panel_añadir_disciplina.add(lblDisciplina);
		
		textField_disciplina_1 = new JTextField();
		textField_disciplina_1.setColumns(10);
		textField_disciplina_1.setBounds(479, 178, 189, 20);
		panel_añadir_disciplina.add(textField_disciplina_1);
		
		final JButton btnAñadir_disciplina = new JButton("Añadir");
		btnAñadir_disciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String accion = modificando? "modificado":"insertado" ;
                Disciplina nuevaDisciplina = new Disciplina(textField_disciplina_1.getText());
				try {
				    if(modificando) {
                    servicioDisciplina.modificarDisciplina(viejaDisciplina.getNomDisciplina(), nuevaDisciplina);
				    }else
                    servicioDisciplina.insertarDisciplina(nuevaDisciplina);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
                            disciplinaTableModel = new TableModelDisciplina(servicioDisciplina.obtenerTodasDisciplinas());
							table_mostrar.setModel(disciplinaTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
			    JOptionPane.showMessageDialog(panel_añadir_disciplina, "Se ha " + accion + " una disciplina");
			    btnAñadir_disciplina.setText("Añadir");
			    textField_disciplina_1.setText(null);
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					 panelPrincipal.repaint();
                     panelPrincipal.revalidate();
                     
				}else{
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}
				atras1 = null;
 				viejaDisciplina= null;
 				modificando = false;
				}else
				JOptionPane.showMessageDialog(panel_añadir_disciplina, "No se ha " + accion + " la disciplina, revise sus entradas");
			
			}
		});
		btnAñadir_disciplina.setBounds(506, 291, 100, 40);
		panel_añadir_disciplina.add(btnAñadir_disciplina);
		
		final JPanel panel_añadir_entrenador = new JPanel();
		panel_añadir.add(panel_añadir_entrenador, "name_2475468247530400");
		panel_añadir_entrenador.setLayout(null);
		
		JLabel lblNombre_1 = new JLabel("Nombre :");
		lblNombre_1.setBounds(429, 93, 71, 14);
		panel_añadir_entrenador.add(lblNombre_1);
		
		textField_nombre_entrenador = new JTextField();
		textField_nombre_entrenador.setColumns(10);
		textField_nombre_entrenador.setBounds(510, 90, 189, 20);
		panel_añadir_entrenador.add(textField_nombre_entrenador);
		
		JLabel lblId_1 = new JLabel("ID :");
		lblId_1.setBounds(429, 136, 48, 14);
		panel_añadir_entrenador.add(lblId_1);
		
		textField_id_entrenador = new JTextField();
		textField_id_entrenador.setColumns(10);
		textField_id_entrenador.setBounds(487, 133, 189, 20);
		panel_añadir_entrenador.add(textField_id_entrenador);
		
		JLabel lblEspecialidad = new JLabel("Especialidad :");
		lblEspecialidad.setBounds(429, 180, 86, 14);
		panel_añadir_entrenador.add(lblEspecialidad);
		
		textField_especialidad_entrenador = new JTextField();
		textField_especialidad_entrenador.setColumns(10);
		textField_especialidad_entrenador.setBounds(525, 177, 189, 20);
		panel_añadir_entrenador.add(textField_especialidad_entrenador);
		
		JLabel lblDireccion = new JLabel("Direccion :");
		lblDireccion.setBounds(429, 223, 71, 14);
		panel_añadir_entrenador.add(lblDireccion);
		
		textField_direccion_entrenador = new JTextField();
		textField_direccion_entrenador.setColumns(10);
		textField_direccion_entrenador.setBounds(510, 220, 189, 20);
		panel_añadir_entrenador.add(textField_direccion_entrenador);
		
		final JButton btnAñadir_entrenador = new JButton("Añadir");
		btnAñadir_entrenador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String accion = modificando? "modificado":"insertado" ;
				try {
	                Entrenador nuevoEntrenador = new Entrenador(textField_id_entrenador.getText(),textField_nombre_entrenador.getText(),textField_direccion_entrenador.getText(),textField_especialidad_entrenador.getText());
				    if(modificando) {
                    servicioEntrenador.modificarEntrenador(viejoEntrenador.getIdEntrenador(), nuevoEntrenador);
				    }else
                    servicioEntrenador.insertarEntrenador(nuevoEntrenador);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
                            entrenadorTableModel = new TableModelEntrenador(servicioEntrenador.obtenerEntrenadores());
							table_mostrar.setModel(entrenadorTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
					 JOptionPane.showMessageDialog(panel_añadir_entrenador, "Se ha " + accion + " un entrenador");
					    btnAñadir_entrenador.setText("Añadir");
			    textField_id_entrenador.setText(null);
			    textField_direccion_entrenador.setText(null);
			    textField_nombre_entrenador.setText(null);
			    textField_especialidad_entrenador.setText(null);
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					 panelPrincipal.repaint();
                     panelPrincipal.revalidate();
                     
				}else{
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}
				atras1 = null;
 				viejaDisciplina= null;
 				modificando = false;
				}else
				JOptionPane.showMessageDialog(panel_añadir_entrenador, "No se ha " + accion + " el entrenador, revise sus entradas");
			
			}
		});
		btnAñadir_entrenador.setBounds(510, 342, 100, 40);
		panel_añadir_entrenador.add(btnAñadir_entrenador);
		
		final JPanel panel_añadir_sede = new JPanel();
		panel_añadir.add(panel_añadir_sede, "name_2479319145738600");
		panel_añadir_sede.setLayout(null);
		
		textField_sede_1 = new JTextField();
		textField_sede_1.setColumns(10);
		textField_sede_1.setBounds(464, 140, 189, 20);
		panel_añadir_sede.add(textField_sede_1);
		
		JLabel lblSede = new JLabel("Sede :");
		lblSede.setBounds(400, 143, 54, 14);
		panel_añadir_sede.add(lblSede);
		
		JLabel lblCiudad_1 = new JLabel("Ciudad :");
		lblCiudad_1.setBounds(400, 186, 59, 14);
		panel_añadir_sede.add(lblCiudad_1);
		
		textField_nom_ciudad_2 = new JTextField();
		textField_nom_ciudad_2.setColumns(10);
		textField_nom_ciudad_2.setBounds(469, 183, 189, 20);
		panel_añadir_sede.add(textField_nom_ciudad_2);
		
		final JButton btnAñadir_sede = new JButton("Añadir");
		btnAñadir_sede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String accion = modificando? "modificado":"insertado" ;
				try {
	                Sede nuevaSede = new Sede(textField_sede_1.getText(),textField_nom_ciudad_2.getText());
				    if(modificando) {
                    servicioSede .modificarSede(viejaSede.getNomSede(), nuevaSede);
				    }else
                    servicioSede.insertarSede(nuevaSede);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
                            sedeTableModel = new TableModelSede(servicioSede.obtenerTodasSedes());
							table_mostrar.setModel(sedeTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
					 JOptionPane.showMessageDialog(panel_añadir_sede, "Se ha " + accion + " una sede");
					    btnAñadir_sede.setText("Añadir");
			   textField_sede_1.setText(null);
			   textField_nom_ciudad_2.setText(null);
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					 panelPrincipal.repaint();
                     panelPrincipal.revalidate();
                     
				}else{
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}
				atras1 = null;
 				viejaSede= null;
 				modificando = false;
				}else
				JOptionPane.showMessageDialog(panel_añadir_sede, "No se ha " + accion + " la sede, revise sus entradas");
			}
		});
		btnAñadir_sede.setBounds(485, 282, 100, 40);
		panel_añadir_sede.add(btnAñadir_sede);
		
		final JPanel panel_añadir_competencia = new JPanel();
		panel_añadir.add(panel_añadir_competencia, "name_2482084492350800");
		panel_añadir_competencia.setLayout(null);
		
		textField_nom_competencia = new JTextField();
		textField_nom_competencia.setColumns(10);
		textField_nom_competencia.setBounds(476, 100, 189, 20);
		panel_añadir_competencia.add(textField_nom_competencia);
		
		JLabel lblNombre_2 = new JLabel("Nombre :");
		lblNombre_2.setBounds(412, 103, 54, 14);
		panel_añadir_competencia.add(lblNombre_2);
		
		JLabel lblSede_1 = new JLabel("Sede :");
		lblSede_1.setBounds(412, 146, 42, 14);
		panel_añadir_competencia.add(lblSede_1);
		
		textField_sede_2 = new JTextField();
		textField_sede_2.setColumns(10);
		textField_sede_2.setBounds(464, 143, 189, 20);
		panel_añadir_competencia.add(textField_sede_2);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio :");
		lblFechaInicio.setBounds(412, 195, 82, 14);
		panel_añadir_competencia.add(lblFechaInicio);
		
		textField_fecha_ini_competencia = new JTextField();
		textField_fecha_ini_competencia.setColumns(10);
		textField_fecha_ini_competencia.setBounds(497, 192, 120, 20);
		panel_añadir_competencia.add(textField_fecha_ini_competencia);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin :");
		lblFechaFin.setBounds(412, 238, 82, 14);
		panel_añadir_competencia.add(lblFechaFin);
		
		textField_fecha_fin_competencia = new JTextField();
		textField_fecha_fin_competencia.setColumns(10);
		textField_fecha_fin_competencia.setBounds(497, 235, 120, 20);
		panel_añadir_competencia.add(textField_fecha_fin_competencia);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setBounds(412, 290, 64, 14);
		panel_añadir_competencia.add(lblEstado);
		
		textField_estado_competencia = new JTextField();
		textField_estado_competencia.setColumns(10);
		textField_estado_competencia.setBounds(476, 287, 120, 20);
		panel_añadir_competencia.add(textField_estado_competencia);
		
		final JButton btnAñadir_competencia = new JButton("Añadir");
		btnAñadir_competencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String accion = modificando? "modificado":"insertado" ;
				try {
	                Competencia nuevaCompetencia = new Competencia(textField_nom_competencia.getText(),textField_sede_2.getText(),textField_estado_competencia.getText(),textField_fecha_ini_competencia.getText(),textField_fecha_fin_competencia.getText());
				    if(modificando) {
                    servicioCompetencia.modificarCompetencia(viejaCompetencia.getNomCompetencia(), nuevaCompetencia);
				    }else
                    servicioCompetencia.insertarCompetencia(nuevaCompetencia);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
                            competenciaTableModel = new TableModelCompetencia(servicioCompetencia.obtenerTodasCompetencias());
							table_mostrar.setModel(competenciaTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
					 JOptionPane.showMessageDialog(panel_añadir_sede, "Se ha " + accion + " una competencia");
					    btnAñadir_competencia.setText("Añadir");
			   textField_sede_2.setText(null);
			   textField_nom_competencia.setText(null);
			   textField_fecha_fin_competencia.setText(null);
			   textField_fecha_ini_competencia.setText(null);
			   textField_estado_competencia.setText(null);
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					 panelPrincipal.repaint();
                     panelPrincipal.revalidate();
                     
				}else{
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}
				atras1 = null;
 				viejaCompetencia= null;
 				modificando = false;
				}else
				JOptionPane.showMessageDialog(panel_añadir_sede, "No se ha " + accion + " la competencia, revise sus entradas");
			
			}
		});
		btnAñadir_competencia.setBounds(476, 377, 100, 40);
		panel_añadir_competencia.add(btnAñadir_competencia);
		
		JLabel lblUseaaaammdd = new JLabel("USE (AAAA-MM-DD) !!!!!!!");
		lblUseaaaammdd.setBounds(638, 238, 178, 14);
		panel_añadir_competencia.add(lblUseaaaammdd);
		
		JLabel label_1 = new JLabel("USE (AAAA-MM-DD) !!!!!!!");
		label_1.setBounds(638, 195, 178, 14);
		panel_añadir_competencia.add(label_1);
		
		final JPanel panel_añadir_inscripcion = new JPanel();
		panel_añadir.add(panel_añadir_inscripcion, "name_2499070192823400");
		panel_añadir_inscripcion.setLayout(null);
		
		textField_nom_competencia_2 = new JTextField();
		textField_nom_competencia_2.setColumns(10);
		textField_nom_competencia_2.setBounds(531, 117, 189, 20);
		panel_añadir_inscripcion.add(textField_nom_competencia_2);
		
		JLabel lblCompetencia = new JLabel("Competencia :");
		lblCompetencia.setBounds(432, 120, 89, 14);
		panel_añadir_inscripcion.add(lblCompetencia);
		
		JLabel lblIdDelAtleta = new JLabel("ID del Atleta :");
		lblIdDelAtleta.setBounds(432, 163, 89, 14);
		panel_añadir_inscripcion.add(lblIdDelAtleta);
		
		textField_id_atleta_2 = new JTextField();
		textField_id_atleta_2.setColumns(10);
		textField_id_atleta_2.setBounds(517, 160, 189, 20);
		panel_añadir_inscripcion.add(textField_id_atleta_2);
		
		JLabel lblIdDelEntrenador = new JLabel("ID del Entrenador :");
		lblIdDelEntrenador.setBounds(432, 206, 120, 14);
		panel_añadir_inscripcion.add(lblIdDelEntrenador);
		
		textField_id_entrenador_2 = new JTextField();
		textField_id_entrenador_2.setColumns(10);
		textField_id_entrenador_2.setBounds(543, 203, 189, 20);
		panel_añadir_inscripcion.add(textField_id_entrenador_2);
		
		JLabel label_2 = new JLabel("Fecha Inicio :");
		label_2.setBounds(432, 259, 82, 14);
		panel_añadir_inscripcion.add(label_2);
		
		JLabel label_3 = new JLabel("Fecha Fin :");
		label_3.setBounds(432, 302, 82, 14);
		panel_añadir_inscripcion.add(label_3);
		
		textField_fecha_fin_inscripcion = new JTextField();
		textField_fecha_fin_inscripcion.setColumns(10);
		textField_fecha_fin_inscripcion.setBounds(517, 299, 120, 20);
		panel_añadir_inscripcion.add(textField_fecha_fin_inscripcion);
		
		textField_fecha_ini_inscripcion = new JTextField();
		textField_fecha_ini_inscripcion.setColumns(10);
		textField_fecha_ini_inscripcion.setBounds(517, 256, 120, 20);
		panel_añadir_inscripcion.add(textField_fecha_ini_inscripcion);
		
		JLabel label_4 = new JLabel("USE (AAAA-MM-DD) !!!!!!!");
		label_4.setBounds(658, 259, 178, 14);
		panel_añadir_inscripcion.add(label_4);
		
		JLabel label_5 = new JLabel("USE (AAAA-MM-DD) !!!!!!!");
		label_5.setBounds(658, 302, 178, 14);
		panel_añadir_inscripcion.add(label_5);
		
		final JButton btnAñadir_inscripcion = new JButton("Añadir");
		btnAñadir_inscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String accion = modificando? "modificado":"insertado" ;
				try {
	                Inscripcion nuevaInscripcion = new Inscripcion(textField_nom_competencia_2.getText(),textField_id_atleta_2.getText(),textField_fecha_ini_inscripcion.getText(),textField_fecha_fin_inscripcion.getText(),textField_id_entrenador_2.getText());
				    if(modificando) {
                    servicioInscripcion.modificarInscripcion(viejaInscripcion, nuevaInscripcion);
				    }else
                    servicioInscripcion.insertarInscripcion(nuevaInscripcion);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
                            inscripcionTableModel = new TableModelInscripcion(servicioInscripcion.obtenerInscripciones());
							table_mostrar.setModel(inscripcionTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
					
					JOptionPane.showMessageDialog(panel_añadir_inscripcion, "Se ha " + accion + " una inscripcion");
			   btnAñadir_inscripcion.setText("Añadir");     
			   textField_id_atleta_2.setText(null);
			   textField_nom_competencia_2.setText(null);
			   textField_fecha_fin_inscripcion.setText(null);
			   textField_fecha_ini_inscripcion.setText(null);
			   textField_id_entrenador_2.setText(null);
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					 panelPrincipal.repaint();
                     panelPrincipal.revalidate();
                     
				}else{
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}
				atras1 = null;
 				viejaInscripcion= null;
 				modificando = false;
				}else
				JOptionPane.showMessageDialog(panel_añadir_sede, "No se ha " + accion + " la inscripcion, revise sus entradas");
			
			}
		});
		btnAñadir_inscripcion.setBounds(531, 372, 100, 40);
		panel_añadir_inscripcion.add(btnAñadir_inscripcion);
		
		final JPanel panel_añadir_registro = new JPanel();
		panel_añadir.add(panel_añadir_registro, "name_2511536960950200");
		panel_añadir_registro.setLayout(null);
		
		textField_nom_competencia_3 = new JTextField();
		textField_nom_competencia_3.setColumns(10);
		textField_nom_competencia_3.setBounds(465, 92, 189, 20);
		panel_añadir_registro.add(textField_nom_competencia_3);
		
		JLabel label_6 = new JLabel("Competencia :");
		label_6.setBounds(366, 95, 89, 14);
		panel_añadir_registro.add(label_6);
		
		JLabel label_7 = new JLabel("ID del Atleta :");
		label_7.setBounds(366, 138, 89, 14);
		panel_añadir_registro.add(label_7);
		
		textField_id_atleta_3 = new JTextField();
		textField_id_atleta_3.setColumns(10);
		textField_id_atleta_3.setBounds(465, 135, 189, 20);
		panel_añadir_registro.add(textField_id_atleta_3);
		
		JLabel lblMarcas = new JLabel("Marcas :");
		lblMarcas.setBounds(366, 221, 62, 14);
		panel_añadir_registro.add(lblMarcas);
		
		textField_marcas = new JTextField();
		textField_marcas.setColumns(10);
		textField_marcas.setBounds(450, 218, 189, 20);
		panel_añadir_registro.add(textField_marcas);
		
		JLabel lblLugar = new JLabel("Lugar :");
		lblLugar.setBounds(366, 260, 51, 14);
		panel_añadir_registro.add(lblLugar);
		
		final JSpinner spinner_lugar = new JSpinner();
		spinner_lugar.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner_lugar.setBounds(427, 257, 45, 20);
		panel_añadir_registro.add(spinner_lugar);
		
		JLabel lblDisciplina_1 = new JLabel("Disciplina :");
		lblDisciplina_1.setBounds(366, 183, 62, 14);
		panel_añadir_registro.add(lblDisciplina_1);
		
		textField_nom_disciplina_2 = new JTextField();
		textField_nom_disciplina_2.setColumns(10);
		textField_nom_disciplina_2.setBounds(450, 180, 189, 20);
		panel_añadir_registro.add(textField_nom_disciplina_2);
		
		final JButton btnAñadir_registro = new JButton("Añadir");
		btnAñadir_registro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String accion = modificando? "modificado":"insertado" ;
				try {
	                Registro nuevoRegistro = new Registro(textField_nom_disciplina_2.getText(),textField_marcas.getText(),textField_id_atleta_3.getText(),textField_nom_competencia_3.getText(),(Integer)spinner_lugar.getValue());
				    if(modificando) {
                    servicioRegistro.modificarRegistro(viejoRegistro, nuevoRegistro);
				    }else
                    servicioRegistro.insertarRegistro(nuevoRegistro);
				} catch (IllegalArgumentException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					error = true;
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!error) {
					if(modificando) {
						try {
                            registroTableModel = new TableModelRegistro(servicioRegistro.obtenerRegistros());
							table_mostrar.setModel(registroTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
					
					JOptionPane.showMessageDialog(panel_añadir_inscripcion, "Se ha " + accion + " un registro");
			   btnAñadir_registro.setText("Añadir");     
			   textField_id_atleta_3.setText(null);
			   textField_nom_competencia_3.setText(null);
			   textField_marcas.setText(null);
			   textField_nom_disciplina_2.setText(null);
			   spinner_lugar.setValue(1);
				if(modificando) {
					panelPrincipal.removeAll();
					panelPrincipal.add(atras1);
					 panelPrincipal.repaint();
                     panelPrincipal.revalidate();
                     
				}else{
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
				}
				atras1 = null;
 				viejoRegistro = null;
 				modificando = false;
				}else
				JOptionPane.showMessageDialog(panel_añadir_registro, "No se ha " + accion + " el registro, revise sus entradas");
			
			}
		});
		btnAñadir_registro.setBounds(505, 306, 100, 40);
		panel_añadir_registro.add(btnAñadir_registro);
		
		final JPanel panel_mostrar = new JPanel();
		panelPrincipal.add(panel_mostrar, "name_2350809799020200");
		panel_mostrar.setLayout(null);
		
		JScrollPane scrollPane_mostrar = new JScrollPane();
		scrollPane_mostrar.setBounds(10, 50, 1064, 570);
		panel_mostrar.add(scrollPane_mostrar);
		
		table_mostrar = new JTable();
		scrollPane_mostrar.setViewportView(table_mostrar);
		
		final JToolBar toolBar_modificacion = new JToolBar();
		toolBar_modificacion.setFloatable(false);
		toolBar_modificacion.setBounds(10, 11, 161, 39);
		panel_mostrar.add(toolBar_modificacion);
		
		this.btnEliminarAtleta = new JButton("Eliminar");
		toolBar_modificacion.add(btnEliminarAtleta);
		btnEliminarAtleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_mostrar.getSelectedRow() != -1) {
				if(JOptionPane.showConfirmDialog(panel_mostrar, "Confirmar?","Confirme",JOptionPane.YES_NO_OPTION) == 0) {
				switch(entidad){
				case 1 :
					try {
						servicioAtleta.eliminarAtleta((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),0));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					atletasTableModel = new TableModelAtletas(servicioAtleta.obtenerTodosAtletas());
					table_mostrar.setModel(atletasTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				case 2:
					try {
						servicioCiudad.eliminarCiudad((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),0));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					ciudadTableModel = new TableModelCiudad(servicioCiudad.obtenerCiudades());
					table_mostrar.setModel(ciudadTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				case 3:
					try {
						servicioPais.eliminarPais((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),0));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					paisTableModel = new TableModelPais(servicioPais.obtenerPaises());
					table_mostrar.setModel(paisTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				case 4:
					try {
						servicioDisciplina.eliminarDisciplina((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),0));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					disciplinaTableModel = new TableModelDisciplina(servicioDisciplina.obtenerTodasDisciplinas());
					table_mostrar.setModel(disciplinaTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				case 5:
					try {
						servicioEntrenador.eliminarEntrenador((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),1));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					entrenadorTableModel = new TableModelEntrenador(servicioEntrenador.obtenerEntrenadores());
					table_mostrar.setModel(entrenadorTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				case 6:
					try {
						servicioSede.eliminarSede((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),0));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					sedeTableModel = new TableModelSede(servicioSede.obtenerTodasSedes());
					table_mostrar.setModel(sedeTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				case 7:
					try {
						servicioCompetencia.eliminarCompetencia((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),0));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					competenciaTableModel = new TableModelCompetencia(servicioCompetencia.obtenerTodasCompetencias());
					table_mostrar.setModel(competenciaTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				case 8:
					try {
						servicioInscripcion.eliminarInscripcion((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),0),(String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),1));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					inscripcionTableModel = new TableModelInscripcion(servicioInscripcion.obtenerInscripciones());
					table_mostrar.setModel(inscripcionTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				case 9 :
					try {
						servicioRegistro.eliminarRegistro((String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),0),(String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),4),(String)table_mostrar.getValueAt(table_mostrar.getSelectedRow(),1));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               try {
					registroTableModel = new TableModelRegistro(servicioRegistro.obtenerRegistros());
					table_mostrar.setModel(registroTableModel);
					panel_mostrar.repaint();
					panel_mostrar.revalidate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               break;
				}
               
			}}else
				JOptionPane.showMessageDialog(panel_mostrar, "Seleccione una entidad.");
				}
		});
		
		this.btn_modificar_atleta = new JButton("Modificar");
		btn_modificar_atleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_mostrar.getSelectedRow() != -1) {
			   switch(entidad){
			   case 1:
				   try {
						ArrayList<Atleta> atletas = servicioAtleta.obtenerTodosAtletas();
						for(Atleta a : atletas) {
							   if(a.getIdAtleta().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),0))) {
								   viejoAtleta= a;
							   }
						    }
						btnAñadir_atleta.setText("Modificar");
						modificando = true;
						
						atras1 = panel_mostrar;
					
						if(Character.toString(viejoAtleta.getSexo()).equals("M")) {
							rdbtn_masculinoAtleta.setSelected(true);
						}else
							rdbtn_femeninoAtleta.setSelected(true);
						panelPrincipal.removeAll();
						panelPrincipal.add(panel_añadir_atleta);
						panelPrincipal.repaint();
						panelPrincipal.revalidate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   break;
			   case 2:
				   try {
					ArrayList<Ciudad> ciudades = servicioCiudad.obtenerCiudades();
					for(Ciudad c : ciudades) {
						   if(c.getNomCiudad().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),0))) {
							   viejaCiudad = c;
						   }}
						   btnAñadir_ciudad.setText("Modificar");
						   modificando = true;
						   textField_nom_ciudad_1.setText(viejaCiudad.getNomCiudad());
						   textField_nom_pais_2.setText(viejaCiudad.getNomPais());
						   panelPrincipal.removeAll();
							panelPrincipal.add(panel_añadir_ciudad);
							panelPrincipal.repaint();
							panelPrincipal.revalidate();
							atras1 = panel_mostrar;
					    
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   break;
			   case 3:
				   try {
						ArrayList<Pais> paises = servicioPais.obtenerPaises();
						for(Pais p : paises) {
							   if(p.getNomPais().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),0))) {
								   viejoPais = p;
							   }}
							   btnAñadir_pais.setText("Modificar");
							   modificando = true;
							   textField_nom_pais_1.setText(viejoPais.getNomPais());
							   panelPrincipal.removeAll();
								panelPrincipal.add(panel_añadir_pais);
								panelPrincipal.repaint();
								panelPrincipal.revalidate();
								atras1 = panel_mostrar;
						    
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   break;
			   case 4:
				   try {
						ArrayList<Disciplina> disciplinas = servicioDisciplina.obtenerTodasDisciplinas();
						for(Disciplina d : disciplinas) {
							   if(d.getNomDisciplina().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),0))) {
								   viejaDisciplina = d;
							   }}
							   btnAñadir_disciplina.setText("Modificar");
							   modificando = true;
							   textField_disciplina_1.setText(viejaDisciplina.getNomDisciplina());
							   panelPrincipal.removeAll();
								panelPrincipal.add(panel_añadir_disciplina);
								panelPrincipal.repaint();
								panelPrincipal.revalidate();
								atras1 = panel_mostrar;
						    
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   break;
			   case 5:
				   try {
						ArrayList<Entrenador> entrenadores = servicioEntrenador.obtenerEntrenadores();
						for(Entrenador en : entrenadores) {
							   if(en.getIdEntrenador().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),1))) {
								   viejoEntrenador = en;
							   }}
							    btnAñadir_entrenador.setText("Modificar");
							    modificando = true;
							    textField_id_entrenador.setText(viejoEntrenador.getIdEntrenador());
							    textField_direccion_entrenador.setText(viejoEntrenador.getDireccion());
							    textField_nombre_entrenador.setText(viejoEntrenador.getNombre());
							    textField_especialidad_entrenador.setText(viejoEntrenador.getEsp());
							    panelPrincipal.removeAll();
								panelPrincipal.add(panel_añadir_entrenador);
								panelPrincipal.repaint();
								panelPrincipal.revalidate();
								atras1 = panel_mostrar;
						    
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				   break;
			   case 6 :
				   try {
						ArrayList<Sede> sedes = servicioSede.obtenerTodasSedes();
						for(Sede s : sedes) {
							   if(s.getNomSede().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),0))) {
								   viejaSede = s;
							   }}
							    btnAñadir_sede.setText("Modificar");
							    modificando = true;
							   textField_sede_1.setText(viejaSede.getNomSede());
							   textField_nom_ciudad_2.setText(viejaSede.getNomCiudad());
							    panelPrincipal.removeAll();
								panelPrincipal.add(panel_añadir_sede);
								panelPrincipal.repaint();
								panelPrincipal.revalidate();
								atras1 = panel_mostrar;
						    
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   break;
			   case 7:
				   try {
						ArrayList<Competencia> competencias = servicioCompetencia.obtenerTodasCompetencias();
						for(Competencia c : competencias) {
							   if(c.getNomCompetencia().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),0))) {
								   viejaCompetencia = c;
							   }}
							    btnAñadir_competencia.setText("Modificar");
							    modificando = true;
							    textField_sede_2.setText(viejaCompetencia.getNomSede());
								   textField_nom_competencia.setText(viejaCompetencia.getNomCompetencia());
								   textField_fecha_fin_competencia.setText(viejaCompetencia.getFechaFin().toString());
								   textField_fecha_ini_competencia.setText(viejaCompetencia.getFechaIni().toString());
								   textField_estado_competencia.setText(viejaCompetencia.getEstado());
							    panelPrincipal.removeAll();
								panelPrincipal.add(panel_añadir_competencia);
								panelPrincipal.repaint();
								panelPrincipal.revalidate();
								atras1 = panel_mostrar;
						    
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				   break;
			   case 8:
				   try {
						ArrayList<Inscripcion> inscripciones = servicioInscripcion.obtenerInscripciones();
						for(Inscripcion i : inscripciones) {
							   if(i.getIdAtleta().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),0)) && i.getNomCompetencia().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),1))  ) {
								   viejaInscripcion = i;
							   }}
							    btnAñadir_inscripcion.setText("Modificar");
							    modificando = true;
							    textField_id_atleta_2.setText(viejaInscripcion.getIdAtleta());
								   textField_nom_competencia_2.setText(viejaInscripcion.getNomCompetencia());
								   textField_fecha_fin_inscripcion.setText(viejaInscripcion.getFechaFin().toString());
								   textField_fecha_ini_inscripcion.setText(viejaInscripcion.getFechaIni().toString());
								   textField_id_entrenador_2.setText(viejaInscripcion.getIdEntrenador());
							    panelPrincipal.removeAll();
								panelPrincipal.add(panel_añadir_inscripcion);
								panelPrincipal.repaint();
								panelPrincipal.revalidate();
								atras1 = panel_mostrar;
								break;
						    
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				   break;
			   case 9:
				   try {
						ArrayList<Registro> registros = servicioRegistro.obtenerRegistros();
						for(Registro r : registros) {
							   if(r.getIdAtleta().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),0)) && r.getNomCompetencia().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),1)) && r.getNomDisciplina().equals(table_mostrar.getValueAt( table_mostrar.getSelectedRow(),4)) ) {
								   viejoRegistro = r;
							   }}
							    btnAñadir_registro.setText("Modificar");
							    modificando = true;
							    textField_id_atleta_3.setText(viejoRegistro.getIdAtleta());
								   textField_nom_competencia_3.setText(viejoRegistro.getNomCompetencia());
								   textField_marcas.setText(viejoRegistro.getMarcas());
								   textField_nom_disciplina_2.setText(viejoRegistro.getNomDisciplina());
								   spinner_lugar.setValue(viejoRegistro.getPuesto());
								   panelPrincipal.removeAll();
								panelPrincipal.add(panel_añadir_registro);
								panelPrincipal.repaint();
								panelPrincipal.revalidate();
								atras1 = panel_mostrar;panelPrincipal.removeAll();
								panelPrincipal.add(panel_añadir_registro);
								panelPrincipal.repaint();
								panelPrincipal.revalidate();
								atras1 = panel_mostrar;
						    
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				   break;
				   
				   
			   }
					
			}
				else
					JOptionPane.showMessageDialog(panel_mostrar, "Seleccione una entidad para modificar");}
		});
		toolBar_modificacion.add(btn_modificar_atleta);
		
		JLabel lbl_nombre_reporte = new JLabel("New label");
		lbl_nombre_reporte.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_reporte.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_nombre_reporte.setBounds(306, 11, 409, 28);
		panel_mostrar.add(lbl_nombre_reporte);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 234, 30);
		contentPane.add(menuBar);
		
		this.mnNewMenu = new JMenu("Añadir");
		menuBar.add(mnNewMenu);
		
		JButton btnAadirPais = new JButton("Añadir Pais");
		btnAadirPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_pais);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
		});
		mnNewMenu.add(btnAadirPais);
		
		JButton btnAadirSede = new JButton("Añadir Sede");
		btnAadirSede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_sede);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
		});
		mnNewMenu.add(btnAadirSede);
		
		JButton btnAadirAtleta = new JButton("A\u00F1adir atleta");
		mnNewMenu.add(btnAadirAtleta);
		
		JButton btnAadirCiudad = new JButton("Añadir Ciudad");
		btnAadirCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_ciudad);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
		});
		mnNewMenu.add(btnAadirCiudad);
		
		JButton btnAadirDisciplina = new JButton("Añadir Disciplina");
		btnAadirDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_disciplina);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
		});
		mnNewMenu.add(btnAadirDisciplina);
		
		JButton btnAadirEntrenador = new JButton("Añadir Entrenador");
		btnAadirEntrenador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_entrenador);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
		});
		
		JButton btnAadirInscripcin = new JButton("Añadir Inscripción");
		btnAadirInscripcin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_inscripcion);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
			
		});
		
		JButton btnAadirRegistros = new JButton("Añadir Registros");
		btnAadirRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_registro);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
		});
		mnNewMenu.add(btnAadirRegistros);
		mnNewMenu.add(btnAadirInscripcin);
		mnNewMenu.add(btnAadirEntrenador);
		
		JButton btnAadirCompetencia = new JButton("Añadir Competencia");
		btnAadirCompetencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_competencia);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
		});
		mnNewMenu.add(btnAadirCompetencia);
		
		JMenu menu = new JMenu("Mostrar");
		menuBar.add(menu);
		
		JButton btnPaises = new JButton("Paises");
		btnPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					paisTableModel = new TableModelPais(servicioPais.obtenerPaises());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 3;
				table_mostrar.setModel(paisTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		
		JButton btnSedes = new JButton("Sedes");
		btnSedes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sedeTableModel = new TableModelSede(servicioSede.obtenerTodasSedes());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 6;
				table_mostrar.setModel(sedeTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		menu.add(btnSedes);
		menu.add(btnPaises);
		
		JButton btnDisciplinas = new JButton("Disciplinas");
		btnDisciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					disciplinaTableModel = new TableModelDisciplina(servicioDisciplina.obtenerTodasDisciplinas());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 4;
				table_mostrar.setModel(disciplinaTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		
		JButton btnCiudades = new JButton("Ciudades");
		btnCiudades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ciudadTableModel = new TableModelCiudad(servicioCiudad.obtenerCiudades());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 2;
				table_mostrar.setModel(ciudadTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		
		
		
		JButton btnAtletas = new JButton("Atletas");
		menu.add(btnAtletas);
		btnAtletas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					atletasTableModel = new TableModelAtletas(servicioAtleta.obtenerTodosAtletas());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 1;
				table_mostrar.setModel(atletasTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		menu.add(btnCiudades);
		
		JButton btnRegistros = new JButton("Registros");
		btnRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registroTableModel = new TableModelRegistro(servicioRegistro.obtenerRegistros());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 9;
				table_mostrar.setModel(registroTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		menu.add(btnRegistros);
		menu.add(btnDisciplinas);
		
		JButton btnEntrenadores = new JButton("Entrenadores");
		btnEntrenadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					entrenadorTableModel = new TableModelEntrenador(servicioEntrenador.obtenerEntrenadores());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 5;
				table_mostrar.setModel(entrenadorTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		
		JButton btnInscripciones = new JButton("Inscripciones");
		btnInscripciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					inscripcionTableModel = new TableModelInscripcion(servicioInscripcion.obtenerInscripciones());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 8;
				table_mostrar.setModel(inscripcionTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		menu.add(btnInscripciones);
		menu.add(btnEntrenadores);
		
		JButton btnCompetencias = new JButton("Competencias");
		btnCompetencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					competenciaTableModel = new TableModelCompetencia(servicioCompetencia.obtenerTodasCompetencias());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(false);
				toolBar_modificacion.setVisible(true);
				entidad = 7;
				table_mostrar.setModel(competenciaTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		menu.add(btnCompetencias);
		
		JMenu mnNewMenu_1 = new JMenu("Reportes");
		menuBar.add(mnNewMenu_1);
		
		btn_rep_atletas_incompletos = new JButton("Atletas Incompletos");
		btn_rep_atletas_incompletos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteAtletasIncompletos.obtenerAtletasIncompletos(),5);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Atletas Incompletos");
			    toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		
		JButton btn_rep_inscripciones = new JButton("Inscripciones");
		btn_rep_inscripciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteInscripciones.obtenerInscripciones(),1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Inscripciones");
				toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		mnNewMenu_1.add(btn_rep_inscripciones);
		
		JButton btn_rep_entrenadores = new JButton("Entrenadores");
		btn_rep_entrenadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteEntrenadores.obtenerEntrenadores(),2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Entrenadores");
				toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		mnNewMenu_1.add(btn_rep_entrenadores);
		
		JButton btn_rep_competencias = new JButton("Competencias");
		btn_rep_competencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteCompetencias.obtenerCompetencias(),3);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Competencias");
				toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		mnNewMenu_1.add(btn_rep_competencias);
		
		JButton btn_rep_atletas_pais = new JButton("Atletas por pais");
		btn_rep_atletas_pais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteAtletasPorPais.obtenerAtletasPorPais(),4);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Atleta Por País");
				toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		mnNewMenu_1.add(btn_rep_atletas_pais);
		mnNewMenu_1.add(btn_rep_atletas_incompletos);
		
		JButton btn_rep_competencias_ciudad = new JButton("Competencias por ciudad");
		btn_rep_competencias_ciudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteCompetenciasPorCiudadYSede.obtenerCompetenciasPorCiudadYSede(),9);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Atleta Por Ciudad");
				toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		
		JButton btn_rep_resultados_anuales = new JButton("Resultados Anuales");
		btn_rep_resultados_anuales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteResultadosAnuales.obtenerResultadosAnuales(),6);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Resultados Anuales");
				toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		mnNewMenu_1.add(btn_rep_resultados_anuales);
		
		JButton btn_estado_competencias = new JButton("Estado competencias");
		btn_estado_competencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteEstadoCompetencias.obtenerEstadoCompetencias(),7);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Estado Competencias");
				toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		mnNewMenu_1.add(btn_estado_competencias);
		
		JButton btn_competencias_pais = new JButton("Competencias por pais");
		btn_competencias_pais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reporteTableModel = new TableModelReporte(ReporteCompetenciasPorPais.obtenerCompetenciasPorPais(),8);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lbl_nombre_reporte.setVisible(true);
				lbl_nombre_reporte.setText("Reporte Competencias Por pais");
				toolBar_modificacion.setVisible(false);
				table_mostrar.setModel(reporteTableModel);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_mostrar);
				panelPrincipal.repaint();
				panelPrincipal.validate();
			}
		});
		mnNewMenu_1.add(btn_competencias_pais);
		mnNewMenu_1.add(btn_rep_competencias_ciudad);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuBar.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(contentPane, "Desea salir?", "Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
				dispose();
			}	
			}});
		btnAadirAtleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtn_masculinoAtleta.setSelected(true);
				panelPrincipal.removeAll();
				panelPrincipal.add(panel_añadir);
				panel_añadir.removeAll();
				panel_añadir.add(panel_añadir_atleta);
				panelPrincipal.repaint();
				panelPrincipal.revalidate();
			}
		});
		configurarInterfazSegunRol();
	
	}
	private void configurarInterfazSegunRol() {
	    // Usar el rol del usuario en lugar del parámetro esAdmin
	    boolean esAdmin = "admin".equalsIgnoreCase(usuarioActual.getRol());
	    
	    if (!esAdmin) {
	        ocultarOpcionesDeAdmin();
	    }
	}
	private void ocultarOpcionesDeAdmin() {
	    // Ocultar menú Añadir
	    if (mnNewMenu != null) {
	        mnNewMenu.setVisible(false);
	    }
	    
	    // Ocultar botones Eliminar y Modificar
	    if (btnEliminarAtleta != null) {
	        btnEliminarAtleta.setVisible(false);
	    }
	    if (btn_modificar_atleta != null) {
	        btn_modificar_atleta.setVisible(false);
	    }
	    
	    // Ocultar la barra de herramientas (toolbar)
	    if (toolBar != null) {
	        toolBar.setVisible(false);
	    }
	    
	    // Hacer la tabla de solo lectura
	    if (table_mostrar != null) {
	        table_mostrar.setDefaultEditor(Object.class, null);
	    }
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}