package utils;

import model.*;
import services.*;
import java.sql.SQLException;

public class DatabaseInitializer {
    public static void initializeBaseData() throws SQLException {
        PaisServices paisService = new PaisServices();
        CiudadServices ciudadService = new CiudadServices();
        EntrenadorServices entrenadorService = new EntrenadorServices();
        AtletaServices atletaService = new AtletaServices();
        SedeServices sedeService = new SedeServices();
        DisciplinaServices disciplinaService = new DisciplinaServices();
        CompetenciaServices competenciaService = new CompetenciaServices();


        try {
            // 1. Insertar países
            Pais espana = new Pais("España");
            Pais usa = new Pais("Estados Unidos");
            Pais francia = new Pais("Francia");
            
            paisService.insertarPais(espana);
            paisService.insertarPais(usa);
            paisService.insertarPais(francia);

            // 2. Insertar ciudades
            Ciudad barcelona = new Ciudad("España", "Barcelona");
            Ciudad madrid = new Ciudad("España", "Madrid");
            Ciudad ny = new Ciudad("Estados Unidos", "Nueva York");
            Ciudad paris = new Ciudad("Francia", "Paris");
            
            ciudadService.insertarCiudad(barcelona);
            ciudadService.insertarCiudad(madrid);
            ciudadService.insertarCiudad(ny);
            ciudadService.insertarCiudad(paris);
            
            Disciplina atletismo = new Disciplina("Atletismo");
            Disciplina natacion = new Disciplina("Natación");
            Disciplina gimnasia = new Disciplina("Gimnasia");
            Disciplina ciclismo = new Disciplina("Ciclismo");
            Disciplina tenis = new Disciplina("Tenis");
            
            disciplinaService.insertarDisciplina(atletismo);
            disciplinaService.insertarDisciplina(natacion);
            disciplinaService.insertarDisciplina(gimnasia);
            disciplinaService.insertarDisciplina(ciclismo);
            disciplinaService.insertarDisciplina(tenis);

            // 3. Insertar entrenadores
            Entrenador entrenador1 = new Entrenador("ENT-001", "Juan Pérez", "Calle Mayor", "Atletismo");
            Entrenador entrenador2 = new Entrenador("ENT-002", "Mike Johnson", "5th Avenue 100", "Natación");
            
            entrenadorService.insertarEntrenador(entrenador1);
            entrenadorService.insertarEntrenador(entrenador2);

            // 4. Insertar atletas
            Atleta atleta1 = new Atleta(
                "ATL-001", 
                "Carlos López", 
                'M', 
                "100m lisos", 
                "ENT-001", 
                25, 
                "España"
            );
            
            Atleta atleta2 = new Atleta(
                "ATL-002", 
                "Emma Smith", 
                'F', 
                "Natación", 
                "ENT-002", 
                23, 
                "Estados Unidos"
            );
            
            atletaService.insertarAtleta(atleta1);
            atletaService.insertarAtleta(atleta2);
            
         // 5. Insertar sedes
            Sede sede1 = new Sede("Sede Central", "Madrid");
            Sede sede2 = new Sede("Sede Costa Este", "Nueva York");
            Sede sede3 = new Sede("Sede Europea", "Paris");
            
            sedeService.insertarSede(sede1);
            sedeService.insertarSede(sede2);
            sedeService.insertarSede(sede3);
            
            Competencia competencia1 = new Competencia(
                    "JJOO 2024",
                    "Sede Europea",
                    "Planificada",
                    "26/07/24",
                    "11/08/24",
                    "Atletismo"
                );
                
                Competencia competencia2 = new Competencia(
                    "Mundial Natación",
                    "Sede Costa Este",
                    "En curso",
                    "14/07/23",
                    "30/07/23",
                    "Natación"
                );
                
                Competencia competencia3 = new Competencia(
                    "Roland Garros",
                    "Sede Europea",
                    "Finalizada",
                    "22/05/23",
                    "11/06/23",
                    "Tenis"
                );
                
                Competencia competencia4 = new Competencia(
                    "Vuelta a España",
                    "Sede Central",
                    "Planificada",
                    "26/08/23",
                    "17/09/23",
                    "Ciclismo"
                );
                
                competenciaService.insertarCompetencia(competencia1);
                competenciaService.insertarCompetencia(competencia2);
                competenciaService.insertarCompetencia(competencia3);
                competenciaService.insertarCompetencia(competencia4);


            System.out.println("✅ Datos iniciales cargados correctamente");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al inicializar datos: " + e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Validación fallida: " + e.getMessage());
            throw new SQLException("Error de validación al inicializar datos", e);
        }
    }
}