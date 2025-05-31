package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteEntrenadores {
    public List<List<String>> obtenerEntrenadores() throws SQLException {
        return ReportGenerator.generarReporte("reporte_entrenadores");
    }
}