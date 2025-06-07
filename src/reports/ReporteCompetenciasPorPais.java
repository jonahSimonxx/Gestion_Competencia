package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteCompetenciasPorPais {
    public static ArrayList<ArrayList<String>> obtenerCompetenciasPorPais() throws SQLException {
        return ReportGenerator.generarReporte("reporte_competencias_por_pais");
    }
}