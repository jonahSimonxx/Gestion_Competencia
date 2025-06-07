package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteAtletasIncompletos {
    public static ArrayList<ArrayList<String>> obtenerAtletasIncompletos() throws SQLException {
        return ReportGenerator.generarReporte("reporte_atletas_incompletos");
    }
}