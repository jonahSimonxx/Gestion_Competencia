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
        UsuarioServices usuarioService = new UsuarioServices();

        try {
            // 1. Insertar países
            Pais espana = new Pais("España");
            Pais usa = new Pais("Estados Unidos");
            Pais francia = new Pais("Francia");
            Pais alemania = new Pais("Alemania");
            Pais italia = new Pais("Italia");
            Pais brasil = new Pais("Brasil");
            Pais japon = new Pais("Japón");
            Pais australia = new Pais("Australia");
            
            paisService.insertarPais(espana);
            paisService.insertarPais(usa);
            paisService.insertarPais(francia);
            paisService.insertarPais(alemania);
            paisService.insertarPais(italia);
            paisService.insertarPais(brasil);
            paisService.insertarPais(japon);
            paisService.insertarPais(australia);

            // 2. Insertar ciudades
            Ciudad barcelona = new Ciudad("España", "Barcelona");
            Ciudad madrid = new Ciudad("España", "Madrid");
            Ciudad ny = new Ciudad("Estados Unidos", "Nueva York");
            Ciudad losAngeles = new Ciudad("Estados Unidos", "Los Ángeles");
            Ciudad paris = new Ciudad("Francia", "Paris");
            Ciudad lyon = new Ciudad("Francia", "Lyon");
            Ciudad berlin = new Ciudad("Alemania", "Berlín");
            Ciudad munich = new Ciudad("Alemania", "Múnich");
            Ciudad roma = new Ciudad("Italia", "Roma");
            Ciudad milan = new Ciudad("Italia", "Milán");
            Ciudad rio = new Ciudad("Brasil", "Río de Janeiro");
            Ciudad saoPaulo = new Ciudad("Brasil", "São Paulo");
            Ciudad tokio = new Ciudad("Japón", "Tokio");
            Ciudad osaka = new Ciudad("Japón", "Osaka");
            Ciudad sydney = new Ciudad("Australia", "Sídney");
            Ciudad melbourne = new Ciudad("Australia", "Melbourne");
            
            ciudadService.insertarCiudad(barcelona);
            ciudadService.insertarCiudad(madrid);
            ciudadService.insertarCiudad(ny);
            ciudadService.insertarCiudad(losAngeles);
            ciudadService.insertarCiudad(paris);
            ciudadService.insertarCiudad(lyon);
            ciudadService.insertarCiudad(berlin);
            ciudadService.insertarCiudad(munich);
            ciudadService.insertarCiudad(roma);
            ciudadService.insertarCiudad(milan);
            ciudadService.insertarCiudad(rio);
            ciudadService.insertarCiudad(saoPaulo);
            ciudadService.insertarCiudad(tokio);
            ciudadService.insertarCiudad(osaka);
            ciudadService.insertarCiudad(sydney);
            ciudadService.insertarCiudad(melbourne);

            // 3. Insertar disciplinas
            Disciplina atletismo = new Disciplina("Atletismo");
            Disciplina natacion = new Disciplina("Natación");
            Disciplina gimnasia = new Disciplina("Gimnasia");
            Disciplina ciclismo = new Disciplina("Ciclismo");
            Disciplina tenis = new Disciplina("Tenis");
            Disciplina baloncesto = new Disciplina("Baloncesto");
            Disciplina voleibol = new Disciplina("Voleibol");
            Disciplina boxeo = new Disciplina("Boxeo");
            Disciplina judo = new Disciplina("Judo");
            Disciplina esgrima = new Disciplina("Esgrima");
            
            disciplinaService.insertarDisciplina(atletismo);
            disciplinaService.insertarDisciplina(natacion);
            disciplinaService.insertarDisciplina(gimnasia);
            disciplinaService.insertarDisciplina(ciclismo);
            disciplinaService.insertarDisciplina(tenis);
            disciplinaService.insertarDisciplina(baloncesto);
            disciplinaService.insertarDisciplina(voleibol);
            disciplinaService.insertarDisciplina(boxeo);
            disciplinaService.insertarDisciplina(judo);
            disciplinaService.insertarDisciplina(esgrima);

            // 4. Insertar entrenadores
            Entrenador entrenador1 = new Entrenador("ENT-001", "Juan Pérez", "Calle Mayor", "Atletismo");
            Entrenador entrenador2 = new Entrenador("ENT-002", "Mike Johnson", "5th Avenue 100", "Natación");
            Entrenador entrenador3 = new Entrenador("ENT-003", "Sophie Martin", "Rue de Paris", "Gimnasia");
            Entrenador entrenador4 = new Entrenador("ENT-004", "Hans Müller", "Berliner Straße", "Ciclismo");
            Entrenador entrenador5 = new Entrenador("ENT-005", "Maria Rossi", "Via Roma", "Tenis");
            Entrenador entrenador6 = new Entrenador("ENT-006", "Carlos Silva", "Avenida Brasil", "Baloncesto");
            Entrenador entrenador7 = new Entrenador("ENT-007", "Yuki Tanaka", "Sakura Street", "Judo");
            Entrenador entrenador8 = new Entrenador("ENT-008", "Emma Wilson", "Bondi Beach", "Esgrima");
            
            entrenadorService.insertarEntrenador(entrenador1);
            entrenadorService.insertarEntrenador(entrenador2);
            entrenadorService.insertarEntrenador(entrenador3);
            entrenadorService.insertarEntrenador(entrenador4);
            entrenadorService.insertarEntrenador(entrenador5);
            entrenadorService.insertarEntrenador(entrenador6);
            entrenadorService.insertarEntrenador(entrenador7);
            entrenadorService.insertarEntrenador(entrenador8);

            // 5. Insertar atletas (3 por país para tener datos significativos)
            // España
            Atleta atleta1 = new Atleta("ATL-001", "Carlos López", 'M', "100m lisos", "ENT-001", 25, "España");
            Atleta atleta2 = new Atleta("ATL-002", "Ana García", 'F', "Maratón", "ENT-001", 28, "España");
            Atleta atleta3 = new Atleta("ATL-003", "David Ruiz", 'M', "Salto de altura", "ENT-001", 22, "España");
            
            // Estados Unidos
            Atleta atleta4 = new Atleta("ATL-004", "Emma Smith", 'F', "Natación", "ENT-002", 23, "Estados Unidos");
            Atleta atleta5 = new Atleta("ATL-005", "Michael Brown", 'M', "Baloncesto", "ENT-006", 26, "Estados Unidos");
            Atleta atleta6 = new Atleta("ATL-006", "Sarah Johnson", 'F', "Gimnasia", "ENT-003", 19, "Estados Unidos");
            
            // Francia
            Atleta atleta7 = new Atleta("ATL-007", "Pierre Dubois", 'M', "Ciclismo", "ENT-004", 27, "Francia");
            Atleta atleta8 = new Atleta("ATL-008", "Claire Martin", 'F', "Esgrima", "ENT-008", 24, "Francia");
            Atleta atleta9 = new Atleta("ATL-009", "Luc Bernard", 'M', "Boxeo", null, 29, "Francia");
            
            // Alemania
            Atleta atleta10 = new Atleta("ATL-010", "Klaus Schmidt", 'M', "Judo", "ENT-007", 30, "Alemania");
            Atleta atleta11 = new Atleta("ATL-011", "Anna Weber", 'F', "Tenis", "ENT-005", 21, "Alemania");
            Atleta atleta12 = new Atleta("ATL-012", "Thomas Müller", 'M', "Voleibol", null, 25, "Alemania");
            
            // Italia
            Atleta atleta13 = new Atleta("ATL-013", "Giovanni Rossi", 'M', "Tenis", "ENT-005", 28, "Italia");
            Atleta atleta14 = new Atleta("ATL-014", "Sofia Conti", 'F', "Natación", "ENT-002", 20, "Italia");
            Atleta atleta15 = new Atleta("ATL-015", "Marco Bianchi", 'M', "Ciclismo", "ENT-004", 26, "Italia");
            
            // Brasil
            Atleta atleta16 = new Atleta("ATL-016", "Pedro Silva", 'M', "Baloncesto", "ENT-006", 27, "Brasil");
            Atleta atleta17 = new Atleta("ATL-017", "Ana Oliveira", 'F', "Voleibol", null, 22, "Brasil");
            Atleta atleta18 = new Atleta("ATL-018", "Carlos Santos", 'M', "Atletismo", "ENT-001", 24, "Brasil");
            
            // Japón
            Atleta atleta19 = new Atleta("ATL-019", "Takeshi Yamamoto", 'M', "Judo", "ENT-007", 29, "Japón");
            Atleta atleta20 = new Atleta("ATL-020", "Yuki Tanaka", 'F', "Gimnasia", "ENT-003", 18, "Japón");
            Atleta atleta21 = new Atleta("ATL-021", "Hiroshi Sato", 'M', "Esgrima", "ENT-008", 25, "Japón");
            
            // Australia
            Atleta atleta22 = new Atleta("ATL-022", "Jack Wilson", 'M', "Natación", "ENT-002", 23, "Australia");
            Atleta atleta23 = new Atleta("ATL-023", "Emily Clark", 'F', "Tenis", "ENT-005", 21, "Australia");
            Atleta atleta24 = new Atleta("ATL-024", "Noah Taylor", 'M', "Atletismo", "ENT-001", 26, "Australia");
            
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
            atletaService.insertarAtleta(atleta11);
            atletaService.insertarAtleta(atleta12);
            atletaService.insertarAtleta(atleta13);
            atletaService.insertarAtleta(atleta14);
            atletaService.insertarAtleta(atleta15);
            atletaService.insertarAtleta(atleta16);
            atletaService.insertarAtleta(atleta17);
            atletaService.insertarAtleta(atleta18);
            atletaService.insertarAtleta(atleta19);
            atletaService.insertarAtleta(atleta20);
            atletaService.insertarAtleta(atleta21);
            atletaService.insertarAtleta(atleta22);
            atletaService.insertarAtleta(atleta23);
            atletaService.insertarAtleta(atleta24);
            
            // 6. Insertar sedes
            Sede sede1 = new Sede("Sede Central", "Madrid");
            Sede sede2 = new Sede("Sede Costa Este", "Nueva York");
            Sede sede3 = new Sede("Sede Europea", "Paris");
            Sede sede4 = new Sede("Sede Asiática", "Tokio");
            Sede sede5 = new Sede("Sede Oceánica", "Sídney");
            Sede sede6 = new Sede("Sede Sudamericana", "Río de Janeiro");
            Sede sede7 = new Sede("Sede Mediterránea", "Roma");
            Sede sede8 = new Sede("Sede Nórdica", "Berlín");
            Sede sedeBarcelona1 = new Sede("Estadio Olímpico", "Barcelona");
            Sede sedeBarcelona2 = new Sede("Palau Sant Jordi", "Barcelona");
            Sede sedeMadrid1 = new Sede("Wanda Metropolitano", "Madrid");
            Sede sedeMadrid2 = new Sede("Palacio de Deportes", "Madrid");
            Sede sedeMadrid3 = new Sede("Caja Mágica", "Madrid");
            Sede sedeNY1 = new Sede("M. Square Garden", "Nueva York");
            Sede sedeNY2 = new Sede("Arthur Ashe Stadium", "Nueva York");
            Sede sedeLA1 = new Sede("Staples Center", "Los Ángeles");
            Sede sedeLA2 = new Sede("Memorial Coliseum", "Los Ángeles");
            Sede sedeParis1 = new Sede("Stade de France", "Paris");
            Sede sedeParis2 = new Sede("AccorHotels Arena", "Paris");
            Sede sedeLyon1 = new Sede("Groupama Stadium", "Lyon");
            Sede sedeLyon2 = new Sede("Halle Tony Garnier", "Lyon");
            Sede sedeBerlin1 = new Sede("Olympiastadion", "Berlín");
            Sede sedeBerlin2 = new Sede("Mercedes-Benz Arena", "Berlín");
            Sede sedeMunich1 = new Sede("Allianz Arena", "Múnich");
            Sede sedeMunich2 = new Sede("Olympiahalle", "Múnich");
            Sede sedeRoma1 = new Sede("Stadio Olimpico", "Roma");
            Sede sedeRoma2 = new Sede("Palazzo dello Sport", "Roma");
            Sede sedeMilan1 = new Sede("San Siro", "Milán");
            Sede sedeMilan2 = new Sede("Mediolanum Forum", "Milán");
            Sede sedeRio1 = new Sede("Estádio do Maracanã", "Río de Janeiro");
            Sede sedeRio2 = new Sede("Jeunesse Arena", "Río de Janeiro");
            Sede sedeSaoPaulo1 = new Sede("Allianz Parque", "São Paulo");
            Sede sedeSaoPaulo2 = new Sede("Ginásio do Ibira", "São Paulo");
            Sede sedeTokio1 = new Sede("Olimpic Stadium", "Tokio");
            Sede sedeTokio2 = new Sede("Nippon Budokan", "Tokio");
            Sede sedeOsaka1 = new Sede("Osaka-jō Hall", "Osaka");
            Sede sedeOsaka2 = new Sede("Kyocera Dome", "Osaka");
            Sede sedeSydney1 = new Sede("Stadium Australia", "Sídney");
            Sede sedeSydney2 = new Sede("Qudos Bank Arena", "Sídney");
            Sede sedeMelbourne1 = new Sede("Cricket Ground", "Melbourne");
            Sede sedeMelbourne2 = new Sede("Rod Laver Arena", "Melbourne");
            
            sedeService.insertarSede(sede1);
            sedeService.insertarSede(sede2);
            sedeService.insertarSede(sede3);
            sedeService.insertarSede(sede4);
            sedeService.insertarSede(sede5);
            sedeService.insertarSede(sede6);
            sedeService.insertarSede(sede7);
            sedeService.insertarSede(sede8);
            sedeService.insertarSede(sedeBarcelona1);
            sedeService.insertarSede(sedeBarcelona2);
            sedeService.insertarSede(sedeMadrid1);
            sedeService.insertarSede(sedeMadrid2);
            sedeService.insertarSede(sedeMadrid3);
            sedeService.insertarSede(sedeNY1);
            sedeService.insertarSede(sedeNY2);
            sedeService.insertarSede(sedeLA1);
            sedeService.insertarSede(sedeLA2);
            sedeService.insertarSede(sedeParis1);
            sedeService.insertarSede(sedeParis2);
            sedeService.insertarSede(sedeLyon1);
            sedeService.insertarSede(sedeLyon2);
            sedeService.insertarSede(sedeBerlin1);
            sedeService.insertarSede(sedeBerlin2);
            sedeService.insertarSede(sedeMunich1);
            sedeService.insertarSede(sedeMunich2);
            sedeService.insertarSede(sedeRoma1);
            sedeService.insertarSede(sedeRoma2);
            sedeService.insertarSede(sedeMilan1);
            sedeService.insertarSede(sedeMilan2);
            sedeService.insertarSede(sedeRio1);
            sedeService.insertarSede(sedeRio2);
            sedeService.insertarSede(sedeSaoPaulo1);
            sedeService.insertarSede(sedeSaoPaulo2);
            sedeService.insertarSede(sedeTokio1);
            sedeService.insertarSede(sedeTokio2);
            sedeService.insertarSede(sedeOsaka1);
            sedeService.insertarSede(sedeOsaka2);
            sedeService.insertarSede(sedeSydney1);
            sedeService.insertarSede(sedeSydney2);
            sedeService.insertarSede(sedeMelbourne1);
            sedeService.insertarSede(sedeMelbourne2);
            
            // 7. Insertar competencias (varias por sede y con diferentes estados)
            Competencia competencia1 = new Competencia(
                "JJOO 2024",
                "Sede Europea",
                "Planificada",
                "2024-07-26",
                "2024-08-11"
            );
            
            Competencia competencia2 = new Competencia(
                "Mundial Natación",
                "Sede Costa Este",
                "En curso",
                "2023-07-14",
                "2023-07-30"
            );
            
            Competencia competencia3 = new Competencia(
                "Roland Garros",
                "Sede Europea",
                "Finalizada",
                "2023-05-22",
                "2023-06-11"
            );
            
            Competencia competencia4 = new Competencia(
                "Vuelta a España",
                "Sede Central",
                "Planificada",
                "2023-08-20",
                "2023-09-17"
            );
            
            Competencia competencia5 = new Competencia(
                "GP Asiático de Judo",
                "Sede Asiática",
                "Finalizada",
                "2023-04-10",
                "2023-04-15"
            );
            
            Competencia competencia6 = new Competencia(
                "Abierto de Australia",
                "Sede Oceánica",
                "Finalizada",
                "2023-01-16",
                "2023-01-29"
            );
            
            Competencia competencia7 = new Competencia(
                "Copa A. de Voley",
                "Sede Sudamericana",
                "En curso",
                "2023-08-01",
                "2023-08-15"
            );
            
            Competencia competencia8 = new Competencia(
                "Torneo de Esgrima UE",
                "Sede Europea",
                "Planificada",
                "2023-10-05",
                "2023-10-10"
            );
            
            Competencia competencia9 = new Competencia(
                "Mundial de Atletismo",
                "Sede Nórdica",
                "Finalizada",
                "2023-07-15",
                "2023-07-23"
            );
            
            Competencia competencia10 = new Competencia(
                "Liga de Baloncesto",
                "Sede Mediterránea",
                "En curso",
                "2023-09-01",
                "2023-09-30"
            );
            
            Competencia competenciaBarcelona1 = new Competencia(
            	    "Copa Atletismo", 
            	    "Estadio Olímpico", 
            	    "Finalizada", 
            	    "2023-05-10", 
            	    "2023-05-12"
            	);
            	Competencia competenciaBarcelona2 = new Competencia(
            	    "Superliga Europea", 
            	    "Estadio Olímpico", 
            	    "Planificada", 
            	    "2024-06-15", 
            	    "2024-06-20"
            	);

            	// Madrid - Wanda Metropolitano
            	Competencia competenciaMadrid1 = new Competencia(
            	    "GP de Madrid", 
            	    "Wanda Metropolitano", 
            	    "En curso", 
            	    "2023-08-01", 
            	    "2023-08-10"
            	);
            	Competencia competenciaMadrid2 = new Competencia(
            	    "Copa del Rey", 
            	    "Wanda Metropolitano", 
            	    "Finalizada", 
            	    "2023-04-15", 
            	    "2023-04-17"
            	);

            	// Nueva York - Arthur Ashe Stadium
            	Competencia competenciaNY1 = new Competencia(
            	    "US Open", 
            	    "Arthur Ashe Stadium", 
            	    "Planificada", 
            	    "2023-08-28", 
            	    "2023-09-10"
            	);

            	// París - Stade de France
            	Competencia competenciaParis1 = new Competencia(
            	    "Meeting de Paris", 
            	    "Stade de France", 
            	    "Finalizada", 
            	    "2023-06-09", 
            	    "2023-06-09"
            	);
            	Competencia competenciaParis2 = new Competencia(
            	    "Copa Francia", 
            	    "Stade de France", 
            	    "Planificada", 
            	    "2024-07-01", 
            	    "2024-07-03"
            	);
            
            competenciaService.insertarCompetencia(competencia1);
            competenciaService.insertarCompetencia(competencia2);
            competenciaService.insertarCompetencia(competencia3);
            competenciaService.insertarCompetencia(competencia4);
            competenciaService.insertarCompetencia(competencia5);
            competenciaService.insertarCompetencia(competencia6);
            competenciaService.insertarCompetencia(competencia7);
            competenciaService.insertarCompetencia(competencia8);
            competenciaService.insertarCompetencia(competencia9);
            competenciaService.insertarCompetencia(competencia10);
            competenciaService.insertarCompetencia(competenciaBarcelona1);
            competenciaService.insertarCompetencia(competenciaBarcelona2);
            competenciaService.insertarCompetencia(competenciaMadrid1);
            competenciaService.insertarCompetencia(competenciaMadrid2);
            competenciaService.insertarCompetencia(competenciaNY1);
            competenciaService.insertarCompetencia(competenciaParis1);
            competenciaService.insertarCompetencia(competenciaParis2);
            
            // 8. Insertar registros (resultados de competencias)
            // JJOO 2024 (planificada, sin resultados aún)
            
            // Mundial Natación (en curso, algunos resultados)
            Registro registro1 = new Registro("Natación", "52.43s", "ATL-004", "Mundial Natación", 2);
            Registro registro2 = new Registro("Natación", "51.87s", "ATL-014", "Mundial Natación", 1);
            Registro registro3 = new Registro("Natación", "53.12s", "ATL-022", "Mundial Natación", 3);
            
            // Roland Garros (finalizada)
            Registro registro4 = new Registro("Tenis", "6-4, 6-3", "ATL-011", "Roland Garros", 1);
            Registro registro5 = new Registro("Tenis", "6-7, 6-4", "ATL-013", "Roland Garros", 2);
            Registro registro6 = new Registro("Tenis", "7-5, 6-3", "ATL-023", "Roland Garros", 3);
            
            // Campeonato Asiático de Judo (finalizada)
            Registro registro7 = new Registro("Judo", "Ippon", "ATL-019",  "GP Asiático de Judo", 1);
            Registro registro8 = new Registro("Judo", "Waza-ari", "ATL-010",  "GP Asiático de Judo", 2);
            
            // Abierto de Australia (finalizada)
            Registro registro9 = new Registro("Tenis", "6-3, 6-2", "ATL-023", "Abierto de Australia", 1);
            Registro registro10 = new Registro("Tenis", "7-6, 6-4", "ATL-011", "Abierto de Australia", 2);
            
            // Copa América de Voleibol (en curso)
            Registro registro11 = new Registro("Voleibol", "3-1", "ATL-017", "Copa A. de Voley", null);
            Registro registro12 = new Registro("Voleibol", "3-2", "ATL-012", "Copa A. de Voley", null);
            
            // Campeonato Mundial de Atletismo (finalizada)
            Registro registro13 = new Registro("Atletismo", "9.87s", "ATL-001", "Mundial de Atletismo", 1);
            Registro registro14 = new Registro("Atletismo", "9.92s", "ATL-018", "Mundial de Atletismo", 2);
            Registro registro15 = new Registro("Atletismo", "9.95s", "ATL-024", "Mundial de Atletismo", 3);
            Registro registro16 = new Registro("Atletismo", "2:08:42", "ATL-002", "Mundial de Atletismo", null);
            
            Registro registroBarcelona1 = new Registro(
            	    "Atletismo", 
            	    "9.99s", 
            	    "ATL-001", 
            	    "Copa Atletismo", 
            	    1
            	);
            	Registro registroBarcelona2 = new Registro(
            	    "Atletismo", 
            	    "10.05s", 
            	    "ATL-018", 
            	    "Copa Atletismo", 
            	    2
            	);

            	Registro registroMadrid1 = new Registro(
            	    "Atletismo", 
            	    "10.12s", 
            	    "ATL-024", 
            	    "GP de Madrid", 
            	    null
            	);
            
            registroService.insertarRegistro(registro1);
            registroService.insertarRegistro(registro2);
            registroService.insertarRegistro(registro3);
            registroService.insertarRegistro(registro4);
            registroService.insertarRegistro(registro5);
            registroService.insertarRegistro(registro6);
            registroService.insertarRegistro(registro7);
            registroService.insertarRegistro(registro8);
            registroService.insertarRegistro(registro9);
            registroService.insertarRegistro(registro10);
            registroService.insertarRegistro(registro11);
            registroService.insertarRegistro(registro12);
            registroService.insertarRegistro(registro13);
            registroService.insertarRegistro(registro14);
            registroService.insertarRegistro(registro15);
            registroService.insertarRegistro(registro16);
            registroService.insertarRegistro(registroBarcelona1);
            registroService.insertarRegistro(registroBarcelona2);
            registroService.insertarRegistro(registroMadrid1);
            
            // 9. Insertar inscripciones (varias por atleta y competencia)
            // Atletas españoles
            Inscripcion inscripcion1 = new Inscripcion("JJOO 2024", "ATL-001", "2024-06-21", "2024-07-25", "ENT-001");
            Inscripcion inscripcion2 = new Inscripcion("Mundial de Atletismo", "ATL-001", "2023-06-01", "2023-07-14", "ENT-001");
            Inscripcion inscripcion3 = new Inscripcion("Mundial de Atletismo", "ATL-002", "2023-06-01", "2023-07-14", null);
            Inscripcion inscripcion4 = new Inscripcion("JJOO 2024", "ATL-003", "2024-06-21", "2024-07-25", "ENT-001");
            
            // Atletas estadounidenses
            Inscripcion inscripcion5 = new Inscripcion("Mundial Natación", "ATL-004", "2023-05-01", "2023-07-13", "ENT-002");
            Inscripcion inscripcion6 = new Inscripcion("Liga de Baloncesto", "ATL-005", "2023-08-15", "2023-08-31", "ENT-006");
            Inscripcion inscripcion7 = new Inscripcion("Torneo de Esgrima UE", "ATL-006", "2023-09-01", "2023-10-04", "ENT-003");
            
            // Atletas franceses
            Inscripcion inscripcion8 = new Inscripcion("Vuelta a España", "ATL-007", "2023-07-01", "2023-08-19", "ENT-004");
            Inscripcion inscripcion9 = new Inscripcion("Torneo de Esgrima UE", "ATL-008", "2023-09-01", "2023-10-04", "ENT-008");
            
            // Atletas alemanes
            Inscripcion inscripcion10 = new Inscripcion("GP Asiático de Judo", "ATL-010", "2023-03-01", "2023-04-09", "ENT-007");
            Inscripcion inscripcion11 = new Inscripcion("Roland Garros", "ATL-011", "2023-04-15", "2023-05-21", "ENT-005");
            Inscripcion inscripcion12 = new Inscripcion("Copa A. de Voley", "ATL-012", "2023-07-15", "2023-07-31", null);
            
            // Atletas italianos
            Inscripcion inscripcion13 = new Inscripcion("Roland Garros", "ATL-013", "2023-04-15", "2023-05-21", "ENT-005");
            Inscripcion inscripcion14 = new Inscripcion("Mundial Natación", "ATL-014", "2023-05-01", "2023-07-13", "ENT-002");
            
            // Atletas brasileños
            Inscripcion inscripcion15 = new Inscripcion("Liga de Baloncesto", "ATL-016", "2023-08-15", "2023-08-31", "ENT-006");
            Inscripcion inscripcion16 = new Inscripcion("Copa A. de Voley", "ATL-017", "2023-07-15", "2023-07-31", null);
            Inscripcion inscripcion17 = new Inscripcion("Mundial de Atletismo", "ATL-018", "2023-06-01", "2023-07-14", "ENT-001");
            
            // Atletas japoneses
            Inscripcion inscripcion18 = new Inscripcion("GP Asiático de Judo", "ATL-019", "2023-03-01", "2023-04-09", "ENT-007");
            Inscripcion inscripcion19 = new Inscripcion("JJOO 2024", "ATL-020", "2024-06-21", "2024-07-25", "ENT-003");
            
            // Atletas australianos
            Inscripcion inscripcion20 = new Inscripcion("Abierto de Australia", "ATL-023", "2022-12-01", "2023-01-15", "ENT-005");
            Inscripcion inscripcion21 = new Inscripcion("Mundial de Atletismo", "ATL-024", "2023-06-01", "2023-07-14", "ENT-001");
            Inscripcion inscripcion22 = new Inscripcion("Mundial Natación", "ATL-022", "2023-05-01", "2023-07-13", "ENT-002");
            
            Inscripcion inscripcionBarcelona1 = new Inscripcion(
            	    "Copa Atletismo", 
            	    "ATL-001", 
            	    "2023-04-01", 
            	    "2023-05-09", 
            	    "ENT-001"
            	);
            	Inscripcion inscripcionBarcelona2 = new Inscripcion(
            	    "Copa Atletismo", 
            	    "ATL-018", 
            	    "2023-04-01", 
            	    "2023-05-09", 
            	    "ENT-001"
            	);

            
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
            inscripcionService.insertarInscripcion(inscripcion11);
            inscripcionService.insertarInscripcion(inscripcion12);
            inscripcionService.insertarInscripcion(inscripcion13);
            inscripcionService.insertarInscripcion(inscripcion14);
            inscripcionService.insertarInscripcion(inscripcion15);
            inscripcionService.insertarInscripcion(inscripcion16);
            inscripcionService.insertarInscripcion(inscripcion17);
            inscripcionService.insertarInscripcion(inscripcion18);
            inscripcionService.insertarInscripcion(inscripcion19);
            inscripcionService.insertarInscripcion(inscripcion20);
            inscripcionService.insertarInscripcion(inscripcion21);
            inscripcionService.insertarInscripcion(inscripcion22);
            inscripcionService.insertarInscripcion(inscripcionBarcelona1);
            inscripcionService.insertarInscripcion(inscripcionBarcelona2);
            
            
            usuarioService.crearUsuario("ADM001", "admin1", "Admin123!", "admin", true);
            usuarioService.crearUsuario("ADM002", "admin2", "SecurePass456!", "admin", true);
            
            // Usuarios normales
            usuarioService.crearUsuario("USR001", "user1", "UserPass123!", "usuario", true);
            usuarioService.crearUsuario("USR002", "user2", "RegularUser456!", "usuario", true);

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