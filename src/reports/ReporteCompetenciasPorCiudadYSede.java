package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteCompetenciasPorCiudadYSede {
    public static ArrayList<ArrayList<String>> obtenerCompetenciasPorCiudadYSede() throws SQLException {
        return ReportGenerator.generarReporte("reporte_competencias_por_ciudad_y_sede");
    }
}