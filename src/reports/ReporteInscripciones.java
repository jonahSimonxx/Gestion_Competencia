package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteInscripciones {
    public static ArrayList<ArrayList<String>> obtenerInscripciones() throws SQLException {
        return ReportGenerator.generarReporte("reporte_inscripciones");
    }
}