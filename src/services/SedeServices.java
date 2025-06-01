package services;

import model.Sede;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class SedeServices {
    
    public void insertarSede(Sede sede) throws SQLException {
        // Validaciones básicas
        if (sede == null) {
            throw new IllegalArgumentException("El objeto sede no puede ser nulo");
        }
        
        if (sede.getNomSede() == null || sede.getNomSede().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sede es requerido");
        }
        
        if (sede.getNomCiudad() == null || sede.getNomCiudad().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad es requerido");
        }

        if (sede.getNomSede().length() > 20) {
            throw new IllegalArgumentException("El nombre de la sede no puede exceder 20 caracteres");
        }

        if (sede.getNomCiudad().length() > 20) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede exceder 20 caracteres");
        }

        // Llamada a la función PostgreSQL
        String sql = "{ call crear_sede(?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, sede.getNomSede());
            stmt.setString(2, sede.getNomCiudad());
            
            stmt.execute();
            
        } catch (SQLException e) {
            manejarErrorSQL(e);
        }
    }
    
    public void eliminarSede(String nomSede) throws SQLException {
        if (nomSede == null || nomSede.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sede es requerido");
        }

        String sql = "{ call eliminar_sede(?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, nomSede);
            stmt.execute();
            
        } catch (SQLException e) {
            if (e.getSQLState() != null && e.getSQLState().equals("23503")) {
                throw new SQLException("No se puede eliminar la sede porque tiene registros relacionados", e);
            }
            throw e;
        }
    }
    
    public void modificarSede(String nomSedeOriginal, Sede sedeActualizada) throws SQLException {
        // Validaciones
        if (nomSedeOriginal == null || nomSedeOriginal.isEmpty()) {
            throw new IllegalArgumentException("El nombre original de la sede es requerido");
        }
        
        if (sedeActualizada == null) {
            throw new IllegalArgumentException("Los datos actualizados de la sede son requeridos");
        }

        if (sedeActualizada.getNomSede() == null || sedeActualizada.getNomSede().isEmpty()) {
            throw new IllegalArgumentException("El nuevo nombre de la sede es requerido");
        }

        if (sedeActualizada.getNomCiudad() == null || sedeActualizada.getNomCiudad().isEmpty()) {
            throw new IllegalArgumentException("La ciudad es requerida");
        }

        if (sedeActualizada.getNomSede().length() > 20) {
            throw new IllegalArgumentException("El nombre de la sede no puede exceder 20 caracteres");
        }

        if (sedeActualizada.getNomCiudad().length() > 20) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede exceder 20 caracteres");
        }

        // Llamada al procedimiento almacenado
        String sql = "{ call modificar_sede(?, ?, ?) }";
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, nomSedeOriginal);
            stmt.setString(2, sedeActualizada.getNomCiudad());
            stmt.setString(3, sedeActualizada.getNomSede());
            
            stmt.execute();
            
        } catch (SQLException e) {
            if (e.getSQLState() != null) {
                switch (e.getSQLState()) {
                    case "23505":
                        throw new SQLException("Ya existe una sede con este nombre", e);
                    case "23503":
                        throw new SQLException("La ciudad especificada no existe", e);
                }
            }
            throw new SQLException("Error al modificar sede: " + e.getMessage(), e);
        }
    }
    
    private void manejarErrorSQL(SQLException e) throws SQLException {
        if (e.getSQLState() != null) {
            switch (e.getSQLState()) {
                case "23505":
                    throw new SQLException("Ya existe una sede con este nombre", e);
                case "23503":
                    throw new SQLException("La ciudad especificada no existe", e);
                case "23514":
                    throw new SQLException("Violación de reglas de validación", e);
                default:
                    throw new SQLException("Error de base de datos: " + e.getMessage(), e);
            }
        }
        throw e;
    }
    
    // Método adicional para verificar existencia por nombre
    public boolean existeSede(String nomSede) throws SQLException {
        String sql = "SELECT 1 FROM \"Sede\" WHERE \"nom_sede\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nomSede);
            return stmt.executeQuery().next();
        }
    }
    
    // Método para obtener una sede por nombre
    public Sede obtenerSede(String nomSede) throws SQLException {
        String sql = "SELECT * FROM \"Sede\" WHERE \"nom_sede\" = ?";
        try (DatabaseConnection dbConn = new DatabaseConnection();
             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nomSede);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Sede(
                    rs.getString("nom_sede"),
                    rs.getString("nom_ciudad")
                );
            }
            return null;
        }
        
        
    }
    
    public ArrayList<Sede> obtenerTodasSedes() throws SQLException {
		 ArrayList<Sede> sedes = new ArrayList<Sede>();
	        String sql = "SELECT * FROM \"Sede\""; 
	        
	        try (DatabaseConnection dbConn = new DatabaseConnection();
	             Statement stmt = dbConn.getConnection().createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            
	            while (rs.next()) {
	                Sede sede = new Sede(rs.getString(1),rs.getString(2));
	                sedes.add(sede);
	            }
	        }
	        return sedes;
	}
}