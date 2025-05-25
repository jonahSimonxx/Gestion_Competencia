package utils;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCleaner {
    
    public static void limpiarDatos() throws SQLException {
        try (DatabaseConnection dbConn = new DatabaseConnection();
             Statement stmt = dbConn.getConnection().createStatement()) {
            
            // Desactivar restricciones temporalmente
            stmt.execute("SET CONSTRAINTS ALL DEFERRED");
            
            // Borrar datos en orden correcto (primero los que tienen dependencias)
            stmt.execute("DELETE FROM \"Atleta\"");
            stmt.execute("DELETE FROM \"Entrenador\"");
            stmt.execute("DELETE FROM \"Pais\"");
            // Agrega otras tablas si es necesario
            
            // Reactivar restricciones
            stmt.execute("SET CONSTRAINTS ALL IMMEDIATE");
            
            System.out.println("✅ Base de datos limpiada correctamente");
        }
    }
}