package reports;

import java.sql.SQLException;
import java.util.List;

public class ReporteCompetenciasPorCiudadYSede {
    public List<List<String>> obtenerCompetenciasPorCiudadYSede() throws SQLException {
        return ReportGenerator.generarReporte("resumen_competencias_por_ciudad_y_sede");
    }
}