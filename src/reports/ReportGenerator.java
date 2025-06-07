package reports;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseConnection;

public class ReportGenerator {
    
    public static ArrayList<ArrayList<String>> generarReporte(String nombreVista) throws SQLException {
    	ArrayList<ArrayList<String>> resultados = new ArrayList<>();
        String sql = "SELECT * FROM \"" + nombreVista + "\"";
        
        try (DatabaseConnection dbConnection = new DatabaseConnection();
             Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            // Agregar nombres de columnas como primera fila
            ArrayList<String> columnas = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnas.add(metaData.getColumnName(i));
            }
            resultados.add(columnas);
            
            // Agregar datos
            while (rs.next()) {
            	ArrayList<String> fila = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    fila.add(rs.getString(i));
                }
                resultados.add(fila);
            }
        }
        return resultados;
    }
}