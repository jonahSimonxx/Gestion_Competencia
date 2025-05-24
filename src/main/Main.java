package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Atleta;
import services.AtletaServices;

public class Main {

	public static void main(String[] args) {
		
	    // Configura tus credenciales aquí
        String url = "jdbc:postgresql://localhost:5432/ManejoCompetencia";
        String user = "postgres";
        String password = "12345";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("\n✅ ¡Conexión exitosa!");
            System.out.println("=================================");
            System.out.println("Base de datos: " + conn.getCatalog());
            System.out.println("Usuario: " + conn.getMetaData().getUserName());
            
        } catch (SQLException e) {
            System.err.println("\n❌ Error de conexión:");
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Código de error: " + e.getErrorCode());
            System.err.println("Mensaje: " + e.getMessage());
            
            System.err.println("\nPosibles soluciones:");
            
            // Forma correcta de verificar el error de autenticación
            if ("28P01".equals(e.getSQLState())) {
                System.err.println("- Usuario/contraseña incorrectos");
                System.err.println("- Verifica pg_hba.conf si usas autenticación local");
            } 
            else if (e.getErrorCode() == 0) {
                System.err.println("- PostgreSQL no está iniciado");
                System.err.println("- Puerto 5432 bloqueado por firewall");
            }
        }
        
        Atleta[] atletasPrueba = {
                new Atleta("ATL-101", "Carlos Martínez", "M", "Atletismo", "ENT-001", "México", 22),
                new Atleta("ATL-102", "Ana López", "F", "Natación", "ENT-002", "España", 19),
                new Atleta("ATL-103", "Pedro Ramírez", "M", "Ciclismo", "ENT-003", "Colombia", 25),
                new Atleta("ATL-104", "Luisa García", "F", "Gimnasia", "ENT-001", "México", 20)
            };

            AtletaServices servicio = new AtletaServices();
            
            for (Atleta atleta : atletasPrueba) {
                try {
                    servicio.insertarAtleta(atleta);
                    System.out.println("✅ Atleta insertado: " + atleta.getNomCompleto());
                } catch (SQLException e) {
                    System.err.println("❌ Error insertando " + atleta.getNomCompleto() + ": " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("❌ Error general: " + e.getMessage());
                }
            }
    }
	
		}


