package tables;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Competencia;

public class TableModelCompetencia extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelCompetencia(ArrayList<Competencia> competencias) {
		String[] identificadores = {"Nombre","Sede","Estado","Inicio","Final"};
		setColumnIdentifiers(identificadores);
		for(Competencia c : competencias) {
		identificadores[0] = c.getNomCompetencia();
		identificadores[1] = c.getNomSede();
		identificadores[2] = c.getEstado();
		identificadores[3] = c.getFechaIni().toString();
		identificadores[4] = c.getFechaFin().toString();
		addRow(identificadores);
		identificadores = new String[5];
		}
	}
}