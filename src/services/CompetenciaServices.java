package services;

import model.Competencia;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetenciaServices {

    public void insertarCompetencia(Competencia competencia) throws SQLException {
        // Validaciones básicas
        if (competencia == null) {
            throw new IllegalArgumentException("El objeto competencia no puede ser nulo");
        }
        
        validarCamposCompetencia(competencia);
        
        // Verificar referencias
        verificarReferencias(competencia);

        // Llamada a la función PostgreSQL
        String sql = "{ call crear_competicion(?, ?, ?, ?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, competencia.getNomCompetencia());
            stmt.setString(2, competencia.getNomSede());
            stmt.setString(3, competencia.getEstado());
            stmt.setString(4, competencia.getFechaIni());
            stmt.setString(5, competencia.getFechaFin());
            stmt.setString(6, competencia.getNomDisciplina());
            
            stmt.execute();
            
        } catch (SQLException e) {
            manejarErrorSQL(e);
        }
    }
    
    public void eliminarCompetencia(String nomCompetencia) throws SQLException {
        if (nomCompetencia == null || nomCompetencia.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la competencia es requerido");
        }

        String sql = "{ call eliminar_competencia(?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, nomCompetencia);
            stmt.execute();
            
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar competencia: " + e.getMessage(), e);
        }
    }
    
    public void modificarCompetencia(String nombreOriginal, Competencia competenciaActualizada) throws SQLException {
        // Validaciones
        if (nombreOriginal == null || nombreOriginal.isEmpty()) {
            throw new IllegalArgumentException("El nombre original de la competencia es requerido");
        }
        
        if (competenciaActualizada == null) {
            throw new IllegalArgumentException("Los datos actualizados de la competencia son requeridos");
        }
        
        validarCamposCompetencia(competenciaActualizada);
        
        // Verificar referencias
        verificarReferencias(competenciaActualizada);

        // Llamada al procedimiento almacenado
        String sql = "{ call modificar_competencia(?, ?, ?, ?, ?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, nombreOriginal);
            stmt.setString(2, competenciaActualizada.getNomSede());
            stmt.setString(3, competenciaActualizada.getEstado());
            stmt.setString(4, competenciaActualizada.getFechaIni());
            stmt.setString(5, competenciaActualizada.getFechaFin());
            stmt.setString(6, competenciaActualizada.getNomDisciplina());
            stmt.setString(7, competenciaActualizada.getNomCompetencia());
            
            stmt.execute();
            
        } catch (SQLException e) {
            manejarErrorSQL(e);
        }
    }
    
    private void validarCamposCompetencia(Competencia competencia) {
        if (competencia.getNomCompetencia() == null || competencia.getNomCompetencia().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la competencia es requerido");
        }
        
        if (competencia.getNomSede() == null || competencia.getNomSede().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sede es requerido");
        }
        
        if (competencia.getEstado() == null || competencia.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado es requerido");
        }
        
        if (competencia.getFechaIni() == null || competencia.getFechaIni().isEmpty()) {
            throw new IllegalArgumentException("La fecha de inicio es requerida");
        }
        
        if (competencia.getFechaFin() == null || competencia.getFechaFin().isEmpty()) {
            throw new IllegalArgumentException("La fecha de fin es requerida");
        }
        
        if (competencia.getNomDisciplina() == null || competencia.getNomDisciplina().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la disciplina es requerido");
        }
        
        // Validar longitudes máximas según la tabla
        if (competencia.getNomCompetencia().length() > 20) {
            throw new IllegalArgumentException("El nombre de la competencia no puede exceder 20 caracteres");
        }
        
        if (competencia.getNomSede().length() > 20) {
            throw new IllegalArgumentException("El nombre de la sede no puede exceder 20 caracteres");
        }
        
        if (competencia.getEstado().length() > 20) {
            throw new IllegalArgumentException("El estado no puede exceder 20 caracteres");
        }
        
        if (competencia.getFechaIni().length() > 9) {
            throw new IllegalArgumentException("La fecha de inicio no puede exceder 9 caracteres");
        }
        
        if (competencia.getFechaFin().length() > 9) {
            throw new IllegalArgumentException("La fecha de fin no puede exceder 9 caracteres");
        }
        
        if (competencia.getNomDisciplina().length() > 20) {
            throw new IllegalArgumentException("El nombre de la disciplina no puede exceder 20 caracteres");
        }
    }
    
    private void verificarReferencias(Competencia competencia) throws SQLException {
        try (DatabaseConnection dbConn = new DatabaseConnection()) {
            Connection conn = dbConn.getConnection();
            
            // Verificar sede
            if (!existeEnTabla(conn, "\"Sede\"", "\"nom_sede\"", competencia.getNomSede())) {
                throw new SQLException("La sede '" + competencia.getNomSede() + "' no existe");
            }
            
            // Verificar disciplina
            if (!existeEnTabla(conn, "\"Disciplina\"", "\"nom_disciplina\"", competencia.getNomDisciplina())) {
                throw new SQLException("La disciplina '" + competencia.getNomDisciplina() + "' no existe");
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
    
    private void manejarErrorSQL(SQLException e) throws SQLException {
        if (e.getSQLState() != null) {
            switch (e.getSQLState()) {
                case "23505":
                    throw new SQLException("Ya existe una competencia con este nombre", e);
                case "23503":
                    throw new SQLException("Violación de integridad referencial (sede o disciplina no existe)", e);
                case "23514":
                    throw new SQLException("Violación de reglas de validación", e);
                default:
                    throw new SQLException("Error de base de datos: " + e.getMessage(), e);
            }
        }
        throw e;
    }
    
    public boolean existeCompetencia(String nomCompetencia) throws SQLException {
        String sql = "SELECT 1 FROM \"Competencia\" WHERE \"nom_competencia\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nomCompetencia);
            return stmt.executeQuery().next();
        }
    }
    
    public List<Competencia> obtenerTodasCompetencias() throws SQLException {
        List<Competencia> competencias = new ArrayList<>();
        String sql = "SELECT * FROM \"Competencia\" ORDER BY \"nom_competencia\"";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             Statement stmt = dbConn.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                competencias.add(new Competencia(
                    rs.getString("nom_competencia"),
                    rs.getString("nom_sede"),
                    rs.getString("estado"),
                    rs.getString("fecha_ini"),
                    rs.getString("fecha_fin"),
                    rs.getString("nom_disciplina")
                ));
            }
        }
        return competencias;
    }
}