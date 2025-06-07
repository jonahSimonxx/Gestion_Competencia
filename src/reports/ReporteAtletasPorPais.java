package reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteAtletasPorPais {
    public static ArrayList<ArrayList<String>> obtenerAtletasPorPais() throws SQLException {
        return ReportGenerator.generarReporte("reporte_atletas_por_pais");
    }
}