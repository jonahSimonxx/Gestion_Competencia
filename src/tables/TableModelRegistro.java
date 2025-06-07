package tables;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Registro;

public class TableModelRegistro extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelRegistro(ArrayList<Registro> registros) {
		String[] identificadores = {"Atleta","competencia","Lugar","Marcas","Disciplina"};
		setColumnIdentifiers(identificadores);
		for(Registro r : registros) {
		identificadores[0] = r.getIdAtleta();
		identificadores[1] = r.getNomCompetencia();
		identificadores[2] = Integer.toString( r.getPuesto());
		identificadores[3] = r.getMarcas();
		identificadores[4] = r.getNomDisciplina();
		addRow(identificadores);
		identificadores = new String[5];
		}
	}
}