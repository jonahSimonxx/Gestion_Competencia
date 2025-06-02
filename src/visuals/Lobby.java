package visuals;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import utils.Autenticador;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lobby extends JFrame {
    private CardLayout cardLayout;
    private JLayeredPane panel;
    private JPasswordField passwordField;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public Lobby() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Proyectos Eclipse\\Gestion_Competencia\\icons\\photo_2025-05-31_22-30-52.jpg"));
        setTitle("Sistema de gestión de competencias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); 
        
        setIconImage(getIconImage());

        cardLayout = new CardLayout();
        panel = new JLayeredPane();
        panel.setLayout(cardLayout);
        
        createUsuarioPanel();
        createAdminPanel();
        
        setupMenuBar();
        
        getContentPane().add(panel);
        
        cardLayout.show(panel, "panel_usuario");
        
        setVisible(true);
        
        configurarBotones();
    }
    
    public Image getIconImage() {
        Image iconImage = null;
        try {
            URL iconURL = getClass().getResource("/icons/photo_2025-05-31_22-30-52.jpg");
            if (iconURL != null) {
                iconImage = new ImageIcon(iconURL).getImage();
            }
        } catch (Exception e) {
            System.err.println("Error al cargar el icono: " + e.getMessage());
        }
        return iconImage;
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu usuarioMenu = new JMenu("Usuario");
        usuarioMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(panel, "panel_usuario");
            }
        });
        
        JMenu adminMenu = new JMenu("Administrador");
        adminMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(panel, "panel_admin");
            }
        });
        
        menuBar.add(usuarioMenu);
        menuBar.add(adminMenu);
        setJMenuBar(menuBar);
    }

    private void createUsuarioPanel() {
        JPanel panel_usuario = new JPanel();
        panel_usuario.setLayout(null);
        
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 29));
        lblUsuario.setBounds(219, 11, 147, 69);
        panel_usuario.add(lblUsuario);
        
        JLabel lblNewLabel = new JLabel("Usuario");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(133, 101, 59, 23);
        panel_usuario.add(lblNewLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(213, 165, 164, 20);
        panel_usuario.add(passwordField);
        
        textField = new JTextField();
        textField.setBounds(213, 103, 164, 20);
        panel_usuario.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Contraseña ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setBounds(133, 164, 70, 23);
        panel_usuario.add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
        textField_1.setBounds(213, 137, 164, 13);
        textField_1.setVisible(false);  
        textField_1.setColumns(10);
        panel_usuario.add(textField_1);
        
        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.setBounds(253, 221, 89, 23);
        panel_usuario.add(btnNewButton);
        
        panel.add(panel_usuario, "panel_usuario");
    }

    private void createAdminPanel() {
        JPanel panel_admin = new JPanel();
        panel_admin.setLayout(null);
        
        JLabel lblAdmin = new JLabel("Admin");
        lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 29));
        lblAdmin.setBounds(219, 11, 147, 69);
        panel_admin.add(lblAdmin);
        
        JLabel lblNewLabel_2 = new JLabel("Usuario");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_2.setBounds(133, 102, 59, 23);
        panel_admin.add(lblNewLabel_2);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(213, 165, 164, 20);
        panel_admin.add(passwordField_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(213, 103, 164, 20);
        panel_admin.add(textField_2);
        
        JLabel lblNewLabel_1_1 = new JLabel("Contraseña ");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1_1.setBounds(133, 164, 72, 23);
        panel_admin.add(lblNewLabel_1_1);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
        textField_3.setColumns(10);
        textField_3.setBounds(213, 137, 164, 13);
        textField_3.setVisible(false);  
        panel_admin.add(textField_3);
        
        JButton btnNewButton_1 = new JButton("Aceptar");
        btnNewButton_1.setBounds(253, 221, 89, 23);
        panel_admin.add(btnNewButton_1);
        
        panel.add(panel_admin, "panel_admin");
    }
    
 // En el método configurarBotones() de Lobby.java
    private void configurarBotones() {
        // Botón Aceptar en panel Usuario
        JButton btnUsuario = (JButton) ((JPanel) panel.getComponent(0)).getComponent(6);
        btnUsuario.addActionListener(e -> {
            String usuario = textField.getText();
            String contrasena = new String(passwordField.getPassword());
            
            if(Autenticador.validarCredenciales(usuario, contrasena, false)) {
                // Credenciales correctas de usuario
                Principal principal = new Principal(false); // false para usuario normal
                principal.setVisible(true);
                dispose(); // Cierra la ventana de login
            } else {
                textField_1.setText("Usuario o contraseña incorrectos");
                textField_1.setVisible(true);
            }
        });

        // Botón Aceptar en panel Admin
        JButton btnAdmin = (JButton) ((JPanel) panel.getComponent(1)).getComponent(6);
        btnAdmin.addActionListener(e -> {
            String usuario = textField_2.getText();
            String contrasena = new String(passwordField_1.getPassword());
            
            if(Autenticador.validarCredenciales(usuario, contrasena, true)) {
                // Credenciales correctas de admin
                Principal principal = new Principal(true); // true para admin
                principal.setVisible(true);
                dispose(); // Cierra la ventana de login
            } else {
                textField_3.setText("Usuario o contraseña incorrectos");
                textField_3.setVisible(true);
            }
        });
    }

    private static final long serialVersionUID = 1L;
}