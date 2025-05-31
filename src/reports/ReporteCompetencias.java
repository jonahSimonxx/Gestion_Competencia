package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteCompetencias {
    public List<List<String>> obtenerCompetencias() throws SQLException {
        return ReportGenerator.generarReporte("reporte_competencias");
    }
}