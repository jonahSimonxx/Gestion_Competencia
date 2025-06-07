package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteCompetencias {
    public static ArrayList<ArrayList<String>> obtenerCompetencias() throws SQLException {
        return ReportGenerator.generarReporte("reporte_competencias");
    }
}