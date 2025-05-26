package services;

import model.Ciudad;
import utils.DatabaseConnection;
import java.sql.*;

public class CiudadServices {
    
    public void insertarCiudad(Ciudad ciudad) throws SQLException {
        // Validaciones básicas
        if (ciudad == null || ciudad.getNomCiudad() == null || ciudad.getNomCiudad().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad es requerido");
        }
        
        if (ciudad.getNomPais() == null || ciudad.getNomPais().isEmpty()) {
            throw new IllegalArgumentException("El nombre del país es requerido");
        }

        if (ciudad.getNomCiudad().length() > 50) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede exceder 50 caracteres");
        }

        if (ciudad.getNomPais().length() > 30) {
            throw new IllegalArgumentException("El nombre del país no puede exceder 30 caracteres");
        }

        // Llamada a la función PostgreSQL
        String sql = "{ call crear_ciudad(?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, ciudad.getNomCiudad());
            stmt.setString(2, ciudad.getNomPais());
            
            stmt.execute();
            
        } catch (SQLException e) {
            manejarErrorSQL(e);
        }
    }
    
    public void eliminarCiudad(String nomCiudad) throws SQLException {
        if (nomCiudad == null || nomCiudad.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad es requerido");
        }

        String sql = "{ call eliminar_ciudad(?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, nomCiudad);
            stmt.execute();
            
        } catch (SQLException e) {
            if (e.getSQLState() != null && e.getSQLState().equals("23503")) {
                throw new SQLException("No se puede eliminar la ciudad porque tiene registros relacionados", e);
            }
            throw e;
        }
    }
    
    public void modificarCiudad(String nombreCiudadOriginal, Ciudad ciudadActualizada) throws SQLException {
        // Validaciones
        if (nombreCiudadOriginal == null || nombreCiudadOriginal.isEmpty()) {
            throw new IllegalArgumentException("El nombre original de la ciudad es requerido");
        }
        
        if (ciudadActualizada == null) {
            throw new IllegalArgumentException("Los datos actualizados de la ciudad son requeridos");
        }

        if (ciudadActualizada.getNomCiudad() == null || ciudadActualizada.getNomCiudad().isEmpty()) {
            throw new IllegalArgumentException("El nuevo nombre de la ciudad es requerido");
        }

        if (ciudadActualizada.getNomPais() == null || ciudadActualizada.getNomPais().isEmpty()) {
            throw new IllegalArgumentException("El país es requerido");
        }

        if (ciudadActualizada.getNomCiudad().length() > 20) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede exceder 20 caracteres");
        }

        if (ciudadActualizada.getNomPais().length() > 20) {
            throw new IllegalArgumentException("El nombre del país no puede exceder 20 caracteres");
        }

        // Llamada al procedimiento almacenado
        String sql = "{ call modificar_ciudad(?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
        	 stmt.setString(1, nombreCiudadOriginal);
        	 stmt.setString(2, ciudadActualizada.getNomPais());
             stmt.setString(3, ciudadActualizada.getNomCiudad());
             
            
            
            stmt.execute();
            
        } catch (SQLException e) {
            if (e.getSQLState() != null) {
                switch (e.getSQLState()) {
                    case "23505":
                        throw new SQLException("Ya existe una ciudad con este nombre", e);
                    case "23503":
                        throw new SQLException("El país especificado no existe", e);
                }
            }
            throw new SQLException("Error al modificar ciudad: " + e.getMessage(), e);
        }
    }
    
    private void manejarErrorSQL(SQLException e) throws SQLException {
        if (e.getSQLState() != null) {
            switch (e.getSQLState()) {
                case "23505":
                    throw new SQLException("Ya existe una ciudad con este nombre", e);
                case "23503":
                    throw new SQLException("El país especificado no existe", e);
                case "23514":
                    throw new SQLException("Violación de reglas de validación", e);
                default:
                    throw new SQLException("Error de base de datos: " + e.getMessage(), e);
            }
        }
        throw e;
    }
    
    // Método adicional para verificar existencia
    public boolean existeCiudad(String nomCiudad) throws SQLException {
        String sql = "SELECT 1 FROM \"Ciudad\" WHERE \"nom_ciudad\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nomCiudad);
            return stmt.executeQuery().next();
        }
    }
}