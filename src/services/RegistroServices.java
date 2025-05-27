package services;

import model.Registro;
import utils.DatabaseConnection;
import java.sql.*;

public class RegistroServices {
    
    public void insertarRegistro(Registro registro) throws SQLException {
        // Validaciones básicas
        if (registro == null) {
            throw new IllegalArgumentException("El objeto registro no puede ser nulo");
        }
        
        validarCamposRegistro(registro);
        
        // Verificar referencias
        verificarReferencias(registro);

        // Llamada a la función PostgreSQL
        String sql = "{ call crear_registro(?, ?, ?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, registro.getNomDisciplina());
            stmt.setString(2, registro.getMarcas());
            stmt.setString(3, registro.getIdAtleta());
            stmt.setString(4, registro.getNomCompetencia());
            stmt.setInt(5, registro.getPuesto());
            
            stmt.execute();
            
        } catch (SQLException e) {
            manejarErrorSQL(e);
        }
    }
    
    public void eliminarRegistro(String idAtleta, String nomDisciplina, String nomCompetencia) throws SQLException {
        if (idAtleta == null || idAtleta.isEmpty()) {
            throw new IllegalArgumentException("El ID del atleta es requerido");
        }
        
        if (nomDisciplina == null || nomDisciplina.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la disciplina es requerido");
        }
        
        if (nomCompetencia == null || nomCompetencia.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la competencia es requerido");
        }

        String sql = "{ call eliminar_registro(?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, idAtleta);
            stmt.setString(2, nomDisciplina);
            stmt.setString(3, nomCompetencia);
            
            stmt.execute();
            
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar registro: " + e.getMessage(), e);
        }
    }
    
    public void modificarRegistro(Registro registroOriginal, Registro registroActualizado) throws SQLException {
        // Validaciones
        if (registroOriginal == null || registroActualizado == null) {
            throw new IllegalArgumentException("Los registros no pueden ser nulos");
        }
        
        validarCamposRegistro(registroActualizado);

        // Llamada al procedimiento almacenado
        String sql = "{ call modificar_registro(?, ?, ?, ?, ?, ?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            // Valores originales (WHERE)
            stmt.setString(1, registroOriginal.getNomDisciplina());
            stmt.setString(3, registroOriginal.getIdAtleta());
            stmt.setString(4, registroOriginal.getNomCompetencia());
            
            // Valores actualizados
            stmt.setString(2, registroActualizado.getMarcas());
            stmt.setInt(5, registroActualizado.getPuesto());
            stmt.setString(6, registroActualizado.getNomDisciplina());
            stmt.setString(7, registroActualizado.getIdAtleta());
            stmt.setString(8, registroActualizado.getNomCompetencia());
            
            stmt.execute();
            
        } catch (SQLException e) {
            manejarErrorSQL(e);
        }
    }
    
    private void validarCamposRegistro(Registro registro) {
        if (registro.getNomDisciplina() == null || registro.getNomDisciplina().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la disciplina es requerido");
        }
        
        if (registro.getIdAtleta() == null || registro.getIdAtleta().isEmpty()) {
            throw new IllegalArgumentException("El ID del atleta es requerido");
        }
        
        if (registro.getNomCompetencia() == null || registro.getNomCompetencia().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la competencia es requerido");
        }
        
        // Validar longitudes máximas según la tabla
        if (registro.getNomDisciplina().length() > 20) {
            throw new IllegalArgumentException("El nombre de la disciplina no puede exceder 20 caracteres");
        }
        
        if (registro.getMarcas() != null && registro.getMarcas().length() > 10) {
            throw new IllegalArgumentException("Las marcas no pueden exceder 10 caracteres");
        }
        
        if (registro.getIdAtleta().length() > 11) {
            throw new IllegalArgumentException("El ID del atleta no puede exceder 11 caracteres");
        }
        
        if (registro.getNomCompetencia().length() > 20) {
            throw new IllegalArgumentException("El nombre de la competencia no puede exceder 20 caracteres");
        }
        
        if (registro.getPuesto() < 0) {
            throw new IllegalArgumentException("El puesto no puede ser negativo");
        }
    }
    
    private void verificarReferencias(Registro registro) throws SQLException {
        try (DatabaseConnection dbConn = new DatabaseConnection()) {
            Connection conn = dbConn.getConnection();
            
            // Verificar atleta
            if (!existeEnTabla(conn, "\"Atleta\"", "\"id_atleta\"", registro.getIdAtleta())) {
                throw new SQLException("El atleta con ID '" + registro.getIdAtleta() + "' no existe");
            }
            
            // Verificar disciplina
            if (!existeEnTabla(conn, "\"Disciplina\"", "\"nom_disciplina\"", registro.getNomDisciplina())) {
                throw new SQLException("La disciplina '" + registro.getNomDisciplina() + "' no existe");
            }
            
            // Verificar competencia
            if (!existeEnTabla(conn, "\"Competencia\"", "\"nom_competencia\"", registro.getNomCompetencia())) {
                throw new SQLException("La competencia '" + registro.getNomCompetencia() + "' no existe");
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
                    throw new SQLException("Ya existe un registro con estos datos", e);
                case "23503":
                    throw new SQLException("Violación de integridad referencial (atleta, disciplina o competencia no existe)", e);
                case "23514":
                    throw new SQLException("Violación de reglas de validación", e);
                default:
                    throw new SQLException("Error de base de datos: " + e.getMessage(), e);
            }
        }
        throw e;
    }
    
    public boolean existeRegistro(String idAtleta, String nomDisciplina, String nomCompetencia) throws SQLException {
        String sql = "SELECT 1 FROM \"Registro\" WHERE \"id_atleta\" = ? AND \"nom_disciplina\" = ? AND \"nom_competencia\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, idAtleta);
            stmt.setString(2, nomDisciplina);
            stmt.setString(3, nomCompetencia);
            return stmt.executeQuery().next();
        }
    }
}