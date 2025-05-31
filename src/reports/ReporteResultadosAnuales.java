package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteResultadosAnuales {
    public List<List<String>> obtenerResultadosAnuales() throws SQLException {
        return ReportGenerator.generarReporte("reporte_resultados_anuales");
    }
}