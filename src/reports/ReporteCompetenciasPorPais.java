package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteCompetenciasPorPais {
    public List<List<String>> obtenerCompetenciasPorPais() throws SQLException {
        return ReportGenerator.generarReporte("reporte_competencias_por_pais");
    }
}