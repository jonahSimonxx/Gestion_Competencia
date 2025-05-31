package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteInscripciones {
    public List<List<String>> obtenerInscripciones() throws SQLException {
        return ReportGenerator.generarReporte("reporte_inscripciones");
    }
}