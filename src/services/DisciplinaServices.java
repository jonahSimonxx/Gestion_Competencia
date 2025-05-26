package services;

import model.Disciplina;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaServices {
    
    public void insertarDisciplina(Disciplina disciplina) throws SQLException {
        // Validaciones básicas
        if (disciplina == null) {
            throw new IllegalArgumentException("El objeto disciplina no puede ser nulo");
        }
        
        if (disciplina.getNomDisciplina() == null || disciplina.getNomDisciplina().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la disciplina es requerido");
        }

        if (disciplina.getNomDisciplina().length() > 20) {
            throw new IllegalArgumentException("El nombre de la disciplina no puede exceder 20 caracteres");
        }

        // Llamada a la función PostgreSQL
        String sql = "{ call crear_disciplina(?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, disciplina.getNomDisciplina());
            stmt.execute();
            
        } catch (SQLException e) {
            manejarErrorSQL(e);
        }
    }
    
    public void eliminarDisciplina(String nomDisciplina) throws SQLException {
        if (nomDisciplina == null || nomDisciplina.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la disciplina es requerido");
        }

        String sql = "{ call eliminar_disciplina(?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, nomDisciplina);
            stmt.execute();
            
        } catch (SQLException e) {
            if (e.getSQLState() != null && e.getSQLState().equals("23503")) {
                throw new SQLException("No se puede eliminar la disciplina porque tiene registros relacionados", e);
            }
            throw e;
        }
    }
    
    public void modificarDisciplina(String nombreOriginal, Disciplina disciplinaActualizada) throws SQLException {
        // Validaciones
        if (nombreOriginal == null || nombreOriginal.isEmpty()) {
            throw new IllegalArgumentException("El nombre original de la disciplina es requerido");
        }
        
        if (disciplinaActualizada == null) {
            throw new IllegalArgumentException("Los datos actualizados de la disciplina son requeridos");
        }

        if (disciplinaActualizada.getNomDisciplina() == null || disciplinaActualizada.getNomDisciplina().isEmpty()) {
            throw new IllegalArgumentException("El nuevo nombre de la disciplina es requerido");
        }

        if (disciplinaActualizada.getNomDisciplina().length() > 20) {
            throw new IllegalArgumentException("El nombre de la disciplina no puede exceder 20 caracteres");
        }

        // Llamada al procedimiento almacenado
        String sql = "{ call modificar_disciplina(?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, nombreOriginal);
            stmt.setString(2, disciplinaActualizada.getNomDisciplina());
            
            stmt.execute();
            
        } catch (SQLException e) {
            if (e.getSQLState() != null) {
                switch (e.getSQLState()) {
                    case "23505":
                        throw new SQLException("Ya existe una disciplina con este nombre", e);
                    case "23503":
                        throw new SQLException("No se puede modificar la disciplina porque tiene registros relacionados", e);
                }
            }
            throw new SQLException("Error al modificar disciplina: " + e.getMessage(), e);
        }
    }
    
    private void manejarErrorSQL(SQLException e) throws SQLException {
        if (e.getSQLState() != null) {
            switch (e.getSQLState()) {
                case "23505":
                    throw new SQLException("Ya existe una disciplina con este nombre", e);
                case "23503":
                    throw new SQLException("Violación de integridad referencial", e);
                case "23514":
                    throw new SQLException("Violación de reglas de validación", e);
                default:
                    throw new SQLException("Error de base de datos: " + e.getMessage(), e);
            }
        }
        throw e;
    }
    
    // Método adicional para verificar existencia
    public boolean existeDisciplina(String nomDisciplina) throws SQLException {
        String sql = "SELECT 1 FROM \"Disciplina\" WHERE \"nom_disciplina\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nomDisciplina);
            return stmt.executeQuery().next();
        }
    }
    
    // Método para obtener todas las disciplinas
    public List<Disciplina> obtenerTodasDisciplinas() throws SQLException {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM \"Disciplina\" ORDER BY \"nom_disciplina\"";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             Statement stmt = dbConn.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                disciplinas.add(new Disciplina(rs.getString("nom_disciplina")));
            }
        }
        return disciplinas;
    }
}