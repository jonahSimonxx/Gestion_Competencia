package services;

import model.Entrenador;
import utils.DatabaseConnection;
import java.sql.*;

public class EntrenadorServices {
    
    public void insertarEntrenador(Entrenador entrenador) throws IllegalArgumentException, SQLException {
        // Validaciones en Java
        validarEntrenador(entrenador);
        
        // Llamar a la función PostgreSQL
        ejecutarInsercion(entrenador);
    }
    
    private void validarEntrenador(Entrenador entrenador) throws IllegalArgumentException {
        // Validación de ID
        if (entrenador.getIdEntrenador() == null || entrenador.getIdEntrenador().isEmpty() || 
            entrenador.getIdEntrenador().length() > 11) {
            throw new IllegalArgumentException("El ID del entrenador es requerido y no debe exceder 11 caracteres");
        }
        
        // Validación de nombre
        if (entrenador.getNombre() == null || entrenador.getNombre().isEmpty() || 
            entrenador.getNombre().length() > 50) {
            throw new IllegalArgumentException("El nombre es requerido y no debe exceder 50 caracteres");
        }
        
        // Validación de dirección (opcional)
        if (entrenador.getDireccion() != null && entrenador.getDireccion().length() > 100) {
            throw new IllegalArgumentException("La dirección no debe exceder 100 caracteres");
        }
        
        // Validación de especialidad
        if (entrenador.getEsp() == null || entrenador.getEsp().isEmpty() || 
            entrenador.getEsp().length() > 30) {
            throw new IllegalArgumentException("La especialidad es requerida y no debe exceder 30 caracteres");
        }
    }
    
    private void ejecutarInsercion(Entrenador entrenador) throws SQLException {
        String sql = "{ call crear_entrenador(?, ?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, entrenador.getIdEntrenador());
            stmt.setString(2, entrenador.getNombre());
            stmt.setString(3, entrenador.getDireccion());
            stmt.setString(4, entrenador.getEsp());
            
            stmt.execute();
        } catch (SQLException e) {
            // Manejo específico de errores de base de datos
            if (e.getSQLState().equals("23505")) { // Violación de unique
                throw new SQLException("Ya existe un entrenador con este ID", e);
            }
            throw e;
        }
    }
    
    // Método adicional para verificar existencia
    public boolean existeEntrenador(String idEntrenador) throws SQLException {
        String sql = "SELECT 1 FROM \"Entrenador\" WHERE \"id_entrenador\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, idEntrenador);
            return stmt.executeQuery().next();
        }
    }
    
    public void eliminarEntrenador(String idEntrenador) throws SQLException {
        String sql = "{ call eliminar_entrenador(?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, idEntrenador);
            stmt.execute();
            
            if (!existeEntrenador(idEntrenador)) {
                System.out.println("✅ Entrenador eliminado correctamente");
            } else {
                throw new SQLException("No se pudo eliminar el entrenador");
            }
        }
    }
    
    public void modificarEntrenador(String idOriginal, Entrenador entrenadorActualizado) throws SQLException {
        // Validaciones de longitud
        if (idOriginal == null || idOriginal.isEmpty()) {
            throw new IllegalArgumentException("El ID original del entrenador es requerido");
        }
        if (idOriginal.length() > 20) {
            throw new IllegalArgumentException("El ID original no puede exceder 20 caracteres");
        }
        if (entrenadorActualizado.getIdEntrenador() != null && entrenadorActualizado.getIdEntrenador().length() > 20) {
            throw new IllegalArgumentException("El nuevo ID no puede exceder 20 caracteres");
        }
        if (entrenadorActualizado.getNombre() != null && entrenadorActualizado.getNombre().length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder 50 caracteres");
        }
        if (entrenadorActualizado.getDireccion() != null && entrenadorActualizado.getDireccion().length() > 100) {
            throw new IllegalArgumentException("La dirección no puede exceder 100 caracteres");
        }
        if (entrenadorActualizado.getEsp() != null && entrenadorActualizado.getEsp().length() > 30) {
            throw new IllegalArgumentException("La especialidad no puede exceder 30 caracteres");
        }

        String sql = "{ call modificar_entrenador(?, ?, ?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, idOriginal);
            stmt.setString(2, entrenadorActualizado.getNombre());
            stmt.setString(3, entrenadorActualizado.getDireccion());
            stmt.setString(4, entrenadorActualizado.getEsp());
            stmt.setString(5, entrenadorActualizado.getIdEntrenador());
            
            stmt.execute();
            
            System.out.println("✅ Entrenador modificado: " + idOriginal + " → " + entrenadorActualizado.getIdEntrenador());
            
        } catch (SQLException e) {
            String errorMsg = "Error modificando entrenador";
            if (e.getSQLState() != null) {
                switch (e.getSQLState()) {
                    case "23505":
                        errorMsg = "Ya existe un entrenador con el ID: " + entrenadorActualizado.getIdEntrenador();
                        break;
                    case "23503":
                        errorMsg = "Violación de integridad referencial";
                        break;
                    case "23514":
                        errorMsg = "Violación de reglas de validación";
                        break;
                }
            } else if (e.getMessage().contains("demasiado largo")) {
                errorMsg = "Uno de los valores excede el límite de caracteres";
            }
            throw new SQLException(errorMsg + ": " + e.getMessage(), e);
        }
    }
}
