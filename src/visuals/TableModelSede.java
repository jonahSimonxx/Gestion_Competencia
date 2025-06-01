package visuals;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Sede;

public class TableModelSede extends DefaultTableModel {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelSede(ArrayList<Sede> sedes){
    	   String fila[] = {"Sede","Ciudad"};
    	   setColumnIdentifiers(fila);
    	   for(Sede s : sedes){
    		   fila = new String[2];
    		   fila[0] = s.getNomSede();
    		   fila[1] = s.getNomCiudad();
    		   addRow(fila);
    		   fila = new String[2];
    	   }
       }
}