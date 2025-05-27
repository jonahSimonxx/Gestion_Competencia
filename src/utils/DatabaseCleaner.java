package utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCleaner {
    
    public static void limpiarDatos() throws SQLException {
        try (DatabaseConnection dbConn = new DatabaseConnection();
             Statement stmt = dbConn.getConnection().createStatement()) {
            
            // Desactivar restricciones temporalmente
            stmt.execute("SET CONSTRAINTS ALL DEFERRED");
            
            // Borrar datos en orden correcto (primero los que tienen dependencias)
            // Verificamos y usamos el nombre exacto de la tabla con comillas dobles
            if (tableExists(dbConn.getConnection(), "Ciudad")) {
                stmt.execute("DELETE FROM \"Ciudad\"");
            }
            stmt.execute("DELETE FROM \"Atleta\"");
            stmt.execute("DELETE FROM \"Entrenador\"");
            stmt.execute("DELETE FROM \"Pais\"");
            stmt.execute("DELETE FROM \"Sede\"");
            stmt.execute("DELETE FROM \"Disciplina\"");
            stmt.execute("DELETE FROM \"Competencia\"");
            stmt.execute("DELETE FROM \"Registro\"");
            stmt.execute("DELETE FROM \"Inscripcion\"");
            
            // Reactivar restricciones
            stmt.execute("SET CONSTRAINTS ALL IMMEDIATE");
            
            System.out.println("✅ Base de datos limpiada correctamente");
        }
    }
    
    private static boolean tableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        try (ResultSet rs = meta.getTables(null, null, tableName, new String[]{"TABLE"})) {
            return rs.next();
        }
    }
}