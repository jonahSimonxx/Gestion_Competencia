package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;
import utils.DatabaseConnection;

public class UsuarioServices {
	public Usuario autenticarUsuario(String nombreUsuario, String contrasena) {
	    Usuario usuario = null;
	    
	    try (DatabaseConnection dbConn = new DatabaseConnection()) {
	        // 1. Primero verificamos las credenciales con la función existente
	        boolean credencialesValidas = false;
	        try (PreparedStatement stmtAuth = dbConn.getConnection().prepareStatement(
	                "SELECT autenticar_usuario(?, ?)")) {
	            stmtAuth.setString(1, nombreUsuario);
	            stmtAuth.setString(2, contrasena);
	            
	            try (ResultSet rs = stmtAuth.executeQuery()) {
	                if (rs.next()) {
	                    credencialesValidas = rs.getBoolean(1);
	                }
	            }
	        }

	        // 2. Si las credenciales son válidas, obtenemos los datos del usuario
	        if (credencialesValidas) {
	            try (PreparedStatement stmtUser = dbConn.getConnection().prepareStatement(
	                    "SELECT id_usuario, nombre_usuario, rol_usuario, activo " +
	                    "FROM \"Usuario\" WHERE nombre_usuario = ?")) {
	                stmtUser.setString(1, nombreUsuario);
	                
	                try (ResultSet rs = stmtUser.executeQuery()) {
	                    if (rs.next()) {
	                        usuario = new Usuario(
	                            rs.getString("id_usuario"),
	                            rs.getString("nombre_usuario"),
	                            null, // No devolvemos la contraseña
	                            rs.getString("rol_usuario"),
	                            rs.getBoolean("activo")
	                        );
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error en autenticación: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return usuario;
	}
    
    
    public void crearUsuario(String idUsuario, String nombreUsuario, String contrasena, String tipoUsuario, boolean activo) 
            throws SQLException {
    	String sql= "{call crear_usuario(?, ?, ?, ?, ?)}";
    	try (DatabaseConnection dbConn = new DatabaseConnection();
                CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {            
            stmt.setString(1, idUsuario);
            stmt.setString(2, nombreUsuario);
            stmt.setString(3, contrasena);
            stmt.setString(4, tipoUsuario);
            stmt.setBoolean(5, activo);
            stmt.execute();
        }
    }

    public boolean usuarioExiste(String nombreUsuario) {
        String sql = "SELECT 1 FROM usuarios WHERE nombre_usuario = ? AND activo = TRUE";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
                CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, nombreUsuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}