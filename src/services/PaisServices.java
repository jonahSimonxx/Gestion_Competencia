package services;

	import model.Pais;
	import utils.DatabaseConnection;
	import java.sql.*;

	public class PaisServices {
	    
	    public void insertarPais(Pais pais) throws SQLException {
	        // Validaciones básicas
	        if (pais == null || pais.getNomPais() == null || pais.getNomPais().isEmpty()) {
	            throw new IllegalArgumentException("El nombre del país es requerido");
	        }
	        
	        if (pais.getNomPais().length() > 30) {
	            throw new IllegalArgumentException("El nombre del país no puede exceder 30 caracteres");
	        }

	        // Llamada a la función PostgreSQL
	        String sql = "{ call crear_pais(?) }";
	        
	        try (DatabaseConnection dbConn = new DatabaseConnection();
	             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
	            
	            stmt.setString(1, pais.getNomPais());
	            stmt.execute();
	            
	        } catch (SQLException e) {
	            manejarErrorSQL(e);
	        }
	    }
	    
	    private void manejarErrorSQL(SQLException e) throws SQLException {
	        switch (e.getSQLState()) {
	            case "23505":
	                throw new SQLException("Error: Ya existe un país con este nombre", e);
	            case "23514":
	                throw new SQLException("Error: Violación de reglas de validación", e);
	            default:
	                throw new SQLException("Error de base de datos: " + e.getMessage(), e);
	        }
	    }
	    
	    // Método adicional para verificar existencia
	    public boolean existePais(String nomPais) throws SQLException {
	        String sql = "SELECT 1 FROM \"Pais\" WHERE \"nom_pais\" = ?";
	        try (DatabaseConnection dbConn = new DatabaseConnection();
	             PreparedStatement stmt = dbConn.getConnection().prepareStatement(sql)) {
	            stmt.setString(1, nomPais);
	            return stmt.executeQuery().next();
	        }
	    }
	    
	    public void eliminarPais(String nomPais) throws SQLException {
	        String sql = "{ call eliminar_pais(?) }";
	        
	        try (DatabaseConnection dbConn = new DatabaseConnection();
	             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
	            
	            stmt.setString(1, nomPais);
	            stmt.execute();
	            
	            if (!existePais(nomPais)) {
	                System.out.println("✅ País eliminado correctamente");
	            } else {
	                throw new SQLException("No se pudo eliminar el país");
	            }
	        }
	    }
	    
	    public void modificarPais(String nombreOriginal, Pais paisActualizado) throws SQLException {
	        // Validaciones de longitud
	        if (nombreOriginal == null || nombreOriginal.isEmpty()) {
	            throw new IllegalArgumentException("El nombre original del país es requerido");
	        }
	        if (paisActualizado.getNomPais() == null || paisActualizado.getNomPais().isEmpty()) {
	            throw new IllegalArgumentException("El nuevo nombre del país es requerido");
	        }
	        if (paisActualizado.getNomPais().length() > 30) {
	            throw new IllegalArgumentException("El nombre del país no puede exceder 30 caracteres");
	        }

	        String sql = "{ call modificar_pais(?, ?) }";
	        
	        try (DatabaseConnection dbConn = new DatabaseConnection();
	             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
	            
	            stmt.setString(1, nombreOriginal);
	            stmt.setString(2, paisActualizado.getNomPais());
	            
	            stmt.execute();
	            
	            System.out.println("✅ País modificado: " + nombreOriginal + " → " + paisActualizado.getNomPais());
	            
	        } catch (SQLException e) {
	            String errorMsg = "Error modificando país";
	            if (e.getSQLState() != null) {
	                switch (e.getSQLState()) {
	                    case "23505":
	                        errorMsg = "Ya existe un país con el nombre: " + paisActualizado.getNomPais();
	                        break;
	                    case "23503":
	                        errorMsg = "No se puede modificar. Hay atletas asociados a este país.";
	                        break;
	                }
	            } else if (e.getMessage().contains("demasiado largo")) {
	                errorMsg = "El nombre del país excede el límite de caracteres";
	            }
	            throw new SQLException(errorMsg, e);
	        }
	    }
	}
