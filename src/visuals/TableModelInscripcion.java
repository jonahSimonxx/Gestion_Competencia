package visuals;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Inscripcion;

public class TableModelInscripcion extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelInscripcion(ArrayList<Inscripcion> inscripciones) {
		String[] identificadores = {"Atleta","Competicion","Entrenador","Inicio","Final"};
		setColumnIdentifiers(identificadores);
		for(Inscripcion i : inscripciones) {
        identificadores[0] = i.getIdAtleta();
		identificadores[1] = i.getNomCompetencia();
		identificadores[2] = i.getIdEntrenador();
		identificadores[3] = i.getFechaIni().toString();
		identificadores[4] = i.getFechaFin().toString();
		addRow(identificadores);
		identificadores = new String[5];
		}
	}
}