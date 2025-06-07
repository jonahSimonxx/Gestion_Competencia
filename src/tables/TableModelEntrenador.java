package tables;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Entrenador;

public class TableModelEntrenador extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelEntrenador(ArrayList<Entrenador> entrenadores){
		String[] identificadores = {"Nombre","ID","Especialidad","Dirección"};
		setColumnIdentifiers(identificadores);
		for(Entrenador e : entrenadores) {
		identificadores[0] = e.getNombre();
		identificadores[1] = e.getIdEntrenador();
		identificadores[2] = e.getEsp();
		identificadores[3] = e.getDireccion();
		
		addRow(identificadores);
		identificadores = new String[4];
		}
	}
}