package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteAtletasPorPais {
    public List<List<String>> obtenerAtletasPorPais() throws SQLException {
        return ReportGenerator.generarReporte("reporte_atletas_por_pais");
    }
}