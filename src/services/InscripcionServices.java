package services;

import model.Inscripcion;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class InscripcionServices {
    
	public void insertarInscripcion(Inscripcion inscripcion) throws SQLException {
	    // Validaciones básicas
	    if (inscripcion == null) {
	        throw new IllegalArgumentException("El objeto inscripción no puede ser nulo");
	    }
	    
	    validarCamposInscripcion(inscripcion);
	    
	    verificarReferencias(inscripcion);

	    // Llamada a la función PostgreSQL
	    String sql = "{ call crear_inscripcion(?, ?, ?, ?, ?) }";
	    
	    try (DatabaseConnection dbConn = new DatabaseConnection();
	         CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
	        
	        stmt.setString(1, inscripcion.getNomCompetencia());
	        stmt.setString(2, inscripcion.getIdAtleta());
	        
	        // Manejar entrenador nulo
	        if (inscripcion.getIdEntrenador() != null) {
	            stmt.setString(3, inscripcion.getIdEntrenador());
	        } else {
	            stmt.setNull(3, Types.VARCHAR);
	        }
	        
	        stmt.setDate(4, inscripcion.getFechaIni());
	        stmt.setDate(5, inscripcion.getFechaFin());
	        
	        stmt.execute();
	        
	    } catch (SQLException e) {
	        manejarErrorSQL(e);
	    }
	}
    
    public void eliminarInscripcion(String idAtleta, String nomCompetencia) throws SQLException {
        if (idAtleta == null || idAtleta.isEmpty()) {
            throw new IllegalArgumentException("El ID del atleta es requerido");
        }
        
        if (nomCompetencia == null || nomCompetencia.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la competencia es requerido");
        }

        String sql = "{ call eliminar_inscripcion(?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, idAtleta);
            stmt.setString(2, nomCompetencia);
            
            stmt.execute();
            
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar inscripción: " + e.getMessage(), e);
        }
    }
    
    public void modificarInscripcion(Inscripcion inscripcionOriginal, Inscripcion inscripcionActualizada) throws SQLException {
        // Validaciones
        if (inscripcionOriginal == null || inscripcionActualizada == null) {
            throw new IllegalArgumentException("Las inscripciones no pueden ser nulas");
        }
        
        validarCamposInscripcion(inscripcionActualizada);

        // Llamada al procedimiento almacenado
        String sql = "{ call modificar_inscripcion(?, ?, ?, ?, ?, ?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            // Valores originales (WHERE)
            stmt.setString(1, inscripcionOriginal.getNomCompetencia());
            stmt.setString(2, inscripcionOriginal.getIdAtleta());
            stmt.setString(5, inscripcionOriginal.getIdEntrenador());
            
            // Valores actualizados
            stmt.setDate(3, inscripcionActualizada.getFechaIni());
            stmt.setDate(4, inscripcionActualizada.getFechaFin());
            stmt.setString(6, inscripcionActualizada.getNomCompetencia());
            stmt.setString(7, inscripcionActualizada.getIdAtleta());
            stmt.setString(8, inscripcionActualizada.getIdEntrenador());
            
            stmt.execute();
            
        } catch (SQLException e) {
            manejarErrorSQL(e);
        }
    }
    
    private void validarCamposInscripcion(Inscripcion inscripcion) {
        if (inscripcion.getNomCompetencia() == null || inscripcion.getNomCompetencia().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la competencia es requerido");
        }
        
        if (inscripcion.getIdAtleta() == null || inscripcion.getIdAtleta().isEmpty()) {
            throw new IllegalArgumentException("El ID del atleta es requerido");
        }
        
        if (inscripcion.getFechaIni() == null) {
            throw new IllegalArgumentException("La fecha de inicio es requerida");
        }
        
        if (inscripcion.getFechaFin() == null) {
            throw new IllegalArgumentException("La fecha de fin es requerida");
        }
        
        // Validar longitudes máximas según la tabla
        if (inscripcion.getNomCompetencia().length() > 20) {
            throw new IllegalArgumentException("El nombre de la competencia no puede exceder 20 caracteres");
        }
        
        if (inscripcion.getIdAtleta().length() > 11) {
            throw new IllegalArgumentException("El ID del atleta no puede exceder 11 caracteres");
        }
        
        // Validar ID entrenador solo si no es nulo
        if (inscripcion.getIdEntrenador() != null && inscripcion.getIdEntrenador().length() > 11) {
            throw new IllegalArgumentException("El ID del entrenador no puede exceder 11 caracteres");
        }
    }
    
    private void verificarReferencias(Inscripcion inscripcion) throws SQLException {
        try (DatabaseConnection dbConn = new DatabaseConnection()) {
            Connection conn = dbConn.getConnection();
            
            // Verificar atleta
            if (!existeEnTabla(conn, "\"Atleta\"", "\"id_atleta\"", inscripcion.getIdAtleta())) {
                throw new SQLException("El atleta con ID '" + inscripcion.getIdAtleta() + "' no existe");
            }
            
            // Verificar competencia
            if (!existeEnTabla(conn, "\"Competencia\"", "\"nom_competencia\"", inscripcion.getNomCompetencia())) {
                throw new SQLException("La competencia '" + inscripcion.getNomCompetencia() + "' no existe");
            }
            
            // Verificar entrenador solo si está presente
            if (inscripcion.getIdEntrenador() != null && !inscripcion.getIdEntrenador().isEmpty()) {
                if (!existeEnTabla(conn, "\"Entrenador\"", "\"id_entrenador\"", inscripcion.getIdEntrenador())) {
                    throw new SQLException("El entrenador con ID '" + inscripcion.getIdEntrenador() + "' no existe");
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
    
    private void manejarErrorSQL(SQLException e) throws SQLException {
        if (e.getSQLState() != null) {
            switch (e.getSQLState()) {
                case "23505":
                    throw new SQLException("Ya existe una inscripción con estos datos", e);
                case "23503":
                    throw new SQLException("Violación de integridad referencial (atleta, competencia o entrenador no existe)", e);
                case "23514":
                    throw new SQLException("Violación de reglas de validación", e);
                default:
                    throw new SQLException("Error de base de datos: " + e.getMessage(), e);
            }
        }
        throw e;
    }
    
    public boolean existeInscripcion(String idAtleta, String nomCompetencia) throws SQLException {
        String sql = "SELECT 1 FROM \"Inscripcion\" WHERE \"id_atleta\" = ? AND \"nom_competencia\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, idAtleta);
            stmt.setString(2, nomCompetencia);
            return stmt.executeQuery().next();
        }
    }

	public ArrayList<Inscripcion> obtenerInscripciones() throws SQLException{
		 ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	        String sql = "SELECT * FROM \"Inscripcion\" ";
	        
	        try (DatabaseConnection dbConn = new DatabaseConnection();
	             Statement stmt = dbConn.getConnection().createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            
	            while (rs.next()) {
	                inscripciones.add(new Inscripcion(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(3)));
	            }
	        }
	        return inscripciones;
	}
}