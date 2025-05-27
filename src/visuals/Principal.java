package visuals;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Atleta;
import services.AtletaServices;

import javax.swing.JButton;

import javax.swing.ButtonGroup;
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


public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ButtonGroup sexo = new ButtonGroup();
	private JPanel contentPane;
	private JPanel atras1;
	protected JPanel atras2;
	private JTable table_atletas;
	private JTextField textField_nombreAtleta;
	private JTextField textField_paisAtleta;
	private JTextField textField_catDeportAtleta;
	private JTextField textField_idEntrenador_Atleta;
	boolean modificando = false;
	Atleta viejoAtleta;
	Atleta nuevoAtleta;
	private AtletaServices servicioAtleta = new AtletaServices();
	private TableModelAtletas atletasTableModel;
	@SuppressWarnings("removal")
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JPanel panel_añadir_atleta = new JPanel();
		panel_añadir_atleta.setBounds(0, 0, 584, 564);
		contentPane.add(panel_añadir_atleta);
		panel_añadir_atleta.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(10, 50, 56, 14);
		panel_añadir_atleta.add(lblNombre);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(10, 94, 35, 14);
		panel_añadir_atleta.add(lblId);
		
		JLabel lblEdad = new JLabel("Edad :");
		lblEdad.setBounds(10, 293, 41, 14);
		panel_añadir_atleta.add(lblEdad);
		
		JLabel lblPais = new JLabel("Pa\u00EDs :");
		lblPais.setBounds(10, 136, 41, 14);
		panel_añadir_atleta.add(lblPais);
		
		JLabel lblCategoriaDeportiva = new JLabel("Categor\u00EDa deportiva :");
		lblCategoriaDeportiva.setBounds(10, 181, 109, 14);
		panel_añadir_atleta.add(lblCategoriaDeportiva);
		
		JLabel lblIdEntrenador = new JLabel("ID Entrenador :");
		lblIdEntrenador.setBounds(10, 232, 89, 14);
		panel_añadir_atleta.add(lblIdEntrenador);
		
		textField_idEntrenador_Atleta = new JTextField();
		textField_idEntrenador_Atleta.setColumns(10);
		textField_idEntrenador_Atleta.setBounds(109, 229, 124, 20);
		panel_añadir_atleta.add(textField_idEntrenador_Atleta);
		
		JLabel lblSexo = new JLabel("Sexo :");
		lblSexo.setBounds(10, 350, 41, 14);
		panel_añadir_atleta.add(lblSexo);
		
		final JRadioButton rdbtn_masculinoAtleta = new JRadioButton("Masculino");
		rdbtn_masculinoAtleta.setBounds(70, 346, 89, 23);
		panel_añadir_atleta.add(rdbtn_masculinoAtleta);
		
		JRadioButton rdbtn_femeninoAtleta = new JRadioButton("Femenino");
		rdbtn_femeninoAtleta.setBounds(161, 346, 89, 23);
		panel_añadir_atleta.add(rdbtn_femeninoAtleta);
		sexo.add(rdbtn_femeninoAtleta);
		sexo.add(rdbtn_masculinoAtleta);
		
		
		textField_nombreAtleta = new JTextField();
		textField_nombreAtleta.setBounds(73, 47, 163, 20);
		panel_añadir_atleta.add(textField_nombreAtleta);
		textField_nombreAtleta.setColumns(10);
		
		final JTextField textField_idAtleta = new JTextField();
		textField_idAtleta.setColumns(10);
		textField_idAtleta.setBounds(55, 94, 86, 20);
		panel_añadir_atleta.add(textField_idAtleta);
		
		textField_paisAtleta = new JTextField();
		textField_paisAtleta.setColumns(10);
		textField_paisAtleta.setBounds(61, 133, 86, 20);
		panel_añadir_atleta.add(textField_paisAtleta);
		
		final JSpinner spinner_edadAtleta = new JSpinner();
		spinner_edadAtleta.setModel(new SpinnerNumberModel(new Integer(18), new Integer(18), null, new Integer(1)));
		spinner_edadAtleta.setBounds(55, 290, 41, 20);
		panel_añadir_atleta.add(spinner_edadAtleta);
		
		textField_catDeportAtleta = new JTextField();
		textField_catDeportAtleta.setColumns(10);
		textField_catDeportAtleta.setBounds(129, 178, 124, 20);
		panel_añadir_atleta.add(textField_catDeportAtleta);
		
		JButton btnAñadir = new JButton("A\u00F1adir");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char sexo = rdbtn_masculinoAtleta.isSelected()? 'M' : 'F';
				boolean error = false;
				nuevoAtleta = new Atleta(textField_idAtleta.getText(),textField_nombreAtleta.getText(),sexo , textField_catDeportAtleta.getText(),textField_idEntrenador_Atleta.getText(),(Integer)spinner_edadAtleta.getValue(), textField_paisAtleta.getText());
				try {
				    if(modificando) {
				    	servicioAtleta.modificarAtleta(nuevoAtleta.getIdAtleta(), nuevoAtleta);
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
							table_atletas.setModel(atletasTableModel);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
					String accion = modificando? "modificado":"insertado" ;
			    JOptionPane.showMessageDialog(panel_añadir_atleta, "Se ha " + accion + " un atleta");
			    btnAñadir.setText("Añadir");
			    textField_idAtleta.setText(null);
			    textField_catDeportAtleta.setText(null);
			    textField_idEntrenador_Atleta.setText(null);
			    textField_nombreAtleta.setText(null);
			    textField_paisAtleta.setText(null);
			    spinner_edadAtleta.setValue(18);
			    rdbtn_masculinoAtleta.setSelected(true);
				contentPane.removeAll();
				if(modificando) {
					contentPane.add(atras2);
					 modificando = false;
				}else
				contentPane.add(atras1);
				contentPane.repaint();
				contentPane.revalidate();
				if(modificando)
				atras1 = null;
				nuevoAtleta = null;}else
				JOptionPane.showMessageDialog(panel_añadir_atleta, "No se ha insertado el atleta, revise sus entradas");
			}
			
		});
		
		btnAñadir.setBounds(485, 530, 89, 23);
		panel_añadir_atleta.add(btnAñadir);
		
		JButton btnCancelar1 = new JButton("Cancelar");
		btnCancelar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(atras1);
				contentPane.repaint();
				contentPane.validate();
			}
		});
		btnCancelar1.setBounds(10, 530, 89, 23);
		panel_añadir_atleta.add(btnCancelar1);
		
		final JPanel panel_mostrar_atletas = new JPanel();
		panel_mostrar_atletas.setBounds(0, 0, 584, 564);
		contentPane.add(panel_mostrar_atletas);
		panel_mostrar_atletas.setLayout(null);
		
		JScrollPane scrollPane_atletas = new JScrollPane();
		scrollPane_atletas.setBounds(10, 50, 564, 503);
		panel_mostrar_atletas.add(scrollPane_atletas);
		
		table_atletas = new JTable();
		scrollPane_atletas.setViewportView(table_atletas);
		
		JButton boton_atras1 = new JButton("Atras");
		boton_atras1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(atras1);
				contentPane.repaint();
				contentPane.validate();
			}
		});
		boton_atras1.setBounds(485, 16, 89, 23);
		panel_mostrar_atletas.add(boton_atras1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 27, 181, 23);
		panel_mostrar_atletas.add(toolBar);
		
		JButton btnEliminarAtleta = new JButton("Eliminar");
		toolBar.add(btnEliminarAtleta);
		btnEliminarAtleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_atletas.getSelectedRow() != -1) {
				if(JOptionPane.showConfirmDialog(panel_mostrar_atletas, "Confirmar?","Confirme",JOptionPane.YES_NO_OPTION) == 0) {
				try {
					servicioAtleta.eliminarAtleta((String)table_atletas.getValueAt(table_atletas.getSelectedRow(),0));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               try {
				atletasTableModel = new TableModelAtletas(servicioAtleta.obtenerTodosAtletas());
				table_atletas.setModel(atletasTableModel);
				panel_mostrar_atletas.repaint();
				panel_mostrar_atletas.revalidate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
               
			}}else
				JOptionPane.showMessageDialog(panel_mostrar_atletas, "Seleccione un Atleta.");
				}
		});
		
		JButton btn_modificar_atleta = new JButton("Modificar");
		btn_modificar_atleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_atletas.getSelectedRow() != -1) {
			   try {
				ArrayList<Atleta> atletas = servicioAtleta.obtenerTodosAtletas();
				for(Atleta a : atletas) {
					   if(a.getIdAtleta().equals(table_atletas.getValueAt( table_atletas.getSelectedRow(),0))) {
						   viejoAtleta = a;
					   }
				    }
				btnAñadir.setText("Modificar");
				modificando = true;
				textField_idAtleta.setText(viejoAtleta.getIdAtleta());
				textField_catDeportAtleta.setText(viejoAtleta.getCatDep());
				textField_idEntrenador_Atleta.setText(viejoAtleta.getIdEntrenador());
				textField_nombreAtleta.setText(viejoAtleta.getNomCompleto());
				textField_paisAtleta.setText(viejoAtleta.getNomPais());
				spinner_edadAtleta.setValue(viejoAtleta.getEdad());
				atras2 = panel_mostrar_atletas;
				contentPane.removeAll();
				contentPane.add(panel_añadir_atleta);
				contentPane.repaint();
				contentPane.revalidate();
				if(Character.toString(viejoAtleta.getSexo()).equals("M")) {
					rdbtn_masculinoAtleta.setSelected(true);
				}else
					rdbtn_femeninoAtleta.setSelected(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
				else
					JOptionPane.showMessageDialog(panel_mostrar_atletas, "Seleccione un atleta para modificar");}
		});
		toolBar.add(btn_modificar_atleta);
			
			final JPanel panelPrincipal = new JPanel();
			panelPrincipal.setBounds(0, 0, 584, 564);
			contentPane.add(panelPrincipal);
			panelPrincipal.setLayout(null);
			
			
			
			JButton btn_mostrar_atletas = new JButton("Atletas");
			btn_mostrar_atletas.setBounds(213, 198, 151, 23);
			panelPrincipal.add(btn_mostrar_atletas);
			
			JButton btnAadirAtleta = new JButton("A\u00F1adir atleta");
			btnAadirAtleta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtn_masculinoAtleta.setSelected(true);
					atras1 = panelPrincipal; 
					contentPane.removeAll();
					contentPane.add(panel_añadir_atleta);
					contentPane.repaint();
					contentPane.revalidate();
				}
			});
			btnAadirAtleta.setBounds(214, 272, 150, 23);
			panelPrincipal.add(btnAadirAtleta);
			
			JButton btnSalir = new JButton("Salir");
			btnSalir.setBounds(214, 361, 150, 23);
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}});
			panelPrincipal.add(btnSalir);
			btn_mostrar_atletas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						atletasTableModel = new TableModelAtletas(servicioAtleta.obtenerTodosAtletas());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_atletas.setModel(atletasTableModel);
					atras1 = panelPrincipal; 
					contentPane.removeAll();
					contentPane.add(panel_mostrar_atletas);
					contentPane.repaint();
					contentPane.validate();
				}
			});
		
	 contentPane.removeAll();
		contentPane.add(panelPrincipal);
	}
}