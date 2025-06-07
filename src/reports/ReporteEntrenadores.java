package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteEntrenadores {
    public static ArrayList<ArrayList<String>> obtenerEntrenadores() throws SQLException {
        return ReportGenerator.generarReporte("reporte_entrenadores");
    }
}