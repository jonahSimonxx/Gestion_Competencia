package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteAtletasIncompletos {
    public List<List<String>> obtenerAtletasIncompletos() throws SQLException {
        return ReportGenerator.generarReporte("reporte_atletas_incompletos");
    }
}