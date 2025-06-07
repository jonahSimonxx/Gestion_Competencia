package tables;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class TableModelReporte extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelReporte(ArrayList<ArrayList<String>> filas,int caso) {
	String identificadores[] = null;
		switch(caso){
	case 1:
		String[] identificadores1 = {"Atleta","Competencia","Ciudad","Sede","Inicio","Fin","Resultado","Medallas","Entrenador"};
		identificadores = identificadores1;
		break;
	case 2:
		String[] identificadores2 = {"Fecha","Identificador","Nombre","Dirección","Especialidad","Atletas asignados"};
		identificadores = identificadores2;
		break;
	case 3:
		String[] identificadores3 = {"Fecha","Competencia","Ciudad","Sede","Estado","Participantes"};
		identificadores = identificadores3;
		break;
	case 4:
		String[] identificadores4 = {"Fecha","Pais","Atleta","Identificicación","Participaciones","Medallas"};
		identificadores = identificadores4;
		break;
	case 5:
		String[] identificadores5 = {"Fecha","Atleta","Fin de la competencia","Resultado"};
		identificadores = identificadores5;
		break;
	case 6:
		String[] identificadores6= {"Fecha","Año","Medallas Anuales","Participantes Anuales","Mes","Medallas Mensuales","Participantes Mensuales"};
		identificadores = identificadores6;
		break;
	case 7:
		String[] identificadores7= {"Fecha","Competencia","Ciudad","Estado de Competición","Fin previsto"};
		identificadores = identificadores7;
		break;
	case 8:
		String[] identificadores8= {"Fecha","País","Ciudad","Sede","Cantidad de Competencias"," Medallas","Participantes","Resultados Generales"};
		identificadores = identificadores8;
		break;
	case 9:
		String[] identificadores9= {"Fecha","Ciudad","Sede","Cantidad de Competencias","Participantes","Medallas"};
		identificadores = identificadores9;
		break;
	}
		setColumnIdentifiers(identificadores);
		for(int i = 1; i<filas.size();i++) {
			for(int e = 0; e < filas.get(i).size();e++){
				identificadores[e] = filas.get(i).get(e); 
				if(identificadores[e] == null)
					identificadores[e] = "-----------";
			}
			addRow(identificadores);
		}
		
		}
}
