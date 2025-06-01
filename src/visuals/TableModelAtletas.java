package visuals;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Atleta;

public class TableModelAtletas extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelAtletas(ArrayList<Atleta> atletas) {
		String[] identificadores = {"Identificador","Nombre","Sexo","Categoría","ID Entrenador","Edad","Pais"};
		setColumnIdentifiers(identificadores);
		for(Atleta a : atletas) {
		identificadores[0] = a.getIdAtleta();
		identificadores[1] = a.getNomCompleto();
		identificadores[2] = Character.toString(a.getSexo());
		identificadores[3] = a.getCatDep();
		identificadores[4] = a.getIdEntrenador();
		identificadores[5] = Integer.toString(a.getEdad());
		identificadores[6] = a.getNomPais();
		addRow(identificadores);
		identificadores = new String[7];
		}
	}
}