package tables;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Disciplina;

public class TableModelDisciplina extends DefaultTableModel {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelDisciplina(ArrayList<Disciplina> disciplinas){
  	   String fila[] = {"Disciplina"};
  	   setColumnIdentifiers(fila);
  	   for(Disciplina d : disciplinas){
  		   fila = new String[1];
  		   fila[0] = d.getNomDisciplina();
  		   addRow(fila);
  		   fila = new String[1];
  	   }
     }

}
