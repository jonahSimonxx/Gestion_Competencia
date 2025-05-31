package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteEstadoCompetencias {
    public List<List<String>> obtenerEstadoCompetencias() throws SQLException {
        return ReportGenerator.generarReporte("reporte_estado_competencias");
    }
}