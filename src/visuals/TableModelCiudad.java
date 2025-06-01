package visuals;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Ciudad;

public class TableModelCiudad extends DefaultTableModel {
	
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableModelCiudad(ArrayList<Ciudad> ciudades){
    	   String fila[] = {"Ciudad","País"};
    	   setColumnIdentifiers(fila);
    	   for(Ciudad c : ciudades){
    		   fila = new String[2];
    		   fila[0] = c.getNomCiudad();
    		   fila[1] = c.getNomPais();
    		   addRow(fila);
    		   fila = new String[2];
    	   }
       }
}