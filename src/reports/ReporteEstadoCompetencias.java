package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteEstadoCompetencias {
    public static ArrayList<ArrayList<String>> obtenerEstadoCompetencias() throws SQLException {
        return ReportGenerator.generarReporte("reporte_estado_competencias");
    }
}