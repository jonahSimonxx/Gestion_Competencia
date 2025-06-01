package utils;

import java.sql.*;
import java.util.List;
import reports.ReportGenerator;

public class ConsoleTester {
    
    public static void mostrarDatosConsola() {
        System.out.println("\n=== INICIANDO PRUEBAS DE CONSOLA ===");
        
        // 1. Probar conexión
        if (!testConexion()) {
            return; // Si falla la conexión, no continuar
        }
        
        // 2. Mostrar tablas disponibles
        mostrarTablas();
        
        // 3. Mostrar reporte de ejemplo
        mostrarReporteEjemplo();
    }
    
    private static boolean testConexion() {
        System.out.println("\n[1/3] Probando conexión a la BD...");
        try (DatabaseConnection dbConn = new DatabaseConnection()) {
            Connection conn = dbConn.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("✓ Conexión exitosa a: " + meta.getURL());
            System.out.println("   Producto: " + meta.getDatabaseProductName());
            System.out.println("   Usuario: " + meta.getUserName());
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error de conexión:");
            e.printStackTrace();
            return false;
        }
    }
    
    private static void mostrarTablas() {
        System.out.println("\n[2/3] Listando tablas disponibles...");
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             Connection conn = dbConn.getConnection()) {
            
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, "public", "%", 
                                  new String[]{"TABLE", "VIEW"});
            
            if (!tables.isBeforeFirst()) {
                System.out.println("No se encontraron tablas en el esquema 'public'");
            } else {
                System.out.println("Tablas/Vistas:");
                System.out.println("----------------------------");
                while (tables.next()) {
                    System.out.printf("- %-25s (%s)\n", 
                        tables.getString("TABLE_NAME"), 
                        tables.getString("TABLE_TYPE"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tablas:");
            e.printStackTrace();
        }
    }
    
    private static void mostrarReporteEjemplo() {
        System.out.println("\n[3/3] Mostrando reporte de ejemplo...");
        String nombreReporte = "reporte_atletas_incompletos";
        
        try {
            List<List<String>> reporte = ReportGenerator.generarReporte(nombreReporte);
            
            if (reporte.isEmpty()) {
                System.out.println("El reporte está vacío");
                return;
            }
            
            // Encabezados
            System.out.println("\n" + String.join(" | ", reporte.get(0)));
            System.out.println("----------------------------------------");
            
            // Datos
            for (int i = 1; i < reporte.size(); i++) {
                System.out.println(String.join(" | ", reporte.get(i)));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al generar reporte '" + nombreReporte + "':");
            e.printStackTrace();
            System.err.println("Posibles causas:");
            System.err.println("- La vista no existe");
            System.err.println("- Problemas de permisos");
            System.err.println("- Error en la consulta SQL");
        }
    }
}