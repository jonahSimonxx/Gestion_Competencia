package services;

import model.Atleta;
import utils.DatabaseConnection;
import java.sql.*;

public class AtletaServices {
    
    public void insertarAtleta(Atleta atleta) throws IllegalArgumentException, SQLException {
        // Validar datos antes de la inserción
        validarDatosAtleta(atleta);
        
        // Verificar referencias en la base de datos
        verificarReferencias(atleta);
        
        // Llamar a la función PostgreSQL
        ejecutarInsercion(atleta);
    }
    
    private void validarDatosAtleta(Atleta atleta) throws IllegalArgumentException {
        // Validación de ID
        if (atleta.getIdAtleta() == null || atleta.getIdAtleta().isEmpty() || atleta.getIdAtleta().length() > 11) {
            throw new IllegalArgumentException("El ID del atleta es requerido y no debe exceder 11 caracteres");
        }
        
        // Validación de nombre
        if (atleta.getNomCompleto() == null || atleta.getNomCompleto().isEmpty() || atleta.getNomCompleto().length() > 50) {
            throw new IllegalArgumentException("El nombre completo es requerido y no debe exceder 50 caracteres");
        }
        
        // Validación de sexo
        if (atleta.getSexo() != 'M' && atleta.getSexo() != 'F') {
            throw new IllegalArgumentException("El sexo debe ser 'M' o 'F'");
        }
        
        // Validación de categoría deportiva
        if (atleta.getCatDep() == null || atleta.getCatDep().isEmpty() || atleta.getCatDep().length() > 30) {
            throw new IllegalArgumentException("La categoría deportiva es requerida y no debe exceder 30 caracteres");
        }
        
        // Validación de edad
        if (atleta.getEdad() < 18) {
            throw new IllegalArgumentException("El atleta debe ser mayor de edad (18+ años)");
        }
        
        // Validación de país
        if (atleta.getNomPais() == null || atleta.getNomPais().isEmpty() || atleta.getNomPais().length() > 30) {
            throw new IllegalArgumentException("El país es requerido y no debe exceder 30 caracteres");
        }
        
        if (atleta.getIdEntrenador() != null && !atleta.getIdEntrenador().isEmpty()) {
            if (atleta.getIdEntrenador().length() > 11) {
                throw new IllegalArgumentException("El ID del entrenador no debe exceder 11 caracteres");
            }
        }
    }
    
    private void verificarReferencias(Atleta atleta) throws SQLException {
        try (DatabaseConnection dbConn = new DatabaseConnection()) {
            Connection conn = dbConn.getConnection();
            
            // Verificar país
            if (!existeEnTabla(conn, "\"Pais\"", "\"nom_pais\"", atleta.getNomPais())) {
                throw new SQLException("El país '" + atleta.getNomPais() + "' no existe");
            }
            
            // Verificar entrenador 
            if (atleta.getIdEntrenador() != null && !atleta.getIdEntrenador().isEmpty()) {
                if (!existeEnTabla(conn, "\"Entrenador\"", "\"id_entrenador\"", atleta.getIdEntrenador())) {
                    throw new SQLException("El entrenador con ID '" + atleta.getIdEntrenador() + "' no existe");
                }
            }
        }
    }
    
    private boolean existeEnTabla(Connection conn, String tabla, String columna, String valor) throws SQLException {
        String sql = "SELECT 1 FROM " + tabla + " WHERE " + columna + " = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, valor);
            return stmt.executeQuery().next();
        }
    }
    
    private void ejecutarInsercion(Atleta atleta) throws SQLException {
        String sql = "{ call crear_atleta(?, ?, ?, ?, ?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, atleta.getIdAtleta());
            stmt.setString(2, atleta.getNomCompleto());
            stmt.setString(3, String.valueOf(atleta.getSexo()));
            stmt.setString(4, atleta.getCatDep());
            stmt.setString(5, atleta.getIdEntrenador());
            stmt.setString(6, atleta.getNomPais());
            stmt.setInt(7, atleta.getEdad());
            
            stmt.execute();
        } catch (SQLException e) {
            // Manejo específico de errores de base de datos
            if (e.getSQLState().equals("23505")) { // Violación de unique
                throw new SQLException("Ya existe un atleta con este ID", e);
            }
            throw e;
        }
    }
    
    public void eliminarAtleta(String idAtleta) throws SQLException {
        String sql = "{ call eliminar_atleta(?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, idAtleta);
            stmt.execute();
            
            // Verificar si realmente se eliminó algo
            if (!existeAtleta(idAtleta)) {
                System.out.println("✅ Atleta eliminado correctamente");
            } else {
                throw new SQLException("No se pudo eliminar el atleta");
            }
        }
    }

    public boolean existeAtleta(String idAtleta) throws SQLException {
        String sql = "SELECT 1 FROM \"Atleta\" WHERE \"id_atleta\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, idAtleta);
            return stmt.executeQuery().next();
        }
    }
}