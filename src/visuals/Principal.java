package visuals;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ButtonGroup sexo = new ButtonGroup();
	private JPanel contentPane;
	private JPanel atras1;
	private JTable table_atletas;
	private JTextField textField_nombreAtleta;
	private JTextField textField_telefonoAtleta;
	private JTextField textField_paisAtleta;
	private JTextField textField_catDeportAtleta;
	private JTextField textField_idEntrenador_Atleta;
	
	
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
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono :");
		lblTelfono.setBounds(10, 145, 67, 14);
		panel_añadir_atleta.add(lblTelfono);
		
		JLabel lblEdad = new JLabel("Edad :");
		lblEdad.setBounds(10, 360, 41, 14);
		panel_añadir_atleta.add(lblEdad);
		
		JLabel lblPais = new JLabel("Pa\u00EDs :");
		lblPais.setBounds(10, 203, 41, 14);
		panel_añadir_atleta.add(lblPais);
		
		JLabel lblCategoriaDeportiva = new JLabel("Categor\u00EDa deportiva :");
		lblCategoriaDeportiva.setBounds(10, 248, 109, 14);
		panel_añadir_atleta.add(lblCategoriaDeportiva);
		
		JLabel lblIdEntrenador = new JLabel("ID Entrenador :");
		lblIdEntrenador.setBounds(10, 299, 89, 14);
		panel_añadir_atleta.add(lblIdEntrenador);
		
		textField_idEntrenador_Atleta = new JTextField();
		textField_idEntrenador_Atleta.setColumns(10);
		textField_idEntrenador_Atleta.setBounds(109, 296, 124, 20);
		panel_añadir_atleta.add(textField_idEntrenador_Atleta);
		
		JLabel lblSexo = new JLabel("Sexo :");
		lblSexo.setBounds(10, 417, 41, 14);
		panel_añadir_atleta.add(lblSexo);
		
		final JRadioButton rdbtn_masculinoAtleta = new JRadioButton("Masculino");
		rdbtn_masculinoAtleta.setBounds(70, 413, 89, 23);
		panel_añadir_atleta.add(rdbtn_masculinoAtleta);
		
		JRadioButton rdbtn_femeninoAtleta = new JRadioButton("Femenino");
		rdbtn_femeninoAtleta.setBounds(161, 413, 89, 23);
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
		
		textField_telefonoAtleta = new JTextField();
		textField_telefonoAtleta.setColumns(10);
		textField_telefonoAtleta.setBounds(70, 142, 86, 20);
		panel_añadir_atleta.add(textField_telefonoAtleta);
		
		textField_paisAtleta = new JTextField();
		textField_paisAtleta.setColumns(10);
		textField_paisAtleta.setBounds(61, 200, 86, 20);
		panel_añadir_atleta.add(textField_paisAtleta);
		
		final JSpinner spinner_edadAtleta = new JSpinner();
		spinner_edadAtleta.setModel(new SpinnerNumberModel(new Integer(18), new Integer(18), null, new Integer(1)));
		spinner_edadAtleta.setBounds(55, 357, 41, 20);
		panel_añadir_atleta.add(spinner_edadAtleta);
		
		textField_catDeportAtleta = new JTextField();
		textField_catDeportAtleta.setColumns(10);
		textField_catDeportAtleta.setBounds(129, 245, 124, 20);
		panel_añadir_atleta.add(textField_catDeportAtleta);
		
		JButton btnAñadir = new JButton("A\u00F1adir");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			panelPrincipal.add(btnSalir);
			btn_mostrar_atletas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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