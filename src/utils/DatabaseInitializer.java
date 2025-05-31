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
        RegistroServices registroService = new RegistroServices();
        InscripcionServices inscripcionService = new InscripcionServices();

        try {
            // 1. Insertar países
            Pais espana = new Pais("España");
            Pais usa = new Pais("Estados Unidos");
            Pais francia = new Pais("Francia");
            Pais italia = new Pais("Italia");
            Pais alemania = new Pais("Alemania");
            Pais brasil = new Pais("Brasil");
            
            paisService.insertarPais(espana);
            paisService.insertarPais(usa);
            paisService.insertarPais(francia);
            paisService.insertarPais(italia);
            paisService.insertarPais(alemania);
            paisService.insertarPais(brasil);

            // 2. Insertar ciudades
            Ciudad barcelona = new Ciudad("España", "Barcelona");
            Ciudad madrid = new Ciudad("España", "Madrid");
            Ciudad ny = new Ciudad("Estados Unidos", "Nueva York");
            Ciudad losAngeles = new Ciudad("Estados Unidos", "Los Ángeles");
            Ciudad paris = new Ciudad("Francia", "Paris");
            Ciudad roma = new Ciudad("Italia", "Roma");
            Ciudad berlin = new Ciudad("Alemania", "Berlín");
            Ciudad rio = new Ciudad("Brasil", "Río de Janeiro");
            
            ciudadService.insertarCiudad(barcelona);
            ciudadService.insertarCiudad(madrid);
            ciudadService.insertarCiudad(ny);
            ciudadService.insertarCiudad(losAngeles);
            ciudadService.insertarCiudad(paris);
            ciudadService.insertarCiudad(roma);
            ciudadService.insertarCiudad(berlin);
            ciudadService.insertarCiudad(rio);
            
            // 3. Insertar disciplinas
            Disciplina atletismo = new Disciplina("Atletismo");
            Disciplina natacion = new Disciplina("Natación");
            Disciplina gimnasia = new Disciplina("Gimnasia");
            Disciplina ciclismo = new Disciplina("Ciclismo");
            Disciplina tenis = new Disciplina("Tenis");
            Disciplina baloncesto = new Disciplina("Baloncesto");
            Disciplina voleibol = new Disciplina("Voleibol");
            
            disciplinaService.insertarDisciplina(atletismo);
            disciplinaService.insertarDisciplina(natacion);
            disciplinaService.insertarDisciplina(gimnasia);
            disciplinaService.insertarDisciplina(ciclismo);
            disciplinaService.insertarDisciplina(tenis);
            disciplinaService.insertarDisciplina(baloncesto);
            disciplinaService.insertarDisciplina(voleibol);

            // 4. Insertar entrenadores
            Entrenador entrenador1 = new Entrenador("ENT-001", "Juan Pérez", "Calle Mayor", "Atletismo");
            Entrenador entrenador2 = new Entrenador("ENT-002", "Mike Johnson", "5th Avenue 100", "Natación");
            Entrenador entrenador3 = new Entrenador("ENT-003", "Marie Dubois", "Rue de Rivoli", "Gimnasia");
            Entrenador entrenador4 = new Entrenador("ENT-004", "Luca Rossi", "Via del Corso", "Ciclismo");
            Entrenador entrenador5 = new Entrenador("ENT-005", "Anna Müller", "Friedrichstraße", "Tenis");
            
            entrenadorService.insertarEntrenador(entrenador1);
            entrenadorService.insertarEntrenador(entrenador2);
            entrenadorService.insertarEntrenador(entrenador3);
            entrenadorService.insertarEntrenador(entrenador4);
            entrenadorService.insertarEntrenador(entrenador5);

            // 5. Insertar atletas (ahora con más diversidad)
            Atleta atleta1 = new Atleta("001", "Carlos López", 'M', "100m lisos", "ENT-001", 25, "España");
            Atleta atleta2 = new Atleta("002", "Emma Smith", 'F', "Natación", "ENT-002", 23, "Estados Unidos");
            Atleta atleta3 = new Atleta("003", "Sophie Martin", 'F', "Gimnasia", "ENT-003", 21, "Francia");
            Atleta atleta4 = new Atleta("004", "Marco Bianchi", 'M', "Ciclismo", "ENT-004", 28, "Italia");
            Atleta atleta5 = new Atleta("005", "Thomas Schmidt", 'M', "Tenis", "ENT-005", 26, "Alemania");
            Atleta atleta6 = new Atleta("006", "Ana Silva", 'F', "100m lisos", null, 24, "Brasil");
            Atleta atleta7 = new Atleta("007", "David Wilson", 'M', "Natación", "ENT-002", 27, "Estados Unidos");
            Atleta atleta8 = new Atleta("008", "Laura García", 'F', "Gimnasia", "ENT-003", 22, "España");
            Atleta atleta9 = new Atleta("009", "Paul Dubois", 'M', "Ciclismo", null, 29, "Francia");
            Atleta atleta10 = new Atleta("010", "Giulia Conti", 'F', "Tenis", "ENT-005", 25, "Italia");
            
            atletaService.insertarAtleta(atleta1);
            atletaService.insertarAtleta(atleta2);
            atletaService.insertarAtleta(atleta3);
            atletaService.insertarAtleta(atleta4);
            atletaService.insertarAtleta(atleta5);
            atletaService.insertarAtleta(atleta6);
            atletaService.insertarAtleta(atleta7);
            atletaService.insertarAtleta(atleta8);
            atletaService.insertarAtleta(atleta9);
            atletaService.insertarAtleta(atleta10);
            
            // 6. Insertar sedes
            Sede sede1 = new Sede("Sede Central", "Madrid");
            Sede sede2 = new Sede("Sede Costa Este", "Nueva York");
            Sede sede3 = new Sede("Sede Europea", "Paris");
            Sede sede4 = new Sede("Sede Mediterránea", "Roma");
            Sede sede5 = new Sede("Sede Alemana", "Berlín");
            Sede sede6 = new Sede("Sede Sudamericana", "Río de Janeiro");
            
            sedeService.insertarSede(sede1);
            sedeService.insertarSede(sede2);
            sedeService.insertarSede(sede3);
            sedeService.insertarSede(sede4);
            sedeService.insertarSede(sede5);
            sedeService.insertarSede(sede6);
            
            // 7. Competencias con diferentes estados y fechas
            Competencia competencia1 = new Competencia(
                "JJOO 2024", "Sede Europea", "Planificada", "26/07/24", "11/08/24", "Atletismo");
            Competencia competencia2 = new Competencia(
                "Mundial Natación", "Sede Costa Este", "En curso", "14/07/23", "30/07/23", "Natación");
            Competencia competencia3 = new Competencia(
                "Roland Garros", "Sede Europea", "Finalizada", "22/05/23", "11/06/23", "Tenis");
            Competencia competencia4 = new Competencia(
                "Vuelta a España", "Sede Central", "Planificada", "26/08/23", "17/09/23", "Ciclismo");
            Competencia competencia5 = new Competencia(
                "Campeonato Gimnasia", "Sede Alemana", "Finalizada", "01/03/23", "15/03/23", "Gimnasia");
            Competencia competencia6 = new Competencia(
                "Torneo Baloncesto", "Sede Sudamericana", "Cancelada", "10/09/23", "25/09/23", "Baloncesto");
            
            competenciaService.insertarCompetencia(competencia1);
            competenciaService.insertarCompetencia(competencia2);
            competenciaService.insertarCompetencia(competencia3);
            competenciaService.insertarCompetencia(competencia4);
            competenciaService.insertarCompetencia(competencia5);
            competenciaService.insertarCompetencia(competencia6);
            
            // 8. Registros de resultados 
            Registro registro1 = new Registro("Atletismo", "9.87s", "001", "JJOO 2024", 1);
            Registro registro2 = new Registro("Natación", "52.43s", "002", "Mundial Natación", 3);
            Registro registro3 = new Registro("Gimnasia", "9.5pts", "003", "Campeonato Gimnasia", 1);
            Registro registro4 = new Registro("Tenis", "6-3, 6-4", "005", "Roland Garros", 3);
            Registro registro5 = new Registro("Natación", "53.21s", "007", "Mundial Natación", 2);
            Registro registro6 = new Registro("Gimnasia", "9.2pts", "008", "Campeonato Gimnasia", 2);
            Registro registro7 = new Registro("Ciclismo", "4h22m", "004", "Vuelta a España", 18); 
            Registro registro8 = new Registro("Tenis", "6-2, 6-3", "010", "Roland Garros", 1);
            
            registroService.insertarRegistro(registro1);
            registroService.insertarRegistro(registro2);
            registroService.insertarRegistro(registro3);
            registroService.insertarRegistro(registro4);
            registroService.insertarRegistro(registro5);
            registroService.insertarRegistro(registro6);
            registroService.insertarRegistro(registro7);
            registroService.insertarRegistro(registro8);
            
            // 9. Inscripciones (más completas)
            Inscripcion inscripcion1 = new Inscripcion("JJOO 2024", "001", "01/06/24", "25/07/24", "ENT-001");
            Inscripcion inscripcion2 = new Inscripcion("Mundial Natación", "002", "01/05/23", "13/07/23", "ENT-002");
            Inscripcion inscripcion3 = new Inscripcion("Campeonato Gimnasia", "003", "01/02/23", "28/02/23", "ENT-003");
            Inscripcion inscripcion4 = new Inscripcion("Roland Garros", "005", "01/04/23", "21/05/23", "ENT-005");
            Inscripcion inscripcion5 = new Inscripcion("Mundial Natación", "007", "01/05/23", "13/07/23", "ENT-002");
            Inscripcion inscripcion6 = new Inscripcion("Campeonato Gimnasia", "008", "01/02/23", "28/02/23", "ENT-003");
            Inscripcion inscripcion7 = new Inscripcion("Vuelta a España", "004", "01/07/23", "25/08/23", "ENT-004");
            Inscripcion inscripcion8 = new Inscripcion("Roland Garros", "010", "01/04/23", "21/05/23", "ENT-005");
            Inscripcion inscripcion9 = new Inscripcion("JJOO 2024", "006", "01/06/24", "25/07/24", null); // Sin entrenador
            Inscripcion inscripcion10 = new Inscripcion("Vuelta a España", "009", "01/07/23", "25/08/23", null); // Sin entrenador
            
            inscripcionService.insertarInscripcion(inscripcion1);
            inscripcionService.insertarInscripcion(inscripcion2);
            inscripcionService.insertarInscripcion(inscripcion3);
            inscripcionService.insertarInscripcion(inscripcion4);
            inscripcionService.insertarInscripcion(inscripcion5);
            inscripcionService.insertarInscripcion(inscripcion6);
            inscripcionService.insertarInscripcion(inscripcion7);
            inscripcionService.insertarInscripcion(inscripcion8);
            inscripcionService.insertarInscripcion(inscripcion9);
            inscripcionService.insertarInscripcion(inscripcion10);

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