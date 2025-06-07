package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteResultadosAnuales {
    public static ArrayList<ArrayList<String>> obtenerResultadosAnuales() throws SQLException {
        return ReportGenerator.generarReporte("reporte_resultados_anuales");
    }
}